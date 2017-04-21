package net.board.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.board.domain.AnswerDAO;
import net.board.domain.AnswerDTO;
import net.board.domain.QuestionDAO;
import net.board.domain.QuestionDTO;
import net.board.domain.UserDTO;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class AnswerController {
	@Autowired
	AnswerDAO answerDAO;
 
	@Autowired
	QuestionDAO questionDAO;
	
	@PostMapping("")
	public AnswerDTO  create(@PathVariable Long questionId, String contents, HttpSession session, Model model){
		if( !HttpSessionUtils.isLoginUser(session) ){
			model.addAttribute("errorMessage" ,"로그인 해주세요" );
			return null;
		}
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		QuestionDTO questionDTO = questionDAO.getOne(questionId);			
		AnswerDTO answerDTO = new AnswerDTO(loginUser, questionDTO,  contents);
		return answerDAO.save(answerDTO);
	}
}
