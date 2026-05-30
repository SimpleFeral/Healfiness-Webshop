package com.healfiness.backend;

import org.springframework.boot.SpringApplication;

public class TestHealfinessApplication {

	public static void main(String[] args) {
		SpringApplication.from(HealfinessApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
