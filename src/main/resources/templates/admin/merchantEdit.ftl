
<!--管理员之商家审核界面-->

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
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 10px;">
    <div class="layui-row layui-card">
        <div class="layui-card-head" style="padding-top: 25px;text-align:center;color: #c80000;font-size: 20px">
            当前商户总销售量为：${merchant.salesCount!}
        </div>
        <div class="layui-card-body"  >
            <from class="layui-form">
                <input type="hidden" name="id" value="${merchant.id!}">
                <input type="hidden" name="apply.id" value="${merchant.apply.id!}">
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;商家名称：</label>
                    <div style="margin-left: 85px">
                        <input name="mname" type="text" class="layui-input" value="${merchant.mname!}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;商家账号：</label>
                    <div style="margin-left: 85px">
                        <input type="text" class="layui-input" disabled
                               style="cursor: not-allowed;background-color: #dedede" value="${merchant.maccount!}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;商家密码：</label>
                    <div style="margin-left: 85px">
                        <input name="mpwd" type="text" class="layui-input" value="${merchant.mpwd!}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;联系电话：</label>
                    <div style="margin-left: 85px">
                        <input name="mphone" type="text" class="layui-input" value="${merchant.mphone!}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;身份证号：</label>
                    <div style="margin-left: 85px">
                        <input name="apply.mcardno" type="text" class="layui-input" value="${merchant.apply.mcardno!}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;商家地址：</label><br>
                    <div style="margin-left: 85px">
                        <textarea name="apply.maddr" placeholder="请输入商家地址"
                                  class="layui-textarea">${merchant.apply.maddr!}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label><em>*</em>&nbsp;&nbsp;商家等级：</label><br>
                    <div style="margin-left: 85px">
                        <select name="mlevel">
                            <option value="NORMAL" ${(merchant.mlevel == 'NORMAL')?string('selected','')}>普通商家</option>
                            <option value="COPPER" ${(merchant.mlevel == 'COPPER')?string('selected','')}>铜牌商家</option>
                            <option value="SLIVER" ${(merchant.mlevel == 'SLIVER')?string('selected','')}>银牌商家</option>
                            <option value="GOLD" ${(merchant.mlevel == 'GOLD')?string('selected','')}>金牌商家</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div style="float: right">
                        <button class="layui-btn layui-btn-warm" lay-submit lay-filter="submit">
                            提交
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
        form.on('submit(submit)', function (data) {
            Base.ajax("editMerchant", "POST", data.field, (res) => {
                if (res.code === Base.status.success) {
                    layer.msg("操作成功", {icon: 6, time: 800});
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
