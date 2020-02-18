
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
<div class="layui-container layui-anim layui-anim-scale" style="padding: 20px">
    <div id="chatContain">
        <#list chatList as msg>
        <#if msg.isSelf == 'true'>
            <div class="chatBlock" style="margin-left: 50%">
                <span>
                    ${(msg.createTime)?string("yyyy/MM/dd HH:mm:ss")}
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span class="userName">
                        我
                    </span>
                </span>
                <p>
                    ${msg.content}
                </p>
            </div>
        <#else >
            <div class="chatBlock">
                <span>
                    <i class="layui-icon layui-icon-username"></i>
                    <span class="userName">
                        ${msg.sendName}
                    </span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ${(msg.createTime)?string("yyyy/MM/dd HH:mm:ss")}
                </span>
                <p style="margin-left: 20px">
                    ${msg.content}
                </p>
            </div>
        </#if>
        </#list>
    </div>
    <div class="submitInput ">
        <div class="layui-row">
            <form id="replyForm">
                <input type="hidden" name="receiveId" value="${uid}">
                <div class="layui-col-xs10 layui-col-sm10 layui-col-md10">
                    <input type="text" class="layui-input" name="content" id="content">
                </div>
                <div class="layui-col-xs2 layui-col-sm2 layui-col-md2">
                    <button type="button" class="layui-btn" id="sendMsg">发送</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
<style>
    .chatBlock {
        margin-bottom: 5px;
    }

    .chatBlock > span > .userName {
        color: #7D6608;
        font-size: 16px;
    }

    .submitInput {
        width: 355px;
        position: fixed;
        top: 560px;
    }
</style>
<script>
    $(() => {
        $(document).on('click', '#sendMsg', () => {
            Base.ajax('/doSendReply', "POST", $("#replyForm").serialize(), (res) => {
                if (res.code == 200) {
                    $("#content").val("");
                    var data = res.data;
                    var html =
                            '<div class="chatBlock" style="margin-left: 60%">\n' +
                            '                <span>' +
                            '                    ' + Base.formatDate(data.createTime, "yyyy/MM/dd HH:mm:ss") + '' +
                            '                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '                    <span class="userName">' +
                            '                        我' +
                            '                    </span>' +
                            '                </span>' +
                            '                <p>' +
                            '                    ' + data.content + '' +
                            '                </p>' +
                            '            </div>'
                    $("#chatContain").append(html);
                } else {
                    layer.msg("发送失败");
                }
            })
        })
    })
</script>
</html>
