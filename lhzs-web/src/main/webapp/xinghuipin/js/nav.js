var navigation = {
    getMenu: function () {
        var menu = {
            data: [],
            firstNavIndex: 0,
            secondNavIndex: 0,
            thirdNavIndex: 0,
            secondNav: []
        };

        var param = {};
        app.getMenu(param, function (result) {
            if (result.code == 200) {
                menu.data = result.data;
                navigation.getCurrNavPosition(menu);
                navigation.setNav(menu);
            }
        });
    },
    getCurrNavPosition: function (menu) {
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
    setTopNav: function (menu, topNav) {
        var data = menu.data;
        for (var i = 0; i < data.length; i++) {
            if (menu.firstNavIndex == i) {
                topNav += '<span class="topNavText topNavSelected" url="' + data[i].url + '">' + data[i].name + '</span>';
            } else {
                topNav += '<span class="topNavText" url="' + data[i].url + '">' + data[i].name + '</span>';
            }
        }
        return topNav;
    },
    setLeftNav: function (menu, leftNav) {
        var secondNavList = menu.secondNav;
        for (var j = 0; j < secondNavList.length; j++) {
            var secondNav = secondNavList[j];
            leftNav +=
                '    <div>' +
                '        <div class="leftNav">' +
                '            <span class="arrow-down"></span>' +
                '            <span>' + secondNav.name + '</span>' +
                '        </div>' +
                '        <div class="pageNav">';
            var thirdNavList = secondNav.subSysAuthMenuList;
            for (var k = 0; k < thirdNavList.length; k++) {
                var thirdNav = thirdNavList[k];
                if (k == menu.thirdNavIndex && j == menu.secondNavIndex) {
                    leftNav += '<div class="dotLine"></div>' +
                        '            <div class="leftNavText leftNavSelected" url="' + thirdNav.url + '">' + thirdNav.name + '</div>';
                } else {
                    leftNav += '<div class="dotLine"></div>' +
                        '            <div class="leftNavText" url="' + thirdNav.url + '">' + thirdNav.name + '</div>';
                }
            }
            leftNav += '        </div>' +
                '        <div class="solidLine"></div>' +
                '    </div>';
        }
        return leftNav;
    },
    setNav: function (menu) {
        var topNav = "";
        var leftNav = "";
        $("#topNav").html(navigation.setTopNav(menu, topNav));
        $("#leftNav").html(navigation.setLeftNav(menu, leftNav));
    },
    getCurrPageUrl: function () {
        var url = location.pathname;
        if (!url) {
            url = "/admin/index.html";
        }
        return url;
    }
}

$(function () {
    var pageNav =
        '<div class="header">' +
        '    <div class="logo">' +
        '        <img src="images/logo2.jpg"/>' +
        '        <span>星惠品管理系统</span>' +
        '    </div>' +
        '    <div class="topNav" id = "topNav">' +
        '    </div>' +
        '    <div class="user">' +
        '        <span>欢迎您：詹海旋</span>' +
        '        <span><a href="#">退出</a></span>' +
        '    </div>' +
        '</div>' +
        '<div class="left" id="leftNav">' +
        '</div>' +
        '<div class="navText"><!--商品管理 > 商品监控 > 商品列表--></div>'
    $("#pageNav").html(pageNav);

    navigation.getMenu();

    setTimeout(function () {
        $(".leftNav").click(function () {
            var nav = $(this).find("span").first();
            if (nav.hasClass("arrow-down")) {
                nav.removeClass("arrow-down");
                nav.addClass("arrow-up");
                $(this).parent().find(".pageNav").addClass("hidden");
            } else {
                nav.addClass("arrow-down");
                nav.removeClass("arrow-up");
                $(this).parent().find(".pageNav").removeClass("hidden");
            }
        });

        $(".leftNavText").click(function () {
            if($(this).hasClass("leftNavSelected")){
                return;
            }
            location.href = $(this).attr("url");
        });

        $(".topNavText").click(function () {
            if($(this).hasClass("topNavSelected")){
                return;
            }
            location.href = $(this).attr("url");
        });
    }, 200);
});
