import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.shi.dao.PersonDao;
import xyz.shi.domain.IdCard;
import xyz.shi.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")

public class PersonTest {
    @Autowired
    PersonDao dao;
    @Test
    public void testFindPage() {
        PageRequest page = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("id")));
        Page<Person> all = dao.findAll(page);
        System.out.println("分页 + 根据id逆序 查询结果: " + all.getContent());
        for(Person person: all) {
            System.out.println(person.getName());
            System.out.println(person.getIdCard().getCardNo());
        }
    }
    @Test
    public void testFindBy() {
        Person person = dao.findById(37).get();
        System.out.println(person.getName() + "_"+ person.getIdCard().getCardNo());
    }
    @Test
    public void save() {
        IdCard idCard = new IdCard();
        idCard.setId(14);

        Person person = new Person();
        person.setName("tttt");
        person.setIdCard(idCard);
        dao.save(person);
    }
    @Test
    public void update() {
        IdCard idCard = new IdCard();
        idCard.setId(17);
        Person person = new Person();
        person.setName("tttt2");
        person.setIdCard(idCard);
        person.setId(38);
        dao.save(person);
    }
    @Test
    public void delete() {
        dao.deleteById(37);
    }
}
