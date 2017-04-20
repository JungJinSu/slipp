package net.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDAO  extends JpaRepository<QuestionDTO, Long>{	
	
}
