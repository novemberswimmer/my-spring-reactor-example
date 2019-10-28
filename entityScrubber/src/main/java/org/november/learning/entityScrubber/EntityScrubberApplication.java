package org.november.learning.entityScrubber;

import org.november.learning.entityScrubber.engine.Scrubber;

public class EntityScrubberApplication {

	public static void main(String[] args) {

		Scrubber scrubber = new Scrubber();
		Employee employee = new Employee("Ruel","Chicago", "1234-456-789",49);
		scrubber.scrubObject(employee);
		System.out.println(employee.toString());
	}

}
