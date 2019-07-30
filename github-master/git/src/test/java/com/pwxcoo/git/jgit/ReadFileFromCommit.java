package com.pwxcoo.git.jgit;

import com.pwxcoo.git.util.CookbookUtil;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import java.io.IOException;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.jgit
 * @email pwxcoo@gmail.com
 * @time 2018/10/06 22:22
 * @description
 */
public class ReadFileFromCommit {

    public static void main(String[] args) throws IOException {
        try (Repository repository = CookbookUtil.openJGitCookbookRepository()) {
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
                    treeWalk.setFilter(PathFilter.create("README.md"));
                    if (!treeWalk.next()) {
                        throw new IllegalStateException("Did not find expected file 'README.md'");
                    }

                    ObjectId objectId = treeWalk.getObjectId(0);
                    ObjectLoader loader = repository.open(objectId);

                    // and then one can the loader to read the file
                    loader.copyTo(System.out);
                }

                revWalk.dispose();
            }
        }
    }
}
