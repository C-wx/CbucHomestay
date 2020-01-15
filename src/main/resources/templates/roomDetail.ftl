
<!--管理员之房源详情界面-->

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
</head>
<body>
<div class="layui-fluid layui-anim layui-anim-scale" style="padding: 30px;">
    <div class="layui-row layui-card">
        <div class="layui-card-body" style="padding-top: 25px;">
            <from class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;发布人名称：</label>
                    <span>${roomInfo.publishName}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间标题：</label>
                    <span>${roomInfo.title}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间描述：</label>
                    <span>${roomInfo.des}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间图片：</label>
                    <div id="layer-photos-demo" class="layer-photos-demo">
                        <#list roomInfo.images as image>
                            <img style="width:230px;height: 150px;margin-right: 30px" layer-pid
                                 layer-src="${image.url}" src="${image.url}">
                        </#list>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间价格：</label>
                    <span>${roomInfo.price}￥</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间状态：</label>
                    <#if roomInfo.auditStatus == 'WA'>
                        <span>待审核</span>
                    <#else >
                        <#if roomInfo.status == 'FR'>
                            <span>空闲中</span>
                        <#elseif rooomInfo.status == 'B'>
                            <span>已预订</span>
                        <#else >
                            <span>已下架</span>
                        </#if>
                    </#if>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间类型：</label>
                    <span>${roomInfo.type}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;是否参加活动：</label>
                    <span>${(roomInfo.isActive == 'Y')?string('是','否')}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;销量：</label>
                    <span>${roomInfo.sales}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;点赞数：</label>
                    <span>${roomInfo.likeCount}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;评论数：</label>
                    <span>${roomInfo.commentCount}</span>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="reset" class="layui-btn" id="returnBtn">返回
                        </button>
                    </div>
                </div>
            </from>
        </div>
    </div>
</div>
</body>
<script>
    $("body").on("click", ".layer-photos-demo img", function (e) {
        layer.photos({
            photos: ".layer-photos-demo"
            , anim: 5
        });
    });
    $("body").on("click", "#returnBtn", function (e) {
        parent.layui.admin.events.closeThisTabs()
    });
</script>
<style>
    .layui-form-item {margin-bottom: 40px;}
    .layui-form-item em {color: indianred;}
    .layui-form-item label {font-size: 20px;font-family: 'kaiti';color: #6bb6b5;width: 200px;}
    .layui-form-item input {width: 250px;}
    .layui-form-item span {background-color: #efefef;border-radius: 5px;font-size: 25px;font-family: 'kaiti';font-weight: bold;letter-spacing: 5px;color: #77724c;}
    #returnBtn{position: fixed;top: 65%;left: 80%;}
</style>
</html>
