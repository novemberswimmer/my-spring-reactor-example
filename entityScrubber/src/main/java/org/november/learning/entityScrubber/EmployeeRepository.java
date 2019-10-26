package org.november.learning.entityScrubber;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface EmployeeRepository extends ReactiveCassandraRepository<Employee, String> {
}
