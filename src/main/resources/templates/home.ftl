<!DOCTYPE html>
<html lang="en">
<#assign base=request.contextPath />
<head>
    <meta charset="UTF-8">
    <title>北墘小屋</title>
    <!--Base-->
    <script src="${base}/js/jquery-1.11.2.min.js"></script>
    <script src="${base}/js/base.js"></script>
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
<form class="formDate">
    <div class="group">
        <input type="text" name="maccount" id="maccount" >
        <span class="highlight"></span><span class="bar"></span>
        <label>账号</label>
    </div>
    <div class="group">
        <input type="password" name="mpwd" id="mpwd"><span class="highlight"></span><span class="bar"></span>
        <label>密码</label>
    </div>
    <div class="group">
        <div class="row">
            <div class="layui-col-xs8">
                <input type="text" name="verifyCode" id="verifyCode"><span class="highlight"></span><span
                    class="bar"></span>
                <label>验证码</label>
            </div>
            <div class="layui-col-xs4" style="margin-top: -9px">
                <img src="/image/code" class="layadmin-user-login-codeimg" id="vercode"
                     onclick="captchaRefresh(this)">
            </div>
        </div>
    </div>
    <button type="button" class="button buttonBlue">登录
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
</form>
</body>
</html>
