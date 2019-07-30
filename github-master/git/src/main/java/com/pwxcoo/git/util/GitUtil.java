package com.pwxcoo.git.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.util
 * @email pwxcoo@gmail.com
 * @time 2018/10/07 12:47
 * @description
 */
@Component
@Slf4j
public class GitUtil {

    private static String gitServiceDirectory;

    @Value("${gitservice.directory}")
    public void setGitServiceDirectory(String dir) {
        gitServiceDirectory = dir;
    }


    public static File getFile(String username, String repositoryName) {
        return new File(String.format("%s/%s/%s/.git", gitServiceDirectory, username, repositoryName));
    }

    public static Repository openRepository(File file) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();


        try (Repository repository = builder.setGitDir(file)
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build()) {
            log.info("Opening repository: " + repository.getDirectory());

            return repository;
        }
    }

    public static Repository openCurrentRepository() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
    }


}
