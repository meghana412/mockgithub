package com.pwxcoo.git;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git
 * @email pwxcoo@gmail.com
 * @time 2018/09/23 10:26
 * @description
 */
@SpringBootApplication
public class GitApplication {

	public static void main(String[] args) {
//		SpringApplication.run(GitApplication.class, args);
		new SpringApplicationBuilder(GitApplication.class)
				.web(false) // non-Web application
				.run(args);
	}
}
