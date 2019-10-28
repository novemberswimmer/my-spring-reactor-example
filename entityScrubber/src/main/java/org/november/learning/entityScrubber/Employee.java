package org.november.learning.entityScrubber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.november.learning.entityScrubber.engine.annotations.Scrubable;
import org.november.learning.entityScrubber.engine.annotations.ScrubbingByMasking;
import org.november.learning.entityScrubber.engine.annotations.ScrubbingByReplacing;
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
    @ScrubbingByMasking(numberOfChar = 4)
    private String name;
    @ScrubbingByMasking(numberOfChar = 4)
    private String address;
    @ScrubbingByReplacing(value = "1111-11-111")
    private String ssn;
    private Integer age;

    public static interface TypeOfScrubbing {
    }
}
