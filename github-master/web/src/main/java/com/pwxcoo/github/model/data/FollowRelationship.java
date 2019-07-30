package com.pwxcoo.github.model.data;

import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 16:59
 * @description
 */
@Data
public class FollowRelationship {

    private Long followRelationshipId;
    private Long followerId;
    private Long followingId;

}
