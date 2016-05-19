---
layout: post
title:  "[Jekyll] -  在windows平台的安装和使用!"
date:   2016-05-19 17:17:01 +0800
categories: jekyll
---

# 参照文档
<http://www.tuicool.com/articles/ruMVjyN>

<http://www.ruanyifeng.com/blog/2012/08/blogging_with_jekyll.html>

<http://blog.javachen.com/2013/08/31/my-jekyll-config.html>

# 安装步骤
首先需要说明的是网上的很多安装步骤现在都是不需要的，比如什么`DevKit`之类的，你可以选择直接忽略掉。

> 具体的安装和使用步骤如下：

*  安装ruby(含gem)
*  安装Jekyll
*  申请并且初始化Git Page
*  安装Git Client
*  配置SSH免登陆提交key
*  初始化并提交自己的网站


`ps: 以上步骤只要完成前面的4个其实已经可以通过git client来完成发布任务了`

## 安装Ruby
在windows平台安装ruby只要通过ruby installer进行安装即可。

[ruby installer下载](http://rubyinstaller.org/downloads/)

下载过来之后的安装文件就是一个普通的windows安装程序，直接双击进行安装，安装的过程中注意要勾选加入`path`的选项。

![安装ruby](http://cn.yizeng.me/assets/images/posts/2013-05-11-ruby-installer.png) 

完成安装之后，可以在CMD下运行一下ruby -version来查看是否安装完成。

## 安装Jekyll

安装完ruby，并且勾选了加入path的选项之后，默认已经安装了Gem，接下来我们就通过gem来安装jekyll.

> 之所以需要通过gem来安装jekyll，是因为jekyll被发布为ruby的一个组件，使用ruby编写的，而gem相当于ruby的安装管理工具，类似于centos的yum或者ubuntu的apt-get。

运行下面的命令来安装Jekyll
{% highlight shell %}
$gem install jekyll
{% endhighlight %}

网上有人说执行到这一步会出现网络异常，我没有遇到，可能是因为开了vpn的原因，如果存在问题的可以考虑用taobao的gem镜像进行安装，使用下面的命令进行gem镜像切换：
{% highlight shell %}
$ gem sources --add https://ruby.taobao.org/ --remove https://rubygems.org/
$ gem sources -l
{% endhighlight %}

安装完jekyll之后，在CMD运行一下jekyll -version来确保已经安装完成。
我安装的版本是`jekyll 3.1.4`


## 申请Github账号

这个步骤其实本来我是省掉的，因为在以前就已经申请并且开通了的，基本步骤就是去[github](https://github.com)注册账号，然后新建repostory并且开通git page服务。

遇到问题的可以参照下面的文档进行：
[申请Ｇｉｔｈｕｂ并开通Ｐａｇｅ服务](http://www.tuicool.com/articles/ruMVjyN)


## 安装Git Client

这个步骤其实对于会使用git的同志来讲基本没有任何压力，对于不会的也很简单，就是下载Git Client并且双击安装

[目前最新的Git Client](https://github-windows.s3.amazonaws.com/GitHubSetup.exe)

双击安装即可，安装完之后右键将会出现`open git gui`和`open git bash`选项，其中`open git bash`将会很常用。

## 初始化自己的博客环境

首先选定自己的工作目录，比如我选定的就是`D:/personal`

然后进入`D:/personal`(这里你改成自己的)，然后右键`open git bash`执行下面的命令：

{% highlight shell %}
$ git clone git@github.com:evergreen-tree/evergreen-tree.github.io.git
{% endhighlight %}

其中`evergreen-tree`替换成你自己在github创建的项目的名字。


------------
为Jekyll保留的小尾巴

Check out the [Jekyll docs][jekyll-docs] for more info on how to get the most out of Jekyll. File all bugs/feature requests at [Jekyll’s GitHub repo][jekyll-gh]. If you have questions, you can ask them on [Jekyll Talk][jekyll-talk].

[jekyll-docs]: http://jekyllrb.com/docs/home
[jekyll-gh]:   https://github.com/jekyll/jekyll
[jekyll-talk]: https://talk.jekyllrb.com/
