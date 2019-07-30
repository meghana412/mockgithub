package com.pwxcoo.git.api;

import com.pwxcoo.git.util.GitUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.api
 * @email pwxcoo@gmail.com
 * @time 2018/10/07 12:34
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class GitHelperTest {

    @Test
    public void testCreate() throws GitAPIException, IOException {
        File file = GitHelper.createNewRepository(GitUtil.getFile("pwxcoo", "test"));
        Assert.assertEquals(true, file.exists());

        GitUtil.openRepository(file);

        GitHelper.deleteRepository(file);

        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void testReadFile() throws IOException {
        String fileContent = GitHelper.readFileFromCommit(GitUtil.getFile("pwxcoo", "tester"), "README.md");
        log.info(fileContent);

        Assert.assertEquals(false, fileContent  == null);
    }

    @Test
    public void testListFiles() throws IOException {
        GitHelper.listFilesFromCommit(GitUtil.getFile("pwxcoo", "tester"));
    }

}