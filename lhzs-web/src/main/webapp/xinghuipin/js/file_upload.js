function fileUploadConfig(config, filesAddedCallback, fileUploadedCallback, uploadCompleteCallback, uploadProgressCallback, errorCallback) {
    var uploader = new plupload.Uploader({
        flash_swf_url: '/web/plupload-2.3.1/Moxie.swf',
        url: config.url,
        browse_button: config.uploadButton,
        filters: {
            max_file_size: config.maxFileSize,//100b, 10kb, 10mb, 1gb
            prevent_duplicates: config.preventDuplicates, //不允许选取重复文件
            multi_selection: false
        },
        init: {
            FilesAdded: filesAddedCallback,
            FileUploaded: fileUploadedCallback,
            UploadComplete: uploadCompleteCallback,
            UploadProgress: uploadProgressCallback,
            Error: errorCallback
        }
    });
    return uploader;
}

function pladdFileId(fileId, resp) {
    var resourceId = $.parseJSON(resp).data.id;
    $("#dl_" + fileId).click(function () {
        toDownloadPage(resourceId);
    });
    $("#item_" + fileId).text(resourceId);
}

function pldeleteFile(table, rowNum, itemId) {
    var infoTable = document.getElementById(table).firstChild;
    var row = document.getElementById(rowNum);
    var param = {};
    param.id = $("#" + itemId).text();
    app.pldeleteFile(param, function (result) {
        if (result.code == 200) {
            infoTable.deleteRow(row.rowIndex);
        } else {
            alert(result.msg);
        }
    });
}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function toDownloadPage(fileId) {
    window.open('/xhp/file/upload/fileDownload?fileId=' + fileId);
}