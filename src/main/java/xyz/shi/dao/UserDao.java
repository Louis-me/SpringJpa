package xyz.shi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.shi.domain.User;
import java.util.List;


public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    // 普通的查询方法
    // 示例 getByName(String name):getBy固定用法
    //1. Name是User成员变量的首字母大写
    //2. name与User的成员变量name相同
//    public User getByUserName(String name);
//    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
//    List<User> findByNameContaining(@Param("name") String name);
//
}
