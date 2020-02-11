/**
 *  @author   Caiwx
 */

layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , element = layui.element;
    element.render();

    /**
     * 初始化表格
     */
    var commentTable = table.render({
        elem: '#commentTable'
        , url: '/merchant/commentPage'
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
                , title: '评论房间'
                , align: 'center'
                , width: 273
                , event: 'origin_detail'
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
                    var ableHtml = d.childComment == null ?
                        '<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="reply">回复</a>' :
                        '<a class="layui-btn layui-btn-sm" lay-event="look">查看</a>';
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
            table.reload('commentTable', {
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
            table.reload('commentTable', {
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
    table.on('tool(commentTable)', function (obj) {
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
        } else if (obj.event == 'reply' || obj.event == 'look') {
            layer.open({
                type: 2
                , title: '消息中心'
                , shadeClose: true
                , shade: 0.2
                , area: ['396px', '305px']
                , offset: 'auto'
                , content: '/merchant/toReply?oid=' + data.oid + '&rid=' + data.id
            });
        }
    });

    exports('comment', {});
});