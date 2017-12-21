var app = {
    cache: [],
    searchShopList: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/shop/search", data, callback);
    },
    getShopDetail: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/shop/getShop", data, callback);
    },
    deleteShop: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/shop/delete", data, callback);
    },
    addShop: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/shop/add", data, callback);
    },
    updateShop: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/shop/update", data, callback);
    },
    batchShopDelete: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/shop/batch/delete", data, callback);
    },
    allShopDelete: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/shop/all/delete", data, callback);
    },
    searchProductList: function (data, callback) {//
        var self = this;
        self.ajax("post", "/admin/prod/search", data, callback);
    },
    getProductDetail: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/prod/getProduct", data, callback);
    },
    deleteProduct: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/prod/delete", data, callback);
    },
    addProd: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/prod/add", data, callback);
    },
    updateProduct: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/prod/update", data, callback);
    },
    batchProdDelete: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/prod/batch/delete", data, callback);
    },
    allProdDelete: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/prod/all/delete", data, callback);
    },
    addArticle: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/article/add", data, callback);
    },
    deleteArticle: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/article/delete", data, callback);
    },
    getArticleList: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/article/search", data, callback);
    },
    addWebGeneralize: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/webGeneralize/add", data, callback);
    },
    deleteWebGeneralize: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/webGeneralize/delete", data, callback);
    },
    updateWebGeneralize: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/webGeneralize/update", data, callback);
    },
    getWebGeneralizeDetail: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/webGeneralize/detail", data, callback);
    },
    getSlideshowPictureList: function (data, callback) {
        var self = this;
        self.ajax("post", "/admin/config/slideshowPicture/list", data, callback);
    },
    deleteSlideshowPicture: function (data, callback) {
        var self = this;
        self.ajax("get", "/admin/config/slideshowPicture/delete", data, callback);
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
                        location.replace("/login.html");
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
                        location.replace("/admin/login.html");
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


