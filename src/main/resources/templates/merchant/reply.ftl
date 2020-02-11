
<!--回复评论模态框-->

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
</head>
<body>
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 10px;">
    <div class="layui-row layui-card">
        <div class="layui-card-body" style="padding-top: 25px;">
            <from class="layui-form">
                <input type="hidden" name="commentor" value="${commentor}">
                <input type="hidden" name="oid" value="${oid}">
                <input type="hidden" name="rid" value="${rid}">
                <input type="hidden" name="type" value="2">
                <div class="layui-form-item">
                    <label><em style="color: #f00;">*</em>&nbsp;&nbsp;回复内容：</label><br>
                    <div style="margin-left: 85px">
                        <#if comment??>
                            <textarea class="layui-textarea" style="background-color: #f0f0f0;cursor: not-allowed" disabled >${comment.content}</textarea>
                        <#else >
                            <textarea name="content" placeholder="请输入回复内容" class="layui-textarea"></textarea>
                        </#if>
                    </div>
                </div>
                <#if comment??>
                <#else>
                    <div class="layui-form-item" >
                        <div style="float: right">
                            <button class="layui-btn layui-btn-radius layui-btn-warm" lay-submit lay-filter="sendSumbit">
                                发送
                            </button>
                        </div>
                    </div>
                </#if>
            </from>
        </div>
    </div>
</div>
<script>
    layui.use(['form'], function () {
        var form = layui.form ;
        form.render();
        form.on('submit(sendSumbit)', function(data){
            Base.ajax("/merchant/doReply","PUT",data.field,(res)=>{
                if (res.code === Base.status.success) {
                    layer.msg("操作成功",{icon:6,time:800});
                    setTimeout(()=>{
                        parent.$(".layuiadmin-btn-forum-list").click();
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index)
                    },800)

                }else{
                    layer.msg(res.msg,{icon:5,time:800});
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
