package net.board.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.board.domain.QuestionDAO;

@Controller
@RequestMapping("/")
public class HomeController {
	
	// questionDTO 에 접근하기 위해 빈 생성
	@Autowired	
	QuestionDAO questionDAO;	
	
	// 인덱스 페이지
		@GetMapping("")
		public String index(Model model){
			model.addAttribute("questions", questionDAO.findAll());		// JPA에서 제공하는 메소드. select * from qeustionDTO.
			return "/index";		
		}

}
