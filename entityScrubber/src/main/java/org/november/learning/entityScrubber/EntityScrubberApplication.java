package org.november.learning.entityScrubber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class EntityScrubberApplication {

	public static void main(String[] args) {

		Scrubber scrubber = new Scrubber();
		Employee employee = new Employee("Ruel","Chicago",49);
		scrubber.scrubObject(employee);
		System.out.println(employee.toString());
	}

}
