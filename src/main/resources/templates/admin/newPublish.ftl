
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
        <div class="layui-col-md9">
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 22px;font-family: 'kaiti';letter-spacing: 2px">发布资讯</strong>
                </div>
                <div class="layui-card-body">
                    <div id="content-editor" style="padding-top: 25px;">
                        <textarea id="editor" style="display: none;"></textarea>
                    </div>
                    <br>
                    <button type="button" class="layui-btn layui-btn-warm layui-btn-radius" lay-submit
                            lay-filter="postSubmit"
                            style="margin-left: 1005px;">
                        立即发布
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-col-md3" id="right-card">
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 18px;font-family: 'kaiti';letter-spacing: 2px">
                        文章标题
                    </strong>
                </div>
                <div class="layui-card-body">
                    <div class="layui-form-item">
                        <input type="text" name="title" id="title" required lay-verify="required" placeholder="请输入文章标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 18px;font-family: 'kaiti';letter-spacing: 2px">
                        文章标题
                    </strong>
                </div>
                <div class="layui-card-body">
                    <div class="layui-form-item">
                        <textarea placeholder="请输入文章摘要" name="summary" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 18px;font-family: 'kaiti';letter-spacing: 2px">
                        生效时间
                    </strong>
                </div>
                <div class="layui-card-body">
                    <div class="layui-form-item">
                        <input type="text" name="beginTime" id="beginTime" autocomplete="off" class="layui-input"
                               style="width: 200px">
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 18px;font-family: 'kaiti';letter-spacing: 2px">
                        失效时间
                    </strong>
                </div>
                <div class="layui-card-body">
                    <div class="layui-form-item">
                        <input type="text" name="endTime" id="endTime" autocomplete="off" class="layui-input"
                               style="width: 200px">
                    </div>
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
    }).use(['index', 'newsPublish']);
</script>
</body>
</html>