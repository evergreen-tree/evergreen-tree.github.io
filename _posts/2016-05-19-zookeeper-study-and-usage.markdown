---
layout: post
title:  "[zookeeper] - 部署和使用"
date:   2016-05-19 17:17:01 +0800
categories: zookeeper research
---

> 前段时间遇到不少分布式的锁，分布式的数据处理的问题，针对问题使用数据库跟一系列的编程方法也得到了解决，所以抓紧时间研究下zookeeper，以期望能开发出来更加简单的编程模型。

<!-- more -->

## 缘起

作为一个架构师，首先需要解决问题的能力，其次是解决问题之后不断的提出更好的解决方案的能力，本着不自满的想法，研究下zookeeper：

首先，从百度找了点儿文档看，看的非常迷糊，话说研究新东西还是要从官网入手，耐着性子读完这个文章，收获不少。

(http://zookeeper.apache.org/doc/r3.4.6/zookeeperStarted.html)

首先，我认识到，zookeeper的一个很重要的思想是一个虚拟的文件系统，应该是所有的node都共享了一个类似于NAS的文件系统（当然这里是软件机制），我这里理解为每次我把自己的机器连接到某个zookeeper的集群，那么我就在该集群里面跟别人共享了一个相同的硬盘，我可以向该硬盘写入我的文件，然后集群的机制保证我写入的文件的一致性和唯一性，很棒的思路，我的Rainy可以在某种程度上借鉴。

#### 然后第一个【小坑】：

zookeeper，如果仅仅是为了玩儿一下，你可以快速的启动起来server，不需要做任何的配置，直接zkServer.sh start即可。

但是如果要想运行集群的话，首先你要准备不少于3个zookeeper的instance（注意这里是instance，而不是机器，所以可以在一台机器上虚拟，等下我会分享下怎么通过hosts来虚拟）。为什么是3个？我还没有仔细研究，但是应该是有某个机制需要不少于3台的服务器，也许是server/leader/client的这种方案决定的也许是投票机制，这个找时间再去研究，今天先研究应用，所以如果你要玩儿，准备不少于三个的instance即可。

#### 【试玩】

在开始编码和使用之前，首先建议通过zkCli进入client来体验下zk到底是啥，在zkCli里面，你可以很明确的感觉出来，zk的文件系统的概念，如果大家做过cms，那么对于node概念一定不会陌生，看起来zk的元素但是就是node了。

#### 【windows系统的第二个坑】

官方的教程都是关于linux的，所以使用windows玩儿的时候还是遇到个小坑，在windows系统的时候如果你定义的dataDir的parent folder不存在话，zookeeper会爆exception而不是自动建立文件夹，而且打印出来的exception的提示过于短小以至于开始的时候我忽略了。。。然后直接从源码开始debug，最终发现是这个问题，当然实际上有提示的，这里需要专门说下的就是要么选用mingw或者cygwin来运行zookeeper，要么为zookeeper把所有的文件路径新建好。为了避免接下来更多的坑，我决定投降，使用mingw来启动zookeeper：

#### 然后狗血的历程开始了。。。。。。。

第一个，“错误: 找不到或无法加载主类 org.apache.zookeeper.server.quorum.QuorumPeerMain”

怎么会找不到类？

仔细定位下明白了，妹的我装的windows的java，mingw调用java也只能调用windows的，然后脚本里面的classpath的分隔符冒号，而windows系统是分号，满满的大坑，切回cmd脚本，老老实实的创建文件夹：

接下来的另一个坑出现了，这个在一定程度上怪我，没有仔细的阅读文档，错误信息提示如下：

{% highlight java %}
Caused by: java.lang.IllegalArgumentException: serverid null is not a number

        at org.apache.zookeeper.server.quorum.QuorumPeerConfig.parseProperties(Q

uorumPeerConfig.java:355)

        at org.apache.zookeeper.server.quorum.QuorumPeerConfig.parse(QuorumPeerC

onfig.java:119)

        ... 2 more
{% endhighlight %}


`Google`之，这个是`myid`这个鬼，`zookeeper`居然需要自己的工作目录下预制ID，感觉一下子收到了成吨的暴击，卧槽，特么你可是一个应用这么广泛的开源软件啊，居然需要两个配置，还要对应起来？太操蛋了。没办法了，自己去新建myid，然后每个id跟配置文件中配置的server列表对应起来：

好吧，现在可以运行起来了，然后你可以发觉启动第一个服务器的时候会每隔一定时间自动轮询另外的机器是否可以连接，启动第二台之后会在第二台上轮询，貌似第一台已经停止轮询了（这个没有仔细观察），第三个启动之后三台打完交互日志就停止了，然后可能会有一些ACK的握手请求过来，不过我们已经可以忽略他们了。

好吧，最后贴一下配置：

我是在一台机器上运行的三台服务器，然而我想用相同的端口，所以用到了hosts文件来虚拟一个dns，hosts文件如下：

{% highlight shell %}
127.0.0.2		zk.server1.lo
127.0.0.3		zk.server2.lo
127.0.0.4		zk.server3.lo
{% endhighlight %}


接下来依次是三台机器的配置：

{% highlight shell %}
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/tmp/zookeeper1
dataLogDir=/tmp/zookeeper1log/
clientPortAddress=zk.server1.lo
clientPort=2181
maxClientCnxns=60
server.1=zk.server1.lo:2182:2183
server.2=zk.server2.lo:2182:2183
server.3=zk.server3.lo:2182:2183
{% endhighlight %}


{% highlight shell %}
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/tmp/zookeeper2
dataLogDir=/tmp/zookeeper2log/
clientPortAddress=zk.server2.lo
clientPort=2181
maxClientCnxns=60
server.1=zk.server1.lo:2182:2183
server.2=zk.server2.lo:2182:2183
server.3=zk.server3.lo:2182:2183
{% endhighlight %}


{% highlight shell %}
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/tmp/zookeeper3
dataLogDir=/tmp/zookeeper3log/
clientPortAddress=zk.server3.lo
clientPort=2181
maxClientCnxns=60
server.1=zk.server1.lo:2182:2183
server.2=zk.server2.lo:2182:2183
server.3=zk.server3.lo:2182:2183
{% endhighlight %}

然后是到你的zookeeper放置的盘符的根目录下新建`/tmp/zookeeper{num}`跟`/tmp/zookeeper{num}log`

然后分别在`/tmp/zookeeper{num}/`下面新建`myid`文件，并且在文件里面写入相应的`num`。
