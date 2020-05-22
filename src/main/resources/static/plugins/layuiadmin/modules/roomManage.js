/**
 *  @author   Caiwx
 *  @Explain  房源管理脚本
 */

layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , element = layui.element;
    element.render();

    /**
     * 初始化表格
     */
    var roomManageTable = table.render({
        elem: '#roomManageTable'
        , url: '/admin/roomManagePage'
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
                , title: '商家名称'
                , align: 'center'
                , Width: 242
            }
            , {
                field: 'title'
                , title: '房间标题'
                , align: 'center'
                , event: 'detail'
                , width: 232
            }
            , {
                field: 'price'
                , title: '价格'
                , align: 'center'
                , Width: 200
            }
            , {
                field: 'type'
                , title: '房间类型'
                , align: 'center'
                , Width: 200
            }
            , {
                field: 'isActive'
                , title: '是否参加活动'
                , align: 'center'
                , Width: 200
                , templet: (d) => {
                    return d.isActive == 'Y' ? '是' : '否';
                }
            }
            , {
                field: 'status'
                , title: '房间状态'
                , align: 'center'
                , Width: 200
                , templet: (d) => {
                    if (d.auditStatus == 'WA') {
                        return '待审核';
                    } else if (d.auditStatus == 'FA') {
                        return '审核不通过';
                    } else {
                        return d.status == 'FR' ? '空闲中' : d.status == 'B' ? '已预订' : '已下架';
                    }
                }
            }
            , {
                field: 'createTime'
                , title: '创建时间'
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
                        ? '<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="audit" lay-tips="审核">审核</a>'
                        : '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="lookHis" lay-tips="审核历史"><i class="fa fa-eye"></i></a>';
                    var ableHtml = d.status == 'D'
                        ? '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="enable" lay-tips="启用"><i class="fa fa-check" style="color: #ffa022"></i></a>'
                        : '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="disable" lay-tips="禁用"><i class="fa fa-minus-circle" style="color: #ff6017"></i></a>';
                    var detail = '<a lay-tips="房源信息" lay-href="/admin/roomDetail?rid='+d.id+'" class="layui-btn layui-btn-primary layui-btn-sm" href="javascript:;" >查看</a>';
                    return auditHtml + ableHtml + detail;
                }
            }
        ]],
        done: function (res, curr, count) {
            tableList = res.data;
            $('th').css({'background-color': '#86b6c6', 'color': '#fff', 'font-weight': 'bold'})
        }
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
            table.reload('roomManageTable', {
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
            table.reload('roomManageTable', {
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
    table.on('tool(roomManageTable)', function (obj) {
        var data = obj.data;
        if (obj.event == 'detail') {         //点击查看内容详情
            layer.open({
                type: 0
                , title: '内容详情'
                , area: ['100px', '200px']
                , offset: 'auto'
                , shadeClose: true
                , id: 'layerDemo' + data.id
                , content: '<div style="padding: 20px;">' + data.title + '</div>'
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
                , content: '/admin/toAudit?parentId=' + data.id + '&type=ROOM'
            });
        } else if (obj.event == 'lookHis') {                    //点击查看审核历史
            layer.open({
                type: 2
                , title: '审核历史'
                , shadeClose: true
                , shade: 0.2
                , area: ['742px', '350px']
                , offset: 'auto'
                , content: '/admin/toAuditHis?parentId=' + data.id + '&type=ROOM'
            });
        } else if (obj.event == 'disable') {
            layer.confirm('是否禁用该房源?', {icon: 3, title: '提示'}, function (index) {
                Base.ajax("/admin/opeRoom", "POST", {'id': data.id, 'status': 'D'}, (res) => {
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
            layer.confirm('是否启用该房源?', {icon: 3, title: '提示'}, function (index) {
                Base.ajax("/admin/opeRoom", "POST", {'id': data.id, 'status': 'FR'}, (res) => {
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
    table.on('sort(roomManageTable)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        } else if (obj.field == "beginTime") {
            sort = "BEGIN_TIME";
        } else if (obj.field == "endTime") {
            sort = "END_TIME";
        }
        roomManageTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });
    exports('roomManage', {});
});