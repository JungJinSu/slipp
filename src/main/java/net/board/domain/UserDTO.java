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
		if( newPassword == null ){
			return false;
		}
		return newPassword.equals(password);
	}
	
	// 아이디 검사
	public boolean matchId(Long newId){
		if( newId == null){
			return false;
		}
		return newId.equals(id);
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

	// 아래의 equals() 오버라이딩 이해하기 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		UserDTO other = (UserDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
