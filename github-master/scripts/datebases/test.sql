INSERT INTO `user` (`id`, `email`, `username`, `password`) VALUES (3, '1050270321@qq.com', 'pwxcoo', '123456');

INSERT INTO `user` (`email`, `username`, `password`) VALUES ('1050270322@qq.com', 'pwxcoo0', '123456');

INSERT INTO `user` (`email`, `username`, `password`) VALUES ('1050270323@qq.com', 'pwxcoo1', '123456');

UPDATE user SET password = '1234567' WHERE username = 'pwxcoo0';

SELECT * FROM user;