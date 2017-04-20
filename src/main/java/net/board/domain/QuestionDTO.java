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

@Entity
public class QuestionDTO {

	@Id
	@GeneratedValue
	private Long id;
	
	//private Long writerId;			// 이런 관계 보다는
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private UserDTO writer;	// 객체지향 관점에서 관계를 맺어주는게 좋다. => 테이블 설계
	private String title;
	@Lob
	private String contents;
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="questionDTO")
	@OrderBy("id ASC")
	private List<AnswerDTO> answerDTO;					// 이 부분 공부
	
	
	public QuestionDTO(){};
	
	//UserDTO 와 다르게 왜 생성자를 만들어서 사용하나? => 오버로딩시
	public QuestionDTO(UserDTO writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	
	}
 
	// 현재시간을 포맷에 맞게 반환
	public String getFormattedCreateDate() {
		if( createDate == null){
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
				+ ", createDate=" + createDate + "]";
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
		
	}
	
	public boolean isSameWriter(UserDTO loginUser) {
		
		return this.writer.equals(loginUser);		// 오.. 객체 자체를 비교
	}
	
	
}
