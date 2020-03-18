
<!--发送消息模态框-->

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
            <div class="modPwdTitle">消息中心</div>
        </div>
        <div class="layui-card-body" style="padding-top: 25px;">
            <from class="layui-form">
                <input type="hidden" name="sendId" value="${sendId!}">
                <input type="hidden" name="sendType" value="${sendType!}">
                <input type="hidden" name="receiveId" value="${receiveId!}">
                <input type="hidden" name="receiveType" value="${receiveType!}">
                <input type="hidden" name="originId" value="${originId!}">
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;通知人：</label>
                    <div style="margin-left: 85px">
                        <input type="text" class="layui-input" value="${mName}" disabled
                               style="cursor: not-allowed;background-color: #dedede">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;发送时间：</label><br>
                    <div style="margin-left: 85px">
                        <input type="text" name="createTime" id="date" lay-verify="datetime"
                               placeholder="yyyy-MM-dd HH:mm:ss"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;发送内容：</label><br>
                    <div style="margin-left: 85px">
                        <textarea name="content" placeholder="请输入发送内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div style="float: right">
                        <button class="layui-btn layui-btn-radius layui-btn-warm" lay-submit lay-filter="sendSumbit">
                            发送
                        </button>
                    </div>
                </div>
            </from>
        </div>
    </div>
</div>
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form, laydate = layui.laydate;
        form.render();
        laydate.render({
            elem: '#date'
            , value: new Date()
            , min: 0
            , type: 'datetime'
            , btns: ['now', 'confirm']
            , theme: '#393D49'
            , trigger: 'click'
        });
        form.on('submit(sendSumbit)', function (data) {
            Base.ajax("/sendMsg", "PUT", data.field, (res) => {
                if (res.code === Base.status.success) {
                    layer.msg("操作成功", {icon: 6, time: 800});
                    if (res.data < 1) {
                        $("#msgNum", window.parent.parent.document).html(' ');
                    } else {
                        $("#msgNum", window.parent.parent.document).html(res.data);
                    }
                    setTimeout(() => {
                        parent.$(".layuiadmin-btn-forum-list").click();
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index)
                    }, 800)

                } else {
                    layer.msg(res.msg, {icon: 5, time: 800});
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
