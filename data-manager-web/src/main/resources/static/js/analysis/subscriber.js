$(function () {
    ComponentsDateTimePickers.init();
});

var charts = function () {

    return {
        createUserChart: function (username, begin, end) {
            var data = {begin: begin, end: end};
            $.ajax({
                type: 'GET',
                url: '/analysis/userRecord/' + username,
                data: data,
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                success: function (json) {
                    if (json.statusCode !== 200) {
                        notify('danger', json.message);
                        return;
                    }
                    var resp = json.data;
                    var dataSeries = resp.analysisVos.map(d => ({name: d.name, data: d.list}));
                    var dChart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container'
                        },
                        title: {
                            text: '数据源具体使用情况'
                        },
                        xAxis: {
                            categories: resp.axis
                        },
                        yAxis: {
                            title: {
                                text: '使用次数'
                            }
                        },
                        tooltip: {
                            shared: true,
                            crosshairs: true
                        },
                        legend: {
                            align: 'left',
                            verticalAlign: 'top',
                            y: 20,
                            floating: true,
                            borderWidth: 0
                        },
                        series: dataSeries
                    });
                }
            });
        }
    }
}();

var ComponentsDateTimePickers = function () {
    return {
        init: function () {
            if (!jQuery().datetimepicker) {
                return;
            }
            $(".form_datetime").datetimepicker({
                autoclose: true,
                isRTL: App.isRTL(),
                format: "yyyy-mm-dd hh:ii",
                fontAwesome: true,
                language: "zh-CN",
                pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
            });
        }
    }
}();

function search() {
    var username = $('#username').val();
    if (isEmpty(username)) {
        notify('danger', "用户名不能为空");
        return;
    }
    var begin = $('#begin').val();
    if (isEmpty(begin)) {
        notify('danger', "开始时间不能为空");
        return;
    }
    var end = $('#end').val();
    if (isEmpty(end)) {
        notify('danger', "结束时间不能为空");
        return;
    }
    charts.createUserChart(username, begin, end);
}