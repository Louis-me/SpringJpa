import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.shi.dao.StudentDao;
import xyz.shi.domain.Course;
import xyz.shi.domain.Grade;
import xyz.shi.domain.Student;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentTest {
    @Autowired
    private StudentDao dao;
    @Test
    public void oneToMany() {
        PageRequest page = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("id")));
        Page<Student> all = dao.findAll(page);
        System.out.println("分页 + 根据id逆序 查询结果: " + all.getContent());
        for(Student student: all) {
            System.out.println(student.getName());
            System.out.println(student.getGrade().getName());
        }
        Grade grade = new Grade();
        grade.setId(36);

        Student student = new Student();
        student.setName("王大伟4");
        //设置学生的班级
        student.setGrade(grade);

        Student student2 = new Student();
        student2.setGrade(grade);
        student2.setName("小红5");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);

        dao.saveAll(studentList);
//        dao.deleteAll();
    }
    @Test
    public void ManyToManyFind() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Object[]> page = dao.findAllCourse(pageable);

        System.out.println("Page " + page.getNumber() + " of " + page.getTotalPages());
        System.out.println("Total elements: " + page.getTotalElements());

        for(Object[] objects: page.getContent()) {
            Student student = (Student) objects[0];
            Course course = (Course) objects[1];
            Grade grade = (Grade) objects[2];
            System.out.println(student.getName());
            System.out.println(course.getName());
            System.out.println(grade.getName());
        }
    }
    @Test
    public void ManyToManySave() {
        //-----新增两个学生，关联到一个班级下面，同时新增的两个学生关联不同的课程（需要用到第三方表）

        //一个班级
        Grade grade = new Grade();
        grade.setId(36);
        // 新建两个学生
        Student student1 = new Student();
        student1.setName("王大伟7");
        //设置学生的班级
        student1.setGrade(grade);

        Student student2 = new Student();
        student2.setGrade(grade);
        student2.setName("小红71");
        // 两个学生各自对应两个课程
        Course course1 = new Course();
        course1.setId(17);

        Course course2 = new Course();
        course2.setId(18);

        //学生选课的关系相互关联
        course1.getStudents().add(student1);
        student1.getCourses().add(course1);

        course2.getStudents().add(student2);
        student2.getCourses().add(course2);

        dao.save(student1);
        dao.save(student2);
    }
    @Test
    public void ManyToManyUpdate() {
        //一个班级
        Grade grade = new Grade();
        grade.setId(36);


        // 新建两个学生
        Student student1 = new Student();
        student1.setName("王大伟7");
        student1.setId(43);
        //设置学生的班级
        student1.setGrade(grade);

        Student student2 = new Student();
        student2.setGrade(grade);
        student2.setName("小红71");
        student2.setId(45);
        // 两个学生各自对应两个课程
        Course course1 = new Course();
        course1.setId(17);

        Course course2 = new Course();
        course2.setId(18);

        //学生选课的关系相互关联

        // 先移除关联关系
        student1.getCourses().remove(course1);
        course1.getStudents().remove(student1);

        student2.getCourses().remove(course2);
        course2.getStudents().remove(student2);
        // 更新关联关系
        course1.getStudents().add(student1);
        student1.getCourses().add(course1);

        course2.getStudents().add(student2);
        student2.getCourses().add(course2);

        dao.save(student1);
        dao.save(student2);
    }
    @Test
    public void ManyToManyDel() {

        Student student = new Student();
        student.setId(46);

        Course course = new Course();
        course.setId(39);

        // 移除关联关系
        student.getCourses().remove(course);
        course.getStudents().remove(student);

        dao.delete(student);

    }
}
