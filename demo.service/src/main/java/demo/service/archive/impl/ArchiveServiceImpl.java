package demo.service.archive.impl;

import demo.dao.mybatis.archive.ArchiveMapper;
import demo.dao.mybatis.interceptorPlugin.page.PageRequest;
import demo.dao.mybatis.interceptorPlugin.page.PageResult;
import demo.domain.Archive;
import demo.service.archive.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhengjianhui on 16/10/2.
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {



    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public void add() {

        for (Long i = 0L; i < 500; i++) {
            Archive archive = new Archive();
            archive.setId(i);
            archive.setFileType("1");
            archive.setCreateStaffId(1231231231123L);
            archive.setDoubtDirectory(false);
            archive.setFileSize(123123L);
            archive.setUrl("12312313");
            archive.setFileName("123123");
            archive.setParentId(0L);
            archive.setSubjectId(12312313123L);
            archive.setSubjectType("1");

            archiveMapper.insertSelective(archive);
        }

    }

    @Override
    public PageResult<Archive> queryList(PageRequest page) {

        return archiveMapper.selectList(page, new Archive());
    }
}
