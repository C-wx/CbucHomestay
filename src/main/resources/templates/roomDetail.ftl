
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
                <blockquote class="layui-elem-quote layui-text" style="width: 300px">
                    参数部分
                </blockquote>
                <div class="layui-form-item">
                    <label class="layui-form-label">发布人名称:</label>
                    <div class="layui-input-inline">
                        <input disabled class="layui-input" value="${roomInfo.publishName!}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">房间标题:</label>
                    <div class="layui-input-inline">
                        <input disabled class="layui-input" value="${roomInfo.title!}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">房间描述:</label>
                    <div class="layui-input-inline" style="width: 450px;">
                        <textarea disabled class="layui-textarea"
                                  style="cursor: not-allowed;background-color: #f4f4f4;">${roomInfo.des!}</textarea>
                    </div>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">房间价格：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <input type="text" name="price" lay-verify="title" autocomplete="off" placeholder="xxx"
                               class="layui-input" value="${roomInfo.price!}">
                    </div>
                    ￥
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">房间类型：</label>
                    <span>${roomInfo.type}</span>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">是否参加活动：</label>
                    <span>${(roomInfo.isActive == 'Y')?string('是','否')}</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">房间图片：</label>
                    <div id="layer-photos-demo" class="layer-photos-demo">
                        <#list roomInfo.images as image>
                            <img style="width:230px;height: 150px;margin-right: 30px" layer-pid
                                 layer-src="${image.url}" src="${image.url}">
                        </#list>
                    </div>
                </div>
                <blockquote class="layui-elem-quote layui-text" style="width: 300px">
                    属性部分
                </blockquote>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">卧室数量：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <input disabled value="${roomInfo.propertyInfo.bedRoomCount!}" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">床位数量：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <input disabled value="${roomInfo.propertyInfo.bedCount!}" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">卫生间数量：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <input disabled value="${roomInfo.propertyInfo.bathRoomCount!}" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">最多可住：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <input disabled value="${roomInfo.propertyInfo.peopleCount!}" class="layui-input">
                    </div>
                </div>
                <blockquote class="layui-elem-quote layui-text" style="width: 300px">
                    运营部分
                </blockquote>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">房间状态：</label>
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
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">销量：</label>
                    <span>${roomInfo.sales}</span>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">点赞数：</label>
                    <span>${roomInfo.likeCount}</span>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label">评论数：</label>
                    <span>${roomInfo.commentCount}</span>
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
</script>
<style>
    .layui-form-item {
        margin-bottom: 40px;
    }

    .layui-form-item label {
        font-size: 20px;
        font-family: 'kaiti';
        color: #6bb6b5;
        width: 200px;
    }

    .layui-form-item input {
        cursor: not-allowed;
        background-color: #f4f4f4;
    }

    .layui-form-item span {
        font-size: 23px;
        font-family: 'kaiti';
        font-weight: bold;
        letter-spacing: 5px;
        color: #77724c;
    }
</style>
</html>
