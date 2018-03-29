var $table = $('#roleTable');
var $resources = $('#resources-select2');
var $role = $('#role');
$(function () {
    var _table = $table.DataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax: {
            url: "/roles",
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
                className: "ellipsis", //文字过长时用省略号显示，CSS实现
                data: "role",
                render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,//会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
                orderable: false
            },
            {
                data: "resources",
                render: function (data, type, row, meta) {
                    var html = '';
                    $.each(data, function (i, item) {
                        html += '<span class="label label-sm label-info" style="margin-right: 2px;">' + item.description + '</span>';
                    });
                    return html;
                },
                orderable: false
            },
            {
                data: null,
                orderable: false,
                render: function (data, type, row, meta) {
                    return "<a href='javascript:editModal(" + meta.row + ")' class='btn btn-outline btn-circle btn-sm blue' style='padding: 4px 12px;'>\n" +
                        "<i class='fa fa-pencil-square-o'></i></a>" +
                        "<a href='javascript:deleteRole(" + meta.row + ")' class='btn btn-outline btn-circle btn-sm red delete-button' style='padding: 4px 12px;width: 44px;'>\n" +
                        "<i class='fa fa-trash-o'></i></a>";
                }
            }
        ]
    }));
});

function deleteRole(row) {
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
        message: '确定删除角色' + data.role + '?',
        callback: function (result) {
            if (result) {
                $.ajax({
                    type: 'DELETE',
                    url: '/roles/' + data.id,
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
    $role.val('');
    $role.data('id', '');
    // 清楚所有选择
    $resources.html('');
    // 初始化所有资源
    $resources.select2({
        placeholder: "请选择资源",
        ajax: {
            type: 'GET',
            url: '/roles/resources',
            processResults: function (resp) {
                var list = resp.data.map(rs => ({id: rs.id, text: rs.description}));
                return {
                    results: list
                };
            }
        }
    });
    $('#editModal').modal('show');
}

function editModal(row) {
    var data = $table.DataTable().rows(row).data()[0];
    $role.val(data.role);
    $role.data('id', data.id);
    // 清楚所有选择
    $resources.html('');
    // 初始化所有资源
    $resources.select2({
        placeholder: "请选择资源",
        ajax: {
            type: 'GET',
            url: '/roles/resources',
            processResults: function (resp) {
                var list = resp.data.map(rs => ({id: rs.id, text: rs.description}));
                return {
                    results: list
                };
            }
        }
    });
    // 初始化角色所具有的资源
    $.ajax({
        type: 'GET',
        url: '/roles/' + data.id + '/resources',
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success: function (json) {
            var data = json.data;
            var list = [];
            data.forEach(function (r) {
                list.push({id: r.id, text: r.description});
                var option = new Option(r.description, r.id, true, true);
                $resources.append(option).trigger('change');
            });
            $resources.trigger({
                type: 'select2:select',
                params: {
                    data: list
                }
            });
        }
    });
    $('#editModal').modal('show');
}

function saveRoleResources() {
    var id = $role.data('id');
    var selects = $resources.select2('data');
    var resources = selects.map(rs => ({id: rs.id, description: rs.text}));
    if (id === null || id === '') {
        // 新增
        var json = {role: $role.val(), resources: resources};
        $.ajax({
            type: 'POST',
            url: '/roles',
            data: JSON.stringify(json),
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            success: function (json) {
                $table.DataTable().ajax.reload(null, false);
                $('#editModal').modal('hide');
                if (json.statusCode === 200) {
                    notify('success', '新增角色成功');
                } else {
                    notify('danger', json.message);
                }
            }
        });
    }else {
        // 更新
        $.ajax({
            type: 'PUT',
            url: '/roles/' + id + '/resources',
            data: JSON.stringify(resources),
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            success: function (json) {
                $table.DataTable().ajax.reload(null, false);
                $('#editModal').modal('hide');
                if (json.statusCode === 200) {
                    notify('success', '修改角色成功');
                } else {
                    notify('danger', json.message);
                }
            }
        });
    }
}