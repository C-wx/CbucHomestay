
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
    <link rel="stylesheet" href="${base}/css/chat.css">
    <link rel="stylesheet" href="${base}/plugins/layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-container layui-anim layui-anim-scale">
    <div class="talk_con">
        <div class="talk_show" id="words">
            <#list chatList as msg>
                <div class="${(msg.isSelf == 'true')?string('btalk','atalk')}">
                    <span>
                        <#if msg.isSelf == 'false'><span style="color: #c2e0b9">
                        <i class="layui-icon layui-icon-username"></i>${msg.sendName}:</span></#if>
                        ${msg.content}
                    </span>
                </div>
            </#list>
        </div>
        <form id="replyForm">
            <div class="talk_input">
                <input type="hidden" name="receiveId" value="${uid!}">
                <input type="text" name="content" class="talk_word" id="talkwords">
                <button type="button" class="talk_sub layui-btn layui-btn-radius" id="sendMsg">发送</button>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    $(() => {
        $(document).on('click', '#sendMsg', () => {
            if ($("#talkwords").val() == "") {
                layer.msg("消息不能为空", {icon: 5, time: 800, anim: 6});
                return
            }
            Base.ajax('/doSendReply', "POST", $("#replyForm").serialize(), (res) => {
                if (res.code == 200) {
                    var html = '<div class="btalk"><span>' + $("#talkwords").val() + '</span></div>';
                    $("#words").append(html);
                    $("#talkwords").val("");
                } else {
                    layer.msg("发送失败");
                }
            });
        })
    })
    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#sendMsg").click();
        }
    });
</script>
</html>
