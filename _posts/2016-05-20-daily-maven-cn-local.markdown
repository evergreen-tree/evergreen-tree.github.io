---
layout: post
title:  "[技术每日说] -  吐槽下墙内的maven的故事!"
date:   2016-05-12 10:30:01 +0800
categories: Daily
---

> 今天帮一个不怎么会技术的同事搭建一个本地环境，因为公司内部有个nexus是从08年搭建过来的，所以各种类包都比较齐全，但是很多apache的公开库，由于这几年的发展都纷纷改头换面，改变了自己的groupId，结果导致直接连接外部的repostry的各种意外。

<!--more-->

>想要使用maven一些基本的概念还是需要懂的，比如最基本的`dependency`，`lifecyle`，以及一些常用的命令，如，`mvn clean packge`,`mvn clean install`,`mvn dependency:tree`

## Maven槽点之向后兼容

maven是个蛮好的库从10年开始一直都在使用maven帮我管理各种依赖，除了极度膨胀的本地库（动辄会出现几个G）以及偶尔发生的依赖冲突，用起来极是方便。

然后随着maven的发展，不知道基于什么样的考虑，maven开始不再向后兼容。

最开始的时候是Maven2跟Maven3的冲突，对于依赖的处理开始出现歧义，有时候用M2编译好好的项目，跑到M3各种依赖问题，然后是各种plugin的冲突升级，不知道在这个问题前面已经崩溃掉了多少程序员，这个问题在很多老牌的公司里面尤为突出，比如，我们上线的jekins的编译环境使用的是maven2，而现在maven2已经停止支持了，下载都只能在某些相对怀旧的网站上搞到。所以经常会出现各种莫名其妙的编译问题，直接导致现在基本上移除了各种定制化的plugin，让自己的项目完全符合maven2跟maven3的要求，不知道这个到底是幸运还是不幸。

然后今天的时候发觉M3自己内部居然开始冲突了，具体的没有仔细测试，但是3.0跟3.3居然不兼容，用3.0编译好好的项目，跑到3.3各种无法读取依赖。。。而且对于mirror的解析规则也发生了变化，终于在跟3.3奋斗了半天之后，直接切回到了3.0.

再就是一个然后激情满满的槽点，如果没有记错，maven的dependency的groupId或者artifactId如果发生了变化，会在一个pom文件中做一个跳转的标记，然后maven自然就能再次找到这个依赖，今天，在切到3.3之后，这个功能神奇的消失了。。。

## Maven的一些有用的小知识

[使用BOM（Bill Of Materials）来有效的管理你的依赖](http://howtodoinjava.com/maven/maven-bom-bill-of-materials-dependency/)
[使用Module来分治你的项目](https://maven.apache.org/guides/mini/guide-multiple-modules.html)
[使用properties来规范化你的项目](http://books.sonatype.com/mvnref-book/reference/resource-filtering-sect-properties.html)

## TIPS

使用系统内置的属性：
{% highlight xml %}
<dependencies>
    <dependency>
        <groupId>${project.groupId}</groupId>
        <artifactId>sibling-project</artifactId>
        <version>${project.version}</version>
    </dependency>
</dependencies>
{% endhighlight %}


{% highlight xml %}
<build>
    <finalName>${project.artifactId}</finalName>
</build>
{% endhighlight %}

通过引入spring的Bom文件来快速统一依赖的spring的版本
{% highlight xml %}
<!-- http://mvnrepository.com/artifact/org.springframework/spring-framework-bom -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-framework-bom</artifactId>
    <version>4.2.5.RELEASE</version>
    <type>pom</type>
</dependency>
{% endhighlight %}

spring IO的bom
{% highlight xml %}
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.spring.platform</groupId>
            <artifactId>platform-bom</artifactId>
            <version>2.0.4.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
{% endhighlight %}
