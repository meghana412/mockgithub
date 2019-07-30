package com.pwxcoo.git.api;

import com.pwxcoo.git.dto.FilesListDto;
import com.pwxcoo.git.util.GitUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.api
 * @email pwxcoo@gmail.com
 * @time 2018/10/07 12:15
 * @description
 */
@Slf4j
public class GitHelper {


    public static File createNewRepository(File file) throws GitAPIException, IOException {

        try (Git git = Git.init().setDirectory( file ).setBare( true ).call()) {
            log.info("Having repository: " + git.getRepository().getDirectory());
        }
        return file;
    }

    public static void deleteRepository(File file) throws IOException {
        log.info("Deleting repository " + file.getAbsolutePath());
        FileUtils.deleteDirectory(file);

    }

    public static String readFileFromCommit(File file, String filename) throws IOException {
        String fileContent = null;

        try (Repository repository = GitUtil.openRepository(file)) {

            // find the HEAD
            ObjectId lastCommitId = repository.resolve(Constants.HEAD);

            // a RevWalk allows to walk over commits based on some filtering that is defined
            try (RevWalk revWalk = new RevWalk(repository)) {

                RevCommit commit = revWalk.parseCommit(lastCommitId);
                // and using commit's tree find the path
                RevTree tree = commit.getTree();
                System.out.println("Having tree: " + tree);

                // now try to find a specific file
                try (TreeWalk treeWalk = new TreeWalk(repository)) {

                    treeWalk.addTree(tree);
                    treeWalk.setRecursive(true);
                    treeWalk.setFilter(PathFilter.create(filename));
                    if (!treeWalk.next()) {
                        throw new IllegalStateException(String.format("Did not find expected file %s", filename));
                    }

                    ObjectId objectId = treeWalk.getObjectId(0);
                    ObjectLoader loader = repository.open(objectId);

                    // and then one can the loader to read the file
//                    loader.copyTo(System.out);
                    fileContent = new String(loader.getBytes(), "utf-8");
//                    log.info("fileContent: " + fileContent);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

                revWalk.dispose();
                return fileContent;
            }
        }
    }

    private static FilesListDto getDir(TreeWalk treeWalk) throws IOException {
        treeWalk.enterSubtree();

        FilesListDto filesListDto = new FilesListDto("", new LinkedList<>());
        String prefix = treeWalk.getPathString();
        boolean head = true;

        while (treeWalk != null){
            if (head) {
                filesListDto.setName(treeWalk.getPathString());
                treeWalk.next();
                head = false;
            } else {
                if (treeWalk.getPathString().startsWith(prefix) == false || treeWalk.getPathString().equals(prefix))
                    break;

                if (treeWalk.isSubtree()) {
                    filesListDto.getChildren().add(getDir(treeWalk));
                } else {
                    filesListDto.getChildren().add(new FilesListDto(treeWalk.getPathString(), null));
                    treeWalk.next();
                }

            }
        }
        return filesListDto;
    }

    public static List<FilesListDto> listFilesFromCommit(File file)  throws IOException {
        List<FilesListDto> filesListDtos = new LinkedList<>();

        try (Repository repository = GitUtil.openRepository(file)) {

            // find the HEAD
            ObjectId lastCommitId = repository.resolve(Constants.HEAD);

            // a RevWalk allows to walk over commits based on some filtering that is defined
            try (RevWalk revWalk = new RevWalk(repository)) {

                RevCommit commit = revWalk.parseCommit(lastCommitId);
                // and using commit's tree find the path
                RevTree tree = commit.getTree();
                System.out.println("Having tree: " + tree);

                // now try to find a specific file
                try (TreeWalk treeWalk = new TreeWalk(repository)) {

                    treeWalk.addTree(tree);
                    treeWalk.setRecursive(false);

                    treeWalk.next();

                    while (treeWalk != null) {
                        if (treeWalk.isSubtree()) {
                            filesListDtos.add(getDir(treeWalk));

                        } else {
                            filesListDtos.add(new FilesListDto(treeWalk.getPathString(), null));
                            treeWalk.next();
                        }

                    }

                } catch (Exception e) {
                    log.error(e.getMessage());
                }

                revWalk.dispose();
            }
        }

        System.out.println(filesListDtos.toString());

        return filesListDtos;
    }


}
