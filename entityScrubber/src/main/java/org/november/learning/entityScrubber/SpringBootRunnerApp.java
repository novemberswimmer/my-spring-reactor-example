package org.november.learning.entityScrubber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.applet.AppletContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootRunnerApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootRunnerApp.class, args);
        EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);

        Scrubber scrubber = new Scrubber();


        List<Employee> employeeList = new ArrayList<>();

        Employee employee = new Employee("Ruel","Chicago",49);
        Employee employee1 = new Employee("Jen","Chicago",49);
        Employee employee2 = new Employee("Kayden","Chicago",49);
        Employee employee3 = new Employee("Mike","Chicago",49);

        employeeList.add(employee);
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        employeeRepository.saveAll(employeeList).subscribe(t-> System.out.println("Done saving"));

        employeeRepository.findAll()
                .map(r -> {scrubber.scrubObject(r);
                return r;})
                .subscribe(System.out::println);


    }
}
