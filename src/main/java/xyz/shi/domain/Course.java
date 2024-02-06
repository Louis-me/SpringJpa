package xyz.shi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 课程实体类
 */
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //学生 Student 的集合作为其属性，维护多对多关联关系
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}