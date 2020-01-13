
/**
 *  @author   Caiwx
 *  @Explain  消息中心脚本
 */

layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , element = layui.element
        , form = layui.form;
    element.render();

    /**
     * 初始化表格
     */
    var msgCenterTable = table.render({
        elem: '#msgCenterTable'
        , url: '/msgCenterPage'
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
                field: 'sendName'
                , title: '发送人'
                , align: 'center'
                , Width: 301
            }
            , {
                field: 'content'
                , title: '消息内容'
                , align: 'center'
                , width: 562
                , event: 'detail'
            }
            , {
                field: 'createTime'
                , title: '发送时间'
                , align: 'center'
                , Width: 341
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                title: '操作'
                , width: 376
                , align: 'center'
                , fixed: 'right'
                , toolbar: '#toolBar'
            }
        ]]
    });

    /**
     * 监听表单开关
     */
    form.on('switch(read)', function () {
        const checkedStatus = this.checked?"YR":"WR";
        Base.ajax("/checkReadStatus","POST", {id: this.value, readStatus: checkedStatus}
            , (result) => {
                if (result.code == Base.status.success) {
                    if (result.data < 1) {
                        $("#msgNum", window.parent.document).html(' ');
                    } else {
                        $("#msgNum", window.parent.document).html(result.data);
                    }
                }else{
                    layer.msg(result.msg);
                    setTimeout(()=>{
                        $(this).context.checked = !checkedStatus;
                        form.render('checkbox');
                    },500);
                }
            }
        );
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
            table.reload('msgCenterTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    sendId: mNameKey.val()
                }
            });
        },
        reload: function () {                           //重置加载页面
            $('#mNameKey').val("");
            table.reload('msgCenterTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    sendId: $('#mNameKey').val()
                }
            });
        }
    };

    /**
     * 创建监听工具
     */
    table.on('tool(msgCenterTable)', function (obj) {
        var data = obj.data;
        if (obj.event == 'detail') {         //点击查看内容详情
            layer.open({
                type: 0
                , title: '内容详情'
                , offset: 'auto'
                , shadeClose: true
                , id: 'layerDemo' + data.id
                , content: '<div style="padding: 20px;">' + data.content + '</div>'
                , shade: 0.3
                , anim: 5
            });
        } else if (obj.event == 'reply') {                       //点击审核商家
            layer.open({
                type: 2
                , title: '消息中心'
                , shadeClose: true
                , shade: 0.2
                , area: ['396px', '505px']
                , offset: 'auto'
                , content: '/sendMsg?receiveId=' + data.sendId + '&sendType=MERCHANT' + '&mName=' + data.sendName + '&receiveType=' + data.sendType
            });
        }
    });

    /**
     * 按照创建时间排序
     */
    table.on('sort(msgCenterTable)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        }
        msgCenterTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });
    exports('msgCenter', {});
});