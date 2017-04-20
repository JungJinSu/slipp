package net.board.web;

import javax.servlet.http.HttpSession;

import net.board.domain.UserDTO;


// 세션에 저장된 회원 정보 클래스
public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionedUser";	// session 속성에 저장한 유저 정보 키값
	
	public static boolean isLoginUser(HttpSession session){
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		
		if( sessionedUser == null){
			return false;
		}
		return true;
	}

	public static UserDTO getUserFromSession(HttpSession session){
		if ( !isLoginUser(session)){	// 망할 느낌표. 널포인트 만세.
			return null;
		}
		return (UserDTO)session.getAttribute(USER_SESSION_KEY);		
	}
	
}
