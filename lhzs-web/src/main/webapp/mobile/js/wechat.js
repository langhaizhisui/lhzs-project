/**
 * Created by ZHX on 2017/12/21.
 */
wechat = {
    share: function () {
        var menuShareTimeline = new Object();
        var menuShareAppMessage = new Object();
        //TODO 获取分享数据
        var menuItems =['menuItem:share:appMessage', 'menuItem:share:timeline'];
        wechat.showMenuItems(menuItems);

        wechat.shareFriendOrCircle(menuShareAppMessage, menuShareTimeline);
    },
    showMenuItems: function (menyList) {
        wx.ready(function () {
            wx.showMenuItems({
                menuList: menyList
            });
        });
    },
    shareFriendOrCircle: function (menuShareAppMessage, menuShareTimeline) {
        wx.ready(function () {
            wx.checkJsApi({
                jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline'],
                success: function (res) {

                }
            });
            wx.onMenuShareAppMessage({
                title: menuShareAppMessage.shareTitle,
                link: menuShareAppMessage.shareUrl,
                desc: menuShareAppMessage.shareDesc,
                imgUrl: menuShareAppMessage.shareImg
            });

            wx.onMenuShareTimeline({
                title: menuShareTimeline.shareTitle,
                link: menuShareTimeline.shareUrl,
                desc: menuShareTimeline.shareDesc,
                imgUrl: menuShareTimeline.shareImg
            });
        });
    },
    isWechat:function () {
        var ua = window.navigator.userAgent.toLowerCase();
        if(ua.match(/MicroMessenger/i) == 'micromessenger'){
            return true;
        }else{
            return false;
        }
    }
}