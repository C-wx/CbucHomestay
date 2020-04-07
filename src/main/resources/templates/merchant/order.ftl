
<!--商户端之评论管理界面-->

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
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 30px;">
    <div class="layui-card">
        <div class="layui-card-header">
            <strong style="font-size: 22px;font-family: 'kaiti';letter-spacing: 2px">订单管理</strong>
        </div>
        <div class="layui-card-body">
            <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                <div id="search_area">
                    <label>订单编号：</label>
                    <div class="layui-inline">
                        <input class="layui-input" id="content" autocomplete="off">
                    </div>
                    <span style="margin-left: 50px">
                        <button class="layui-btn layuiadmin-btn-forum-list" data-type="keyLike">
                            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                        </button>
                        <button class="layui-btn layui-btn-primary" data-type="reload">
                            <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>
                        </button>
                    </span>
                </div>
            </div>
            <div class="layui-card-body">
                <table id="orderTable" lay-filter="orderTable"></table>
            </div>
        </div>
    </div>
</div>
<style>
    .layui-layer-setwin > .layui-layer-ico {
        display: none;
    }

    .layui-table-cell {
        height: auto;
        line-height: 28px;
    }

    .layui-btn {
        border: none;
    }
</style>
<script>
    layui.config({
        base: '/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'order']);
</script>
</body>
</html>
