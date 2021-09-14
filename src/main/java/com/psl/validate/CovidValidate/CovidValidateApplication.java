package com.psl.validate.CovidValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.psl.validate.CovidValidate.Service.ValidatorService;

@SpringBootApplication
public class CovidValidateApplication implements CommandLineRunner{
	
	@Autowired
	ValidatorService validatorService;

	public static void main(String[] args) {
		SpringApplication.run(CovidValidateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		validatorService.populateValidateData();
	}

}
