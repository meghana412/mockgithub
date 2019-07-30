package com.pwxcoo.github.api.restful;

import com.pwxcoo.github.dto.RepositoryDto;
import com.pwxcoo.github.service.repository.RepositoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.api.restful
 * @email pwxcoo@gmail.com
 * @time 2018/10/06 13:21
 * @description
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/random")
public class RandomRestController {

    @Autowired
    RepositoryService repositoryService;

    @ApiOperation(value="Get random repositories by current user", notes="will upgrade to recommend")
    @RequestMapping(value = "/repository", method = RequestMethod.GET)
    public List<RepositoryDto> getRandomRepositories() {
        return repositoryService.getRandomRepository(3);

    }
}
