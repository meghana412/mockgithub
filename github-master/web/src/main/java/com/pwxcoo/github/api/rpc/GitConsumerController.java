package com.pwxcoo.github.api.rpc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pwxcoo.git.dto.FilesListDto;
import com.pwxcoo.git.service.GitService;
import com.pwxcoo.github.dto.ReadCodeRpcDto;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.api
 * @email pwxcoo@gmail.com
 * @time 2018/09/25 10:17
 * @description
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/git")
public class GitConsumerController {
    @Reference(version = "${git.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}")
    private GitService gitService;

    @RequestMapping("/initRep")
    public String createNewRepository(@RequestParam String username, @RequestParam String repositoryName) throws GitAPIException, IOException {
        return gitService.createNewRepository(username, repositoryName);
    }

    @RequestMapping(value = "/code", method = RequestMethod.POST)
    public ReadCodeRpcDto readfileFromCommit(@RequestBody ReadCodeRpcDto readCodeRpcDto) throws GitAPIException, IOException {
        String fileContent = gitService.readFileFromCommit(readCodeRpcDto.getUsername(), readCodeRpcDto.getRepositoryName(), readCodeRpcDto.getFilename());
        readCodeRpcDto.setFileContent(fileContent);
        return readCodeRpcDto;
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public List<FilesListDto> ListFiles(@RequestParam String username, @RequestParam String repositoryName) throws IOException {
        System.out.println(username + "  " + repositoryName);
        return gitService.listFiles(username, repositoryName);
    }
}
