package com.pwxcoo.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github
 * @email pwxcoo@gmail.com
 * @time 2018/09/21 20:11
 * @description
 */
//@SpringBootApplication(scanBasePackages = "com.pwxcoo.github.api")
@SpringBootApplication
public class GithubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubApplication.class, args);
	}
}
