var $table = $('#userTable');
var $role = $('#roles-select2');
$(function () {

    var _table = $table.DataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax: {
            url: "/users",
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
                className: "ellipsis", //文字过长时用省略号显示，CSS实现
                data: "username",
                render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,   //会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
                orderable: false
            },
            {
                data: "email",
                render: function (data, type, row, meta) {
                    return '<a href="mailto:' + data + '">' + data + '</a>';
                },
                orderable: false
            },
            {
                data: "roles",
                render: function (data, type, row, meta) {
                    var html = '';
                    $.each(data, function (i, item) {
                        html += '<span class="label label-sm label-info" style="margin-right: 2px;">' + item.role + '</span>';
                    });
                    return html;
                },
                orderable: false
            },
            {
                className: "center",
                data: "createTime",
                render: CONSTANT.DATA_TABLES.RENDER.DATE,
                orderable: false
            },
            {
                data: null,
                orderable: false,
                render: function (data, type, row, meta) {
                    return "<a href='javascript:editModal(" + meta.row + ")' class='btn btn-outline btn-circle btn-sm blue' style='padding: 4px 12px;'>\n" +
                        "<i class='fa fa-pencil-square-o'></i></a>" +
                        "<a href='javascript:deleteUser(" + meta.row + ")' class='btn btn-outline btn-circle btn-sm red delete-button' style='padding: 4px 12px;width: 44px;'>\n" +
                        "<i class='fa fa-trash-o'></i></a>";
                }
            }
        ]
    }));
});

function deleteUser(row) {
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
        message: '确定删除用户' + data.username + '?',
        callback: function (result) {
            if (result) {
                $.ajax({
                    type: 'DELETE',
                    url: '/users/' + data.username,
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

function editModal(row) {
    var data = $table.DataTable().rows(row).data()[0];
    $('#username').val(data.username);
    // 清楚所有选择
    $role.html('');
    // 初始化所有权限
    $role.select2({
        placeholder: "请选择角色",
        ajax: {
            type: 'GET',
            url: '/users/roles',
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            processResults: function (resp) {
                var data = resp.data;
                var list = data.map(role => ({id: role.id, text: role.role}));
                return {
                    results: list
                };
            }
        }
    });
    // 初始化用户所具有的权限
    $.ajax({
        type: 'GET',
        url: '/users/' + data.username + '/roles',
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success: function (json) {
            var data = json.data;
            var list = data.map(role => ({id: role.id, text: role.role}));
            data.forEach(function (r) {
                var option = new Option(r.role, r.id, true, true);
                $role.append(option).trigger('change');
            });
            $role.trigger({
                type: 'select2:select',
                params: {
                    data: list
                }
            });
        }
    });
    $('#editModal').modal('show');
}

function saveUserRoles() {
    var username = $('#username').val();
    var selects = $role.select2('data');
    var roles = selects.map(role => ({id: role.id, role: role.text}));
    $.ajax({
        type: 'PUT',
        url: '/users/' + username + '/roles',
        data: JSON.stringify(roles),
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        success: function (json) {
            $table.DataTable().ajax.reload(null, false);
            $('#editModal').modal('hide');
            if (json.statusCode === 200) {
                notify('success', '修改成功');
            } else {
                notify('danger', json.message);
            }
        }
    });
}