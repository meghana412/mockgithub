package com.pwxcoo.github.model.data;

import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 16:44
 * @description
 */
@Data
public class ContributorRelationship {

    private Long contributorRelationshipId;
    private Long userId;
    private Long repositoryId;
}
