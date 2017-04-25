package net.board.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
public class QuestionDTO extends AbstractEntity {
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private UserDTO writer; // 객체지향 관점에서 관계를 맺어주는게 좋다. => 테이블 설계
	@JsonProperty
	private String title;
	@Lob
	@JsonProperty
	private String contents;

	@JsonProperty
	private Integer countOfAnswer=0;
	
	@OneToMany(mappedBy = "questionDTO")
	@OrderBy("id DESC")									// 내림차순
	private List<AnswerDTO> answerDTOList; 				// 이 부분이 뷰단으로 까지 이어진다. 네이밍 실수 조심.. 뷰에도 DTO로 해두자.

	public QuestionDTO() {
	};
 
	// UserDTO 와 다르게 왜 생성자를 만들어서 사용하나? => 오버로딩시
	public QuestionDTO(UserDTO writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;

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

	public void addAnswer() {
		this.countOfAnswer += 1;
	}
	public void deleteAnswer() {
		this.countOfAnswer -= 1;
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(UserDTO loginUser) {

		return this.writer.equals(loginUser); // 오.. 객체 자체를 비교
	}

}