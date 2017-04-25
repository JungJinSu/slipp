package net.board.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class AnswerDTO extends AbstractEntity {
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonProperty
	private UserDTO writer; // 객체지향 관점에서 관계를 맺어주는게 좋다. => 테이블 설계

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	@JsonProperty
	private QuestionDTO questionDTO;

	@Lob					// 긴 문자 처리
	@JsonProperty
	private String contents;

	public AnswerDTO() {
	}

	public AnswerDTO(UserDTO writer, QuestionDTO questionDTO, String contents) {
		this.writer = writer;
		this.questionDTO = questionDTO;
		this.contents = contents;
	}

	public UserDTO getWriter() {
		return writer;
	}

	public void setWriter(UserDTO writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}

	// 로그인 유저 , 답글등록자 비교
	public boolean isSameWriter(UserDTO loginUser) {
		return loginUser.equals(this.writer);
	}

	@Override
	public String toString() {
		return "AnswerDTO [" + super.toString() + ", writer=" + writer  + ", contents=" + contents + "]";
	}
}
