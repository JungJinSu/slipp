package net.board.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String create(UserDTO user) {
		System.out.println("create User Info : " + user);
		userDAO.save(user); // repository method 로 insert

		return "redirect:/users";
	}

	// 회원가입 페이지
	@GetMapping("/form")
	public String form(Model model) {
		return "/user/form";
	}

	// 회원 정보 수정 페이지
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		UserDTO userDTO = userDAO.findOne(id); 	// pk 에 해당하는 사용자 정보 조회, DTO 에 담아서 뷰 전달
		model.addAttribute("user", userDTO );			
		
		return "/user/updateForm";
	}

	// 회원정보 페이지
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("userDTOList", userDAO.findAll()); // List 필요 없어짐.
		return "/user/list"; // user 밑으로 기능을 구분해서 정리
	}
}
