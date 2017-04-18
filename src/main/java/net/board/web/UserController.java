package net.board.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.board.domain.UserDAO;
import net.board.domain.UserDTO;

@Controller
@RequestMapping("/users")
public class UserController {
	// private List<UserDTO> userDTOList = new ArrayList<UserDTO>();
	
	@Autowired
	private UserDAO userDAO;

	@PostMapping("")
	public String create(UserDTO user){
		System.out.println("create User Info : " + user);
		//userDTOList.add(user);				// 목록 조회 DTOList 추가 
		userDAO.save(user);					// repository method 로  insert
		
		return "redirect:/user/list";
	}
	
	@GetMapping("")
	public String list(Model model){
		//model.addAttribute("userDTOList", userDTOList);
		model.addAttribute("userDTOList", userDAO.findAll());		// List 필요 없어짐.  
		
		return "/user/list";		// user 밑으로 기능을 구분해서 정리
	}
	
	
}
