Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function notify(type, message) {
    $.bootstrapGrowl(message, {
        ele: 'body',
        type: type, // (null, 'info', 'danger', 'success', 'warning')
        offset: {
            from: 'top',
            amount: 100
        },
        align: 'right',
        width: 'auto',
        delay: 5000, // Time while the message will be displayed. It's not equivalent to the *demo* timeOut!
        allow_dismiss: true,
        stackup_spacing: 10
    });
}

function isEmpty(str) {
    if (str == null || str === '') {
        return true;
    }
    return false;
}

function isNotEmpty(str) {
    return !isEmpty(str);
}