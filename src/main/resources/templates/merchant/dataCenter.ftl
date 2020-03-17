
<!--商户之数据中心界面-->

<!DOCTYPE html>
<#assign base=request.contextPath />
<head>
    <meta charset="utf-8">
    <title>后台管理</title>
    <!--Base-->
    <script src="${base}/js/jquery-1.11.2.min.js" type="application/javascript"></script>
    <script src="${base}/js/base.js"></script>
    <!--Layui-->
    <script src="${base}/plugins/layui/layui.all.js" type="application/javascript"></script>
    <link rel="stylesheet" href="${base}/plugins/layui/css/layui.css">
    <!--css-->
    <link rel="stylesheet" href="${base}/plugins/layuiadmin/style/admin.css" media="all">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${base}/plugins/font-awesome/css/font-awesome.min.css">
    <!--echart-->
    <script src="${base}/plugins/layuiadmin/lib/extend/echarts.js"></script>
</head>
<style>
    body {
        background-color: rgba(194, 194, 194, 0.3);
    }
</style>
<body>
<div class="layui-fluid layui-anim layui-anim-scale">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="layui-row layui-col-space15">
                <div class="layui-card">
                    <div class="layui-card-header">
                        <strong style="font-size: 20px;font-family: 'kaiti';letter-spacing: 2px">系数数据</strong>
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog">
                            <ul class="layui-row layui-col-space10">
                                <li class="layui-col-xs6">
                                    <div class="layadmin-backlog-body">
                                        <h3>订单数量</h3>
                                        <p><cite>${dataMap.orderTotal!}</cite></p>
                                    </div>
                                </li>
                                <li class="layui-col-xs6">
                                    <div class="layadmin-backlog-body">
                                        <h3>资讯数量</h3>
                                        <p><cite>${dataMap.newsTotal!}</cite></p>
                                    </div>
                                </li>
                                <li class="layui-col-xs6">
                                    <div class="layadmin-backlog-body">
                                        <h3>房源数量</h3>
                                        <p><cite>${dataMap.roomTotal!}</cite></p>
                                    </div>
                                </li>
                                <li class="layui-col-xs6">
                                    <div class="layadmin-backlog-body">
                                        <h3>消息数量</h3>
                                        <p><cite>${dataMap.msgTotal!}</cite></p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            <strong style="font-size: 20px;font-family: 'kaiti';letter-spacing: 2px">销售情况统计</strong>
                        </div>
                        <div class="layui-card-body">
                            <div class="layui-input-inline">
                                <span style="position: relative;left: 10px;top: 12px;">统计周期:</span>
                                <input type="text" name="date" id="date" class="layui-input" style="position: relative;left: 100px;top: -20px;">
                            </div>
                            <div id="main" style="width: 1000px;height:400px;position: relative;top: -20px;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 20px;font-family: 'kaiti';letter-spacing: 2px">最新订单</strong>
                </div>
                <#if lastMap.lastOrder??>
                    <div class="layui-card-body layadmin-takerates">
                        <p>
                            <span class="layui-word-aux">房间名称：</span>
                            <span>${lastMap.lastOrder.roomInfo.title!}</span>
                        </p>
                        <hr>
                        <p>
                            <span class="layui-word-aux">订单号：</span>
                            <span>${lastMap.lastOrder.orderCode!}</span>
                        </p>
                        <hr>
                        <p>
                            <span class="layui-word-aux">买家名称：</span>
                            <span>${lastMap.lastOrder.name!}</span>
                        </p>
                        <hr>
                        <p>
                            <span class="layui-word-aux">买家电话：</span>
                            <span>${lastMap.lastOrder.phone!}</span>
                        </p>
                        <hr>
                        <p>
                            <span class="layui-word-aux">买家身份证：</span>
                            <span>${lastMap.lastOrder.cardno!}</span>
                        </p>
                        <hr>
                        <p>
                            <span class="layui-word-aux">时间：</span>
                            <span>${lastMap.lastOrder.createTime?string('yyyy-MM-dd hh:mm:ss')}</span>
                        </p>
                    </div>
                <#else>
                    <div class="layui-card-body layadmin-takerates">
                        暂无订单数据
                    </div>
                </#if>
            </div>

            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 20px;font-family: 'kaiti';letter-spacing: 2px">最新评论</strong>
                </div>
                <#if lastMap.lastComment??>
                    <div class="layui-card-body layadmin-takerates">
                        <p>
                            <span class="layui-word-aux">用户名称：</span>
                            <span>${lastMap.lastComment.publishName!}</span>
                        </p>
                        <hr>
                        <p style="word-break: break-all;">
                            <span class="layui-word-aux">评论内容：</span>
                            <span>${lastMap.lastComment.content!}</span>
                        </p>
                        <hr>
                        <p th:inline="text">
                            <span class="layui-word-aux">时间：</span>
                            <span>${lastMap.lastComment.createTime?string('yyyy-MM-dd hh:mm:ss')}</span>
                        </p>
                    </div>
                <#else >
                    <div class="layui-card-body layadmin-takerates">
                        暂无评论数据
                    </div>
                </#if>
            </div>

            <div class="layui-card">
                <div class="layui-card-header">
                    <strong style="font-size: 20px;font-family: 'kaiti';letter-spacing: 2px">最新消息</strong>
                </div>
                <#if lastMap.lastMessage??>
                    <div class="layui-card-body layadmin-takerates">
                        <p>
                            <span class="layui-word-aux">用户：</span>
                            <span>${lastMap.lastMessage.sendName!}</span>
                        </p>
                        <hr>
                        <p style="word-break: break-all;">
                            <span class="layui-word-aux">留言：</span>
                            <span>${lastMap.lastMessage.content!}</span>
                        </p>
                        <hr>
                        <p th:inline="text">
                            <span class="layui-word-aux">时间：</span>
                            <span>${lastMap.lastMessage.createTime?string('yyyy-MM-dd hh:mm:ss')}</span>
                        </p>
                    </div>
                <#else >
                     <span style="margin-left: 40%;font-size: 16px;color: #F00;">暂无消息数据</span>
                </#if>
            </div>

        </div>
    </div>
</div>
<script>
    layui.config({
        base: '/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'dataCenter']);
</script>
</body>
</html>