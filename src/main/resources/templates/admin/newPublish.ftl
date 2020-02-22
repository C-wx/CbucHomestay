
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
    var editor;
    layui.define(['form', 'element', 'laydate', 'table', 'upload'], function (exports) {
        var $ = layui.$,
                element = layui.element,
                form = layui.form,
                upload = layui.upload,
                laydate = layui.laydate;
        element.render();
        form.render();

        laydate.render({
            elem: '#beginTime',
            type: 'datetime',
            value: new Date(),
            min: 0,
            trigger: 'click'
        });
        laydate.render({
            elem: '#endTime',
            type: 'datetime',
            value: new Date(Date.parse(new Date())+100000000),
            min: 0,
            trigger: 'click'
        });

        upload.render();
        /**
         * 引入富文本组价
         */
        KindEditor.options.filterMode = false;
        KindEditor.ready(function (K) {
            editor = K.create('#editor', {
                cssData: 'body {font-family: 微软雅黑; font-size: 16px}',
                width: "1100px",
                height: "700px",
                items: [
                    'source', 'preview', 'undo', 'redo', 'code', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall',
                    'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 'image',
                    'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
                    'link', 'unlink', 'fullscreen'
                ],
                uploadJson: '/uploadImg',
                dialogOffset: 0, //对话框距离页面顶部的位置，默认为0居中，
                allowImageUpload: true,
                allowMediaUpload: true,
                themeType: 'black',
                fixToolBar: true,
                autoHeightMode: true,
                filePostName: 'file',//指定上传文件form名称，默认imgFile
                resizeType: 1,//可以改变高度
                afterCreate: function () {
                    var self = this;
                    KindEditor.ctrl(document, 13, function () {
                        self.sync();
                        K('form[name=example]')[0].submit();
                    });
                    KindEditor.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        KindEditor('form[name=example]')[0].submit();
                    });
                },
                //错误处理 handler
                errorMsgHandler: function (message, type) {
                    try {
                        JDialog.msg({type: type, content: message, timer: 2000});
                    } catch (Error) {
                        alert(message);
                    }
                }
            });
        });
        /**
         * 发布资讯
         */
        form.on('submit(postSubmit)', function (data) {
            var content = data.field.content = editor.html();
            if (content == "") {
                return layer.msg("发布内容不能为空");
            }
            Base.ajax("pubNews","PUT" ,data.field , function (res) {
                if (res.code == Base.status.success) {
                    layer.msg("发布成功",{icon:6,time:500});
                    setTimeout(function () {
                        $("#title").val("");
                        editor.html("");
                    }, 500)
                } else {
                    layer.msg(res.msg);
                }
            });
            return false;
        });
        exports('newsPublish', {});
    });
</script>
</body>
</html>