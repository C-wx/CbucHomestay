
<!--公告详情模态框-->

<!DOCTYPE html>
<html lang="en">
<#assign base=request.contextPath />
<head>
    <meta charset="UTF-8">
    <title>北墘小屋</title>
    <!--Layui-->
    <link rel="stylesheet" href="${base}/plugins/layui/css/layui.css">
</head>
<body style="background-color: rgba(194, 194, 194, 0.3);">
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 10px;">
    <div class="layui-row layui-card">
        <div class="layui-card-body" style="padding-top: 25px;">
            <from class="layui-form">
                <div class="layui-form-item">
                    <label>创建时间：</label>
                    <div style="margin-left: 85px">
                        <input type="text" class="layui-input" value="${bulletin.createTime?string('yyyy-MM-dd hh:mm:ss')}" disabled style="cursor: not-allowed;background-color: #ebebeb">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label>公告内容：</label><br>
                    <div style="margin-left: 85px">
                        ${bulletin.content!}
                    </div>
                </div>
            </from>
        </div>
    </div>
</div>
</body>
</html>
