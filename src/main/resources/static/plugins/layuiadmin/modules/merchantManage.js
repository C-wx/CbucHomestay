/**
 *  @author   Caiwx
 *  @Explain  商户管理脚本
 */

layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , element = layui.element;
    element.render();
    /**
     * 初始化表格
     */
    var merchantTable = table.render({
        elem: '#merchantTable'
        , url: '/admin/merchantPage'
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
                field: 'mname'
                , title: '商户名称'
                , align: 'center'
                , Width: 169
            }
            , {
                field: 'maccount'
                , title: '商户账号'
                , align: 'center'
                , width: 159
            }
            , {
                field: 'mphone'
                , title: '联系电话'
                , align: 'center'
                , width: 176
            }
            , {
                field: 'apply.mcardno'
                , title: '身份证'
                , align: 'center'
                , width: 185
                , templet: (d) => {
                    return d.apply == null ? '' : '<span>' + d.apply.mcardno + '</span>';
                }
            }
            , {
                field: 'maddr'
                , title: '商家地址'
                , align: 'center'
                , width: 281
                , event: 'maddr'
                , templet: (d) => {
                    return d.apply == null ? '' : d.apply.maddr;
                }
            }
            , {
                field: 'mlevel'
                , title: '商户称号'
                , align: 'center'
                , width: 182
                , templet: (d) => {
                    let level = '';
                    switch (d.mlevel) {
                        case "NORMAL":
                            level += '<div >普通商家</div>';
                            break;
                        case "GOLD":
                            level += '<div style="color: #cecf00;">金牌商家</div>';
                            break;
                        case "SLIVER":
                            level += '<div style="color: #bbb6b3;">银牌商家</div>';
                            break;
                        case "COPPER":
                            level += '<div style="color: #c9943f;">铜牌商家</div>';
                            break;
                    }
                    return level;
                }
            }
            , {
                field: 'createTime'
                , title: '创建时间'
                , align: 'center'
                , Width: 195
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                title: '操作'
                , width: 253
                , align: 'center'
                , fixed: 'right'
                , templet: (d) => {
                    var html = '';
                    d.status == 'E' ? html += '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="disable" lay-tips="禁用商家"><i class="fa fa-minus-circle" style="color: #ff6017"></i></a>'
                        : html += '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="enable" lay-tips="启用商家"><i class="fa fa-check" style="color: #ffa022"></i></a>'
                    return '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="notify" lay-tips="通知"><i class="fa fa-bullhorn"></i></a>'
                        + '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="msgHistory" lay-tips="通知记录"><i class="fa fa-ellipsis-h"></i></a>'
                        + html
                        + '<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="editMerchant" lay-tips="编辑商家"><i class="fa fa-pencil-square-o"></i></a>';
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
        keyLike: function () {                          //关键字模糊查询
            const titleSearch = $('#titleKeyword');
            //执行重载
            table.reload('merchantTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: titleSearch.val()
                }
            });
        },
        reload: function () {                           //重置页面重载
            $('#titleKeyword').val("");
            const titleSearch = $('#titleKeyword');
            table.reload('merchantTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: titleSearch.val()
                }
            });
        }
    };

    /**
     * 创建监听工具
     */
    table.on('tool(merchantTable)', function (obj) {
        var data = obj.data;
        if (obj.event == 'maddr') {
            layer.open({
                type: 0
                , title: '内容详情'
                , offset: 'auto'
                , shadeClose: true
                , id: 'layerDemo' + data.id
                , content: '<div style="padding: 20px;">' + data.apply.maddr + '</div>'
                , shade: 0.3
                , anim: 5
            });
        } else if (obj.event == 'disable') {
            layer.confirm('是否禁用该商户?', {icon: 3, title: '提示'}, function (index) {
                Base.ajax("/admin/opeMerchant", "POST", {'id': data.id, 'status': 'D'}, (res) => {
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
            layer.confirm('是否启用该商户?', {icon: 3, title: '提示'}, function (index) {
                Base.ajax("/admin/opeMerchant", "POST", {'id': data.id, 'status': 'E'}, (res) => {
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
        } else if (obj.event == 'notify') {
            layer.open({
                type: 2
                ,
                title: '消息中心'
                ,
                shadeClose: true
                ,
                shade: 0.2
                ,
                area: ['396px', '505px']
                ,
                offset: 'auto'
                ,
                content: '/sendMsg?receiveId=' + data.id + '&sendType=ADMIN' + '&mName=' + data.mname + '&receiveType=MERCHANT'
            });
        } else if (obj.event == 'msgHistory') {
            layer.open({
                type: 2
                ,
                title: '消息中心'
                ,
                shadeClose: true
                ,
                shade: 0.2
                ,
                area: ['742px', '350px']
                ,
                offset: 'auto'
                ,
                content: '/toMsgHistory?receiveId=' + data.id + '&sendType=ADMIN' + '&mName=' + data.mname + '&receiveType=MERCHANT'
            });
        } else if (obj.event == 'editMerchant') {
            layer.open({
                type: 2
                , title: '编辑'
                , shadeClose: true
                , shade: 0.2
                , area: ['600px', '790px']
                , offset: 'auto'
                , content: 'editMerchant?id=' + data.id
            });
        }
    });

    /**
     * 按照创建时间排序
     */
    table.on('sort(merchantTable)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        }
        merchantTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });
    exports('merchantManage', {});
});