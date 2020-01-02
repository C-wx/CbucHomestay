/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
var editor, editorMd, formSelects;
layui.define(['form', 'formSelects', 'upload', 'element', 'inputTags', 'admin'], function (exports) {
    var $ = layui.$,
        element = layui.element,
        form = layui.form,
        upload = layui.upload,
        inputTags = layui.inputTags;
     formSelects = layui.formSelects;
    element.render();
    form.render();

    formSelects.btns('cateId', []);

    formSelects.maxTips('cateId', function (id, vals, val, max) {
        layer.alert("最多只能选择3个");
    });

    var tagList = tags.split(",");

    inputTags.render({
        elem: '#inputTags',
        content: tagList,
        aldaBtn: false,
        done: function (value) {
        }
    });

    upload.render({
        url: '/upload?reqType=lay' //上传接口
        , elem: '#coverImg' //绑定元素
        , done: function (res) {
            if (res.code === 0) {
                $("#coverImg")
                    .html('<p><img style="width: 100%;height: 120px;margin-top: -20px" src="' + res.data.src + '"></p>');
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }
        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });

    form.on('radio(editor)', function (data) {
        if (data.value === "markdown") {
            editor.remove();
            $(".ke-container-black").hide();
            $("#content-editor").append("<div id='editormd'></div>");
            $.getScript("/static/plugins/editormd/editormd.min.js", function () {
                editorMd = editormd("editormd", {
                    height: 640,
                    watch: false,
                    codeFold: true,
                    toolbarIcons: function () {
                        return [
                            "undo", "redo", "|",
                            "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
                            "h1", "h2", "h3", "h4", "h5", "h6", "|",
                            "list-ul", "list-ol", "hr", "|",
                            "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak", "|",
                            "goto-line", "watch", "preview", "clear", "search", "fullscreen"
                        ]
                    },
                    pluginPath: '/static/plugins/editormd/plugins/',
                    path: '/static/plugins/editormd/lib/',
                    markdown: mdContents,
                    placeholder: '请在此书写你的内容,切换编辑器会清空内容',
                    saveHTMLToTextarea: true,
                    searchReplace: true,
                    taskList: true,
                    tex: true,// 开启科学公式TeX语言支持，默认关闭
                    flowChart: true,//开启流程图支持，默认关闭
                    sequenceDiagram: true,//开启时序/序列图支持，默认关闭,
                    imageUpload: true,
                    imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
                    imageUploadURL: "/upload/editorMD?reqType=lay",
                    onfullscreen: function () {
                        $("#right-card").css("z-index", "-1");
                        editorMd.width("100%");
                    },
                    onfullscreenExit: function () {
                        $("#right-card").css("z-index", "999");
                        editorMd.width("100%");
                    }
                });
            });
        } else if (data.value === "html") {
            KindEditor.options.filterMode = false;
            editorMd.editor.remove();
            $(".ke-container-black").show();
            editor = KindEditor.create('#editor', {
                cssData: 'body {font-family: "Helvetica Neue", Helvetica, "PingFang SC", 微软雅黑, Tahoma, Arial, sans-serif; font-size: 14px}',
                width: "auto",
                height: "600px",
                items: [
                    'source', 'preview', 'undo', 'redo', 'code', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', '/',
                    'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 'image', 'graft',
                    'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
                    'link', 'unlink', 'about', 'br', 'fullscreen'
                ],
                uploadJson: '/upload?reqType=nk',
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
        }
    });

    var post = function (data, msg) {
        data.field.cateIds = formSelects.value('cateId', 'val').toString();
        data.field.aiId = aiId;
        data.field.acId = acId;
        if (data.field.editor === 'html') {
            data.field.mdContent = "";
            data.field.content = editor.html();
        }
        if (data.field.editor === 'markdown') {
            data.field.mdContent = editorMd.getMarkdown();
            data.field.content = editorMd.getHTML();
        }
        data.field.cover = $("#coverImg").find("img").attr("src") || "";
        var $tags = $("#tags>span");
        var tns = [];
        $tags.each(function (index, elem) {
            tns.push($(elem).find("em").text())
        });
        data.field.tagNames = tns.toString();
        layer.msg('提交中，请稍等', {
            icon: 16,//信息框和加载层的私有参数
            shade: 0.01,
            time:800,//自动关闭的时间 也可以通过layer.closeAll();来关闭所有弹层
        });
        Base.ajax("/admin/editArticle", data.field, function (result) {
            if (result.code == Base.status.success) {
                layer.load(2, {time: 1000});
                setTimeout(()=>{
                    layer.msg(msg,{time:1000});
                },1000);
                setTimeout(()=>{
                    location.reload();
                },1000)
            }else{
                layer.msg(result.msg);
            }
        });
    };

    //监听提交9
    form.on('submit(postSubmit)', function (data) {
        post(data, "更新博文成功！");
        return false;
    });

    $("#addRemoveCover").click(function () {
        $("#coverImg").find("img").attr("src", "");
        $("#coverImg").html('  <i class="layui-icon"></i><p>点击上传博文封面，或将封面拖拽到此处</p>');
    });


    exports('article_add', {});
});

$(function () {
    if (!isMD) {
        try {
            editorMd.editor.remove();
        } catch (e) {
            console.log("清除失效");
        }
        KindEditor.options.filterMode = false;
        editor = KindEditor.create('#editor', {
            cssData: 'body {font-family: "Helvetica Neue", Helvetica, "PingFang SC", 微软雅黑, Tahoma, Arial, sans-serif; font-size: 14px}',
            width: "auto",
            height: "600px",
            items: [
                'source', 'preview', 'undo', 'redo', 'code', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', 'quickformat', 'selectall', 'fullscreen', '/',
                'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 'image', 'graft',
                'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
                'link', 'unlink', 'about', 'br', '|', 'hide4comment', 'hide4login', 'hide4purchase'
            ],
            uploadJson: '/management/upload?reqType=nk',
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
    } else {
        $("#content-editor").append("<div id='editormd'></div>");
        $.getScript("/static/plugins/editormd/editormd.min.js", function () {
            editorMd = editormd("editormd", {
                height: 640,
                watch: false,
                codeFold: true,
                toolbarIcons: function () {
                    return [
                        "undo", "redo", "|",
                        "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
                        "h1", "h2", "h3", "h4", "h5", "h6", "|",
                        "list-ul", "list-ol", "hr", "|",
                        "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak", "|",
                        "goto-line", "watch", "preview", "fullscreen", "clsummaryear", "search", "|",
                        "help", "info"
                    ]
                },
                pluginPath: '/static/plugins/editormd/plugins/',
                markdown: mdContents,
                path: '/static/plugins/editormd/lib/',
                placeholder: '请在此书写你的内容',
                saveHTMLToTextarea: true,
                searchReplace: true,
                taskList: true,
                tex: true,// 开启科学公式TeX语言支持，默认关闭
                flowChart: true,//开启流程图支持，默认关闭
                sequenceDiagram: true,//开启时序/序列图支持，默认关闭,
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
                imageUploadURL: "/management/upload/editorMD?reqType=lay",
                onfullscreen: function () {
                    $("#right-card").css("z-index", "-1");
                    editorMd.width("100%");
                },
                onfullscreenExit: function () {
                    $("#right-card").css("z-index", "999");
                    editorMd.width("100%");
                }
            });
        });
    }
});

