/**
 * Created by ZHX on 2017/12/28.
 */
var navigation = {
    getMenu:function () {
        var param = {};
        var menu = {
            data: [],
            firstNavIndex: 0,
            secondNavIndex: 0,
            thirdNavIndex: 0,
            secondNav: []
        };
        app.getMenu(param, function (result) {
            if (result.code == 200) {
                menu.data = result.data;
                navigation.getCurrNavPosition(menu);
                navigation.setNavPage(menu);
            }
        });
    },
    getCurrNavPosition:function(menu){
        var data = menu.data;
        var currPageUrl = navigation.getCurrPageUrl();
        for (var i = 0; i < data.length; i++) {
            var secondNavList = data[i].subSysAuthMenuList;
            for (var j = 0; j < secondNavList.length; j++) {
                var thirdNavList = secondNavList[j].subSysAuthMenuList;
                for (var k = 0; k < thirdNavList.length; k++) {
                    var thirdNavUrl = thirdNavList[k].url;
                    if (currPageUrl == thirdNavUrl) {
                        menu.firstNavIndex = i;
                        menu.secondNavIndex = j;
                        menu.secondNav = secondNavList;
                        menu.thirdNavIndex = k;
                    }
                }
            }
        }
    },
    setTopNav:function(menu, topNav){
        var data = menu.data;
        for (var i = 0; i < data.length; i++) {
            if (menu.firstNavIndex == i) {
                topNav += '<li id="prod-manage" class="nav-bg"><a href="' + data[i].url + '">' + data[i].name + '</a></li>';
            } else {
                topNav += '<li id="prod-manage"><a href="' + data[i].url + '">' + data[i].name + '</a></li>';
            }
        }
        return topNav;
    },
    setLeftNav:function(menu, leftNav){
        var secondNavList = menu.secondNav;
        for (var j = 0; j < secondNavList.length; j++) {
            var secondNav = secondNavList[j];
            leftNav += '<div>' +
                '<div class="left-nav-grade1">' + secondNav.name + '</div>' +
                '<div class="left-nav-grade2">';
            var thirdNavList = secondNav.subSysAuthMenuList;
            for (var k = 0; k < thirdNavList.length; k++) {
                var thirdNav = thirdNavList[k];
                if (k == menu.thirdNavIndex && j == menu.secondNavIndex) {
                    leftNav += '<div class="left-nav-bg prod-list prod-operation prod-all">' +
                        '<a href="' + thirdNav.url + '">' + thirdNav.name + '</a>' +
                        '</div>';
                } else {
                    leftNav += '<div class="left-nav-bg">' +
                        '<a href="' + thirdNav.url + '">' + thirdNav.name + '</a>' +
                        '</div>';
                }
            }
            leftNav += '</div></div>';
        }
        return leftNav;
    },
    setNavPage:function(menu){
        var topNav = "";
        var leftNav = "";
        $(".top-nav").html(navigation.setTopNav(menu, topNav));
        $(".left-nav").html(navigation.setLeftNav(menu, leftNav));
    },
    getCurrPageUrl:function(){
        var url = location.pathname;
        if (!url) {
            url = "/admin/index.html";
        }
        return url;
    }
}

$(function(){
    navigation.getMenu();
});