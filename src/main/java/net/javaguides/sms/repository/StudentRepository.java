package net.javaguides.sms.repository;

import net.javaguides.sms.entity.Sex;
import net.javaguides.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByClassName(String className);

	List<Student> findByScoreGreaterThan(Double score);

	@Query("SELECT s.age, COUNT(s) FROM Student s GROUP BY s.age")
	List<Object[]> countByAgeGroup();

	@Query("SELECT s.sex, COUNT(s) FROM Student s GROUP BY s.sex")
	List<Object[]> countBySexGroup();

}
