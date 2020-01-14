/**
 *  @author   Caiwx
 *  @Explain  资讯管理脚本
 */

layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , element = layui.element;
    element.render();

    /**
     * 初始化表格
     */
    var newsManageTable = table.render({
        elem: '#newsManageTable'
        , url: '/admin/newsManagePage'
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
                , title: '发布人名称'
                , align: 'center'
                , Width: 242
            }
            , {
                field: 'content'
                , title: '资讯内容'
                , align: 'center'
                , width: 232
                , event: 'detail'
            }
            , {
                field: 'createTime'
                , title: '申请时间'
                , align: 'center'
                , Width: 200
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                field: 'beginTime'
                , title: '开始展示时间'
                , align: 'center'
                , Width: 200
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                field: 'endTime'
                , title: '结束展示时间'
                , align: 'center'
                , Width: 200
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                title: '操作'
                , width: 200
                , align: 'center'
                , fixed: 'right'
                , templet: (d) => {
                    var auditHtml = d.auditStatus == 'WA'
                        ? '<a class="layui-btn layui-bg-red layui-btn-sm" lay-event="audit">审核</a>'
                        : '<a class="layui-btn layui-bg-lightsteelblue layui-btn-sm" lay-event="lookHis">查看</a>';
                    var ableHtml = d.status == 'E'
                        ? '<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="disable">禁用</a>'
                        : '<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="enable">启用</a>';
                    return auditHtml + ableHtml;
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
            table.reload('newsManageTable', {
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
            table.reload('newsManageTable', {
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
    table.on('tool(newsManageTable)', function (obj) {
        var data = obj.data;
        if (obj.event == 'detail') {         //点击查看内容详情
            layer.open({
                type: 0
                , title: '内容详情'
                , area: ['700px', '800px']
                , offset: 'auto'
                , shadeClose: true
                , id: 'layerDemo' + data.id
                , content: '<div style="padding: 20px;">' + data.content + '</div>'
                , shade: 0.3
                , anim: 5
            });
        } else if (obj.event == 'audit') {                       //点击审核商家
            layer.open({
                type: 2
                , title: '审核'
                , shadeClose: true
                , shade: 0.2
                , area: ['396px', '505px']
                , offset: 'auto'
                , content: '/admin/toAudit?parentId=' + data.id + '&type=NEWS'
            });
        } else if (obj.event == 'lookHis') {                    //点击查看审核历史
            layer.open({
                type: 2
                , title: '审核历史'
                , shadeClose: true
                , shade: 0.2
                , area: ['742px', '350px']
                , offset: 'auto'
                , content: '/admin/toAuditHis?parentId=' + data.id + '&type=NEWS'
            });
        } else if (obj.event == 'disable') {
            layer.confirm('是否禁用该公告?', {icon: 3, title: '提示'}, function (index) {
                Base.ajax("/admin/opeNews", "POST", {'id': data.id, 'status': 'D'}, (res) => {
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
                Base.ajax("/admin/opeNews", "POST", {'id': data.id, 'status': 'E'}, (res) => {
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
    table.on('sort(newsManageTable)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        } else if (obj.field == "beginTime") {
            sort = "BEGIN_TIME";
        } else if (obj.field == "endTime") {
            sort = "END_TIME";
        }
        newsManageTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });
    exports('bulletinAudit', {});
});