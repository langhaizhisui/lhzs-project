/**
 * Created by ZHX on 2017/12/21.
 */
wechat = {
    share: function (friend, moments) {
        wechat.getConfig();
        wechat.showMenuItems(['menuItem:share:appMessage', 'menuItem:share:timeline']);
        wechat.shareFriendOrCircle(friend, moments);
    },
    showMenuItems: function (menuList) {
        wx.ready(function () {
            wx.showMenuItems({
                menuList: menuList
            });
        });
    },
    shareFriendOrCircle: function (friend, moments) {
        wx.ready(function () {
            wx.checkJsApi({
                jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline'],
                success: function (res) {

                }
            });
            wx.onMenuShareAppMessage({
                title: friend.title,
                link: friend.link,
                desc: friend.desc,
                imgUrl: friend.imgUrl
            });

            wx.onMenuShareTimeline({
                title: moments.title,
                link: moments.link,
                desc: moments.desc,
                imgUrl: moments.imgUrl
            });
        });
    },
    isWechat: function () {
        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true;
        } else {
            return false;
        }
    },
    getConfig: function () {
        var params = {
            "url": window.location.href
        };
        $.ajax({
            type: "post",
            url: "/app/weixin/config",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(params),
            success: function (result) {
                var data = result.data;
                wx.config({
                    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: data.appId,
                    timestamp: data.timestamp,
                    nonceStr: data.nonceStr,
                    signature: data.signature,
                    jsApiList: [
                        'checkJsApi',
                        'onMenuShareTimeline',
                        'onMenuShareAppMessage',
                        'hideMenuItems',
                        'showMenuItems',
                        'hideAllNonBaseMenuItem',
                        'showAllNonBaseMenuItem',
                        'getNetworkType',
                        'getLocation',
                        'hideOptionMenu',
                        'showOptionMenu',
                        'closeWindow',
                        'scanQRCode',
                        'chooseWXPay'
                    ]
                });
            }
        });
    },
    authorUrl: function (params, callback) {
        $.ajax({
            type: "post",
            url: "/app/weixin/author/url",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(params),
            success: callback
        });
    }
}