$(function () {
    charts.init();
    ComponentsDateTimePickers.init();
    ComponentsSelect2.init();
});

var charts = function () {
    var chart = null;
    var mainChart = function () {
        chart = new Highcharts.Chart({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                renderTo: 'mainContainer',
                events: {
                    load: requestData
                }
            },
            title: {
                text: '总体使用情况'
            },
            tooltip: {
                headerFormat: '{series.name}<br>',
                pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    states: {
                        hover: {
                            enabled: false
                        }
                    },
                    slicedOffset: 20,         // 突出间距
                    point: {                  // 每个扇区是数据点对象，所以事件应该写在 point 下面
                        events: {
                            // 鼠标滑过是，突出当前扇区
                            mouseOver: function () {
                                this.slice();
                            },
                            // 鼠标移出时，收回突出显示
                            mouseOut: function () {
                                this.slice();
                            },
                            // 默认是点击突出，这里屏蔽掉
                            click: function () {
                                return false;
                            }
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '数据源访问量占比'
            }]
        });
    };

    var requestData = function () {
        $.ajax({
            type: 'GET',
            url: '/analysis/dataSources',
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            success: function (json) {
                var arr = [];
                $(json.data).each(function (index, item) {
                    arr.push([item.name, item.count]);
                });
                // 新增点操作
                chart.series[0].setData(arr);

                // 5秒后继续调用本函数
                setTimeout(requestData, 5000);
            },
            cache: false
        });
    };

    return {
        init: function () {
            mainChart();
        },
        createDataSourceChart: function (dataSources, begin, end) {
            var data = {begin: begin, end: end};
            $.ajax({
                type: 'GET',
                url: '/analysis/dataSources/' + dataSources,
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
                            renderTo: 'dataSourceContainer'
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

var ComponentsSelect2 = function () {
    return {
        init: function () {
            $('#dataSources-select2').select2({
                placeholder: "请选择数据源",
                ajax: {
                    type: 'GET',
                    url: '/data/dataSources',
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    processResults: function (resp) {
                        var data = resp.data;
                        var list = data.map(d => ({id: d.dataSource, text: d.dataSource}));
                        return {
                            results: list
                        };
                    }
                }
            });
        }
    }
}();

function search() {
    var selects = $('#dataSources-select2').select2('data');
    var list = [];
    selects.forEach(function (value, index, array) {
        list.push(value.text);
    });
    var dataSources = list.join("-");
    if (isEmpty(dataSources)) {
        notify('danger', "数据源不能为空");
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
    charts.createDataSourceChart(dataSources, begin, end);
}