package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Section;

public interface SectionRepo extends CrudRepository<Section, Integer> {
	// need retrieve sections in test
	 @Query("select * from Section s where s.title=:title")
	 public Section findSectionByTitle(@Param("title") String title);
}
