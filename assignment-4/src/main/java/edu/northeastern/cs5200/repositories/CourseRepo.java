package edu.northeastern.cs5200.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Course;
public interface CourseRepo extends CrudRepository<Course, Integer> {

	@Query("selct * from Course c where c.label=:label")
    public Course findCourseByLabel(@Param("label") String label);
}
