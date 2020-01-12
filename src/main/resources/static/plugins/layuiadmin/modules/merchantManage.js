
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
                , width: 189
                , event: 'remark_detail'
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
                    return d.apply==null?'':'<span>'+d.apply.mcardno+'</span>';
                }
            }
            , {
                field: 'maddr'
                , title: '商家地址'
                , align: 'center'
                , width: 281
                , event: 'maddr'
                , templet: (d) => {
                    return d.apply==null?'':d.apply.maddr;
                }
            }
            , {
                field: 'mlevel'
                , title: '商户称号'
                , align: 'center'
                , width: 182
                , event: 'remark_detail'
                , templet: (d) => {
                    let level = '';
                    switch (d.mlevel) {
                        case "NORMAL":
                            level += '普通商家';
                            break;
                        case "GOLD":
                            level += '金牌商家';
                            break;
                        case "SLIVER":
                            level += '银牌商家';
                            break;
                        case "COPPER":
                            level += '铜牌商家';
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
                , width: 203
                , align: 'center'
                , fixed: 'right'
                , templet: (d) => {
                    var html = '';
                    d.status == 'E' ? html+='<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="disable">禁用</a>'
                                    : html+='<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="enable">启用</a>'
                    return '<a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="notify">通知</a>' + html;
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
            table.reload('article-table', {
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
        }else if (obj.event == 'disable') {
            //TODO
        }else if (obj.event == 'enable') {

        }else if (obj.event == 'notify') {

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