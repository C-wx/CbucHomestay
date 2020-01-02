<!DOCTYPE html>
<html lang="en">
<#assign base=request.contextPath />
<head>
    <meta charset="UTF-8">
    <title>登录页</title>
    <!--JQeruy-->
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <!--Layui-->
    <script src="${base}/plugins/layui/layui.all.js" type="application/javascript"></script>
    <link rel="stylesheet" href="${base}/plugins/layui/css/layui.css">
    <!--login-->
    <link rel="stylesheet" href="${base}/css/login.css">
    <script src="${base}/js/login.js"></script>
</head>
<body>
<hgroup>
    <h1>民宿后台登录</h1>
</hgroup>
<form>
    <div class="group">
        <input type="text"><span class="highlight"></span><span class="bar"></span>
        <label>账号</label>
    </div>
    <div class="group">
        <input type="email"><span class="highlight"></span><span class="bar"></span>
        <label>密码</label>
    </div>
    <div class="group">
        <input type="email"><span class="highlight"></span><span class="bar"></span>
        <label>验证码</label>
    </div>
    <button type="button" class="button buttonBlue">登录
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
</form>
</body>
</html>
