---
layout: post
title:  "[Jekyll] -  使用[多说]作为评论的plugin!"
date:   2016-05-19 17:17:01 +0800
categories: jekyll
---

> 安装完Jekyll之后，选择了几个主题，偶然之间发现了一个带评论插件的主题感觉样式之类的还蛮好，但是那个插件需要使用Google，facebook之类的账号进行登录，所以切成多说

<!--more-->

# 参照文档
<http://www.ituring.com.cn/article/114888>

[多说官网](http://duoshuo.com/)

# 安装步骤

想要安装多说插件到Jekyll，首先要大体上对自己所使用的theme的结构有所了解。

> 具体的安装和使用步骤如下：

*  注册[多说]账号
*  生成需要安装的代码
*  代码安装到post.htm
*  提交自己的网站

## 申请[多说]账号

直接进入[多说]官网进行注册即可。

注册完成之后会进入后台，点击工具，可以拷贝代码。


## 集成代码

我所集成的代码如下：

{% highlight html %}
<!-- 多说评论框 start -->
	<div class="ds-thread" data-thread-key="请将此处替换成文章在你的站点中的ID" data-title="请替换成文章的标题" data-url="请替换成文章的网址"></div>
<!-- 多说评论框 end -->
<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
<script type="text/javascript">
var duoshuoQuery = {short_name:"evergreen"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0] 
		 || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
	</script>
<!-- 多说公共JS代码 end -->
{% endhighlight %}

记得将上面的中文部分替换为下面的：
{% highlight html %}
<div class="ds-thread" data-thread-key="{{page.url}}" data-title="{{ page.title }}" data-url="{{ page.url }}"></div>
{% endhighlight %}

## 提交代码

依次执行下面的命令进行编译提交：





[多说]: http://duoshuo.com/
[jekyll-gh]:   https://github.com/jekyll/jekyll
[jekyll-talk]: https://talk.jekyllrb.com/
