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
    $("body").on("click", ".button", () => {
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
                    layer.msg("登录成功", {icon: 6, time: 800, anim: 1});
                    setTimeout(()=>{
                        location.href = "/admin/";
                    },1000);
                }else {
                    layer.msg(e.msg,{icon: 5, time: 800, anim: 1});
                    setTimeout(function () {
                        $("#verifyCode").val("");
                        captchaRefresh($("#vercode"));
                    }, 1000);
                }
            })
        }
    });
    $("body").on("change", "#maccount", () => {
        try{
            layer.close(errorPrompt);
        }catch(e){}
    });
    $("body").on("change", "#mpwd", () => {
        try{
            layer.close(errorPrompt);
        }catch(e){}
    });
    $("body").on("change", "#verifyCode", () => {
        try{
            layer.close(errorPrompt);
        }catch(e){}
    })
})

function captchaRefresh(img) {
    $(img).attr("src", "/image/code?t=" + Math.random());
}

$(document).keyup(function(event){
    if(event.keyCode ==13){
        $(".button").click();
    }
});