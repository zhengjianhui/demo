package demo.contorller;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.dao.mybatis.interceptorPlugin.page.PageRequest;
import demo.dao.mybatis.interceptorPlugin.page.PageResult;
import demo.dao.redis.DbTest1;
import demo.domain.Archive;
import demo.domain.user.User;
import demo.event.ListnenrEventMessage;
import demo.service.archive.ArchiveService;
import demo.service.event.EventTestService;
import demo.shiro.utils.ShiroSecurityUtils;
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
@Api("各种测试")
public class DemoContorller {

    @Autowired
    private Demo ddddd;

    @Autowired
    private EventTestService eventTestService;

    @Autowired
    private DbTest1 dbTest1;

    @Autowired
    private ArchiveService archiveService;

    @ApiOperation("测试事件发布")
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public void eventTest(@ApiParam(name = "flag", required = true, value = "是否使用事件发布") @RequestParam(value = "flag", required = true) Boolean flag) {

        ListnenrEventMessage message = new ListnenrEventMessage();
        message.setName("admin");
        message.setMessage("测试发布事件");

        if(flag) {
            eventTestService.sendEventTransaction(message);
        } else {
            eventTestService.sendEvent(message);
        }

    }



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

        User user = ShiroSecurityUtils.getUser();
        System.out.println(user.getUsername());

        archiveService.add();

        return a;
    }

    @ApiOperation("测试spring-mvc接受枚举参数")
    @RequestMapping(value = "/enumDemo", method = RequestMethod.GET)
    public void enumTest(@ApiParam(name = "a", value = "枚举对象") @RequestParam(value = "a", required = true) String a,
            @ApiParam(name = "b", value = "枚举对象") @RequestParam(value = "b", required = true) String b) throws InterruptedException {

        String key = dbTest1.addTest(a, b);

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

    @ApiOperation("patch测试")
    @RequestMapping(value = "/patchTest/{id}", method = RequestMethod.PATCH)
    public void queryAll111(@ApiParam(name = "user", value = "用户对象") @RequestBody User user,
                            @ApiParam(name = "id", value = "主键", required = true) @PathVariable("id") Long id) {
        System.out.println(user.getUsername());

    }


    @ApiOperation("角色验证1")
    @RequiresRoles("admin")
    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    public String demo1() {

        return "郑建辉";
    }

    @ApiOperation("角色验证2")
    @RequiresRoles("user")
    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    public String demo2() {

        return "郑建辉";
    }

    @ApiOperation("权限验证1")
    @RequiresPermissions("test")
    @RequestMapping(value = "/demo3", method = RequestMethod.GET)
    public String demo3() {

        return "郑建辉";
    }

    @ApiOperation("权限验证2")
    @RequiresRoles("user")
    @RequiresPermissions("asd")
    @RequestMapping(value = "/demo4", method = RequestMethod.GET)
    public String demo4() {

        return "郑建辉";
    }

    @ApiOperation("权限验证3")
    @RequiresRoles("user")
    @RequiresPermissions("test")
    @RequestMapping(value = "/demo5", method = RequestMethod.GET)
    public String demo5() {

        return "郑建辉";
    }

    @ApiOperation("权限验证4")
    @RequiresRoles("admin")
    @RequiresPermissions("test")
    @RequestMapping(value = "/demo6", method = RequestMethod.GET)
    public String demo6() {

        return "郑建辉";
    }
}
