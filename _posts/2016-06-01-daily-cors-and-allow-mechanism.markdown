---
layout: post
title:  "[技术每日说] - CORS与Cross-Domain(跨域)问题的分析和解决方案!"
date:   2016-05-25 10:57:01 +0800
categories: daily java cors
---

> 首先，概念性的东西一定要搞清楚，不然会害死人 ，就好比CORS的问题被大家统一称为了跨域问题，已经不知道第一个把CORS所规定的浏览器的同源策略解释成为跨域的问题了，说这个叫做请求的跨域是对的，但是这个不是跨域的问题，而是一个为了保护站点，防止站外访问而提出的规则，简单的可以理解为为了防止盗链而存在的一个标准。而我们平常所说的跨域呢叫做Cross Domain，是跟XSS攻击绑定的概念，这里一定要区分开
> 
>

<!--more-->

# 什么叫做CORS

 Cross-origin resource sharing（CORS）：从名字也可以看出来，叫做`非同源资源分享`策略，就是一套让浏览器实现的，非同源资源的访问策略，目的是为了保护网站自己的资源的,就是说你可以对自己的资源进行标记，告诉浏览器哪些是你某个网站可以访问的，哪些是某个(些)网站所不能访问的，从而达到保护自己的资源的目的。

怎么达到保护的呢？

首先这个是一个白名单的机制，就是说网站可以自己决定自己的资源可以分享给谁，既然是白名单，那么也就是说如果我在自己的网站里面嵌入了别的网站的css，scritpt或者图片，那么你需要跟对方的网站申请权限才行，除非对方已经把自己的资源标记为谁都可以用的了。

那位可能说了，我的网站从来就没有允许过别人访问我的静态资源啊，为啥还经常被盗链呢，这个就是个标准跟实现的问题了，所谓理想很丰满，显示很骨感即源于此，因为这个标准出现的时间远远的晚于浏览器，所以，并不是所有的浏览器都实现了整个的标准（据我所知，chrome也不过是近几年才实现了这个标准，所以网上才有那么多jquery跨域的文章，以及jsonp的实现，不过话说除非真的盗链别用jsonp，这个只是钻了浏览器的空子），或者说大家选择只是实现了一部分的标准，所以这个并没有起到相应的作用。

> 这里有必要说明的是，CORS标准所带来的编码上必须解决的同源策略的问题往往被称之为跨域，从而让很多人以为CORS就是为了解决XSS跨域攻击的，实际上二者没啥关系。

# CORS所带来的编程上的要求

`这里之所以不说带来的问题而说是带来的要求，是因为标准是需要遵守的，浏览器实现了标准，我们的编程也需要遵循这个标准`

其实网站的出现之初就存在这种对于跨域资源访问的需求，但是因为那个时候并没有XMLHttpRequest这种东西，在某个domain内的网页如果对另一个domain进行请求，就会出现浏览器的跳转，而无法在当前domain中对于另一个domain进行访问而不跳转到另一个domain，所以那个时候对于资源的控制的要求也没有那么高。

但是随着互联网的发展，为了更好的用户体验，局部刷新，异步请求慢慢的变成了主流，突然之间xmlhttprequest变的流行了起来，然后从某个domain对另一个domain的请求变的简单而且通用了起来，所以就出现了对于跨站资源保护的需求，随之而来的也就是这个CORS标准。所以再说一次这个东西就是为了保护资源而生，跟XSS没啥关系。

CORS出现之后，因为是个白名单，所以如果没有经过任何设置的话默认是不能访问的，所以很多人的网站突然变的不灵光了，然后大家就开始查找问题通过浏览器的提示发觉是cross domain的字眼，然后就被称之为跨域了。。。。。

那么我们扯了这么半天，这个CORS标准的具体内容到底是什么呢？

`CORS标准规定了一组用于表示在非同源访问的情况标识访问权限的header`

也就是说，CORS标准说只要在header里面增加几个关于非同源访问要求的header即可，到这里大家也可以看出来，这个标准是个很无奈的东西，只能要求说浏览器你看到我这个header就帮我保护一下吧，具体的对于资源的保护实际上并没有做任何事情。

那么具体包含那几个header呢？

- 请求时可以包含的headers

```HTML
Origin  -- 访问的源domain，之所以要有这个是因为中间可能有proxy
Access-Control-Request-Method 
Access-Control-Request-Headers 
```

- 响应时需要包含的headers

```
Access-Control-Allow-Origin  -- -- 非同源时可以允许的domain
Access-Control-Allow-Credentials  -- 非同源时使用的证书
Access-Control-Expose-Headers 
Access-Control-Max-Age -- 缓存使用
Access-Control-Allow-Methods -- 非同源时可以允许的请求方法
Access-Control-Allow-Headers -- 非同源时可以允许携带的header
```

`这里我使用了大量的非同源，目的不外乎就是想说明这个事情需要从资源拥有者的服务器进行配置，思考的时候要把自己放在资源拥有者服务器的角度`

上面虽然有这么多的header，但是实际上为了能够让自己的资源可以非同源使用，那么只需要增加`Access-Control-Allow-Origin`即可。

# Nginx对于CORS的支持

如果你使用了nginx作为网站服务器，那么你可以简单的在你的资源配置下增加下面的配置即可完成对于CORS的支持

```
add_header                          'Access-Control-Allow-Origin' '*';
```

然而，上面的配置有个bug，就是只能支持http status为200，201，302，304的请求，如果你需要对400，404这一类的status也增加CORS，那么可以使用下面的配置

```
more_set_headers                    'Access-Control-Allow-Origin: *';
```

# Tomcat对于CORS的支持

如果你在使用tomcat 7及以上版本，那么你可以简单的通过增加下面的配置在你的web.xml里面即可拥有对于CORS的支持：

```xml
<filter>
  <filter-name>CorsFilter</filter-name>
  <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
</filter>
<filter-mapping>
  <filter-name>CorsFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

当然了，上面的配置默认对于所有的domain都开放，如果你需要详细的配置可以参照：

```xml
<filter>
  <filter-name>CorsFilter</filter-name>
  <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
  <init-param>
    <param-name>cors.allowed.origins</param-name>
    <param-value>*</param-value>
  </init-param>
  <init-param>
    <param-name>cors.allowed.methods</param-name>
    <param-value>GET,POST,HEAD,OPTIONS,PUT</param-value>
  </init-param>
  <init-param>
    <param-name>cors.allowed.headers</param-name>
    <param-value>Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers</param-value>
  </init-param>
  <init-param>
    <param-name>cors.exposed.headers</param-name>
    <param-value>Access-Control-Allow-Origin,Access-Control-Allow-Credentials</param-value>
  </init-param>
  <init-param>
    <param-name>cors.support.credentials</param-name>
    <param-value>true</param-value>
  </init-param>
  <init-param>
    <param-name>cors.preflight.maxage</param-name>
    <param-value>10</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>CorsFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

如果你正在使用非tomcat或者tomcat 6及以下的版本，上面的filter是不存在，有一个替代方案

```xml
<filter>
    <filter-name>CORS</filter-name>
    <filter-class>com.thetransactioncompany.cors.CORSFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>CORS</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

想要使用上面的这个filter，需要增加下面的两个jar：

[cors-filter-2.4.jar](http://search.maven.org/remotecontent?filepath=com/thetransactioncompany/cors-filter/2.4/cors-filter-2.4.jar)

[java-property-utils-1.9.1.jar](http://search.maven.org/remotecontent?filepath=com/thetransactioncompany/java-property-utils/1.9.1/java-property-utils-1.9.1.jar)

或者如果你使用maven的话可以使用下面的dependency：

```xml
		<dependency>
			<groupId>com.thetransactioncompany</groupId>
			<artifactId>cors-filter</artifactId>
			<version>2.4</version>
		</dependency>
```


## 参考文档

<https://en.wikipedia.org/wiki/Cross-origin_resource_sharing>

<https://tomcat.apache.org/tomcat-7.0-doc/config/filter.html>

<http://software.dzhuvinov.com/cors-filter-installation.html>