package net.board.web;

import javax.servlet.http.HttpSession;

import net.board.domain.UserDTO;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionedUser";	// 
	
	public static boolean isLoginUser(HttpSession session){
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		
		if( sessionedUser == null){
			return false;
		}
		return true;
	}

	public static UserDTO getUserFromSession(HttpSession session){
		if ( isLoginUser(session)){
			return null;
		}
		return(UserDTO) session.getAttribute(USER_SESSION_KEY);		
	}
	
}
