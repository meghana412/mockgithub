package com.pwxcoo.github.model.data;

import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 11:55
 * @description
 */
@Data
public class Repository {


    private Long repositoryId;
    private Long userId;
    private String description = "no description";
    private String repositoryName;
    private Integer repositoryStar;
    private Integer repositoryFork;
}
