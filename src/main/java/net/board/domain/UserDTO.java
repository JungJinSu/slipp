package net.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity // DB source mapping
public class UserDTO extends AbstractEntity {

	@Column(nullable = false, length = 20, unique = true)
	@JsonProperty
	private String userId;

	private String password;
	@JsonProperty
	private String name;
	@JsonProperty
	private String email;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	// 비밀번호 검사
	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}
		return newPassword.equals(password);
	}

	// 아이디 검사
	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}
		return newId.equals(getId());
	}

	public void update(UserDTO newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
		this.userId = newUser.userId;
	}

	@Override
	public String toString() {
		return "User [" + super.toString()+ ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}


}
