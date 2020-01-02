layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , form = layui.form
        , element = layui.element;

    element.render();
    var articleTable = table.render({
        elem: '#article-table'
        , url: '/admin/articlePage'
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
                field: 'title'
                , title: '标题'
                , align: 'center'
                , Width: 520
                , templet: function (d) {
                    return '<a href="/article/' + d.id + '" class="layui-blue" target="_blank">' + d.title + '</a>';
                }
            }
            , {
                field: 'createTime'
                , title: '发布时间'
                , align: 'center'
                , Width: 282
                , sort: true
                , templet: (d) => {
                    return Base.formatDate(d.createTime, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {
                field: 'accessCount'
                , title: '浏览数'
                , align: 'center'
                , width: 113
                , sort: true,
            }
            , {
                field: 'likeCount'
                , title: '点赞数'
                , align: 'center'
                , width: 113
                , sort: true,
            }
            , {
                field: 'commentCount'
                , title: '评论数'
                , align: 'center'
                , width: 113
                , sort: true
            }
            , {
                field: 'status'
                , title: '状态'
                , align: 'center'
                , width: 183
                , templet: function (d) {
                    return d.status == 'D' ? '<span class="layui-badge layui-bg-orange"></span>' : '<span class="layui-badge layui-bg-green">展示中</span>';
                }
            }
            , {
                field: 'top'
                , title: '置顶'
                , align: 'center'
                , width: 122
                , templet: '#topTpl'
            }
            , {
                title: '操作'
                , width: 191
                , align: 'center'
                , toolbar: '#articleBar'
                , fixed: 'right'
            }
        ]]
    });
    var active = {
        keyLike: function () {
            const titleSearch = $('#titleKeyword');
            //执行重载
            table.reload('article-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: titleSearch.val()
                }
            });
        },
        reload: function () {
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

    $('button[data-type]').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

//监听工具条
    table.on('tool(article)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('删除不可恢复（包括文章的评论），确认删除吗？', function (index) {
                Base.ajax("modArticle", {'id': data.id, 'status': 'D'}, function (result) {
                    if (result.code == Base.status.success) {
                        layer.msg("操作成功",{time:800});
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

    table.on('sort(article)', function (obj) {
        var sort;
        if (obj.field == "createTime") {
            sort = "CREATE_TIME";
        }else if (obj.field == "accessCount") {
            sort = "ACCESS_COUNT";
        } else if (obj.field == "likeCount") {
            sort = "LIKE_COUNT";
        } else if (obj.field == "commentCount") {
            sort = "COMMENT_COUNT";
        } else {
            sort = obj.field;
        }
        articleTable.reload({
            initSort: obj
            , where: {
                sort: sort
                , order: obj.type
            }
        });
    });

    form.on('checkbox(top)', function (obj) {
        const objId = this.value;
        let isTop;
        if (obj.elem.checked) {
            isTop = '1';
        }else{
            isTop = '0';
        }
        Base.ajax("modArticle",{'id':objId,'isTop':isTop},function (result) {
            if (result.code == Base.status.success) {
                layer.msg("操作成功",{time:800});
            }else{
                layer.msg(result.msg);
            }
        })
    });

    exports('article_page', {});
});