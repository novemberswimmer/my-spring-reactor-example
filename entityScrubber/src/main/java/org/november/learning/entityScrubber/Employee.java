package org.november.learning.entityScrubber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Table
@Scrubable(name="employee")
public class Employee {

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @ScrubbingMethod(type="masked")
    private String name;
    @ScrubbingMethod(type="masked")
    private String address;
    private Integer age;
}
