---
layout: post
title:  "[zookeeper] -  日常之zkCli试验!"
date:   2016-05-19 22:30:01 +0800
categories: zookeeper
---

> 使用zookeeper提供的zkCli脚本连接zookeeper的集群，简单尝试下zookeeper的强大功能

<!--more-->


上吧吐槽了好多zookeeper的反人类的地方，这次简单试用下，话说zookeeper的理念真的做的很棒：

首先你要参照个我的上个文章启动zookeeper集群，启动之后进入任意zookeeper的bin目录，运行以下命令

> zkCli.cmd -server 127.0.0.3:2181

后面的server的地址随你机器的地址而变化，接下来会进入一个shell，在shell中，可以进行简单的操作：

新建node，并写入数据（居然叫create，不明白为什么不直接使用mkdir跟echo或者vi，保持跟linux一致多好）：

> create /zk_test my_data

然后使用ls（跟linux一样奥）

> ls /

会得到下面的内容：

> [zookeeper, zk_test]

然后你可以读取它：

> get /zk_test

也可以删除或者更改：

> set /zk_test junk

> delete /zk_test

当然了最主要的，还是要切换到别的服务器看看是不是成功的分布式，

使用最开始的命令

> zkCli.cmd -server 127.0.0.2:2181

检查下，果然是有的。