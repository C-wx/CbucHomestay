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
            uploadJson: '/upload',
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


