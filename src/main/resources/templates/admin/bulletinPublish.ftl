
<!--公告发布界面-->

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
    <!--plugins-->
    <script src="${base}/plugins/nkeditor/libs/JDialog/JDialog.min.js"></script>
    <script src="${base}/plugins/nkeditor/NKeditor-all-min.js"></script>
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${base}/plugins/font-awesome/css/font-awesome.min.css">
</head>
<body>
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 30px;">
    <div class="layui-row layui-col-space20 layui-form">
        <div class="layui-col-md7">
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 22px;font-family: 'kaiti';letter-spacing: 2px">添加公告</strong>
                </div>
                <div class="layui-card-body" style="padding-top: 25px;">
                    <div id="content-editor" style="padding-top: 25px;">
                        <textarea id="editor" style="display: none;"></textarea>
                    </div>
                    <br>
                    <button type="button" class="layui-btn layui-btn-warm layui-btn-radius" lay-submit lay-filter="postSubmit"
                            style="margin-left: 800px;">
                        发布公告
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-col-md5">
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 22px;font-family: 'kaiti';letter-spacing: 2px">历史公告</strong>
                </div>
                <div class="layui-card-body" style="padding-top: 25px;">
                    <ul class="layui-timeline" id="historyBulletin"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.config({
        base: '/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'bulletinPublish']);
</script>
</body>
</html>