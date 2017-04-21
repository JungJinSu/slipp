package net.board.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.board.domain.UserDAO;
import net.board.domain.UserDTO;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	// 회원가입 기능
	@PostMapping("")
	public String create(UserDTO userDTO) {
		userDAO.save(userDTO); // repository method 로 insert
		return "redirect:/users";
	}

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		return "/user/login";
	}
	
	// 로그인 기능
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session){
		UserDTO userDTO = userDAO.findByUserId(userId);
		
		if ( userDTO == null){									// 회원 정보가 없는 경우
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		if ( !userDTO.matchPassword(password)){	// 비밀번호가 다른경우
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		
		session.setAttribute("loginUser", userDTO);
		
		System.out.println("Login Success!");
		
		return "redirect:/";
	}
	
	// 로그아웃  페이지
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println("로그아웃 유저 정보 : " + loginUser.toString() );
		session.removeAttribute("loginUser");		//  해당 유저의 세션을 지움
		
		return "redirect:/";
	}
	
	// 회원가입 페이지
	@GetMapping("/form")
	public String form(Model model) {
		return "/user/form";
	}

	// 회원 정보 수정 페이지
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		try{
			hasPermission(session, id);
			System.out.println("22");
			UserDTO userDTO = userDAO.findOne(id); // pk 에 해당하는 사용자 정보 조회, DTO 에 담아서
			model.addAttribute("user", userDTO);
			return "/user/updateForm";
		
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			System.out.println("33 : " + e.getMessage());
			return "/user/login";
		}
		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, UserDTO updatedUser, HttpSession session) {
		Object tempUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);	// 로그인 된 사용자
		if( !HttpSessionUtils.isLoginUser(session) ){
			return "redirect:/users/loginForm";
		}
		
		UserDTO loginUser = (UserDTO) tempUser;
	 	if(  !loginUser.matchId(id)){								// 로그인된 사용자가 같은 경우
			throw new IllegalStateException("You can't update the anther user.");
		}
		
	 	UserDTO userDTO = userDAO.findOne(id);
		userDTO.update(updatedUser); // DTO 를 수정
		userDAO.save(userDTO); // JPA 에 의해서 업데이트 됨
		return "redirect:/users";
	}
	
	// 회원정보 페이지
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("userDTOList", userDAO.findAll()); // List 필요 없어짐.
		return "/user/list"; // user 밑으로 기능을 구분해서 정리
	}
	
	public void hasPermission(HttpSession session, Long newUserId){
		if( !HttpSessionUtils.isLoginUser(session) ){
			throw new IllegalStateException("로그인을 해주세요.");
		}
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
	 	if(  !loginUser.matchId(newUserId)){								
	 		throw new IllegalStateException("다른사람의 정보를 수정 할 수 없습니다.");
		}
	}

}
