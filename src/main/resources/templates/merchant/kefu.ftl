
<!--商户端之客服中心界面-->

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
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 30px;padding-left: 300px">
    <div style="margin: 20px;">
        <strong style="font-size: 22px;font-family: 'kaiti';letter-spacing: 2px;margin: 20px;">消息列表</strong>
    </div>
    <ul class="layui-timeline">
        <#list msgList as msg>
            <li class="layui-timeline-item cursor" onclick="showModel(${msg.sendId})">
                <i class="layui-icon layui-icon-notice"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">
                        <i class="layui-icon layui-icon-username" style="font-size: 28px"></i>
                        ${msg.sendName}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${(msg.createTime)?string("yyyy/MM/dd HH:mm:ss")}</h3>
                    <p>
                        ${msg.content}
                        <#if msg.unreadCount gt 0>
                        <span class="layui-badge">${msg.unreadCount}</span>
                        <#else>
                        <span class="layui-badge-rim">已读</span>
                        </#if>
                    </p>

                </div>
            </li>
        </#list>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-icon-loading"></i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">过去</div>
            </div>
        </li>
    </ul>
</div>
</body>
<style>
    .cursor {
        cursor: pointer;
        margin-bottom: 10px;
    }

    .layui-timeline-item > i {
        font-size: 20px;
        color: #993333;
    }

    .layui-timeline-content > h3 {
        font-size: 17px;
        font-weight: bold;
        color: #bf997c;
    }

    .layui-timeline-content > p {
        font-size: 24px;
        font-weight: bold;
        font-family: kaiti;
        color: #0C0C0C;
    }

    .layui-layer-setwin > .layui-layer-ico {
        display: none;
    }
</style>
<script>
    layui.use(['form'], function () {
        showModel = (id) => {
            var idx = layer.open({
                type: 2
                , id: 1
                , title: false
                , shadeClose: true
                , zIndex: layer.zIndex
                , content: '/showChat?uid=' + id
                , anim: 5
            });
            layer.style(idx, {
                "width": '700px'
                , "height": '590px'
                , "top": '80px'
                , "left": '400px'
                , "border-radius": '10px'
                , "background": '#c3c3c3'
            });
        }
    })

</script>
</html>
