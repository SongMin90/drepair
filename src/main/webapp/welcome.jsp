<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.drepair.controller.WetsetCotroller" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>寝室快修系统</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="${pageContext.request.contextPath}/admin/assets/i/favicon.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/assets/css/amazeui.min.css"/>
  <style>
    .get {
      background: #1E5B94;
      color: #fff;
      text-align: center;
      padding: 100px 0;
    }

    .get-title {
      font-size: 200%;
      border: 2px solid #fff;
      padding: 20px;
      display: inline-block;
    }

    .get-btn {
      background: #fff;
    }

    .detail {
      background: #fff;
    }

    .detail-h2 {
      text-align: center;
      font-size: 150%;
      margin: 40px 0;
    }

    .detail-h3 {
      color: #1f8dd6;
    }

    .detail-p {
      color: #7f8c8d;
    }

    .detail-mb {
      margin-bottom: 30px;
    }

    .hope {
      background: #0bb59b;
      padding: 50px 0;
    }

    .hope-img {
      text-align: center;
    }

    .hope-hr {
      border-color: #149C88;
    }

    .hope-title {
      font-size: 140%;
    }

    .about {
      background: #fff;
      padding: 40px 0;
      color: #7f8c8d;
    }

    .about-color {
      color: #34495e;
    }

    .about-title {
      font-size: 180%;
      padding: 30px 0 50px 0;
      text-align: center;
    }

    .footer p {
      color: #7f8c8d;
      margin: 0;
      padding: 15px 0;
      text-align: center;
      background: #2d3e50;
    }
  </style>
</head>
<body>
<header class="am-topbar am-topbar-fixed-top">
  <div class="am-container">
    <h1 class="am-topbar-brand">
      <a href="">寝室快修系统</a>
    </h1>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-secondary am-show-sm-only"
            data-am-collapse="{target: '#collapse-head'}"><span class="am-sr-only">导航切换</span> <span
        class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="collapse-head">
      <ul class="am-nav am-nav-pills am-topbar-nav">
        <li class="am-active"><a href="welcome.jsp">首页</a></li>
        <li class="am-dropdown" data-am-dropdown>
          <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
            报修 <span class="am-icon-caret-down"></span>
          </a>
          <ul class="am-dropdown-content">
           	<li>版本：<%=WetsetCotroller.version_baoxiu%></li>
           	<li>更新内容：<%=WetsetCotroller.updateInfo_baoxiu%></li>
            <li><a href="${pageContext.request.contextPath}/apk/Baoxiu.apk"><i class="am-icon-mobile am-icon-sm"></i> 下载客户端</a></li>
          </ul>
        </li>
        <li class="am-dropdown" data-am-dropdown>
          <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
            抢修 <span class="am-icon-caret-down"></span>
          </a>
          <ul class="am-dropdown-content">
            <li>版本：<%=WetsetCotroller.version_qiangxiu%></li>
           	<li>更新内容：<%=WetsetCotroller.updateInfo_qiangxiu%></li>
            <li><a href="${pageContext.request.contextPath}/apk/Qiangxiu.apk"><i class="am-icon-mobile am-icon-sm"></i> 下载客户端</a></li>
          </ul>
        </li>
      </ul>
	  
      <div class="am-topbar-right">
        <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm" onclick="location.href='${pageContext.request.contextPath}/admin/login'"><span class="am-icon-user"></span> Sign in</button>
      </div>
    </div>
  </div>
</header>

<div class="get">
  <div class="am-g">
    <div class="am-u-lg-12">
      <h1 class="get-title">欢迎使用 - 寝室快修系统</h1>

      <p>
        用最完善的系统，打造最实用的学生寝室维修方案
      </p>

      <p>
        <a href="https://github.com/SongMin90/drepair" class="am-btn am-btn-sm get-btn">
			<i class="am-icon-github am-icon-fw"></i>
			<span id="gh-text" class="gh-text">get源码</span>
		</a>
      </p>
    </div>
  </div>
</div>

<div class="detail">
  <div class="am-g am-container">
    <div class="am-u-lg-12">
      <h2 class="detail-h2">优美的界面，实用的功能，期待和你一起去发现!</h2>

      <div class="am-g">
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">

          <h3 class="detail-h3">
            <i class="am-icon-mobile am-icon-sm"></i>
            移动端
          </h3>

          <p class="detail-p">
            现代社会，手机已经成为了必需品，而一款实用，简单的软件就能改变我们的生活。
          </p>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
          <h3 class="detail-h3">
            <i class="am-icon-cogs am-icon-sm"></i>
            功能，组件丰富
          </h3>

          <p class="detail-p">
           一切实用的功能都让我们事半功倍。
          </p>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
          <h3 class="detail-h3">
            <i class="am-icon-check-square-o am-icon-sm"></i>
            完全免费
          </h3>

          <p class="detail-p">
           此系统及软件完全免费使用，代码也提供开源。
          </p>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
          <h3 class="detail-h3">
            <i class="am-icon-send-o am-icon-sm"></i>
            贴近校园
          </h3>

          <p class="detail-p">
            这套系统适用于任意高校使用。
          </p>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="hope">
  <div class="am-g am-container">
    <div class="am-u-lg-4 am-u-md-6 am-u-sm-12 hope-img">
      <img src="admin/assets/i/examples/landing.png" alt="" data-am-scrollspy="{animation:'slide-left', repeat: false}">
      <hr class="am-article-divider am-show-sm-only hope-hr">
    </div>
    <div class="am-u-lg-8 am-u-md-6 am-u-sm-12">
      <h2 class="hope-title">同我们一起打造你的未来</h2>

      <p>
        在知识爆炸的年代，我们不愿成为知识的过客，拥抱开源文化，发挥社区的力量，用你们活跃的思维，一起丰富这实用的系统。
      </p>
    </div>
  </div>
</div>

<div class="about">
  <div class="am-g am-container">
    <div class="am-u-lg-12">
      <h2 class="about-title about-color">我们崇尚开放、自由，非常欢迎大家的参与</h2>

      <div class="am-g">
        <div class="am-u-lg-6 am-u-md-4 am-u-sm-12">
          <form class="am-form">
            <label for="name" class="about-color">你的姓名</label>
            <input id="name" type="text">
            <br/>
            <label for="email" class="about-color">你的邮箱</label>
            <input id="email" type="email">
            <br/>
            <label for="message" class="about-color">你的留言</label>
            <textarea id="message"></textarea>
            <br/>
            <button type="submit" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-check"></i> 提 交</button>
          </form>
          <hr class="am-article-divider am-show-sm-only">
        </div>

        <div class="am-u-lg-6 am-u-md-8 am-u-sm-12">
          <h4 class="about-color">关于我们</h4>

          <p>SongM：只希望大家因一款好用而实用的系统或软件而改变生活，丰富生活。</p>
          <h4 class="about-color">团队介绍</h4>

          <p>...</p>
        </div>
      </div>
    </div>
  </div>
</div>

<footer class="footer">
  <p>版权所有 © SongM All Rights Reserved. Theme by <a href="http://amazeui.org">Amaze</a></p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${pageContext.request.contextPath}/admin/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="${pageContext.request.contextPath}/admin/assets/js/amazeui.min.js"></script>
</body>
</html>