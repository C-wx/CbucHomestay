var editor;
layui.define(['form', 'element', 'laydate', 'table'], function (exports) {
    var $ = layui.$,
        element = layui.element,
        form = layui.form,
        laydate = layui.laydate;
    element.render();
    form.render();

    laydate.render({
        elem: '#beginTime',
        type: 'datetime',
        value: new Date(),
        min: 0
    });
    laydate.render({
        elem: '#endTime',
        type: 'datetime',
        value: new Date(Date.parse(new Date())+100000000),
        min: 0
    });

    /**
     * 引入富文本组价
     */
    KindEditor.options.filterMode = false;
    KindEditor.ready(function (K) {
        editor = K.create('#editor', {
            cssData: 'body {font-family: 微软雅黑; font-size: 16px}',
            width: "900px",
            height: "300px",
            items: [
                'undo', 'redo', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold', 'italic'
            ],
            dialogOffset: 0,
            themeType: 'black',
            resizeType: 0//可以改变高度
        });
    });

    /**
     * 发布公告
     */
    form.on('submit(postSubmit)', function (data) {
        var content = data.field.content = editor.html();
        if (content == "") {
            return layer.msg("发布内容不能为空");
        }
        Base.ajax("pubBulletin","PUT" ,data.field , function (res) {
            if (res.code == Base.status.success) {
                layer.msg("发布成功",{icon:6,time:500});
                setTimeout(function () {
                    getHisBulletin();
                    editor.html("");
                }, 500)
            } else {
                layer.msg(res.msg);
            }
        });
        return false;
    });

    /**
     * 获取历史公告
     */
    getHisBulletin();
    function getHisBulletin(){
        $("#historyBulletin").html("");
        Base.ajax("/admin/getHisBulletin", "POST", null, function (result) {
            if (result.code == Base.status.success) {
                result.data.forEach((data) => {
                    var $html =
                        "<li class=\"layui-timeline-item\">\n" +
                        "    <i class=\"layui-icon layui-timeline-axis\">&#xe63f;</i>\n" +
                        "    <div class=\"layui-timeline-content layui-text\">\n" +
                        "        <h3 class=\"layui-timeline-title\">" + Base.formatDate(data.createTime, "yyyy-MM-dd HH:mm") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ Base.isEnable(data) + "</h3>" +
                        "        <p style='cursor:pointer;' onclick='lookDetail("+data.id+")'>\n" + data.content +
                        "        </p>\n" +
                        "    </div>\n" +
                        "</li>";
                    $("#historyBulletin").append($html);
                });
                const $end =
                    "<li class=\"layui-timeline-item\">\n" +
                    "    <i class=\"layui-icon layui-timeline-axis\" style='color: red'>&#xe63f;</i>\n" +
                    "    <div class=\"layui-timeline-content layui-text\">\n" +
                    "        <div class=\"layui-timeline-title\">过去</div>\n" +
                    "    </div>\n" +
                    " </li>"
                $("#historyBulletin").append($end);
            } else {
                layer.msg(result.msg);
            }
        });
    }

    /**
     * 查看公告详情
     */
    lookDetail = (e) => {
        layer.open({
            type: 2
            , title: '公告详情'
            , shadeClose: true
            , shade: 0.2
            , area: ['442px', '450px']
            , offset: 'auto'
            , content: '/admin/toBulletinDetail?id=' + e
        });
    };

    /**
     * 删除公告
     */
    delBulletin = (e) => {
        layer.confirm('是否删除该公告?', {icon: 3, title: '提示'}, function (index) {
            Base.ajax("/admin/opeBulletin", "POST", {'id': e, 'status': 'D'}, (res) => {
                if (res.code === Base.status.success) {
                    layer.msg("操作成功", {icon: 6, time: 800});
                    setTimeout(() => {
                        layer.close(index);
                        getHisBulletin();
                    }, 800)
                } else {
                    layer.msg(res.msg, {icon: 5, time: 500});
                }
            })
        });
    };

    exports('bulletinPublish', {});
});


