layui.define(['laydate'], function (exports) {
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
    exports('dataCenter', {});
});
$(function () {
    blogData();
});

function getBlogData(dataName, dataNum, Time) {
    $.ajax({
        async: false,
        url: "getSalesData",
        type: "GET",
        data: {'beginTime': Time},
        dataType: "json",
        success: function (result) {
            for (var i = 0; i < result.data.length; i++) {
                dataName.push(result.data[i].name);
                dataNum.push(result.data[i].value);
            }
        }
    });
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
            name: '销售量',
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
