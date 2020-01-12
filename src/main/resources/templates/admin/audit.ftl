
<!--管理员之审核界面-->

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
            <div class="modPwdTitle">审核</div>
        </div>
        <div class="layui-card-body" style="padding-top: 25px;">
            <from class="layui-form">
                <input type="hidden" name="parentId" value="${parentId!}">
                <input type="hidden" name="type" value="${type!}">
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;审核状态：</label><br>
                    <div style="margin-left: 85px">
                        <input type="radio" name="auditStatus" value="SA" title="审核通过">
                        <input type="radio" name="auditStatus" value="FA" title="审核不通过">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;审核时间：</label><br>
                    <div style="margin-left: 85px">
                        <input type="text" name="createTime" id="date" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;审核说明：</label><br>
                    <div style="margin-left: 85px">
                        <textarea name="auditRemark" placeholder="请输入审核说明" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div style="float: right">
                        <button class="layui-btn layui-btn-radius layui-btn-warm" lay-submit lay-filter="auditSumbit">
                            立即提交
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
            , left: '200'
            , btns: ['now', 'confirm']
            , theme: '#393D49'
            , trigger: 'click'
        });
        form.on('submit(auditSumbit)', function(data){
            Base.ajax("/admin/doAudit","POST",data.field,(res)=>{
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
