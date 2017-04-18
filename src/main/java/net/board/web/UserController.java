package net.board.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	private List<UserDTO> userDTOList = new ArrayList<UserDTO>();
	
	
	@PostMapping("/create")
	public String create(UserDTO user){
		System.out.println("create User Info : " + user);
		userDTOList.add(user);
		
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model){
		System.out.println("userDTOList : " + userDTOList);
		model.addAttribute("userDTOList", userDTOList);
		
		return "list";
	}
	
	
}
