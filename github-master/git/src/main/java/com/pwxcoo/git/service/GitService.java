package com.pwxcoo.git.service;

import com.pwxcoo.git.dto.FilesListDto;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.service
 * @email pwxcoo@gmail.com
 * @time 2018/09/25 10:03
 * @description
 */
public interface GitService {

    String createNewRepository(String username, String repositoryName) throws GitAPIException, IOException;

    void deleteRepository(String username, String repositoryName) throws IOException;

    String readFileFromCommit(String username, String repositoryName, String filename) throws IOException;

    List<FilesListDto> listFiles(String username, String repositoryName) throws IOException;
}
