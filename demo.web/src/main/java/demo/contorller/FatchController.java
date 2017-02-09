package demo.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.util.Random;

import demo.dao.mybatis.fatch.FatchOnMapper;
import demo.domain.fatch.FatchOn;
import demo.util.id.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by zhengjianhui on 17/2/9.
 */
@Api("测试唯一性索引")
@RequestMapping
@RestController
public class FatchController {

    @Autowired
    private FatchOnMapper fatchOnMapper;

    @ApiOperation("测试唯一性索引插入异常")
    @RequestMapping(value = "/fatch", method = RequestMethod.POST)
    public void add(@ApiParam(name = "fatch", value = "唯一索引值", required = true) @RequestParam(value = "fatch", required = true) Long fatch) {

        FatchOn f = new FatchOn();

        try {
            f.setId(IdUtil.getId());
            f.setFatchOn(fatch);
            int i = fatchOnMapper.insert(f);
            System.out.println(i);

        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if(cause instanceof MySQLIntegrityConstraintViolationException) {
                System.out.println(1);
            }
        }
    }


    @ApiOperation("测试唯一性索引更新异常")
    @RequestMapping(value = "/fatch/{id}", method = RequestMethod.PUT)
    public void update(@ApiParam(name = "id", value = "标识", required = true) @PathVariable("id") Long id) {
        FatchOn f = new FatchOn();
        f.setId(id);
        Random ran = new Random();

        updateF(f, ran);

    }



    private void updateF(FatchOn f, Random ran) {

        Long ff = Long.valueOf(ran.nextInt(3));
        try {
            f.setFatchOn(ff);
            int i = fatchOnMapper.updateByPrimaryKeySelective(f);
            System.out.println(i);

        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if(cause instanceof MySQLIntegrityConstraintViolationException) {
                updateF(f, ran);
            }
        }

    }



}
