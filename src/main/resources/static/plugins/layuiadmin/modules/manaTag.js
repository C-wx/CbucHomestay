/**
 * Copyright:CBUC
 */
var tagTable;
layui.define(['form', 'element', 'table', 'laydate'], function () {
    var $ = layui.$,
        element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table;
    element.render();
    form.render();

    laydate.render({
        elem: '#beginTime',
        type: 'datetime',
        value: new Date(),
    });
    laydate.render({
        elem: '#endTime',
        type: 'datetime',
        value: new Date(Date.parse(new Date()) + 100000000)
    });

    tagTable = table.render({
        elem: '#tagTable'
        , url: '/admin/tagPage'
        , page: true
        , limit: 10
        , height: 'full'
        , method: 'get'
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , cols: [[
            {
                field: 'name'
                , width: 300
                , title: '标签内容'
            }
            , {
                field: 'createTime'
                , title: '创建时间'
                , sort: true
                , width: 300
                , align: 'center'
                , minWidth: 100
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                title: '状态'
                , width: 150
                , align: 'center'
                , templet: (d) => {
                    if (d.status == 'D') {
                        return "已失效";
                    } else {
                        return "生效中";
                    }
                }
            }
            , {
                title: '操作'
                , width: 280
                , align: 'center'
                , templet: (d) => {
                    return "<button class='layui-btn layui-btn-primary' dataId=" + d.id + " onclick='deleteTag(this)' style='height: 24px;line-height: 24px'>删除</button>" +
                        "<button class='layui-btn layui-btn-primary' id='updateId" + d.id + "' dataId=" + d.id + " onclick='updateTag(this)' style='height: 24px;line-height: 24px'>修改</button>"
                }
            }
        ]]
    });
});
$(function () {
    Base.ajax("/admin/getHisTag", null, function (result) {
        if (result.code == Base.status.success) {
            result.data.forEach((data) => {
                var $html =
                    "<li class=\"layui-timeline-item\">\n" +
                    "    <i class=\"layui-icon layui-timeline-axis\">&#xe63f;</i>\n" +
                    "    <div class=\"layui-timeline-content layui-text\">\n" +
                    "        <h3 class=\"layui-timeline-title\">" + Base.formatDate(data.createTime, "yyyy-MM-dd HH:mm") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + Base.isEnable(data) + "</h3>\n" +
                    "        <p>\n" + data.name +
                    "        <br></p>\n" +
                    "    </div>\n" +
                    "</li>";
                $("#historyTag").append($html);
            });
            const $end =
                "<li class=\"layui-timeline-item\">\n" +
                "    <i class=\"layui-icon layui-timeline-axis\" style='color: red'>&#xe63f;</i>\n" +
                "    <div class=\"layui-timeline-content layui-text\">\n" +
                "        <div class=\"layui-timeline-title\">过去</div>\n" +
                "    </div>\n" +
                " </li>"
            $("#historyTag").append($end);
        } else {
            layer.msg(result.msg);
        }
    });


});
deleteTag = (d) => {
    layer.msg("<strong style='display:block;text-align: center;font-size: 20px'>确认删除?</strong>", {
        btn: ['取消', '确定']
        , skin: 'layui-layer-lan'
        , yes: function () {
            layer.closeAll();
        }
        , btn2: function () {
            Base.ajax("deleteTag", {'id': $(d).attr("dataId")}, function (result) {
                if (result.code == Base.status.success) {
                    layer.msg("删除成功!", {icon: 5});
                    setTimeout(() => {
                        tagTable.reload();
                    }, 500)
                }
            });
        }
    })
};
updateTag = (d) => {
    layer.open({
        type: 4
        ,
        skin: 'layui-layer-lan'
        ,
        width: '400'
        ,
        content: ['<input id="mod_Tag" style="font-size:22px;margin: 20px 50px;border: none;border-bottom:1px rgb(177,255,172) solid;width: 200px;background-color: #92B8B1"/>', '#updateId' + $(d).attr("dataId")]
        ,
        btn: ['关闭', '保存']
        ,
        shade: 0
        ,
        yes: function () {
            layer.closeAll();
        }
        ,
        btn2: function () {
            Base.ajax("modTag", {'id': $(d).attr("dataId"), 'name': $("#mod_Tag").val()}, function (result) {
                if (result.code == Base.status.success) {
                    layer.msg("修改成功", {icon: 6});
                    setTimeout(function () {
                        tagTable.reload();
                    }, 500)
                }
            })
        }
    });
};
addTag = () => {
    layer.open({
        type: 4
        ,
        skin: 'layui-layer-lan'
        ,
        width: '400'
        ,
        content: ['<input id="tag" style="font-size:22px;margin: 20px 50px;border: none;border-bottom:1px rgb(177,255,172) solid;width: 200px;background-color: #92B8B1"/>', '#addTag']
        ,
        btn: ['关闭', '保存']
        ,
        shade: 0
        ,
        yes: function () {
            layer.closeAll();
        }
        ,
        btn2: function () {
            Base.ajax("addTag", {'name': $("#tag").val()}, function (result) {
                if (result.code == Base.status.success) {
                    layer.msg("添加成功", {icon: 6});
                    setTimeout(function () {
                        var $html =
                            "<li class=\"layui-timeline-item\">\n" +
                            "    <i class=\"layui-icon layui-timeline-axis\">&#xe63f;</i>\n" +
                            "    <div class=\"layui-timeline-content layui-text\">\n" +
                            "        <h3 class=\"layui-timeline-title\">" + Base.formatDate(result.data.createTime, "yyyy-MM-dd HH:mm") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(生效中)</h3>\n" +
                            "        <p>\n" + result.data.name +
                            "        <br><br></p>\n" +
                            "    </div>\n" +
                            "</li>";
                        $("#historyTag").prepend($html);
                        tagTable.reload();
                    }, 500)
                }
            })
        }
    });
};
