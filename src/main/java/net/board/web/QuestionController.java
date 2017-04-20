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
	public String form(HttpSession session){
		if ( !HttpSessionUtils.isLoginUser(session)){
			return "/users/loginform";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session){
		if ( !HttpSessionUtils.isLoginUser(session)){		// 로그인 한 경우에만 글쓰기 가능
			return "/users/loginform";
		}
		UserDTO sessionedUser = HttpSessionUtils.getUserFromSession(session);							// 사용자 아이디를 알기 위해 
		QuestionDTO newQuestion = new QuestionDTO(sessionedUser, title, contents); 					// 사용자 아이디=작성자로 dto 생성 
		qusetionDAO.save(newQuestion);																			// 디비 저장
		return "redirect:/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model ){
		model.addAttribute("question", qusetionDAO.findOne(id));	// 해당 질문을 가져와서 뷰 모델로 전달
		return "/qna/show";
	}
	
	@GetMapping("/{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model){
		model.addAttribute("question", qusetionDAO.findOne(id));	// 해당 질문을 가져와서 뷰 모델로 전달
		return "/qna/updateForm";
		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents){
		QuestionDTO questionDTO = qusetionDAO.findOne(id); // 질문 가져오기
		questionDTO.update(title, contents);			// 질문 수정
		qusetionDAO.save(questionDTO);				// 디비 저장
		
		return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id){
		qusetionDAO.delete(id);
		return "redirect:/index";
	}
}
