package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Student;

public interface StudentRepo extends CrudRepository<Student, Integer> {
	// retrieve student when do test for alice and charlie
	@Query("select * from Person p where p.username=:username")
    public Student findPersonByUsername(@Param("username") String username);
	

}
