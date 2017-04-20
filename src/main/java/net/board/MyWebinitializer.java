package net.board;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// 내장 톰캣은 자동으로 초기화 되지만, 외장 톰캣의 경우 초기화가 필요하다.
public class MyWebinitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SlippBoardApplication.class);		// 내 프로젝트를 초기화 설정
	}
	
}
