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
    <!-- 验证码 -->
    <script src="https://ssl.captcha.qq.com/TCaptcha.js"></script>
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
    <button type="button" class="button buttonBlue" id="login" data-appid="1300603860" data-cbfn="callback">登录
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
    <button type="button" style="display: none" id="TencentCaptcha" data-appid="1300603860" data-cbfn="callback">登录</button>
    <h4 class="forget">忘记密码</h4>
</form>
<form class="forgetForm" style="display: none">
    <input type="hidden" name="sessionId" id="sessionId"/>
    <div class="group">
        <input type="text" name="mphone" id="for_phone" maxlength="11"><span class="highlight"></span><span class="bar"></span>
        <label>手机号码</label>
    </div>
    <div class="group">
        <input type="password" name="npwd" id="for_new_pwd" maxlength="11"><span class="highlight"></span><span class="bar"></span>
        <label>新密码</label>
    </div>
    <div class="group">
        <input type="password" id="for_old_pwd" maxlength="11"><span class="highlight"></span><span class="bar"></span>
        <label>确认密码</label>
    </div>
    <div class="group">
        <div class="row">
            <div class="layui-col-xs8">
                <input type="text" name="msgCode" id="for_Code"><span class="highlight"></span><span
                    class="bar"></span>
                <label>短信验证码</label>
            </div>
            <div class="layui-col-xs4">
                <input class="layui-btn layui-btn-radius" type="button" value="获取验证码" id="getCode">
            </div>
        </div>
    </div>
    <button type="button" class="button buttonBlue" id="forget">提交
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
    <h4 class="re_login">返回登录</h4>
</form>
</body>
</html>
