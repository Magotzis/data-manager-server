var $table = $('#importTable');
var importTable = function () {
    return {
        init: function () {
            var _table = $table.DataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
                ajax: {
                    url: "/data/importHistory",
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
                        data: "id",
                        visible: false,
                        searchable: false
                    },
                    {
                        data: "state",
                        orderable: false,
                        render: function (data, type, row, meta) {
                           if (data === 0) {
                                return '<span class="label label-sm label-info" style="margin-right: 2px;">执行成功</span>';
                            }else {
                                return '<span class="label label-sm label-danger" style="margin-right: 2px;">执行失败</span>';
                            }
                        }
                    },
                    {
                        data: "username"
                    },
                    {
                        data: "applyContent",
                        orderable: false
                    },
                    {
                        className: "center",
                        data: "updateTime",
                        render: CONSTANT.DATA_TABLES.RENDER.DATE
                    },
                    {
                        data: null,
                        orderable: false,
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:modal.viewModal(" + meta.row + ")' class='btn btn-outline btn-circle btn-sm blue' style='padding: 4px 12px;'>\n" +
                                "<i class='fa fa-search'></i></a>";
                        }
                    }
                ]
            }));
        }
    };
}();

var modal = function () {
    return {
        viewModal: function (row) {
            var data = $table.DataTable().rows(row).data()[0];
            $.ajax({
                type: 'GET',
                url: '/data/importHistory/' + data.id,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                success: function (json) {
                    var data = json.data;
                    $('#edit_applyContent').val(data.applyContent);
                    $('#edit_content').val(data.content);
                    $('#errorMsg').val(data.errorMsg);
                }
            });
            $('#editModal').modal('show');
        }
    }
}();

jQuery(document).ready(function () {
    importTable.init();
});

function importData() {
    var data = {content: $('#content').val(), applyContent: $('#applyContent').val()};
    $.ajax({
        type: 'POST',
        url: '/data/importHistory',
        data: JSON.stringify(data),
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        success: function (json) {
            $('#addModal').modal('hide');
            if (json.statusCode === 200) {
                notify('success', '导入成功');
                $('#content').val('');
                $('#applyContent').val('');
            } else {
                notify('danger', json.message);
            }
        }
    });
}