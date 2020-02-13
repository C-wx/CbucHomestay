
<!--商户端之公告中心界面-->

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
    <!--css-->
    <link rel="stylesheet" href="${base}/plugins/layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 30px;">
        <#list bulletinList as bulletin>
            <div class="layui-card">
                <div class="layui-card-header">${bulletin.createTime?string('yyyy-MM-dd hh:mm:ss')}</div>
                <div class="layui-card-body">
                    ${bulletin.content}
                </div>
            </div>
        </#list>
</div>
</body>
<style>
    .layui-card{
        float: left;
        margin-left: 50px;
        margin-right: 50px;
        width: 625px;
        height: 250px;
    }
    .layui-card-header{
        color: #993333;
        font-size: 18px;
    }
    .layui-card-body{
        font-size: 24px;
        font-weight: 800;
        font-family: kaiti;
    }
</style>
</html>
