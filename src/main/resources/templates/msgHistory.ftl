
<!--查看消息历史界面-->

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
    <!--audit-->
    <link rel="stylesheet" href="${base}/css/audit.css">
</head>
<body>
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 10px;">
    <div class="layui-row layui-card">
        <div class="layui-card-header">
            <div class="modPwdTitle">消息历史</div>
        </div>
        <div class="layui-card-body" style="padding-top: 25px;">
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>消息内容</th>
                        <th>读取状态</th>
                        <th>发送时间</th>
                    </tr>
                </thead>
                <tbody>
                    <#list messageList as ml>
                        <tr>
                            <td>${ml.content}</td>
                            <td>${(ml.readStatus=='WR')?string('未读','已读')}</td>
                            <td>${ml.createTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
