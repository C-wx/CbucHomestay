$(()=>{
    $("[type='reset']").click(function(){
        $("#mpwd").val("");
        $("#npwd").val("");
        $("#cpwd").val("");
    })
});
layui.use('form', function(){
    var form = layui.form;
    form.on('submit(modSumbit)', function(data){
        Base.ajax("/doModPwd","POST",data.field,(res)=>{
            if (res.code === Base.status.success) {
                layer.msg("修改成功,请重新登录",{icon:6,time:800});
                setTimeout(()=>{
                    location.href = "/toLogin";
                },800)
            }else{
                layer.msg(res.msg,{icon:5,time:800});
            }
        });
        return false;
    });
});
