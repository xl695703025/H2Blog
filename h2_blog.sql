/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : h2_blog

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-01 19:46:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `articleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `articleUserName` varchar(255) DEFAULT '1',
  `articleTitle` varchar(255) DEFAULT NULL,
  `articleContent` mediumtext,
  `articleParentCategoryId` int(10) DEFAULT NULL,
  `articleChildCategoryId` int(10) DEFAULT NULL,
  `articleTagIds` varchar(50) DEFAULT NULL,
  `articleViewCount` int(10) DEFAULT '0',
  `articleCommentCount` int(5) DEFAULT '0',
  `articleLikeCount` int(5) DEFAULT '0',
  `articlePostTime` datetime DEFAULT NULL,
  `articleUpdateTime` datetime DEFAULT NULL,
  `articleStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'yuxia', 'Java中的String,StringBuilder,StringBuffer三者的区别', '<p>请输入内容最近在学习Java的时候，遇到了这样一个问题，就是String,StringBuilder以及StringBuffer这三个类之间有什么区别呢，自己从网上搜索了一些资料，有所了解了之后在这里整理一下，便于大家观看，也便于加深自己学习过程中对这些知识点的记忆，如果哪里有误，恳请指正。</p><p>　　这三个类之间的区别主要是在两个方面，即运行速度和线程安全这两方面。</p><ol><li>首先说运行速度，或者说是执行速度，<strong style=\"color: rgb(194, 79, 74);\">在这方面运行速度快慢为：StringBuilder &gt; StringBuffer &gt; String</strong></li></ol><p><strong>　　<span style=\"color: rgb(194, 79, 74);\">String最慢的原因：<br></span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　String为字符串常量，而StringBuilder和StringBuffer均为字符串变量，即String对象一旦创建之后该对象是不可更改的，但后两者的对象是变量，是可以更改的。</strong>以下面一段代码为例：</p><pre>1 String str=\"abc\";\n2 System.out.println(str);\n3 str=str+\"de\";\n4 System.out.println(str);</pre><p>如果运行这段代码会发现先输出“abc”，然后又输出“abcde”，好像是str这个对象被更改了，其实，这只是一种假象罢了，JVM对于这几行代码是这样处理的，首先创建一个String对象str，并把“abc”赋值给str，然后在第三行中，其实JVM又创建了一个新的对象也名为str，然后再把原来的str的值和“de”加起来再赋值给新的str，而原来的str就会被JVM的垃圾回收机制（GC）给回收掉了，所以，str实际上并没有被更改，也就是前面说的String对象一旦创建之后就不可更改了。所以，Java中对String对象进行的操作实际上是一个不断创建新的对象并且将旧的对象回收的一个过程，所以执行速度很慢。</p><p>　　而StringBuilder和StringBuffer的对象是变量，对变量进行操作就是直接对该对象进行更改，而不进行创建和回收的操作，所以速度要比String快很多。</p><p>　　另外，有时候我们会这样对字符串进行赋值</p><pre>1 String str=\"abc\"+\"de\";\n2 StringBuilder stringBuilder=new StringBuilder().append(\"abc\").append(\"de\");\n3 System.out.println(str);\n4 System.out.println(stringBuilder.toString());</pre><p>这样输出结果也是“abcde”和“abcde”，但是String的速度却比StringBuilder的反应速度要快很多，这是因为第1行中的操作和</p><p>　　String str=\"abcde\";</p><p>　　是完全一样的，所以会很快，而如果写成下面这种形式</p><pre>1 String str1=\"abc\";\n2 String str2=\"de\";\n3 String str=str1+str2;</pre><p>那么JVM就会像上面说的那样，不断的创建、回收对象来进行这个操作了。速度就会很慢。</p><p>　　2. 再来说线程安全</p><p>　　<strong style=\"color: rgb(194, 79, 74);\">在线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的</strong></p><p><strong>　　</strong>如果一个StringBuffer对象在字符串缓冲区被多个线程使用时，StringBuffer中很多方法可以带有synchronized关键字，所以可以保证线程是安全的，但StringBuilder的方法则没有该关键字，所以不能保证线程安全，有可能会出现一些错误的操作。所以如果要进行的操作是多线程的，那么就要使用StringBuffer，但是在单线程的情况下，还是建议使用速度比较快的StringBuilder。</p><p>　　3. 总结一下<br><strong>　　<span style=\"color: rgb(194, 79, 74);\">String：适用于少量的字符串操作的情况</span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况</strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况</strong></p>', '1', '3', '7,18', '0', '0', '0', '2018-08-01 19:31:35', '2018-08-01 19:31:35', '1');
INSERT INTO `article` VALUES ('2', 'yuxia', 'Java中的String,StringBuilder,StringBuffer三者的区别', '<p>请输入内容最近在学习Java的时候，遇到了这样一个问题，就是String,StringBuilder以及StringBuffer这三个类之间有什么区别呢，自己从网上搜索了一些资料，有所了解了之后在这里整理一下，便于大家观看，也便于加深自己学习过程中对这些知识点的记忆，如果哪里有误，恳请指正。</p><p>　　这三个类之间的区别主要是在两个方面，即运行速度和线程安全这两方面。</p><ol><li>首先说运行速度，或者说是执行速度，<strong style=\"color: rgb(194, 79, 74);\">在这方面运行速度快慢为：StringBuilder &gt; StringBuffer &gt; String</strong></li></ol><p><strong>　　<span style=\"color: rgb(194, 79, 74);\">String最慢的原因：<br></span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　String为字符串常量，而StringBuilder和StringBuffer均为字符串变量，即String对象一旦创建之后该对象是不可更改的，但后两者的对象是变量，是可以更改的。</strong>以下面一段代码为例：</p><pre>1 String str=\"abc\";\n2 System.out.println(str);\n3 str=str+\"de\";\n4 System.out.println(str);</pre><p>如果运行这段代码会发现先输出“abc”，然后又输出“abcde”，好像是str这个对象被更改了，其实，这只是一种假象罢了，JVM对于这几行代码是这样处理的，首先创建一个String对象str，并把“abc”赋值给str，然后在第三行中，其实JVM又创建了一个新的对象也名为str，然后再把原来的str的值和“de”加起来再赋值给新的str，而原来的str就会被JVM的垃圾回收机制（GC）给回收掉了，所以，str实际上并没有被更改，也就是前面说的String对象一旦创建之后就不可更改了。所以，Java中对String对象进行的操作实际上是一个不断创建新的对象并且将旧的对象回收的一个过程，所以执行速度很慢。</p><p>　　而StringBuilder和StringBuffer的对象是变量，对变量进行操作就是直接对该对象进行更改，而不进行创建和回收的操作，所以速度要比String快很多。</p><p>　　另外，有时候我们会这样对字符串进行赋值</p><pre>1 String str=\"abc\"+\"de\";\n2 StringBuilder stringBuilder=new StringBuilder().append(\"abc\").append(\"de\");\n3 System.out.println(str);\n4 System.out.println(stringBuilder.toString());</pre><p>这样输出结果也是“abcde”和“abcde”，但是String的速度却比StringBuilder的反应速度要快很多，这是因为第1行中的操作和</p><p>　　String str=\"abcde\";</p><p>　　是完全一样的，所以会很快，而如果写成下面这种形式</p><pre>1 String str1=\"abc\";\n2 String str2=\"de\";\n3 String str=str1+str2;</pre><p>那么JVM就会像上面说的那样，不断的创建、回收对象来进行这个操作了。速度就会很慢。</p><p>　　2. 再来说线程安全</p><p>　　<strong style=\"color: rgb(194, 79, 74);\">在线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的</strong></p><p><strong>　　</strong>如果一个StringBuffer对象在字符串缓冲区被多个线程使用时，StringBuffer中很多方法可以带有synchronized关键字，所以可以保证线程是安全的，但StringBuilder的方法则没有该关键字，所以不能保证线程安全，有可能会出现一些错误的操作。所以如果要进行的操作是多线程的，那么就要使用StringBuffer，但是在单线程的情况下，还是建议使用速度比较快的StringBuilder。</p><p>　　3. 总结一下<br><strong>　　<span style=\"color: rgb(194, 79, 74);\">String：适用于少量的字符串操作的情况</span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况</strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况</strong></p>', '1', '3', '7', '12', '1', '1', '2018-07-11 07:44:28', '2018-08-01 10:48:21', '1');
INSERT INTO `article` VALUES ('3', 'yuxia', 'Java中的String,StringBuilder,StringBuffer三者的区别', '<p>请输入内容最近在学习Java的时候，遇到了这样一个问题，就是String,StringBuilder以及StringBuffer这三个类之间有什么区别呢，自己从网上搜索了一些资料，有所了解了之后在这里整理一下，便于大家观看，也便于加深自己学习过程中对这些知识点的记忆，如果哪里有误，恳请指正。</p><p>　　这三个类之间的区别主要是在两个方面，即运行速度和线程安全这两方面。</p><ol><li>首先说运行速度，或者说是执行速度，<strong style=\"color: rgb(194, 79, 74);\">在这方面运行速度快慢为：StringBuilder &gt; StringBuffer &gt; String</strong></li></ol><p><strong>　　<span style=\"color: rgb(194, 79, 74);\">String最慢的原因：<br></span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　String为字符串常量，而StringBuilder和StringBuffer均为字符串变量，即String对象一旦创建之后该对象是不可更改的，但后两者的对象是变量，是可以更改的。</strong>以下面一段代码为例：</p><pre>1 String str=\"abc\";\n2 System.out.println(str);\n3 str=str+\"de\";\n4 System.out.println(str);</pre><p>如果运行这段代码会发现先输出“abc”，然后又输出“abcde”，好像是str这个对象被更改了，其实，这只是一种假象罢了，JVM对于这几行代码是这样处理的，首先创建一个String对象str，并把“abc”赋值给str，然后在第三行中，其实JVM又创建了一个新的对象也名为str，然后再把原来的str的值和“de”加起来再赋值给新的str，而原来的str就会被JVM的垃圾回收机制（GC）给回收掉了，所以，str实际上并没有被更改，也就是前面说的String对象一旦创建之后就不可更改了。所以，Java中对String对象进行的操作实际上是一个不断创建新的对象并且将旧的对象回收的一个过程，所以执行速度很慢。</p><p>　　而StringBuilder和StringBuffer的对象是变量，对变量进行操作就是直接对该对象进行更改，而不进行创建和回收的操作，所以速度要比String快很多。</p><p>　　另外，有时候我们会这样对字符串进行赋值</p><pre>1 String str=\"abc\"+\"de\";\n2 StringBuilder stringBuilder=new StringBuilder().append(\"abc\").append(\"de\");\n3 System.out.println(str);\n4 System.out.println(stringBuilder.toString());</pre><p>这样输出结果也是“abcde”和“abcde”，但是String的速度却比StringBuilder的反应速度要快很多，这是因为第1行中的操作和</p><p>　　String str=\"abcde\";</p><p>　　是完全一样的，所以会很快，而如果写成下面这种形式</p><pre>1 String str1=\"abc\";\n2 String str2=\"de\";\n3 String str=str1+str2;</pre><p>那么JVM就会像上面说的那样，不断的创建、回收对象来进行这个操作了。速度就会很慢。</p><p>　　2. 再来说线程安全</p><p>　　<strong style=\"color: rgb(194, 79, 74);\">在线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的</strong></p><p><strong>　　</strong>如果一个StringBuffer对象在字符串缓冲区被多个线程使用时，StringBuffer中很多方法可以带有synchronized关键字，所以可以保证线程是安全的，但StringBuilder的方法则没有该关键字，所以不能保证线程安全，有可能会出现一些错误的操作。所以如果要进行的操作是多线程的，那么就要使用StringBuffer，但是在单线程的情况下，还是建议使用速度比较快的StringBuilder。</p><p>　　3. 总结一下<br><strong>　　<span style=\"color: rgb(194, 79, 74);\">String：适用于少量的字符串操作的情况</span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况</strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况</strong></p>', '1', '3', '7', '12', '1', '1', '2018-07-11 07:44:28', '2018-08-01 10:48:23', '1');
INSERT INTO `article` VALUES ('4', 'yuxia', 'Java中的String,StringBuilder,StringBuffer三者的区别', '<p>请输入内容最近在学习Java的时候，遇到了这样一个问题，就是String,StringBuilder以及StringBuffer这三个类之间有什么区别呢，自己从网上搜索了一些资料，有所了解了之后在这里整理一下，便于大家观看，也便于加深自己学习过程中对这些知识点的记忆，如果哪里有误，恳请指正。</p><p>　　这三个类之间的区别主要是在两个方面，即运行速度和线程安全这两方面。</p><ol><li>首先说运行速度，或者说是执行速度，<strong style=\"color: rgb(194, 79, 74);\">在这方面运行速度快慢为：StringBuilder &gt; StringBuffer &gt; String</strong></li></ol><p><strong>　　<span style=\"color: rgb(194, 79, 74);\">String最慢的原因：<br></span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　String为字符串常量，而StringBuilder和StringBuffer均为字符串变量，即String对象一旦创建之后该对象是不可更改的，但后两者的对象是变量，是可以更改的。</strong>以下面一段代码为例：</p><pre>1 String str=\"abc\";\n2 System.out.println(str);\n3 str=str+\"de\";\n4 System.out.println(str);</pre><p>如果运行这段代码会发现先输出“abc”，然后又输出“abcde”，好像是str这个对象被更改了，其实，这只是一种假象罢了，JVM对于这几行代码是这样处理的，首先创建一个String对象str，并把“abc”赋值给str，然后在第三行中，其实JVM又创建了一个新的对象也名为str，然后再把原来的str的值和“de”加起来再赋值给新的str，而原来的str就会被JVM的垃圾回收机制（GC）给回收掉了，所以，str实际上并没有被更改，也就是前面说的String对象一旦创建之后就不可更改了。所以，Java中对String对象进行的操作实际上是一个不断创建新的对象并且将旧的对象回收的一个过程，所以执行速度很慢。</p><p>　　而StringBuilder和StringBuffer的对象是变量，对变量进行操作就是直接对该对象进行更改，而不进行创建和回收的操作，所以速度要比String快很多。</p><p>　　另外，有时候我们会这样对字符串进行赋值</p><pre>1 String str=\"abc\"+\"de\";\n2 StringBuilder stringBuilder=new StringBuilder().append(\"abc\").append(\"de\");\n3 System.out.println(str);\n4 System.out.println(stringBuilder.toString());</pre><p>这样输出结果也是“abcde”和“abcde”，但是String的速度却比StringBuilder的反应速度要快很多，这是因为第1行中的操作和</p><p>　　String str=\"abcde\";</p><p>　　是完全一样的，所以会很快，而如果写成下面这种形式</p><pre>1 String str1=\"abc\";\n2 String str2=\"de\";\n3 String str=str1+str2;</pre><p>那么JVM就会像上面说的那样，不断的创建、回收对象来进行这个操作了。速度就会很慢。</p><p>　　2. 再来说线程安全</p><p>　　<strong style=\"color: rgb(194, 79, 74);\">在线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的</strong></p><p><strong>　　</strong>如果一个StringBuffer对象在字符串缓冲区被多个线程使用时，StringBuffer中很多方法可以带有synchronized关键字，所以可以保证线程是安全的，但StringBuilder的方法则没有该关键字，所以不能保证线程安全，有可能会出现一些错误的操作。所以如果要进行的操作是多线程的，那么就要使用StringBuffer，但是在单线程的情况下，还是建议使用速度比较快的StringBuilder。</p><p>　　3. 总结一下<br><strong>　　<span style=\"color: rgb(194, 79, 74);\">String：适用于少量的字符串操作的情况</span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况</strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况</strong></p>', '1', '3', '7', '12', '1', '1', '2018-07-11 07:44:28', '2018-08-07 10:48:02', '1');
INSERT INTO `article` VALUES ('5', 'yuxia', '123', '内容摘要5<img src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533052060757&di=c92955aadaba3cb9620f74c172837750&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F32fa828ba61ea8d329499969910a304e251f5870.jpg\" />', '1', '3', '1', '2', '5', '1', '2018-07-05 10:54:15', '2018-07-05 10:54:16', '1');
INSERT INTO `article` VALUES ('6', 'yuxia', '测试标题', '<h1><em>测试ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss内sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss容</em></h1><p><img style=\"max-width: 100%;\" src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533052060757&di=c92955aadaba3cb9620f74c172837750&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F32fa828ba61ea8d329499969910a304e251f5870.jpg\"></p><p><br></p>', '1', '3', '2,3', '0', '0', '1', '2018-07-09 19:04:10', '2018-07-25 20:23:52', '1');
INSERT INTO `article` VALUES ('7', 'yuxia', 'Java中的String,StringBuilder,StringBuffer三者的区别', '最近在学习Java的时候，遇到了这样一个问题，就是String,StringBuilder以及StringBuffer这三个类之间有什么区别呢，自己从网上搜索了一些资料，有所了解了之后在这里整理一下，便于大家观看，也便于加深自己学习过程中对这些知识点的记忆，如果哪里有误，恳请指正。</p><p>　　这三个类之间的区别主要是在两个方面，即运行速度和线程安全这两方面。</p><ol><li>首先说运行速度，或者说是执行速度，<strong style=\"color: rgb(194, 79, 74);\">在这方面运行速度快慢为：StringBuilder &gt; StringBuffer &gt; String</strong></li></ol><p><strong>　　<span style=\"color: rgb(194, 79, 74);\">String最慢的原因：<br></span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　String为字符串常量，而StringBuilder和StringBuffer均为字符串变量，即String对象一旦创建之后该对象是不可更改的，但后两者的对象是变量，是可以更改的。</strong>以下面一段代码为例：</p><pre>1 String str=\"abc\";\n2 System.out.println(str);\n3 str=str+\"de\";\n4 System.out.println(str);</pre><p>如果运行这段代码会发现先输出“abc”，然后又输出“abcde”，好像是str这个对象被更改了，其实，这只是一种假象罢了，JVM对于这几行代码是这样处理的，首先创建一个String对象str，并把“abc”赋值给str，然后在第三行中，其实JVM又创建了一个新的对象也名为str，然后再把原来的str的值和“de”加起来再赋值给新的str，而原来的str就会被JVM的垃圾回收机制（GC）给回收掉了，所以，str实际上并没有被更改，也就是前面说的String对象一旦创建之后就不可更改了。所以，Java中对String对象进行的操作实际上是一个不断创建新的对象并且将旧的对象回收的一个过程，所以执行速度很慢。</p><p>　　而StringBuilder和StringBuffer的对象是变量，对变量进行操作就是直接对该对象进行更改，而不进行创建和回收的操作，所以速度要比String快很多。</p><p>　　另外，有时候我们会这样对字符串进行赋值</p><pre>1 String str=\"abc\"+\"de\";\n2 StringBuilder stringBuilder=new StringBuilder().append(\"abc\").append(\"de\");\n3 System.out.println(str);\n4 System.out.println(stringBuilder.toString());</pre><p>这样输出结果也是“abcde”和“abcde”，但是String的速度却比StringBuilder的反应速度要快很多，这是因为第1行中的操作和</p><p>　　String str=\"abcde\";</p><p>　　是完全一样的，所以会很快，而如果写成下面这种形式</p><pre>1 String str1=\"abc\";\n2 String str2=\"de\";\n3 String str=str1+str2;</pre><p>那么JVM就会像上面说的那样，不断的创建、回收对象来进行这个操作了。速度就会很慢。</p><p>　　2. 再来说线程安全</p><p>　　<strong style=\"color: rgb(194, 79, 74);\">在线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的</strong></p><p><strong>　　</strong>如果一个StringBuffer对象在字符串缓冲区被多个线程使用时，StringBuffer中很多方法可以带有synchronized关键字，所以可以保证线程是安全的，但StringBuilder的方法则没有该关键字，所以不能保证线程安全，有可能会出现一些错误的操作。所以如果要进行的操作是多线程的，那么就要使用StringBuffer，但是在单线程的情况下，还是建议使用速度比较快的StringBuilder。</p><p>　　3. 总结一下<br><strong>　　<span style=\"color: rgb(194, 79, 74);\">String：适用于少量的字符串操作的情况</span></strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况</strong></p><p><strong style=\"color: rgb(194, 79, 74);\">　　StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况</strong></p>', '1', '3', '7', '11', '2', '1', '2018-07-11 07:44:28', '2018-07-11 20:22:15', '1');
INSERT INTO `article` VALUES ('9', 'yuxia', '测试一下', '<p><span style=\"color: rgb(0, 0, 0);\">1.web服务器负载均衡简介web服务器负载均衡是指将多台可用单节点服务器组合成web服务器集群，然后通过负载均衡器将客户端请求均匀的转发到不同的单节点web服务器上，从而增加整个web服务器集群的</span></p><ul><p><span style=\"color: rgb(0, 0, 0);\">1、为什么选Linux？</span></p><p><span style=\"color: rgb(0, 0, 0);\">程序用PHP，速度快，配置低（windows必选1G的内存Linux选512MB能同样达到要求）。Linux的系统安全性非常高。Linux服务器的维护与扩展到性价比和性能都高于Windows。</span></p><p><span style=\"color: rgb(0, 0, 0);\">1) 最流行的服务器端操作系统，强大的安全性和稳定性</span></p><p><span style=\"color: rgb(0, 0, 0);\">2) 免费且开源，轻松建立和编译源代码</span></p><p><span style=\"color: rgb(0, 0, 0);\">3) 通过SSH方式远程访问您的云服务器</span></p><p><span style=\"color: rgb(0, 0, 0);\">4) 一般用于高性能web等服务器应用，支持常见的PHP/Python等编程语言，支持MySQL等数据库（需自行安装)</span></p></ul><p><img src=\"http://localhost:8080/H2Blog/statics/uploadImg/1533092140421.jpg\" style=\"max-width: 100%;\"></p>', '1', '2', '15,16', '2', '1', '1', '2018-08-01 10:51:17', null, '1');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categoryId` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `categoryUserName` varchar(255) DEFAULT NULL,
  `categoryPid` int(5) DEFAULT NULL,
  `categoryName` varchar(50) DEFAULT NULL,
  `categoryDescription` varchar(255) DEFAULT NULL,
  `categoryStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'yuxia', '0', '技术', '123123', '1');
INSERT INTO `category` VALUES ('2', 'yuxia', '1', 'java', '123213', '1');
INSERT INTO `category` VALUES ('3', 'yuxia', '1', 'c++', '123213', '1');
INSERT INTO `category` VALUES ('180', 'yuxia', '0', '小说', '213123', '1');
INSERT INTO `category` VALUES ('181', 'yuxia', '180', '玄幻', '123123', '1');
INSERT INTO `category` VALUES ('182', 'yuxia', '180', '神魔', '123123', '1');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `commentPid` int(10) unsigned DEFAULT '0',
  `commentPname` varchar(255) DEFAULT NULL,
  `commentArticleId` int(10) unsigned DEFAULT NULL,
  `commentAuthorName` varchar(50) DEFAULT NULL,
  `commentAuthorEmail` varchar(50) DEFAULT NULL,
  `commentContent` varchar(1000) DEFAULT NULL,
  `commentIp` varchar(50) DEFAULT NULL,
  `commentCreateTime` datetime DEFAULT NULL,
  `commentStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '111', '1', 'lalala', '123@qq.com', '还不错的文章', '192.168.0.111', '2018-06-08 14:18:55', '1');
INSERT INTO `comment` VALUES ('2', '1', '111', '1', 'lalala', '123@qq.com', '还不错的文章', '192.168.0.111', '2018-06-08 14:18:55', '1');
INSERT INTO `comment` VALUES ('4', '1', '111', '1', 'lalala', '123@qq.com', '还不错的文章', '192.168.0.111', '2018-06-08 14:18:55', '1');
INSERT INTO `comment` VALUES ('112', '0', null, '1', '雨夏', '123@qq.com', '测试一下哈~！', '0:0:0:0:0:0:0:1', '2018-08-01 14:10:41', '1');
INSERT INTO `comment` VALUES ('113', '0', null, '1', '雨夏', '123@qq.com', '测试一下哈~！', '0:0:0:0:0:0:0:1', '2018-08-01 14:11:34', '1');
INSERT INTO `comment` VALUES ('114', '0', null, '7', '雨夏', '123@qq.com', '哈？\n', '0:0:0:0:0:0:0:1', '2018-08-01 15:12:13', '1');
INSERT INTO `comment` VALUES ('115', '0', null, '9', '雨夏', '123@qq.com', '啦啦啦~！', '0:0:0:0:0:0:0:1', '2018-08-01 15:22:22', '1');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagId` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `tagUserName` varchar(255) DEFAULT NULL,
  `tagName` varchar(20) DEFAULT NULL,
  `tagDescription` varchar(255) DEFAULT NULL,
  `tagStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'yuxia', '科技', '很不错的东西', '1');
INSERT INTO `tag` VALUES ('2', 'yuxia', '科', '很不错的东西', '1');
INSERT INTO `tag` VALUES ('3', 'yuxia', 'ss', 'ss', '1');
INSERT INTO `tag` VALUES ('7', 'yuxia', 's', 's', '1');
INSERT INTO `tag` VALUES ('15', 'yuxia', '哈', '', '1');
INSERT INTO `tag` VALUES ('16', 'yuxia', '啦啦啦', '', '1');
INSERT INTO `tag` VALUES ('17', 'yuxia', 'sssss', '', '1');
INSERT INTO `tag` VALUES ('18', 'yuxia', 'aaaa', '', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL DEFAULT '',
  `userPassword` varchar(255) NOT NULL DEFAULT '',
  `userNickName` varchar(255) NOT NULL DEFAULT '',
  `userEmail` varchar(100) DEFAULT '',
  `userAvatar` varchar(255) DEFAULT NULL,
  `userRegisterTime` datetime DEFAULT NULL,
  `userLastLoginTime` datetime DEFAULT NULL,
  `userStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('42', '123qwe', '123123', 'qwe', '12@qq.com', 'statics\\images\\1530754799004avatar.jpg', '2018-07-05 09:26:22', '2018-07-05 09:43:03', '1');
INSERT INTO `user` VALUES ('43', 'yuxia', '123123', '雨夏', '123@qq.com', 'statics\\images\\1530755128945avatar.jpg', '2018-07-05 09:37:53', '2018-07-07 16:41:19', '1');
INSERT INTO `user` VALUES ('44', 'lixuan', 'li8618121', 'lixuan', '410199246@qq.com', 'statics\\images\\1530793678329avatar.jpg', '2018-07-05 20:24:38', '2018-07-05 20:37:13', '1');
