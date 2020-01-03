/**
 * Copyright:CBUC
 */
var Base = {
    status: {
        success: 200,
        error: 501
    }
    , ajax: function (url, type, data, success) {
        $.ajax({
            type: type
            , dataType: 'json'
            , url: url
            , data: data
            , success: success
            , error: function () {
                layer.msg("发生未知错误！");
            }
        })
    }
    , formatDate: function (date, format) {
        return new Date(date).format(format);
    }
    , isEnable: function (data) {
        if (data.status == 'D') {
            return "(已删除)";
        } else if (Base.formatDate(data.endTime, 'yyyy-MM-dd HH:mm:ss') < Base.formatDate(new Date(), 'yyyy-MM-dd HH:mm:ss')) {
            return "(已失效)";
        } else {
            return "(生效中)";
        }
    }
    , openError: function (msg, target) {
        errorPrompt = layer.tips('<span style="font-size: 14px">' + msg + '</span>', target, {
            tips: [1, '#aaa7b2'],
            time: 30000
        });
    }
    , verifyEmail: function () {
        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (!reg.test($("#email").val())) {
            $("#email").addClass("border-warning");
            Base.openError("不是正确的邮箱地址", "#email");
        } else {
            $("#email").removeClass("border-warning");
            layer.close(errorPrompt);
        }
    }
    , verifyNull: function (value, target) {
        if (!value) {
            $(target).css("border", "1px orange solid");
            return false
        } else {
            return true;
        }
    }
    , loadPage: function () {
        location.reload();
    }

};
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};