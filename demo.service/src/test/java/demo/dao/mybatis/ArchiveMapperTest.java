package demo.dao.mybatis;

import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.dao.mybatis.archive.ArchiveMapper;
import demo.domain.Archive;

/**
 * Created by zhengjianhui on 16/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ArchiveMapperTest {

    @Autowired
    private ArchiveMapper archiveMapper;

    @Test
    public void selectByParentId() throws Exception {

        Map<Long, Archive> result =archiveMapper.selectByParentId(0L);


        Set<Map.Entry<Long, Archive>> sout = result.entrySet();

        for (Map.Entry<Long, Archive> e : sout) {
            System.out.println(e.getKey() + "        " +e.getValue().getId());
        }

        System.out.println(sout.size());
    }

    @Test
    public void tes() {

        System.out.println(archiveMapper.selectByPrimaryKey(2L));
    }

}