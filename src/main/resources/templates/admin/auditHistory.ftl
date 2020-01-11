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
            <div class="modPwdTitle">审核历史</div>
        </div>
        <div class="layui-card-body" style="padding-top: 25px;">
            <table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>审核人</th>
                    <th>审核状态</th>
                    <th>审核说明</th>
                    <th>审核时间</th>
                </tr>
                </thead>
                <tbody>
                <#list auditLogs as al>
                <tr>
                    <td>系统管理员</td>
                    <td>${(al.auditStatus=='SA')?string('审核通过','审核不通过')}</td>
                    <td>${al.auditRemark!}</td>
                    <td>${al.createTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
