package net.board.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QuestionDTO {

	@Id
	@GeneratedValue
	private Long id;
	
	private String writer;
	private String title;
	private String contents;
	
	public QuestionDTO(){};
	
	// 질문, UserDTO 와 다르게 왜 생성자를 만들어서 사용하나? => 파라미터 값으로 제목과 내용을 전달하기 위해서 명세
	public QuestionDTO(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
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
		return "QuestionDTO [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
	
	
	
	
}
