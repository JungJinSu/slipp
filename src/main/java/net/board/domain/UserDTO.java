package net.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity								// DB source mapping
public class UserDTO {
	@Id								// primary key
	@GeneratedValue				// auto increment
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	
	private String password;
	private String name;
	private String email;
	
	
	// 비밀번호 검사
	public boolean matchPassword(String newPassword){
		if( newPassword ==null ){
			return false;
		}
		return true;
	}
	
	// 아이디 검사
	public boolean matchId(Long newId){
		if( newId == null){
			return false;
		}
		return newId.equals(this.id);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void update(UserDTO newUser){
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
		this.userId = newUser.userId;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
}