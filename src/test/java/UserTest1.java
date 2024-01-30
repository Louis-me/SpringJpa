import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.shi.dao.UserDao;
import xyz.shi.domain.User;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")

public class UserTest1 {
    @Resource
    UserDao userDao;
    @Test
    public void testFindPage() {

        PageRequest page = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("userId")));
        Page<User> all = userDao.findAll(page);
        System.out.println("分页 + 根据id逆序 查询结果: " + all.getContent());
    }
    @Test
    public void testFindBy() {
        User user2 = userDao.findById(50).get();
        System.out.println(user2.getUserName());
    }
    @Test
    public void save() {
        User user = new User();
        user.setUserName("tttt");
        user.setPassword("pppp");
        User user11  = userDao.save(user);
        System.out.println(user11.getUserId());
    }
    @Test
    public void update() {
        User user = new User();
        user.setUserName("tttt60");
        user.setPassword("pppp60");
        user.setUserId(60);
        userDao.save(user);
    }
    @Test
    public void delete() {
        User user = new User();
        user.setUserId(59);
        userDao.delete(user);
    }
}
