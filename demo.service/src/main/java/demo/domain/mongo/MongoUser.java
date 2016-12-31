package demo.domain.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zhengjianhui on 16/12/25.
 *
 *
 * 注解 ： @Document 注解与类上  被注解的类会被认定为一个 文档（Collection）在xml 中配置后可以不写 collction 属性
 * 注解 ： @Document(collection = "MongoUser")   collection 属性可以指定 类对应的文档
 */
@Document
public class MongoUser {

    /**
     * ObjectId 注解的字段会被称为 ObjectId 的唯一 key
     */
    @Id
    private Long id;

    private String name;

    public MongoUser() {
    }

    /**
     *  注解 ： @PersistenceConstructor 注解
     *  注解与构造器上，可以根据查询结果自动构造对象
     *  注意在于，无参构造要同时加入
     * @param id
     * @param name
     */
    @PersistenceConstructor
    public MongoUser(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
