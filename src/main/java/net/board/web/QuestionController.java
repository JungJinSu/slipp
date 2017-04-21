package net.board.web;

import javax.servlet.http.HttpSession;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.board.domain.QuestionDAO;
import net.board.domain.QuestionDTO;
import net.board.domain.Result;
import net.board.domain.UserDTO;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	QuestionDAO questionDAO;

	@GetMapping("/form")
	public String form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) { 
			return "/users/loginForm";
		}
		UserDTO sessionedUser = HttpSessionUtils.getUserFromSession(session); 
		QuestionDTO newQuestion = new QuestionDTO(sessionedUser, title, contents); 
		questionDAO.save(newQuestion); 
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionDAO.findOne(id)); 
		return "/qna/show";
	}

	@GetMapping("/{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		QuestionDTO questionDTO = questionDAO.findOne(id);
		Result result = valid(session, questionDTO);
		if( !result.isValid()){
			model.addAttribute("errorMessage", result.getErrorMessage());	
			return "/user/login";
		}
			model.addAttribute("question", questionDTO); 
			return "/qna/updateForm";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, HttpSession session, Model model) {
		QuestionDTO questionDTO = questionDAO.findOne(id);
		Result result = valid(session, questionDTO);
		if( !result.isValid()){
			model.addAttribute("errorMessage", result.getErrorMessage());	
			return "/user/login";
		
		}
			questionDTO.update(title, contents); 
			questionDAO.save(questionDTO); 
			return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session, Model model) {
		try{
			QuestionDTO questionDTO = questionDAO.findOne(id);
			hasPermission(session, questionDTO);		// 예외처리
			questionDAO.delete(id);
			return "redirect:/index";
		
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());	
			return "/user/login";
		}
	}
	
	// Result : valid Class 정의로 사용자 인증	
	private Result valid(HttpSession session, QuestionDTO question){
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인이 필요합니다.");
		}
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session); 
		if( !question.isSameWriter(loginUser) ){
			return Result.fail("자신이 쓴 글만 수정, 삭제가 가능 합니다..");
		}
		return Result.ok();
	}

	// haspermission : 예외처리로 사용자 인증	
	private void hasPermission(HttpSession session, QuestionDTO question){
		if (!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요합니다.");			//  예외 처리 
		}
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session); 
		if( !question.isSameWriter(loginUser) ){
			throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능 합니다..");		
		}
	}
}
