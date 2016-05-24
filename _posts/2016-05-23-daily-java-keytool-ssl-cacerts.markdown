---
layout: post
title:  "[技术每日说] -  Java对于SSL的支持以及证书的安装!"
date:   2016-05-20 10:30:01 +0800
categories: Daily java ssl
---

> 随着最近行业内部对于安全的要求的提高，公司的各条业务线都纷纷切成SSL，虽然SSL未必说一定安全，但是最起码可以在一定程度上保证用户免于网络监听的烦恼。在切换的过程中遇到过很多问题，比如证书，签名以及升级之后跟各个客户之间的联调升级等。通过仔细的研究SSL不得不说，SSL还真是安全。

<!--more-->

> 首先请允许我对数学致敬：(g^b%p)^a%p=(g^a%p)^b%p，当年创造这种算式的人是天才，无可比拟的天才。这篇文章在一定程度上解释明白了秘钥交换算法：<http://my.oschina.net/u/1382972/blog/330456>




> 什么叫做cacerts，certified authority certificates的缩写。就是存证书的地方

Transport Layer Security (TLS) 1.0 / Secure Sockets Layer (SSL) 3.0是我们的web体系中用来保护Client（浏览器）到服务器的交互的安全性的。使用了这两种协议的交互方式叫做HTTPS

http://idiotechie.com/understanding-transport-layer-security-secure-socket-layer/

从网上摘抄来的一个介绍SSL流程的说明：

## SSL协议的工作流程：

#### 服务器认证阶段：

`SSL最核心的两点就是交换秘钥以及使用HASH算法进行的数字签名验证`

- 1）客户端向服务器发送一个开始信息“Hello”以便开始一个新的会话连接；
- 2）服务器根据客户的信息确定是否需要生成新的主密钥，如需要则服务器在响应客户的“Hello”信息时将包含生成主密钥所需的信息；
- 3）客户根据收到的服务器响应信息，产生一个主密钥，并用服务器的公开密钥加密后传给服务器；
- 4）服务器恢复该主密钥，并返回给客户一个用主密钥认证的信息，以此让客户认证服务器。

#### 用户认证阶段：

- 在此之前，服务器已经通过了客户认证，这一阶段主要完成对客户的认证。经认证的服务器发送一个提问给客户，客户则返回（数字）签名后的提问和其公开密钥，从而向服务器提供认证。

> 通过上面的介绍可以看出来，使用SSL进行的网络通讯，虽然内容是基于TCP和HTTP的，但是因为内容是加密的，而且，每个客户端向服务器发送的秘钥是不同的，从而可以保证传输的安全性。

## TSL协议的工作流程

协议由两层组成： TLS 记录协议（TLS Record）和 TLS 握手协议（TLS Handshake）。



在SSL中会使用密钥交换算法`交换密钥`；使用密钥对数据进行加密；使用散列算法对数据的`完整性进行验证`，使用`数字证书`证明自己的身份。好了，下面开始介绍SSL协议。

好吧，以上的部分，对于非专业人员来讲，只有以票友身份进行参与的份儿，但是刚才标亮的关键字也说明了SSL或者TLS（高级的SSL）主要解决的就是交换秘钥，对数据完整性负责和保证数据传输的过程中不被破坏。

## Java对于SSL的支持

上面说到了需要交换秘钥，需要数字证书验明正身，但是不敢什么样的秘钥，首先第一次传输的时候都需要服务器把相关的秘钥传输给客户端，基本应该包含DES的秘钥跟RSA的公钥，那么不管是DES的秘钥还是RSA的公钥，对于服务器来说都是公用的，而且对于一台服务器来说可能需要支持多组这样的私钥跟公钥，所以服务器以证书的形式保存一组秘钥跟验证信息，所以就存在一个存储跟使用的问题。

`keystore` - JVM用来存放证书的库文件。

`KeyTool` - JVM用来管理证书的工具。

因为证书本来就是需要被保护，所以为了方便Java提供了工具供我们简单使用。


keystore的生成 

```shell
keytool -genkey -dname "cn=Mark Jones, ou=JavaSoft, o=Sun, c=US" -keyalg RSA -alias business -keypass kpi135 -keystore C:\working\mykeystore  -storepass ab987c -validity 180
```

参数说明： 

-genkey表示要创建一个新的密钥 

-dname表示密钥的Distinguished Names， 

        CN=commonName 

        OU=organizationUnit 

        O=organizationName 

        L=localityName 

        S=stateName 

        C=country 

Distinguished Names表明了密钥的发行者身份 

-keyalg使用加密的算法，这里是RSA 

-alias密钥的别名 

-keypass私有密钥的密码，这里设置为kpi135 

-keystore 密钥保存在C：\working目录下的mykeystore文件中 

-storepass 存取密码，这里设置为ab987c，这个密码提供系统从mykeystore文件中将信息取出 

-validity该密钥的有效期为 180天 

### cacerts证书文件(The cacerts Certificates File) 

改证书文件存在于java.home\lib\security目录下，是Java系统的CA证书仓库 

CA证书的导入（Importing Certificates） 

```shell
keytool -import -alias joe -file jcertfile.cer
```

这个命令将证书文件jcertfile.cer中别名为joe的证书导入系统的受信任证书列表中 
通常该命令用以导入来自CA中心的证书（Importing a Certificate for the CA） 

### 导入被CA中心授权的证书（Importing the Certificate Reply from the CA） 

```shell
keytool -import -trustcacerts -file VSMarkJ.cer
```

### 证书的导出（Exporting Certificates） 

```shell
keytool -export -alias jane -file janecertfile.cer
```


# 如何在HTTPClient中使用证书

大家在编写java的web程序的时候经常会遇到没有安装证书的问题，解决方案不外乎两种：

1. 安装证书
	1. 参照<http://blog.sina.com.cn/s/blog_56d8ea9001017uo4.html>即可。
	2. 核心思想在于通过浏览器的功能导出证书到本地，然后通过上面的导入被CA中心授权的证书命令，导入证书接口即可。
	
2. 选择信任服务器的证书。
	1. Google 搜索`httpclient信任证书`即可.
	2. 简单解释下为什么需要信任证书，从上面的介绍可以看到整个SSL的协议要求必须要实现证书的下载验证这些流程，而Java出于安全考虑肯定不能像浏览器那样自动安装证书的（要知道Java可是运行在服务器端的），所以需要手工安装证书来决定自己是否要信任某个网络连接的安全性。基本而言，httpclient会在连接的步骤中参照SSL的标准协议进行证书的校验，所以网上给出的方案也都是自己编写校验证书的逻辑，然后不做任何校验。


## 参考文档

<http://hengstart.iteye.com/blog/840561>

<http://kb.cnblogs.com/page/162080/>


















