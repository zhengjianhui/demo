package demo.contorller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.dao.mongodb.MongoMessageRepository;
import demo.dao.mongodb.MongoUserRepository;
import demo.domain.mongo.Message;
import demo.domain.mongo.MongoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by zhengjianhui on 16/12/25.
 */
@Api("mongodb 测试")
@RequestMapping
@RestController
public class MongodbController {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @ApiOperation("新增保存")
    @RequestMapping(value = "/mongos", method = RequestMethod.POST)
    public MongoUser addMongoUser(@ApiParam(name = "mongoUser", value = "新增", required = true) @RequestBody MongoUser mongoUser) {

        mongoUserRepository.save(mongoUser);

        return mongoUser;
    }

    @ApiOperation("查询全部")
    @RequestMapping(value = "/mongos/queryAll", method = RequestMethod.GET)
    public List<MongoUser> queryMongoUserList() {

        List<MongoUser> mongoUsers = mongoUserRepository.findAll();

        return mongoUsers;
    }

    @ApiOperation("根据条件查询全部")
    @RequestMapping(value = "/mongos/byCondition", method = RequestMethod.GET)
    public List<MongoUser> queryMongoUserList(@ApiParam(name = "id", value = "id", required = false) @RequestParam(value = "id", required = false) Long id,
            @ApiParam(name = "name", value = "名字", required = false) @RequestParam(value = "name", required = false) String name) {

        MongoUser query = new MongoUser();
        query.setId(id);
        query.setName(name);

        List<MongoUser> mongoUsers = mongoUserRepository.findAll(Example.of(query));

        return mongoUsers;
    }

    @ApiOperation("根据id查询")
    @RequestMapping(value = "/mongos/{id}", method = RequestMethod.GET)
    public MongoUser queryMongoUserList(@ApiParam(name = "id", value = "id", required = false) @PathVariable("id") Long id) {

        return mongoUserRepository.findOne(id);
    }

    @ApiOperation("删除单个")
    @RequestMapping(value = "/mongos/{id}", method = RequestMethod.DELETE)
    public void removeMongoUser(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Long id) {

        mongoUserRepository.delete(id);

    }

    @ApiOperation("分页")
    @RequestMapping(value = "/mongos/byConditionPage", method = RequestMethod.GET)
    public Page<MongoUser> queryMongoUserPage(@ApiParam(name = "id", value = "id", required = false) @RequestParam(value = "id", required = false) Long id,
            @ApiParam(name = "name", value = "名字", required = false) @RequestParam(value = "name", required = false) String name,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10&sort=name,asc/desc") @PageableDefault(page = 0, size = 10) Pageable pageable) {

        // Sort sort = new Sort(Sort.Direction.DESC, "id");
        // Pageable pageable = new PageRequest(0, 2, sort);

        MongoUser query = new MongoUser();
        query.setId(id);
        query.setName(name);

        Page<MongoUser> mongoUsers = mongoUserRepository.findAll(Example.of(query), pageable);

        return new PageImpl<MongoUser>(mongoUsers.getContent(), pageable, mongoUsers.getTotalElements());
    }

}
