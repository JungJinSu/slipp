package net.board.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		qusetionDAO.save(newQuestion); 
		return "redirect:/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", qusetionDAO.findOne(id)); 

		
		return "/qna/show";
	}

	@GetMapping("/{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}

		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		QuestionDTO questionDTO = qusetionDAO.findOne(id);
		if ( !questionDTO.isSameWriter(loginUser)) {
			return "/users/loginForm";
		}

		model.addAttribute("question", questionDTO); 
		return "/qna/updateForm";

	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}

		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		QuestionDTO questionDTO = qusetionDAO.findOne(id);

		if (questionDTO.isSameWriter(loginUser)) {
			return "/users/loginForm";
		}
		questionDTO.update(title, contents); 
		qusetionDAO.save(questionDTO); 

		return String.format("redirect:/questions/%d", id);
	}

	
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}

		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		QuestionDTO questionDTO = qusetionDAO.findOne(id);
		if (questionDTO.isSameWriter(loginUser)) {
			return "/users/loginForm";
		}

		qusetionDAO.delete(id);
		return "redirect:/index";
	}
	
}
