-- select following By follower

SELECT
    *
FROM
(
SELECT 
    t.follower_id, t.following_id, t1.username as follower_username, t2.username as following_username
FROM
    follow_relationship t
    JOIN user t1 ON t1.user_id = t.follower_id
    JOIN user t2 ON t2.user_id = t.following_id
) AS tt
WHERE
    tt.follower_id = 1;

-- select follower By following

SELECT
    *
FROM
(
SELECT 
    t.follower_id, t.following_id, t1.username as follower_username, t2.username as following_username, t1.avatar as follower_avatar, t2.avatar as following_avatar
FROM
    follow_relationship t
    JOIN user t1 ON t1.user_id = t.follower_id
    JOIN user t2 ON t2.user_id = t.following_id
) AS tt
WHERE
    tt.following_id = 1;

-- random rows
-- https://stackoverflow.com/questions/4329396/mysql-select-10-random-rows-from-600k-rows-fast

SELECT 
    * 
FROM 
        user 
    JOIN
        repository
    ON 
        user.user_id = repository.user_id
ORDER BY 
    RAND()
LIMIT
    10;

-- get user_subscripiton

SELECT 
    t.user_id as user_id, t.action as action, t1.username as username, 
    t1.avatar as user_avatar, t2.username as action_object, t2.avatar as action_avatar, 
    t2.bio as action_bio, t.creation_time as time
FROM 
    user_subscription t
    JOIN user t1 ON t.user_id = t1.user_id
    JOIN user t2 ON t.action_id = t2.user_id
WHERE 
    t.user_id = 1
ORDER BY
    time DESC;

SELECT 
    *
FROM
    (
    SELECT 
        t.user_id as user_id, t.action as action, t1.username as username, 
        t1.avatar as user_avatar, t2.username as action_object, t2.avatar as action_avatar, 
        t2.bio as action_bio, t.creation_time as time
    FROM 
        user_subscription t
        JOIN user t1 ON t.user_id = t1.user_id
        JOIN user t2 ON t.action_id = t2.user_id
    ) f1
JOIN 
    (
        SELECT 
            following_id
        FROM
            follow_relationship
        WHERE
            follower_id = 1
    ) f2
ON
    f1.user_id = f2.following_id
ORDER BY
    time DESC;
