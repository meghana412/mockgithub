/**
 * @author pwxcoo0
 * @package com.pwxcoo.github
 * @email pwxcoo@gmail.com
 * @date 2018/09/21
 * @time 23:36
 * @description
 */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id`           BIGINT NOT NULL auto_increment,
  `email`             VARCHAR(255) NOT NULL,
  `avatar`            VARCHAR(255) NOT NULL,
  `bio`               VARCHAR(255),
  `location`          VARCHAR(255),
  `link`              VARCHAR(255),
  `username`          VARCHAR(255) NOT NULL,
  `password`          VARCHAR(255) NOT NULL,
  `salt`              VARCHAR(255) NOT NULL,
  `creation_time`     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time` DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY (`username`),
  UNIQUE KEY (`email`)
) engine = innodb;

DROP TABLE IF EXISTS `repository`;
CREATE TABLE `repository` (
  `repository_id`     BIGINT NOT NULL auto_increment,
  `user_id`           BIGINT NOT NULL,
  `description`       VARCHAR(255) NOT NULL,
  `repository_name`   VARCHAR(255) NOT NULL,
  `repository_star`   INT NOT NULL DEFAULT 0,
  `repository_fork`   INT NOT NULL DEFAULT 0,
  `creation_time`     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time` DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`repository_id`),
  UNIQUE KEY (`user_id`, `repository_name`)
) engine = innodb;

DROP TABLE IF EXISTS `contributor_relationship`;
CREATE TABLE `contributor_relationship` (
  `contributor_relationship_id`       BIGINT NOT NULL auto_increment,
  `user_id`                           BIGINT NOT NULL,
  `repository_id`                     BIGINT NOT NULL,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (contributor_relationship_id),
  UNIQUE KEY (`user_id`, `repository_id`)
) engine = innodb;

DROP TABLE IF EXISTS `follow_relationship`;
CREATE TABLE `follow_relationship` (
  `follow_relationship_id`            BIGINT NOT NULL auto_increment,
  `follower_id`                       BIGINT NOT NULL,
  `following_id`                      BIGINT NOT NULL,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`follow_relationship_id`),
  UNIQUE KEY (`follower_id`, `following_id`)
) engine = innodb;

DROP TABLE IF EXISTS `user_subscription`;
CREATE TABLE `user_subscription` (
  `user_subscription_id`              BIGINT NOT NULL auto_increment,
  `user_id`                           BIGINT NOT NULL,
  `action`                            VARCHAR(255) NOT NULL,       -- 'follow'
  `action_id`                         BIGINT NOT NULL,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`user_subscription_id`)
) engine = innodb;

DROP TABLE IF EXISTS `repository_subscription`;
CREATE TABLE `repository_subscription` (
  `repository_subscription_id`        BIGINT NOT NULL auto_increment,
  `user_id`                           BIGINT NOT NULL,
  `action`                            VARCHAR(255) NOT NULL,       -- 'star', 'create', 'fork'
  `repository_id`                     BIGINT NOT NULL,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`repository_subscription_id`)
) engine = innodb;

DROP TABLE IF EXISTS `watch_relationship`;
CREATE TABLE `watch_relationship` (
  `watch_relationship_id`             BIGINT NOT NULL auto_increment,
  `user_id`                           BIGINT NOT NULL,
  `repository_id`                     BIGINT NOT NULL,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`watch_relationship_id`)
) engine = innodb;

DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `notification_id`                   BIGINT NOT NULL auto_increment,
  `user_id`                           BIGINT NOT NULL,
  `repository_id`                     BIGINT NOT NULL,
  `action`                            VARCHAR(255) NOT NULL,     -- 'pr', 'issue'
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`notification_id`)
) engine = innodb;

DROP TABLE IF EXISTS `pull_request`;
CREATE TABLE `pull_request` (
  `pull_request_id`                   BIGINT NOT NULL auto_increment,
  `pull_request_number`               BIGINT NOT NULL,
  `origin_repository_id`              BIGINT NOT NULL,
  `next_repository_id`                BIGINT NOT NULL,
  `status`                            VARCHAR(255) NOT NULL,       -- 'open', 'closed'
  `comment_count`                     BIGINT NOT NULL DEFAULT 0,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`pull_request_id`),
  UNIQUE KEY (`pull_request_number`, `origin_repository_id`)
) engine = innodb;

DROP TABLE IF EXISTS `issue`;
CREATE TABLE `issue` (
  `issue_id`                          BIGINT NOT NULL auto_increment,
  `issue_number`                      BIGINT NOT NULL,
  `repository_id`                     BIGINT NOT NULL,
  `user_id`                           BIGINT NOT NULL,
  `text`                              TEXT NOT NULL,
  `status`                            VARCHAR(255) NOT NULL,       -- 'open', 'closed'
  `comment_count`                     BIGINT NOT NULL DEFAULT 0,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`issue_id`),
  UNIQUE KEY (`issue_number`, `repository_id`)
) engine = innodb;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id`                        BIGINT NOT NULL auto_increment,
  `comment_number`                    BIGINT NOT NULL,
  `issue_id`                          BIGINT,
  `pull_request_id`                   BIGINT,
  `creation_time`                     DATETIME NOT NULL DEFAULT current_timestamp,
  `modification_time`                 DATETIME NOT NULL DEFAULT current_timestamp on UPDATE current_timestamp,
  PRIMARY KEY (`comment_id`)
) engine innodb;
