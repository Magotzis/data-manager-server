var $table = $('#summaryTable');
var summaryTable = function () {
    return {
        init: function () {
            var _table = $table.DataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
                ajax: {
                    url: "/data/dataSources",
                    type: "GET",
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8"
                },
                serverSide: false,
                paging: true,//是否分页
                pagingType: "full_numbers",//除首页、上一页、下一页、末页四个按钮还有页数按钮
                processing: true, //显示处理过程
                autoWidth: true, //自动计算宽度
                searching: true,
                columns: [
                    CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
                    {
                        data: "dataSource"
                    },
                    {
                        data: "rows"
                    },
                    {
                        className: "center",
                        data: "createTime",
                        render: CONSTANT.DATA_TABLES.RENDER.DATE
                    },
                    {
                        className: "center",
                        data: "updateTime",
                        render: CONSTANT.DATA_TABLES.RENDER.DATE
                    }
                ]
            }));
        }
    };
}();

jQuery(document).ready(function () {
    summaryTable.init();
});