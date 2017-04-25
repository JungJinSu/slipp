package net.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;		// static lib 는 수동으로 추가해야함 


@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class SlippBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlippBoardApplication.class, args);		//
	}
	
	 @Bean
	    public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("net.board")
	                .apiInfo(apiInfo())
	                .select()
	                //.paths(regex(.*"))							정규표현식도 가능하지만,  다음과 같이 지정 가능
	                .paths(PathSelectors.ant("/api/**"))		// api 로시작하는 url 에 대해서 문서화 시작
	                .build();
	    }
	     
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Board API")
	                .description("Board API")
	                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
	                .contact("Niklas Heidloff")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
	                .version("2.0")
	                .build();
	    }
}
