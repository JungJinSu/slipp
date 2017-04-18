package net.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserDTO, Long> {

	
}
