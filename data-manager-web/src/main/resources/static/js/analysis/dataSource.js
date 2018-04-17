var chart = null;
$(function () {
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
                        mouseOver: function() {
                            this.slice();
                        },
                        // 鼠标移出时，收回突出显示
                        mouseOut: function() {
                            this.slice();
                        },
                        // 默认是点击突出，这里屏蔽掉
                        click: function() {
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
});

function requestData() {
    $.ajax({
        type: 'GET',
        url: '/analysis/dataSources',
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success: function(json) {
            var arr = [];
            $(json.data).each(function(index,item){
                arr.push([item.name,item.count]);
            });
            // 新增点操作
            chart.series[0].setData(arr);

            // 5秒后继续调用本函数
            setTimeout(requestData, 5000);
        },
        cache: false
    });
}