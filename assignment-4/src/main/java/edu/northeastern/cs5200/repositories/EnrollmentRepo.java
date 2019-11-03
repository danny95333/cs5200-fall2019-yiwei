package edu.northeastern.cs5200.repositories;
import org.springframework.data.repository.*;
import edu.northeastern.cs5200.entities.Enrollment;
public interface EnrollmentRepo extends CrudRepository<Enrollment, Integer> {

}
