
/**
 *  @author   Caiwx
 *  @Explain  商户审核脚本
 */

layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , element = layui.element;
    element.render();

    /**
     * 初始化表格
     */
    var merchantApplyTable = table.render({
        elem: '#merchantApplyTable'
        , url: '/admin/merchantApplyPage'
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
                , Width: 217
            }
            , {
                field: 'mphone'
                , title: '联系电话'
                , align: 'center'
                , width: 173
            }
            , {
                field: 'mcardno'
                , title: '身份证'
                , align: 'center'
                , width: 182
            }
            , {
                field: 'maddr'
                , title: '商家地址'
                , align: 'center'
                , width: 266
                , event: 'addr_detail'
            }
            , {
                field: 'remark'
                , title: '申请说明'
                , align: 'center'
                , width: 225
                , event: 'remark_detail'
            }
            , {
                field: 'mlicense'
                , title: '营业执照'
                , align: 'center'
                , width: 129
                , event: 'lookImg'
                , templet: function () {
                    return '<span class="look">查看大图</span>';
                }
            }
            , {
                field: 'createTime'
                , title: '申请时间'
                , align: 'center'
                , Width: 218
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                title: '操作'
                , width: 169
                , align: 'center'
                , fixed: 'right'
                , templet: (d) => {
                    return d.auditStatus == 'WA'
                        ? '<a class="layui-btn layui-bg-red layui-btn-sm" lay-event="audit">审核</a>'
                        : '<a class="layui-btn layui-bg-lightsteelblue layui-btn-sm" lay-event="lookHis">查看</a>';
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
            const mNameKey = $('#mNameKey');
            //执行重载
            table.reload('merchantApplyTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: mNameKey.val()
                }
            });
        },
        reload: function () {                           //重置加载页面
            $('#mNameKey').val("");
            table.reload('merchantApplyTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: $('#mNameKey').val()
                }
            });
        }
    };

    /**
     * 创建监听工具
     */
    table.on('tool(merchantApplyTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'lookImg') {                          //点击查看大图
            var imgHtml = "<img src='" + data.mlicense + "' width='600px' height='550px'/>";
            layer.open({
                type: 1,
                shade: 0.5,
                offset: 'auto',
                area: [600 + 'px', 550 + 'px'],
                shadeClose: true,
                scrollbar: false,
                title: false,
                content: imgHtml,
                resize: false
            });
        } else if (obj.event.indexOf('detail') != -1) {         //点击查看内容详情
            var flag = obj.event.split("_")[0];
            var html = '';
            if (flag == 'addr') {
                html += data.maddr;
            } else {
                html += data.remark;
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
        } else if (obj.event == 'audit') {                       //点击审核商家
            layer.open({
                type: 2
                , title: '审核'
                , shadeClose: true
                , shade: 0.2
                , area: ['396px', '505px']
                , offset: 'auto'
                , content: '/admin/toAudit?parentId=' + data.id + '&type=MERCHANT'
            });
        } else if (obj.event == 'lookHis') {                    //点击查看审核历史
            layer.open({
                type: 2
                , title: '审核历史'
                , shadeClose: true
                , shade: 0.2
                , area: ['742px', '350px']
                , offset: 'auto'
                , content: '/admin/toAuditHis?parentId=' + data.id + '&type=MERCHANT'
            });
        }
    });

    /**
     * 按照创建时间排序
     */
    table.on('sort(merchantApplyTable)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        }
        merchantApplyTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });
    exports('merchantAudit', {});
});