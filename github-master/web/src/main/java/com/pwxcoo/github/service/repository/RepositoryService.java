package com.pwxcoo.github.service.repository;

import com.pwxcoo.github.dto.RepositoryDto;
import com.pwxcoo.github.model.data.Repository;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service.repository
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 12:00
 * @description
 */
public interface RepositoryService {

    List<RepositoryDto> getRandomRepository(Integer limitRows);

    List<RepositoryDto> getRepositoriesByUsername(String username);

    Boolean createRepository(Repository repository);

    RepositoryDto getRepositoryByUserIdAndRepositoryName(Long userId, String repositoryName);

    RepositoryDto getRepositoryByRepositoryNameAndUsername(String username, String repositoryName);
}
