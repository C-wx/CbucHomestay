<!DOCTYPE html>
<html lang="en">
<#assign base=request.contextPath />
<head>
    <meta charset="utf-8">
    <title>北墘小屋</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- layui -->
    <script src="${base}/plugins/layui/layui.all.js" type="application/javascript"></script>
    <script src="${base}/plugins/layui/layui.js"></script>
    <link rel="stylesheet" href="${base}/plugins/layui/css/layui.css">
    <!-- 客户端-->
    <link rel="stylesheet" href="${base}/plugins/layuiadmin/style/admin.css" media="all">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${base}/plugins/font-awesome/css/font-awesome.min.css">
</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <li class="layui-nav-item" lay-unselect>
                    <a lay-href='/toMessageCenter' layadmin-event="message" lay-text="消息中心">
                        <i class="fa fa-bell"></i>
                     <#if (msgNum > 0)>
                     <span class="layui-badge-dot"></span>
                     </#if>
                     <span id="msgNum" style="color: darkred;">
                     <#if (msgNum > 0)>
                         ${msgNum}
                     </#if>
                     </span>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect style="margin-right: 40px">
                    <a href="javascript:;">
                        <cite style="font-size: 20px">
                            <i class="fa fa-user"></i>&nbsp;&nbsp;${LOGIN_MERCHANT.mname!}
                        </cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="/logout"><i class="fa fa-sign-out"></i> 退出</a></dd>
                        <dd><a lay-href="/toModPwd"><i class="fa fa-unlock"></i> 修改密码</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo">
                    <cite><i class="fa fa-home"></i><span>北墘小屋</span></cite>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">
                    <li data-name="dataStatistic" class="layui-nav-item layui-this" style="padding-top: 45px">
                        <a href="javascript:;" lay-href=${(MLEVEL == 'ADMIN')?string('/admin/dataCenter','/dataCenter')} lay-tips="数据统计" lay-direction="2">
                            <i class="layui-icon layui-icon-tabs" style="color: #f7ab8e;"></i>
                            <cite>数据统计</cite>
                        </a>
                    </li>
                    <#if MLEVEL == 'ADMIN'>
                        <li data-name="merchant" class="layui-nav-item">
                            <a href="javascript:;" lay-tips="商户中心" lay-direction="2">
                                <i class="layui-icon layui-icon-survey" style="color: #f7ab8e;"></i>
                                <cite>商户中心</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd data-name="merchantAudit">
                                    <a lay-href="/admin/merchantAudit">商户审核</a>
                                </dd>
                                <dd data-name="merchantManage">
                                    <a lay-href="/admin/merchantManage">商户管理</a>
                                </dd>
                            </dl>
                        </li>
                    </#if>
                    <#if MLEVEL?index_of("ADMIN")== -1>
                        <li data-name="order" class="layui-nav-item">
                            <a lay-href='/toOrderCenter' href="javascript:;" lay-tips="订单中心" lay-direction="2">
                                <i class="layui-icon layui-icon-chart-screen" style="color: #f7ab8e;"></i>
                                <cite>订单中心</cite>
                            </a>
                        </li>
                    </#if>
                    <#if MLEVEL == 'ADMIN'>
                        <li data-name="home" class="layui-nav-item">
                            <a lay-href="/admin/roomManage" href="javascript:;" lay-tips="房源中心" lay-direction="2">
                                <i class="layui-icon layui-icon-home" style="color: #f7ab8e;"></i>
                                <cite>房源管理</cite>
                            </a>
                        </li>
                    <#else >
                        <li data-name="home" class="layui-nav-item">
                            <a lay-href="/merchant/toRoom" href="javascript:;" lay-tips="房源中心" lay-direction="2">
                                <i class="layui-icon layui-icon-home" style="color: #f7ab8e;"></i>
                                <cite>房源管理</cite>
                            </a>
                        </li>
                    </#if>
                    <#if MLEVEL == 'ADMIN'>
                    <li data-name="news" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="资讯中心" lay-direction="2">
                            <i class="layui-icon layui-icon-template" style="color: #ffcbd6;"></i>
                            <cite>资讯中心</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="newsManage">
                                <a lay-href="/admin/newsManage">资讯管理</a>
                            </dd>
                            <dd data-name="newsPublish">
                                <a lay-href="/admin/newPublish">资讯发布</a>
                            </dd>
                        </dl>
                    </li>
                    <#else >
                        <li data-name="news" class="layui-nav-item">
                            <a href="javascript:;" lay-tips="资讯中心" lay-direction="2">
                                <i class="layui-icon layui-icon-template" style="color: #f7ab8e;"></i>
                                <cite>资讯中心</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd data-name="toNews">
                                    <a lay-href="/merchant/toNews">资讯管理</a>
                                </dd>
                                <dd data-name="newsPublish">
                                    <a lay-href="/admin/newPublish">资讯发布</a>
                                </dd>
                            </dl>
                        </li>
                    </#if>
                    <#if MLEVEL == 'ADMIN'>
                    <li data-name="comment" class="layui-nav-item">
                        <a lay-href="/admin/commentManage" href="javascript:;" lay-tips="评论管理" lay-direction="2">
                            <i class="layui-icon layui-icon-survey" style="color: #f7ab8e;"></i>
                            <cite>评论管理</cite>
                        </a>
                    </li>
                    <#else >
                        <li data-name="comment" class="layui-nav-item">
                            <a lay-href="/merchant/toCommentManage" href="javascript:;" lay-tips="评论管理" lay-direction="2">
                                <i class="layui-icon layui-icon-survey" style="color: #f7ab8e;"></i>
                                <cite>评论管理</cite>
                            </a>
                        </li>
                    </#if>
                    <#if MLEVEL == 'ADMIN'>
                        <li data-name="bulletin" class="layui-nav-item">
                            <a lay-href="/admin/bulletinPublish" href="javascript:;" lay-tips="公告管理" lay-direction="2">
                                <i class="layui-icon layui-icon-list" style="color: #f7dbbb;"></i>
                                <cite>公告中心</cite>
                            </a>
                        </li>
                    <#else >
                        <li data-name="bulletin" class="layui-nav-item">
                            <a lay-href="/merchant/toBulletin" href="javascript:;" lay-tips="公告管理" lay-direction="2">
                                <i class="layui-icon layui-icon-list" style="color: #f7ab8e;"></i>
                                <cite>公告中心</cite>
                            </a>
                        </li>
                    </#if>
                    <li data-name="message" class="layui-nav-item">
                        <a lay-href='/toMessageCenter' href="javascript:;" lay-tips="消息中心" lay-direction="2">
                            <i class="layui-icon layui-icon-reply-fill" style="color: #f7ab8e;"></i>
                            <cite>消息中心</cite>
                        </a>
                    </li>
                    <#if MLEVEL?index_of("ADMIN")== -1>
                        <li data-name="kefu" class="layui-nav-item">
                            <a lay-href='/toKefuCenter' href="javascript:;" lay-tips="客服中心" lay-direction="2">
                                <i class="layui-icon layui-icon-service" style="color: #f7ab8e;"></i>
                                <cite>客服中心</cite>
                            </a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="/admin/dashboard" lay-attr="/admin/dashboard" class="layui-this">
                        <i class="layui-icon layui-icon-home" style="color: #646c7d;"></i></li>
                </ul>
            </div>
        </div>

        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe id="LAY_content_iframe" src="${(MLEVEL == 'ADMIN')?string('/admin/dataCenter','/dataCenter')}" frameborder="0"
                        class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>
<script>
    layui.config({
        base: '/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');
</script>
</body>
</html>
