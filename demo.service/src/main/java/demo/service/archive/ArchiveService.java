package demo.service.archive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import demo.domain.Archive;

/**
 * Created by zhengjianhui on 16/10/2.
 */
public interface ArchiveService {

    void add();

    Page<Archive> queryList(Pageable page);


}
