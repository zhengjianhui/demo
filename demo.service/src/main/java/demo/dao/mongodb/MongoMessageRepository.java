package demo.dao.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import demo.domain.mongo.Message;
import demo.domain.mongo.MongoUser;

/**
 * Created by zhengjianhui on 16/12/25.
 * 与HibernateRepository类似，通过继承MongoRepository接口，我们可以非常方便地实现对一个对象的增删改查，
 * 要使用Repository的功能，先继承MongoRepository<T, TD>接口，
 * 其中T为仓库保存的bean类，TD为该bean的唯一标识的类型，一般为ObjectId。之后在service中注入该接口就可以使用，
 * 无需实现里面的方法，spring会根据定义的规则自动生成。
 *
 */
public interface MongoMessageRepository extends MongoRepository<Message, Long> {
}
