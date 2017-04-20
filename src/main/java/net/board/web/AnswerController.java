package net.board.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.board.domain.AnswerDAO;
import net.board.domain.AnswerDTO;
import net.board.domain.QuestionDAO;
import net.board.domain.QuestionDTO;
import net.board.domain.UserDTO;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired
	AnswerDAO answerDAO;
 
	@Autowired
	QuestionDAO questionDAO;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session){
		if( !HttpSessionUtils.isLoginUser(session) ){
			return "/users/loginForm";
		}
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		QuestionDTO questionDTO = questionDAO.getOne(questionId);			// 이부분 
		AnswerDTO answerDTO = new AnswerDTO(loginUser, questionDTO,  contents);
		answerDAO.save(answerDTO);
		
		return String.format("redirect:/questions/%d", questionId);
	}
	
}
