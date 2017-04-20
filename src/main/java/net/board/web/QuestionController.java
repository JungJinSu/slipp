package net.board.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.board.domain.QuestionDAO;
import net.board.domain.QuestionDTO;
import net.board.domain.UserDTO;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	QuestionDAO qusetionDAO;
	
	
	@GetMapping("/form")
	public String form(HttpSession session){
		if ( !HttpSessionUtils.isLoginUser(session)){
			return "/users/loginform";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String content, HttpSession session){
		if ( !HttpSessionUtils.isLoginUser(session)){		// 로그인 한 경우에만 글쓰기 가능
			return "/users/loginform";
		}
		UserDTO sessionedUser = HttpSessionUtils.getUserFromSession(session);							// 사용자 아이디를 알기 위해 
		QuestionDTO newQuestion = new QuestionDTO(sessionedUser, title, content); 					// 사용자 아이디=작성자로 dto 생성 
		qusetionDAO.save(newQuestion);																			// 디비 저장
		return "redirect:/index";
	}

}
