/**
 * @author   Caiwx
 * @Explain  基本组件脚本
 */
var Base = {
    /**
     * 响应状态吗
     * 200  请求成功
     * 501  请求错误
     */
    status: {
        success: 200,
        error: 501
    }
    /**
     * 发送AJAX请求(封装)
     * @param url       请求地址
     * @param type      请求类型    (GET/POST/PUT/DELETE)
     * @param data      请求数据    (JSON)
     * @param success   请求成功回调函数
     */
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
    /**
     * 日期格式化
     * @param date      日期
     * @param format    日期格式    (yyyy-mm-dd)
     * @returns         格式化后的日期
     */
    , formatDate: function (date, format) {
        return new Date(date).format(format);
    }
    /**
     *
     * @param msg
     * @param target
     */
    , openError: function (msg, target) {
        errorPrompt = layer.tips('<span style="font-size: 14px">' + msg + '</span>', target, {
            tips: [1, '#aaa7b2'],
            time: 30000
        });
    }
    /**
     * 校验邮箱地址
     */
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
    /**
     * 页面重载
     */
    , loadPage: function () {
        location.reload();
    }
    /**
     * 校验电话号码
     * @param phone
     * @returns {boolean}
     */
    , checkPhone(phone){
        if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
            return false;
        }else{
            return true;
        }
    }
    /**
     * 校验是否生效
     * @param data
     * @returns {string}
     */
    , isEnable: function (data) {
        var delHtml = '&nbsp;&nbsp;<i class="fa fa-trash-o" onclick="delBulletin('+data.id+')" style="cursor: pointer;color: #ff8686;font-size: 20px"></i>';
        if (data.status == 'E') {
            return "(生效中)"+delHtml;
        }else{
            return "(已删除)";
        }
    }

};
/**
 * 日期格式化
 * @param format
 * @returns {*}
 */
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