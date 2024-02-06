package xyz.shi.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import xyz.shi.domain.Student;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    @Query("SELECT s, c, g FROM Student s JOIN s.courses c JOIN s.grade g")
    Page<Object[]> findAllCourse(Pageable var1);
}
