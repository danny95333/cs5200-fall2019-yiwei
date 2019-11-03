package edu.northeastern.cs5200.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import edu.northeastern.cs5200.entities.Faculty;

public interface FacultyRepo extends CrudRepository<Faculty, Integer> {

	// retrieve alan and ada when do test
	@Query("select * from Person p where p.username=:username")
    public Faculty findPersonByUsername(@Param("username") String username);
}
