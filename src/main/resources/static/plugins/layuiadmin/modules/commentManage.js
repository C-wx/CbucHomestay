/**
 *  @author   Caiwx
 *  @Explain  公告审核脚本
 */

layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , element = layui.element;
    element.render();

    /**
     * 初始化表格
     */
    var commentManageTable = table.render({
        elem: '#commentManageTable'
        , url: '/admin/commentManagePage'
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
                field: 'publishName'
                , title: '评论人名称'
                , align: 'center'
                , Width: 186
            }
            , {
                field: 'origin'
                , title: '评论源'
                , align: 'center'
                , width: 273
                , event: 'origin_detail'
            }
            , {
                field: 'type'
                , title: '评论类型'
                , align: 'center'
                , width: 150
                , templet: (d) => {
                    return d.type == '1' ? '一级评论' : '二级评论';
                }
            }
            , {
                field: 'content'
                , title: '评论内容'
                , align: 'center'
                , width: 264
                , event: 'content_detail'
            }
            , {
                field: 'likeCount'
                , title: '点赞数'
                , align: 'center'
                , width: 155
            }
            , {
                field: 'commentCount'
                , title: '评论数'
                , align: 'center'
                , width: 160
            }
            , {
                field: 'createTime'
                , title: '评论时间'
                , align: 'center'
                , Width: 183
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                title: '操作'
                , width: 202
                , align: 'center'
                , fixed: 'right'
                , templet: (d) => {
                    var ableHtml = d.status == 'E'
                        ? '<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="disable">禁用</a>'
                        : '<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="enable">启用</a>';
                    return ableHtml;
                }
            }
        ]]
    });

    /**
     * 监听事件
     */
    $('button[data-type]').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    var active = {
        keyLike: function () {                          //关键词模糊搜索
            const content = $('#content');
            //执行重载
            table.reload('commentManageTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    content: content.val()
                }
            });
        },
        reload: function () {                           //重置加载页面
            $('#content').val("");
            table.reload('commentManageTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    content: $('#content').val()
                }
            });
        }
    };

    /**
     * 创建监听工具
     */
    table.on('tool(commentManageTable)', function (obj) {
        var data = obj.data;
        if (obj.event.indexOf('detail') != -1) {         //点击查看内容详情
            var flag = obj.event.split("_")[0];
            var html = '';
            if (flag == 'origin') {
                html += data.origin;
            } else {
                html += data.content;
            }
            layer.open({
                type: 0
                , title: '内容详情'
                , offset: 'auto'
                , shadeClose: true
                , id: 'layerDemo' + data.id
                , content: '<div style="padding: 20px;">' + html + '</div>'
                , shade: 0.3
                , anim: 5
            });
        } else if (obj.event == 'disable') {
            layer.confirm('是否禁用该评论?', {icon: 3, title: '提示'}, function (index) {
                Base.ajax("/admin/opeComment", "POST", {'id': data.id, 'status': 'D'}, (res) => {
                    if (res.code === Base.status.success) {
                        layer.msg("操作成功", {icon: 6, time: 800});
                        setTimeout(() => {
                            layer.close(index);
                            $(".layui-icon-refresh").click();
                        }, 800)
                    } else {
                        layer.msg(res.msg, {icon: 5, time: 500});
                    }
                })
            });
        } else if (obj.event == 'enable') {
            layer.confirm('是否启用该公告?', {icon: 3, title: '提示'}, function (index) {
                Base.ajax("/admin/opeComment", "POST", {'id': data.id, 'status': 'E'}, (res) => {
                    if (res.code === Base.status.success) {
                        layer.msg("操作成功", {icon: 6, time: 800});
                        setTimeout(() => {
                            layer.close(index);
                            $(".layui-icon-refresh").click();
                        }, 800)
                    } else {
                        layer.msg(res.msg, {icon: 5, time: 500});
                    }
                })
            });
        }
    });

    /**
     * 创建排序规则
     */
    table.on('sort(commentManageTable)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        } else if (obj.field == "beginTime") {
            sort = "BEGIN_TIME";
        } else if (obj.field == "endTime") {
            sort = "END_TIME";
        }
        commentManageTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });
    exports('commentManage', {});
});