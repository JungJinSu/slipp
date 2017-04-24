package net.board.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.board.domain.AnswerDAO;
import net.board.domain.AnswerDTO;
import net.board.domain.QuestionDAO;
import net.board.domain.QuestionDTO;
import net.board.domain.Result;
import net.board.domain.UserDTO;
 
@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	@Autowired
	AnswerDAO answerDAO;

	@Autowired
	QuestionDAO questionDAO;
	
	@PostMapping("")
	public AnswerDTO create(@PathVariable Long questionId, String contents, HttpSession session, Model model){
		if( !HttpSessionUtils.isLoginUser(session) ){
			model.addAttribute("errorMessage" ,"로그인 해주세요" );
			return null;
		}
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		
		// 맨탈 쥐어 흔든 에러 부분 (getOne, findOne) 손장난..
		//QuestionDTO questionDTO2 = questionDAO.getOne(questionId);						// 여기의심스러워...		
		QuestionDTO questionDTO = questionDAO.findOne(questionId);
		AnswerDTO answerDTO = new AnswerDTO(loginUser, questionDTO,  contents);
		
		System.out.println("questionDTO : "+questionDTO);
		System.out.println("answerDTO : "+answerDTO);
		
		return answerDAO.save(answerDTO);
	}
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인해야 합니다.");
		}
		
		AnswerDTO answerDTO = answerDAO.findOne(id);
		UserDTO loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!loginUser.isSameWriter(loginUser)) {
			return Result.fail("자신의 글만 삭제할 수 있습니다.");
		}
		
		answerDAO.delete(id);
		
		QuestionDTO questionDTO = questionDAO.findOne(questionId);
		questionDTO.deleteAnswer();
		questionDAO.save(questionDTO);
		return Result.ok();
	}
	
}
