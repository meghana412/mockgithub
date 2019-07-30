package com.pwxcoo.github.model.type;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.type
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 12:04
 * @description
 */
public enum Action {

    FOLLOW("follow"), STAR("star"), CREATE("create"), FORK("fork"), PULL_REQUEST("pull request"), ISSUE("issue");

    private final String actionType;

    Action(final String actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return actionType;
    }

}
