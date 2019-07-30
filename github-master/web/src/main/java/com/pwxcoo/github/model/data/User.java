package com.pwxcoo.github.model.data;

import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/09/23 11:20
 * @description
 */
@Data
public class User {

    private Long userId;
    private String email;
    private String avatar;
    private String bio = "No bio yet";
    private String location = "Earth";
    private String link = "https://pwxcoo.github.io";
    private String username;
    private String password;
    private String salt;
}