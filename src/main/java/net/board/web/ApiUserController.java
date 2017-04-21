package net.board.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.board.domain.UserDAO;
import net.board.domain.UserDTO;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	
	@Autowired
	private UserDAO userDAO;

	@GetMapping("/{id}")
	public UserDTO show(@PathVariable Long id){
		return  userDAO.findOne(id);
	}
}
