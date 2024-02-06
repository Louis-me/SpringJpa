package xyz.shi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.shi.domain.Person;

public interface PersonDao extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {
}
