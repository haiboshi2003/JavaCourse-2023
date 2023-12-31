CREATE DATABASE QuestionBank CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE QuestionBank;
CREATE TABLE user (
    id INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    create_time DATETIME,
    update_time DATETIME
);

CREATE TABLE title (
    id INT,
    titleName VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    projectName VARCHAR(255) NOT NULL,
    textData TEXT,
    imgPath VARCHAR(255),
    svgPath VARCHAR(255),
    voicePath VARCHAR(255),

    PRIMARY KEY (id)
);

INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (1, '2023年秋季学期操作系统期末试题', '选择题，难度容易', '操作系统', '一、选择题（每小题 分）\r\n1.下列不属于操作系统目标的是（ ）\r\nA、方便性 B、有效性 C、可扩充性\r\nD、开放性 E、通用性', NULL, NULL, NULL);
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (2, '2023年秋季学期数据库原理及应用期末试题', '大题，常规题目', '数据库原理与应用', '', '/data/2/testdemo1.jpg', NULL, NULL);
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (3, '计算机视觉第二次作业', '程序题', '计算机视觉', '请分割识别下面的矢量图', NULL, '/data/3/1.svg', NULL);
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (4, '2023年秋季学期数据库课程期末试题', '基础题，较容易', '数据库原理与应用', '1.如何在命令窗口连接MySQL数据库（ ）  A、MySQL -p root -u   B、MySQL -u root -p   C、MySQL -u -p root   D、MySQL root -u -p', NULL, NULL, NULL);
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (5, '2023年秋季学期期末试卷', '2021年春季高等数学(下)期末试题', '高等数学(下)', '35.求y=(x+1)(x+2)²(x+3)³....（x+10）10在（0，+∞）内的导数', NULL, NULL, NULL);
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (6, '期末项目', '', '音乐赏析', '赏析以下音乐并写一篇分析报告', NULL, NULL, '/data/6/1.mp3');
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (7, '数据库课程期末试题', '2023年秋季学期期末试卷', '数据库原理与应用', ' 1.如何在命令窗口连接MySQL数据库（ ）  A、MySQL -p root -u   B、MySQL -u root -p   C、MySQL -u -p root   D、MySQL root -u -p', NULL, NULL, NULL);
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (8, '计算机网络期末试题', '本题为填空题，难度一般', '计算机网络', '24、C类网络地址包含24个网络ID位和8个主机ID位，C类网络实际最多可支持_______台计算机。', NULL, NULL, NULL);
INSERT INTO `title`(`id`, `titleName`, `description`, `projectName`, `textData`, `imgPath`, `svgPath`, `voicePath`) VALUES (9, '数据结构期末试题', '本题为算法简单的应用题', '数据结构 ', ' 3. 已知后缀表达式为”abcde+f*/-g*+h-”，其中所有运算符都是双目运算符。（10 分） (1) 画出等价的表达式二叉树； (2) 写出等价的、包含括号的中缀表达式； (3) 用表达式二叉树的树根的一个空栈 S 为实参，调用下面算法，写出算法执行后全 局变量 m 的值。', NULL, NULL, NULL);
INSERT INTO `user`(`id`, `username`, `password`) VALUES (123456, '测试用例', '123456');
