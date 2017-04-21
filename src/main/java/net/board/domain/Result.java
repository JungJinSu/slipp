package net.board.domain;


// 유효성 검사 결과 클래스
public class Result {
	private boolean valid;
	private String errorMessage;

	// 결과. default true
	private Result(){
		this (true, null);
	}
	
	// 생성자 
	private Result(boolean valid, String errorMessage){
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	// 인증 성공
	public static Result ok(){
		return new Result();
	}
	
	// 인증 실패
	public static Result fail(String errorMessage){
		return new Result(false, errorMessage);
	}

	// 현재 상태
	public boolean isValid() {
		return valid;
	}
	
	// 에러메시진
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
