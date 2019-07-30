package com.pwxcoo.github.api.restful;

import com.pwxcoo.github.dto.RepositoryDto;
import com.pwxcoo.github.model.data.Repository;
import com.pwxcoo.github.model.exception.ServerException;
import com.pwxcoo.github.model.exception.UnauthorizedException;
import com.pwxcoo.github.service.repository.RepositoryService;
import com.pwxcoo.github.utils.SessionUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.api.restful
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 11:54
 * @description
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/repository")
public class RepositoryRestController {

    @Autowired
    RepositoryService repositoryService;


    @ApiOperation(value="Get all repositories by current user", notes="By current user")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<RepositoryDto> getRepositories() throws UnauthorizedException {
        if (SessionUtil.session().isPresent()) {
            return repositoryService.getRepositoriesByUsername(SessionUtil.session().get().getAttribute("username").toString());
        } else throw new UnauthorizedException("The user is not logined!");

    }

    @ApiOperation(value="Get all repositories by username", notes="By username")
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public List<RepositoryDto> getRepositoriesByUsername(@PathVariable String username) {
        return repositoryService.getRepositoriesByUsername(username);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RepositoryDto createRepository(@RequestBody Repository repository) throws UnauthorizedException, ServerException {
        if (repository.getUserId() == null) {
            if ( SessionUtil.session().isPresent() == false) {
                throw new UnauthorizedException("The user is not logined!");
            }
            repository.setUserId(SessionUtil.getUserId());
        }

        log.info(repository.toString());
        if (repositoryService.createRepository(repository)) {
            return repositoryService.getRepositoryByUserIdAndRepositoryName(repository.getUserId(), repository.getRepositoryName());
        }
        throw new ServerException("create repository failed!");
    }
}
