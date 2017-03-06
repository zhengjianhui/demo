package demo.service.archive.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import demo.dao.mybatis.db1.archive.ArchiveMapper;
import demo.domain.Archive;
import demo.service.archive.ArchiveService;


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
    public Page<Archive> queryList(Pageable page) {

        return archiveMapper.selectList(page, new Archive());
    }
}
