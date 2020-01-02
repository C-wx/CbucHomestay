/**
 * Copyright:CBUC
 */
layui.define(['form', 'layer', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        , layer = layui.layer
        , form = layui.form;
    element.render();

    var messageTable = table.render({
        elem: '#comment-table'
        , url: '/admin/commentPage'
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
                field: 'loginIp'
                , align: 'center'
                , title: '评论人IP'
                , sort: true
                , width: 230
            }
            , {
                field: 'type'
                , align: 'center'
                , title: '被评类型'
                , width: 100
                , templet:(d)=>{
                    return d.type==1?'文章':'评论';
                }
            }
            , {
                field: 'detail'
                , align: 'center'
                , title: '被评内容'
                , width: 321
            }
            , {
                field: 'content'
                , align: 'center'
                , Width: 321
                , title: '评论内容'
                , event: 'detail'
            }
            , {
                field: 'createTime'
                , align: 'center'
                , title: '评论时间'
                , sort: true
                , width: 265
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                field: 'likeCount'
                , align: 'center'
                , Width: 165
                , title: '点赞数'
                , sort: true
            }
            , {
                field: 'commentCount'
                , align: 'center'
                , Width: 165
                , title: '评论数'
                , sort: true
            }
            , {
                title: '操作'
                , align: 'center'
                , width: 150
                , align: 'center'
                , toolbar: '#opera'
            }
        ]]
    });


    form.on('switch(enable)', function () {
        const checkedStatus = this.checked;
        Base.ajax(
            "/admin/modContact"
            , {
                id: this.value,
                status: checkedStatus
            }
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

    //监听单元格事件
    table.on('tool(comment)', function (obj) {
        const data = obj.data;
        if (obj.event === 'detail') {
            layer.open({
                type: 0
                , offset: 'auto'
                , id: 'layerDemo' + data.id
                , content: '<div style="padding: 20px;">' + data.content + '</div>'
                , shade: 0.3
                , anim: 5
            });
        }
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                console.log(data.id);
                Base.ajax("modComment", {'id': data.id}, function (result) {
                    if (result.code == Base.status.success) {
                        layer.msg("删除成功",{time:800});
                        setTimeout(() => {
                            active.reload();
                            layer.close(index);
                        }, 800);

                    }else{
                        layer.msg(result.msg);
                    }
                });
            });
        }
    });

    table.on('sort(comment)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        }else if (obj.field == "likeCount") {
            sort = "LIKE_COUNT";
        } else if (obj.field == "commentCount") {
            sort = "COMMENT_COUNT";
        }else {
            sort = obj.field;
        }
        messageTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });

    var $ = layui.$
    , active = {
        reload: function () {
            let commentKeyWard = $("#messageKeyword").val();
            let nameKeyword = $("#nameKeyword").val();
            //执行重载
            table.reload('comment-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    commentKeyWard:commentKeyWard,
                    nameKeyword: nameKeyword
                }
            });
        }
    };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $('.layui-btn-primary').on('click', function (){
        $("#messageKeyword").val("");
        $("#nameKeyword").val("");
        table.reload('comment-table', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                commentKeyWard:$("#messageKeyword").val(),
                nameKeyword: $("#nameKeyword").val()
            }
        });
    });
});