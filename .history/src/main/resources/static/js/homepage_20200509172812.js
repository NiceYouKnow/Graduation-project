<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>首页</title>
    <link rel="stylesheet" href="index.css">
    <style>
        canvas{
        display: block;
        width: 100%;
        height: 100%;
        z-index: -1;
        }
    </style>
</head>
<body>
    <header class="header">   
        <div class="welcome">
            <p> 欢迎访问 </p>
        </div>
        <!--向下滚动提示-->
        <a class="scroll">
            向下滚动
        </a>
        <canvas id="canvas"></canvas>
    </header>
    <div class="container">
        <section class="section">
            <div class="mk-item-box">
                <div class="mk-item">
                    <a href="usernews" >
                        <article class="mk-color-item color1">
                            <div class="item-logo">
                            </div>
                            <h3>个人信息</h3>
                            <p>查看修改个人信息</p>
                        </article>
                    </a>
                </div>
                <div class="mk-item">
                    <a href="videoupload">
                        <article class="mk-color-item color2">
                            <div class="item-logo">
                            </div>
                            <h3>上传</h3>
                            <p>上传您的文件</p>
                        </article>
                    </a>
                </div>
                <div class="mk-item">
                    <a href="videolist" >
                        <article class="mk-color-item color3">
                            <div class="item-logo">
                            </div>
                            <h3>文件列表</h3>
                            <p>查看您已经上传的文件</p>
                        </article>
                    </a>
                </div>
                <div class="mk-item">
                    <a th:href="@{/loginout}" >
                        <article class="mk-color-item color2">
                            <div class="item-logo">
                            </div>
                            <h3>登出账号</h3>
                            <br>
                        </article>
                    </a>
                </div>
            </div>
</body>
<script src='index.js'></script>
</html>