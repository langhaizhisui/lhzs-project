var app = {
    cache: [],
    pldeleteFile: function (data, callback) {
        var self = this;
        self.ajax("post", "/xhp/admin/file/upload/deleteFile", data, callback);
    },
    addProduct: function (data, callback) {
        var self = this;
        self.ajax("post", "/xhp/admin/product/add", data, callback);
    },
    getProductList: function (data, callback) {
        var self = this;
        self.ajax("post", "/xhp/admin/product/list", data, callback);
    },
    getCatalogList: function (data, callback) {
        var self = this;
        self.ajax("post", "/xhp/admin/product/catalog", data, callback);
    },
    getProductDetail: function (data, callback) {
        var self = this;
        self.ajax("post", "/xhp/admin/product/detail", data, callback);
    },
    deleteProductSku: function (data, callback) {
        var self = this;
        self.ajax("post", "/xhp/admin/product/sku/delete", data, callback);
    },
    getMenu: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/user/menu", data, callback);
    },
    login: function (data, callback) {
        var self = this;
        self.ajax("post", "/login", data, callback);
    },
    "ajax": function (type, url, data, callback) {
        var self = this;
        if (self.isParamEmpty(type) || self.isParamEmpty(url) || self.isParamEmpty(data)) {
            return;
        }
        if (type.toLowerCase() == "get") {
            //遍历data的json格式，拼成get参数类型(格式：{"id":"1","name":"name"})
            var param = "";
            if (data) {
                param += "?";
            }
            for (var key in data) {
                param += key + "=" + data[key] + "&";
            }
            param = param.substring(0, param.length - 1);

            $.ajax({
                type: 'get',
                url: url + param,
                success: function (result) {
                    if (result.code == 600) {
                        location.replace("/xinghuipin/login.html");
                        return;
                    }
                    return callback(result);
                }
            });
        } else {
            $.ajax({
                type: "post",
                url: url,
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (result) {
                    if (result.code == 600) {
                        location.replace("/xinghuipin/login.html");
                        return;
                    }
                    return callback(result);
                }
            });
        }
    },
    isParamEmpty: function (param) {
        if (param == "" || param == null) {
            console.log("请检查参数，不能为空");
            return true;
        }
    }
}

function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
}


