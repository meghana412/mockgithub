package com.pwxcoo.github.service.repository;

import com.pwxcoo.github.dto.RepositoryDto;
import com.pwxcoo.github.mapper.RepositoryMapper;
import com.pwxcoo.github.model.data.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service.repository
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 12:02
 * @description
 */
@Service
@Slf4j
public class RepositoryServiceImpl implements RepositoryService{

    @Autowired
    RepositoryMapper repositoryMapper;

    @Override
    public List<RepositoryDto> getRandomRepository(Integer limitRows) {
        return repositoryMapper.getRandomRepository(limitRows);
    }

    @Override
    public List<RepositoryDto> getRepositoriesByUsername(String username) {
        return repositoryMapper.getRepositoriesByUsername(username);
    }

    @Override
    public Boolean createRepository(Repository repository) {
        try {
            if (repositoryMapper.insertRepository(repository.getUserId(), repository.getRepositoryName(), repository.getDescription()) > 0)
                return true;
            else {
                log.error("Unknown Error when insert Repository: " + repository.toString());
                return false;
            }
        } catch (Exception e) {
            log.error("Error when insert Repository: " + repository.toString());
            return false;
        }
    }

    @Override
    public RepositoryDto getRepositoryByUserIdAndRepositoryName(Long userId, String repositoryName) {
        return repositoryMapper.getRepositoryByRepositoryNameAndUserId(userId, repositoryName);
    }

    @Override
    public RepositoryDto getRepositoryByRepositoryNameAndUsername(String username, String repositoryName) {
        return repositoryMapper.getRepositoryByRepositoryNameAndUsername(username, repositoryName);
    }
}
