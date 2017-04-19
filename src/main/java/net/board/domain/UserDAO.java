package net.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<UserDTO, Long> {
	UserDTO findByUserId(String userId);				// userId 를 기반으로 사용자 정보 조회
}