package com.thebestgroup.teamquest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "какой-то тайтл",
		version = "2.1.0",
		description = "какой-то дескриптион"
))
@SpringBootApplication
public class TeamQuestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamQuestApplication.class, args);
	}

}
