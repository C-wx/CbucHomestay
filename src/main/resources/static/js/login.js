$(window, document, undefined).ready(function () {
    $('input').blur(function () {
        var $this = $(this);
        if ($this.val())
            $this.addClass('used');
        else
            $this.removeClass('used');
    });
    var $ripples = $('.ripples');
    $ripples.on('click.Ripples', function (e) {
        var $this = $(this);
        var $offset = $this.parent().offset();
        var $circle = $this.find('.ripplesCircle');
        var x = e.pageX - $offset.left;
        var y = e.pageY - $offset.top;
        $circle.css({
            top: y + 'px',
            left: x + 'px'
        });
        $this.addClass('is-active');
    });
    $ripples.on('animationend webkitAnimationEnd mozAnimationEnd oanimationend MSAnimationEnd', function (e) {
        $(this).removeClass('is-active');
    });
});
$(() => {
    /**
     * 提交登录
     */
    $("body").on("click", "#login", () => {
        let account = $("#maccount").val();
        let pwd = $("#mpwd").val();
        let code = $("#verifyCode").val();
        if (!account) {
            $("#maccount").focus();
            Base.openError("请输入账号", "#maccount");
            return
        } else if (!pwd) {
            $("#mpwd").focus();
            Base.openError("请输入密码", "#mpwd");
            return
        } else if (!code) {
            $("#verifyCode").focus();
            Base.openError("请输入验证码", "#verifyCode");
            return
        } else {
            Base.ajax('/doLogin', 'POST', $(".formDate").serialize(), (e) => {
                if (e.code == Base.status.success) {
                    layer.msg("登录成功", {icon: 6, time: 800});
                    setTimeout(() => {
                        location.href = "/home/" + e.data.mlevel;
                    }, 1000);
                } else {
                    layer.msg(e.msg, {icon: 5, time: 800, anim: 6});
                    setTimeout(function () {
                        $("#verifyCode").val("");
                        captchaRefresh($("#vercode"));
                    }, 1000);
                }
            })
        }
    });
    $("body").on("change", "#maccount", () => {
        try {
            layer.close(errorPrompt);
        } catch (e) {
        }
    });
    $("body").on("change", "#mpwd", () => {
        try {
            layer.close(errorPrompt);
        } catch (e) {
        }
    });
    $("body").on("change", "#verifyCode", () => {
        try {
            layer.close(errorPrompt);
        } catch (e) {
        }
    });
    $("body").on("change", "#for_phone", () => {
        try {
            layer.close(errorPrompt);
        } catch (e) {
        }
    });
    $("body").on("change", "#for_new_pwd", () => {
        try {
            layer.close(errorPrompt);
        } catch (e) {
        }
    });
    $("body").on("change", "#for_old_pwd", () => {
        try {
            layer.close(errorPrompt);
        } catch (e) {
        }
    });
    /**
     * 切换状态
     */
    $("body").on("click", ".forget", () => {
        $("h1").html("找回密码");
        $(".formDate").hide();
        $(".forgetForm")[0].reset();
        $(".forgetForm").show();
    });
    $("body").on("click", ".re_login", () => {
        $("h1").html("民宿后台登录");
        $(".formDate").show();
        $(".forgetForm").hide();
    });
    /**
     * 提交忘记密码
     */
    $("body").on("click", "#forget", () => {
        let for_phone = $("#for_phone").val();
        let for_new_pwd = $("#for_new_pwd").val();
        let for_old_pwd = $("#for_old_pwd").val();
        if (!for_phone) {
            Base.openError("请输入手机号码", "#for_phone");
            $("#for_phone").focus();
            return
        }
        if (!Base.checkPhone(for_phone)) {
            Base.openError("请输入正确的手机号码", "#for_phone");
            $("#for_phone").focus();
            return
        }
        if (!for_new_pwd) {
            Base.openError("请输入密码", "#for_new_pwd");
            $("#for_new_pwd").focus();
            return
        }
        if (!for_old_pwd) {
            Base.openError("请确认密码", "#for_old_pwd");
            $("#for_old_pwd").focus();
            return
        }
        if (for_new_pwd != for_old_pwd) {
            layer.msg("两次不一致,请重新输入!", {icon: 5, time: 800, anim: 6});
            return
        }
        Base.ajax("/doForget", "POST", $(".forgetForm").serialize(), (res) => {
            debugger
            if (res.code == Base.status.success) {
                layer.msg("修改成功!", {icon: 6, time: 800, anim: 1});
                setTimeout(() => {
                    $(".forgetForm")[0].reset();
                    clearInterval(timer);
                    $("#getCode").attr("disabled", false);
                    $("#getCode").val("获取验证码");
                    $("#getCode").removeClass("on");
                    $("h1").html("民宿后台登录");
                    $(".formDate").show();
                    $(".forgetForm").hide();
                }, 800)
            }
        });
    });
    /**
     * 按钮倒计时常用于获取手机短信验证码
     */
    $("#getCode").click(function () {
        let for_phone = $("#for_phone").val();
        if (!for_phone) {
            Base.openError("请输入手机号码", "#for_phone");
            $("#for_phone").focus();
            return
        }
        if (!Base.checkPhone(for_phone)) {
            Base.openError("请输入正确的手机号码", "#for_phone");
            $("#for_phone").focus();
            return
        }
        var time = 60;
        Base.ajax("/getMsgCode", "GET", {smsMob: for_phone}, (res) => {
            if (res.code != Base.status.success) {
                layer.msg(res.msg, {icon: 5, time: 800});
                return
            } else {
                $(this).addClass("on");
                $(this).attr("disabled", true);
                $("#sessionId").val(res.msg);
            }
        });
        var timer = setInterval(function () {
            if (time == 0) {
                clearInterval(timer);
                $("#getCode").attr("disabled", false);
                $("#getCode").val("获取验证码");
                $("#getCode").removeClass("on");
            } else {
                $('#getCode').val(time + "秒");
                time--;
            }
        }, 1000);
    });
})

function captchaRefresh(img) {
    $(img).attr("src", "/image/code?t=" + Math.random());
}

$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        $("#login").click();
    }
});