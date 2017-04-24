package net.board.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class QuestionDTO {

	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;

	// private Long writerId; // 이런 관계 보다는
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private UserDTO writer; // 객체지향 관점에서 관계를 맺어주는게 좋다. => 테이블 설계
	@JsonProperty
	private String title;
	@Lob
	@JsonProperty
	private String contents;

	private LocalDateTime createDate;

	@OneToMany(mappedBy = "questionDTO")
	@OrderBy("id DESC")									// 내림차순
	private List<AnswerDTO> answerDTO; 				// 이 부분이 뷰단으로 까지 이어진다. 네이밍 실수 조심.. 뷰에도 DTO로 해두자.

	public QuestionDTO() {
	};
 
	// UserDTO 와 다르게 왜 생성자를 만들어서 사용하나? => 오버로딩시
	public QuestionDTO(UserDTO writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();

	}

	public List<AnswerDTO> getAnswerDTO() {
		return answerDTO;
	}

	public void setAnswerDTO(List<AnswerDTO> answerDTO) {
		this.answerDTO = answerDTO;
	}
	

	// 현재시간을 포맷에 맞게 반환
	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getWriter() {
		return writer;
	}

	public void setWriter(UserDTO writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}


	@Override
	public String toString() {
		return "QuestionDTO [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents
				+ ", createDate=" + createDate + ", answerDTO=" + answerDTO + "]";
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;

	}

	public boolean isSameWriter(UserDTO loginUser) {

		return this.writer.equals(loginUser); // 오.. 객체 자체를 비교
	}

}
