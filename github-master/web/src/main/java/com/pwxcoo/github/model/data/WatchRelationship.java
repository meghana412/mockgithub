package com.pwxcoo.github.model.data;

import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 17:04
 * @description
 */
@Data
public class WatchRelationship {

    private Long watchRelationshipId;
    private Long userId;
    private Long repositoryId;
}
