
<!--商家之房源操作界面-->

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
        <div class="layui-card-body" style="padding-top: 25px;">
            <from class="layui-form" enctype="multipart/form-data" method="post" id="roomForm">
                <input type="hidden" name="id" value="${(ri.id)!""}" id="rid">
                <blockquote class="layui-elem-quote layui-text" style="width: 300px">
                    参数填写
                </blockquote>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间标题:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                               class="layui-input" value="${(ri.title)!""}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间描述:</label>
                    <div class="layui-input-inline" style="width: 450px">
                        <textarea placeholder="请输入房间描述" class="layui-textarea" name="des" id="textarea">${(ri.des)!""}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间图片：</label>
                    <div class="layui-input-inline" id="addImg">
                        <div class="content-img">
                            <ul class="content-img-list"></ul>
                            <div class="file" style="cursor:pointer;">
                                <span style="line-height: 150px" onclick="clickImage()"><i
                                        class="layui-icon layui-icon-picture" style="font-size: 160px"></i></span>
                                <input type="file" name="files" accept="image/*" id="upload" style="display: none">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间价格：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <input type="text" name="price" lay-verify="title" autocomplete="off" placeholder="xxx"
                               class="layui-input" value="${(ri.price)!""}">
                    </div>
                    ￥
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;房间类型：</label>
                    <div class="layui-input-inline">
                        <select name="type">
                            <option value="">请选择类型</option>
                            <option value="大床房" ${(ri.type == '大床房')???string('selected','')}>大床房</option>
                            <option value="单人间" ${(ri.type == '单人间')???string('selected','')}>单人间</option>
                            <option value="双人间" ${(ri.type == '双人间')???string('selected','')}>双人间</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;是否参加活动：</label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="isActive" id="active"
                               lay-skin="switch" ${(ri.isActive=='Y')?string('checked','')} lay-text="是|否">
                    </div>
                </div>
                <blockquote class="layui-elem-quote layui-text" style="width: 300px">
                    属性填写
                </blockquote>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;卧室数量：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <#if ri??>
                            <input type="text" name="bedRoomCount" lay-verify="title" autocomplete="off"
                                   value="${ri.propertyInfo.bedRoomCount!}" class="layui-input">
                        <#else >
                           <input type="text" name="bedRoomCount" lay-verify="title" autocomplete="off"
                                  class="layui-input">
                        </#if>
                    </div>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;床位数量：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <#if ri??>
                            <input type="text" name="bedCount" lay-verify="title" autocomplete="off"
                                   value="${ri.propertyInfo.bedCount!}" class="layui-input">
                        <#else >
                            <input type="text" name="bedCount" lay-verify="title" autocomplete="off"
                                   class="layui-input">
                        </#if>

                    </div>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;卫生间数量：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <#if ri??>
                            <input type="text" name="bathRoomCount" lay-verify="title" autocomplete="off"
                                   value="${ri.propertyInfo.bathRoomCount!}" class="layui-input">
                        <#else >
                            <input type="text" name="bathRoomCount" lay-verify="title" autocomplete="off"
                                   class="layui-input">
                        </#if>
                    </div>
                </div>
                <div class="layui-form-item" style="display: inline-block">
                    <label class="layui-form-label"><em>*</em>&nbsp;&nbsp;最多可住：</label>
                    <div class="layui-input-inline" style="width: 70px;">
                        <#if ri??>
                            <input type="text" name="peopleCount" lay-verify="title" autocomplete="off"
                                   value="${ri.propertyInfo.peopleCount!}" class="layui-input">
                        <#else >
                            <input type="text" name="peopleCount" lay-verify="title" autocomplete="off"
                                   class="layui-input">
                        </#if>
                    </div>
                </div>
                <div class="opeBtn">
                    <button type="button" class="layui-btn layui-btn-lg layui-btn-warm layui-btn-radius" lay-submit
                            lay-filter="postSubmit">
                        发布
                    </button>
                    <button type="reset" class="layui-btn layui-btn-lg layui-btn-radius" id="returnBtn">返回</button>
                </div>
            </from>
        </div>
    </div>
</div>
</body>
<script>
    layui.config({
        base: '/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'opeRoom']);
</script>
<style>
    .layui-form-item {
        margin-bottom: 40px;
    }

    .layui-form-item em {
        color: indianred;
    }

    .layui-form-item label {
        font-size: 20px;
        font-family: 'kaiti';
        color: #518a89;
        width: 200px;
    }

    .opeBtn{
        margin-left: 43%;
    }

    #addImg{
        width: 840px;
        height: 180px;
        padding: 10px;
    }
    .hide{
        display: none;
    }
    .delete-btn{
        position:relative;
        top: -57px;
        left: -44px;
        z-index: 999;
        font-size:25px;
        color: red;
    }
    .content-img-list-item{
        float: left;
        margin-right: 45px;
    }
    .content-img-list-item img {
        width: 200px;
        height: 150px;
        margin-right: 20px;
    }
    .file{
        height: 150px;
    }
    .max{
        width: 100%;
        height: auto;
    }
</style>
</html>
