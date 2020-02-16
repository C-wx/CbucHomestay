
<!--商户端之客服聊天界面-->

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
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 20px">
        <#list chatList as msg>
            <#if msg.ifSelf == 'true'>
                <div class="layui-timeline-content layui-text">
                    <span>
                        ${(msg.createTime)?string("yyyy/MM/dd")}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="userName">
                            我
                        </span>
                    </span>
                    <p>
                        ${msg.content}
                    </p>
                </div>
            <#else >
                <div class="layui-timeline-content layui-text">
                    <span>
                        <span class="userName">
                            ${msg.sendName}
                        </span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        ${(msg.createTime)?string("yyyy/MM/dd")}
                    </span>
                    <p>
                        ${msg.content}
                    </p>
                </div>
            </#if>

        </#list>
</div>
</body>
<style>
    .layui-timeline-content{
        margin-bottom: 5px;
    }
    .layui-timeline-content>span>.userName{
        color: #7D6608;
        font-size: 16px;
    }
</style>
</html>
