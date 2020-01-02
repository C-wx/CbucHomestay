layui.use(['laydate'], function () {
    var laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#date'
        , value: new Date()
        , type: 'month'
        , trigger: 'mouseover'
        , left: '200'
        , btns: ['now', 'confirm']
        , theme: '#393D49'
        , done: function (value) {
            blogData(value);
        }
    });
    laydate.render({
        elem: '#date1'
    });

});
$(function () {
    blogData();
    getLog();
    setInterval('getLog()',1000 * 60 * 10);
});

function getBlogData(dataName, dataNum, Time) {
    $.ajax({
        async: false,
        url: "getBlogData",
        type: "get",
        data: {'beginTime': Time},
        dataType: "json",
        success: function (result) {
            for (var i = 0; i < result.data.length; i++) {
                dataName.push(result.data[i].name);
                dataNum.push(result.data[i].value);
            }
        }
    })
}

function blogData(Time) {
    var dataName = [];
    var dataNum = [];
    getBlogData(dataName, dataNum, Time == null ? Base.formatDate(new Date(), "yyyy-MM") : Time);
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        tooltip: {},
        xAxis: [
            {
                data: dataName,
                axisLabel: {
                    interval: 0,
                    rotate: -45,
                },
                splitLine: {
                    show: false
                }
            }
        ],
        yAxis: [],
        series: [{
            name: '发布量',
            type: 'bar',
            data: dataNum,
            itemStyle: {
                normal: {
                    color: '#6e95ba'
                }
            }
            , label: {
                normal: {
                    show: true,
                    position: 'top',
                    textStyle: {
                        color: 'black'
                    },
                    formatter: function (params) {
                        if (params.value > 0) {
                            return params.value;
                        } else {
                            return ' ';
                        }
                    }
                }
            }
        }]
    };
    myChart.setOption(option);
}
function getLog() {
    Base.ajax("/admin/getLog", null, function (result) {
        if (result.code == Base.status.success) {
            $("#accessLog").empty();
            var data = result.data.log;
            console.log(data);
            data.forEach((data) => {
                var $html =
                    "<li class=\"layui-timeline-item\">\n" +
                    "    <i class=\"layui-icon layui-timeline-axis\">&#xe63f;</i>\n" +
                    "    <div class=\"layui-timeline-content layui-text\">\n" +
                    "        <p>\n<strong>" + data.loginIp +"</strong>"+"&nbsp;&nbsp;于&nbsp;&nbsp;<strong>"+ Base.formatDate(data.createTime, "yyyy-MM-dd HH:mm:ss") + "</strong>&nbsp;&nbsp;&nbsp;&nbsp;访问了&nbsp;&nbsp;&nbsp;&nbsp;<stong>"+ data.remark +"</stong>&nbsp;&nbsp;接口"+
                    "        </p>\n" +
                    "    </div>\n" +
                    "</li>";
                $("#accessLog").append($html);
            });
            const $end =
                "<li class=\"layui-timeline-item\">\n" +
                "    <i class=\"layui-icon layui-timeline-axis\" style='color: red'>&#xe63f;</i>\n" +
                "    <div class=\"layui-timeline-content layui-text\">\n" +
                "        <div class=\"layui-timeline-title\">过去</div>\n" +
                "    </div>\n" +
                " </li>"
            $("#accessLog").append($end);
        } else {
            layer.msg(result.msg);
        }
    });
}