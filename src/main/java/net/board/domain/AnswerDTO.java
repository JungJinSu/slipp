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
public class AnswerDTO {
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;

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
	
	private LocalDateTime createDate;

	public AnswerDTO() {
	}

	public AnswerDTO(UserDTO writer, QuestionDTO questionDTO, String contents) {
		this.writer = writer;
		this.questionDTO = questionDTO;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}

	// 현재시간을 포맷에 맞게 반환
	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerDTO other = (AnswerDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AnswerDTO [id=" + id + ", writer=" + writer + ", questionDTO=" + questionDTO + ", contents=" + contents
				+ ", createDate=" + createDate + "]";
	}

	

}
