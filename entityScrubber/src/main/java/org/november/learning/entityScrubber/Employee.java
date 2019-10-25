package org.november.learning.entityScrubber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Scrubable(name="employee")
public class Employee {

    @ScrubbingMethod(type="masked")
    private String name;
    @ScrubbingMethod(type="masked")
    private String address;
    private Integer age;
}
