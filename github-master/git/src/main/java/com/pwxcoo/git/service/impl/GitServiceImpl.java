package com.pwxcoo.git.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pwxcoo.git.api.GitHelper;
import com.pwxcoo.git.dto.FilesListDto;
import com.pwxcoo.git.service.GitService;
import com.pwxcoo.git.util.GitUtil;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.service.impl
 * @email pwxcoo@gmail.com
 * @time 2018/09/25 10:04
 * @description
 */
@Service(
        version = "${git.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class GitServiceImpl implements GitService {

    @Override
    public String createNewRepository(String username, String repositoryName) throws GitAPIException, IOException {
        File file = GitUtil.getFile(username, repositoryName);
        return GitHelper.createNewRepository(file).getAbsolutePath();
    }

    @Override
    public void deleteRepository(String username, String repositoryName) throws IOException {
        GitHelper.deleteRepository(GitUtil.getFile(username, repositoryName));
    }

    @Override
    public String readFileFromCommit(String username, String repositoryName, String filename) throws IOException {
        return GitHelper.readFileFromCommit(GitUtil.getFile(username, repositoryName), filename);
    }

    @Override
    public List<FilesListDto> listFiles(String username, String repositoryName) throws IOException {
        return GitHelper.listFilesFromCommit(GitUtil.getFile(username, repositoryName));
    }

}
