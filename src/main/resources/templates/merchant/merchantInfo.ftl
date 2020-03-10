
<!--商家之店铺信息操作界面-->

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
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${base}/plugins/font-awesome/css/font-awesome.min.css">
</head>
<body>
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 30px;">
    <div class="layui-row layui-card">
        <div class="layui-card-body">
            <form class="layui-form" style="padding: 50px;">
                <input type="hidden" name="id" value="${LOGIN_MERCHANT.id!}">
                <div class="layui-form-item">
                    <label class="layui-form-label">个人头像:</label>
                    <input id="file" type="file" name="file" style="display:none;" onChange="replace_image(0)"/>
                    <img id="image" onclick="click_image()" src="${(LOGIN_MERCHANT.avatarUrl)!'${base}/images/bg.jpg'}" height="260px" width="260px"/>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">店铺名称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="mname" lay-verify="required" class="layui-input" value="${LOGIN_MERCHANT.mname!""}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系电话：</label>
                    <div class="layui-input-block">
                        <input type="text" name="mphone" lay-verify="required" class="layui-input" value="${LOGIN_MERCHANT.mphone!""}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">店铺描述：</label>
                    <div class="layui-input-block" style="width: 400px">
                         <textarea name="mdesc" lay-verify="required"  class="layui-textarea">${LOGIN_MERCHANT.mdesc!""}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商家等级：</label>
                    <div class="layui-input-block" style="width: 150px">
                        <input type="text" name="name" class="layui-input" disabled style="background-color: #ddd;cursor: not-allowed"
                               value="<#if LOGIN_MERCHANT.mlevel == 'NORMAL'>普通商家<#elseif LOGIN_MERCHANT.mlevel == 'COPPER'>铜牌商家<#elseif LOGIN_MERCHANT.mlevel == 'GOLD'>金牌商家</#if>">
                    </div>
                </div>
                <div class="layui-form-item" style="text-align: center">
                    <button class="layui-btn" lay-filter="submit" lay-submit type="button">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<style>
    .layui-input-block {
        width: 300px;
    }
</style>
<script>

    layui.use(['form'], function () {
        var form = layui.form;
        form.render();
        form.on('submit(submit)', function () {
            $.ajax({
                url: "doEditInfo",
                type: "POST",
                data: new FormData($("form")[0]),
                processData: false,
                contentType: false,
                success: function (result) {
                    if (200 === result.code) {
                        layer.msg("操作成功", {icon: 6, time: 800});
                    } else {
                        layer.msg(res.msg, {icon: 5, time: 800});
                    }
                }
            });
            return false;
        });
    });
    function click_image() {
        $("#file").click();
    }

    function replace_image() {
        $("#inputImg").hide();
        // 获得图片对象
        var blob_image = $("#file")[0].files[0];
        var url = window.URL.createObjectURL(blob_image);
        // 替换image
        $("#image").attr("src", url);
    }
</script>
</html>