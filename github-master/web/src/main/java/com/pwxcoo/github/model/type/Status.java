package com.pwxcoo.github.model.type;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.type
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 15:39
 * @description
 */
public enum  Status {

    OPEN("open"), CLOSED("closed");

    private final String statusType;

    Status(final String statusType) {
        this.statusType = statusType;
    }

    @Override
    public String toString() {
        return statusType;
    }
}
