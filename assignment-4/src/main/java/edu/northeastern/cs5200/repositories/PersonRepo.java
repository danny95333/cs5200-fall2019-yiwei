package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.entities.Person;
import org.springframework.data.repository.*;

public interface PersonRepo extends CrudRepository<Person, Integer> {

	
}
