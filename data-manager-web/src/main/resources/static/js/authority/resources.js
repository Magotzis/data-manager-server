var $table = $('#resourceTable');
var $description = $('#description');
var $method = $('#method');
$(function () {
    var _table = $table.DataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax: {
            url: "/resources",
            type: "GET",
            dataType: 'json',
            contentType: "application/json; charset=utf-8"
        },
        serverSide: true,
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
                data: "description",
                orderable: false
            },
            {
                data: "method",
                orderable: false
            },
            {
                data: null,
                orderable: false,
                render: function (data, type, row, meta) {
                    return "<a href='javascript:editModal(" + meta.row + ")' class='btn btn-outline btn-circle btn-sm blue' style='padding: 4px 12px;'>\n" +
                        "<i class='fa fa-pencil-square-o'></i></a>" +
                        "<a href='javascript:deleteResource(" + meta.row + ")' class='btn btn-outline btn-circle btn-sm red delete-button' style='padding: 4px 12px;width: 44px;'>\n" +
                        "<i class='fa fa-trash-o'></i></a>";
                }
            }
        ]
    }));
});

function deleteResource(row) {
    var data = $table.DataTable().rows(row).data()[0];
    bootbox.confirm({
        buttons: {
            confirm: {
                label: '确定',
                className: 'btn btn-primary'
            },
            cancel: {
                label: '取消',
                className: 'btn btn-default'
            }
        },
        message: '确定删除资源' + data.description + '?',
        callback: function (result) {
            if (result) {
                $.ajax({
                    type: 'DELETE',
                    url: '/resources/' + data.id,
                    dataType: 'json',
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    success: function (json) {
                        $table.DataTable().ajax.reload(null, false);
                        if (json.statusCode === 200) {
                            notify('success', '删除成功');
                        } else {
                            notify('danger', json.message);
                        }
                    }
                });
            }
        }
    });
}

function addModal() {
    $description.val('');
    $description.data('id', '');
    $method.val('');
    $('#editModal').modal('show');
}

function editModal(row) {
    var data = $table.DataTable().rows(row).data()[0];
    $description.val(data.description);
    $method.val(data.method);
    $description.data('id', data.id);
    $('#editModal').modal('show');
}

function saveRoleResources() {
    var id = $description.data('id');
    var description = $description.val();
    var method = $method.val();
    if (id === null || id === '') {
        // 新增
        $.ajax({
            type: 'POST',
            url: '/resources',
            data: {
                description: description,
                method: method
            },
            dataType: "json",
            contentType: "application/x-www-form-urlencoded",
            success: function (json) {
                $table.DataTable().ajax.reload(null, false);
                $('#editModal').modal('hide');
                if (json.statusCode === 200) {
                    notify('success', '新增资源成功');
                } else {
                    notify('danger', json.message);
                }
            }
        });
    } else {
        // 更新
        $.ajax({
            type: 'PUT',
            url: '/resources/' + id,
            data: {description: description, method: method},
            dataType: "json",
            success: function (json) {
                $table.DataTable().ajax.reload(null, false);
                $('#editModal').modal('hide');
                if (json.statusCode === 200) {
                    notify('success', '修改资源成功');
                } else {
                    notify('danger', json.message);
                }
            }
        });
    }
}