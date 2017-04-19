package net.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<UserDTO, Long> {
	UserDTO findByUserId(String userId);	// userId 를 기반으로 user data 조회 가능
}