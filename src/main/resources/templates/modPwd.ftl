<!DOCTYPE html>
<html lang="en">
<#assign base=request.contextPath />
<head>
    <meta charset="UTF-8">
    <title>北墘小屋</title>
    <!--Base-->
    <script src="${base}/js/jquery-1.11.2.min.js" type="application/javascript"></script>
    <script src="${base}/js/base.js"></script>
    <!--Layui-->
    <script src="${base}/plugins/layui/layui.all.js" type="application/javascript"></script>
    <link rel="stylesheet" href="${base}/plugins/layui/css/layui.css">
    <!--ModPwd-->
    <script src="${base}/js/modPwd.js"></script>
    <link rel="stylesheet" href="${base}/css/modPwd.css">
</head>
<body>
<div class="layui-fluid layui-anim layui-anim-upbit" style="padding: 50px;">
    <div class="layui-row layui-card">
        <div class="layui-card-header">
            <div class="modPwdTitle">修改密码
                <small class="prompt">（该登录密码为弱密码或初始密码！请修改）</small>
            </div>
        </div>
        <div class="layui-card-body" style="padding-top: 25px;">
            <div class="layui-col-md6 layui-col-md-offset3">
                <from class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;旧密码：</label>
                        <div class="layui-input-block">
                            <input type="text" name="mpwd" id="mpwd" lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;新密码：</label>
                        <div class="layui-input-block">
                            <input type="text" name="npwd" id="npwd" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;确认密码：</label>
                        <div class="layui-input-block">
                            <input type="text" id="cpwd" lay-verify="required" placeholder="请确认密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn layui-btn-radius layui-btn-warm" lay-submit
                                    lay-filter="modSumbit">立即提交
                            </button>
                            <button type="reset" class="layui-btn layui-btn-radius layui-btn-primary">重置</button>
                        </div>
                    </div>
                </from>
            </div>
        </div>
    </div>
</div>
</body>
</html>
