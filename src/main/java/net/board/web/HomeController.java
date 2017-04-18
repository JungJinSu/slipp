package net.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// 인덱스 페이지
		@GetMapping("/index")
		public String index(Model model){
			return "index";		
		}
}
