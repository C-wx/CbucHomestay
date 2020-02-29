layui.define(['form'], function (exports) {
    var form = layui.form;
    form.render();
    form.on('submit(sendSumbit)', function (data) {
        Base.ajax("/sendMsg", "PUT", data.field, (res) => {
            if (res.code === Base.status.success) {
                layer.msg("操作成功", {icon: 6, time: 800});
                setTimeout(() => {
                    parent.$(".layuiadmin-btn-forum-list").click();
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index)
                }, 800)

            } else {
                layer.msg(res.msg, {icon: 5, time: 800});
            }
        });
        return false;
    });

    var imgName = [], imgSrc = [], imgFile = [];
    //获取房间图片
    Base.ajax("/merchant/getRoomImage", "GET", {rid: $("#rid").val()}, (res) => {
        var html = '';
        res.data.forEach((image, index) => {
            imgSrc.push(image.url);
            html += '<li class="content-img-list-item"><img src="' + image.url + '"><a index="' + index + 1 + '" class="hide delete-btn"><i class="fa fa-trash-o\n"></i></a></li>';
        });
        $(".content-img-list").html(html);
        console.log(res.data.length)
        if (res.data.length >= 3) {
            $(".file").hide();
        }
    });

    $("body").on("click", ".content-img-list-item img", function (e) {
        layer.photos({
            photos: ".content-img-list-item"
            , anim: 5
        });
    });
    $("body").on("click", "#returnBtn", function (e) {
        parent.layui.admin.events.closeThisTabs()
    });


    $("#upload").on('change', function (e) {
        var fileList = this.files;
        for (var i = 0; i < fileList.length; i++) {
            var imgSrcI = getObjectURL(fileList[i]);
            imgName.push(fileList[i].name);
            imgSrc.push(imgSrcI);
            imgFile.push(fileList[i]);
        }
        if (imgSrc.length == 3) {
            $(".file").hide();
        }
        var imgSize = this.files[0].size;  //b
        if (imgSize > 1024 * 1024 * 1) {//1M
            return alert("上传图片不能超过1M");
        }
        if (this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif') {
            return alert("图片上传格式不正确");
        }
        var boxId = ".content-img-list";
        addNewContent(boxId);
    });

    function addNewContent(obj) {
        $(obj).html("");
        for (var a = 0; a < imgSrc.length; a++) {
            var oldBox = $(obj).html();
            $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '"><a index="' + a + '" class="hide delete-btn"><i class="fa fa-trash-o\n"></i></a></li>');
        }
    }

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    };
    $('.content-img-list').on('mouseover', '.content-img-list-item', function () {
        $(this).children('a').removeClass('hide');
    });
    $('.content-img-list').on('mouseleave', '.content-img-list-item', function () {
        $(this).children('a').addClass('hide');
    });
    $(".content-img-list").on("click", '.content-img-list-item a', function () {
        var index = $(this).attr("index");
        var url = $(this).prev().attr('src');
        if (url.indexOf("cbucbm.club") > -1) {
            Base.ajax("/merchant/delImage", "POST", {url: url}, (res) => {
            })
        }
        imgSrc.splice(index, 1);
        imgFile.splice(index, 1);
        imgName.splice(index, 1);
        var boxId = ".content-img-list";
        addNewContent(boxId);
        if (imgSrc.length < 4) {//显示上传按钮
            $('.content-img .file').show();
        }
    });

    /**
     * 发布房间
     */
    form.on('submit(postSubmit)', function (data) {
        var formFile = new FormData();
        $.each(imgFile, function (i, file) {
            formFile.append('myFiles', file);
        });
        formFile.append("roomInfo", JSON.stringify(data.field));
        $.ajax({
            url: "/merchant/doSaveRoom"
            , type: "POST"
            , data: formFile
            , processData: false
            , contentType: false
            , dataType: 'json'
            , success: function (result) {
                if (result.code == Base.status.success) {
                    layer.msg("发布成功!", {icon: 6, time: 2000});
                    setTimeout(() => {
                        parent.layui.admin.events.closeThisTabs()
                    }, 2000);
                } else {
                    layer.msg(result.msg);
                }
            }
        });
        return false;
    });

    exports('opeRoom', {});
});

//上传图片
function clickImage() {
    $("#upload").click();
}
