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
$(()=> {
    $("body").on("click",".button",()=> {
        let account = $("#maccount").val();
        let pwd = $("#mpwd").val();
        let code = $("#verifyCode").val();
        if (!account) {
            $("#maccount").focus();
            Base.openError("请输入账号","#maccount"); return
        }else if (!pwd) {
            $("#mpwd").focus();
            Base.openError("请输入密码","#mpwd"); return
        }else if (!code) {
            $("#verifyCode").focus();
            Base.openError("请输入验证码","#verifyCode"); return
        }else {
            Base.ajax('/doLogin', 'POST', $(".formDate").serialize(), (e)=>{
                if (e.data.code == Base.status.success) {
                    layer.msg() // TODO
                }
            })
        }
    });
    $("body").on("change","#maccount",()=> {
        layer.close(errorPrompt)
    });
    $("body").on("change","#mpwd",()=> {
        layer.close(errorPrompt)
    });
    $("body").on("change","#verifyCode",()=> {
        layer.close(errorPrompt)
    })
})
function captchaRefresh(img) {
    $(img).attr("src", "/image/code?t=" + Math.random());
}