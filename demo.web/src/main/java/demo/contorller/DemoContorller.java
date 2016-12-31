package demo.contorller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.dao.mybatis.interceptorPlugin.page.PageRequest;
import demo.dao.mybatis.interceptorPlugin.page.PageResult;
import demo.dao.nosql.DbTest1;
import demo.dao.nosql.PubTest;
import demo.domain.Archive;
import demo.service.archive.ArchiveService;
import demo.test.Aaa;
import demo.test.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by zhengjianhui on 16/10/1.
 */

@RestController
@RequestMapping
@Api("架构测试")
public class DemoContorller {

    @Autowired
    private Demo ddddd;

    @Autowired
    private PubTest pubTest;

    @Autowired
    private DbTest1 dbTest1;

    @Autowired
    private ArchiveService archiveService;

    @ApiOperation("测试返回值")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {

        return "郑建辉";
    }

    @ApiOperation("测试返回值2")
    @RequestMapping(value = "/dd", method = RequestMethod.GET)
    public Aaa dd() {
        Aaa a = new Aaa();
        a.setAge("中文");

        archiveService.add();

        return a;
    }

    @ApiOperation("测试spring-mvc接受枚举参数")
    @RequestMapping(value = "/enumDemo", method = RequestMethod.GET)
    public void enumTest(@ApiParam(name = "a", value = "枚举对象") @RequestParam(value = "a", required = true) String a,
            @ApiParam(name = "b", value = "枚举对象") @RequestParam(value = "b", required = true) String b) throws InterruptedException {

        String key = dbTest1.addTest(a, b);
        pubTest.addTest("java", key);

    }

    @ApiOperation("异常测试, 顺便测试接收时间")
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public void aVoid(@ApiParam(name = "date", value = "时间测试") @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
            throws Exception {

        System.out.println(date);

        throw new Exception("我操抛出异常了");
    }

    @ApiOperation("mybatis 自定义 interceptor 拦截分页测试")
    @RequestMapping(value = "/archveAll", method = RequestMethod.POST)
    public PageResult<Archive> queryAll(@ApiParam(name = "page", value = "分页参数") @RequestBody PageRequest page) {

        return archiveService.queryList(page);
    }
}
