package com.mynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstraps the MyNotes REST Server app
 * 
 * @author Frank
 * Apr 1, 2018 11:25:45 AM
 */
@SpringBootApplication
public class MyNotesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNotesServerApplication.class, args);
	}
}
