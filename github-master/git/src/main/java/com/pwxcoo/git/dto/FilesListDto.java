package com.pwxcoo.git.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.dto
 * @email pwxcoo@gmail.com
 * @time 2018/10/07 22:15
 * @description
 */
@Data
@AllArgsConstructor
public class FilesListDto implements Serializable {

    private String name;
    private List<FilesListDto> children;
}
