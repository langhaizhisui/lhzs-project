var pageNav =
    '<div class="header">' +
    '    <div class="logo">' +
    '        <img src="images/logo2.jpg"/>' +
    '        <span>星品网管理系统</span>' +
    '    </div>' +
    '    <div class="topNav">' +
    '        <span class="topNavText topNavSelected">商品管理</span>' +
    '        <span class="topNavText">用户管理</span>' +
    '        <span class="topNavText">系统管理</span>' +
    '    </div>' +
    '    <div class="user">' +
    '        <span>欢迎您：詹海旋</span>' +
    '        <span><a href="#">退出</a></span>' +
    '    </div>' +
    '</div>' +
    '<div class="left">' +
    '    <div>' +
    '        <div class="leftNav">' +
    '            <span class="arrow-down"></span>' +
    '            <span>商品监控</span>' +
    '        </div>' +
    '        <div class="pageNav">' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText leftNavSelected">商品列表</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品编辑</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品报警</div>' +
    '        </div>' +
    '        <div class="solidLine"></div>' +
    '    </div>' +
    '    <div>' +
    '        <div class="leftNav">' +
    '            <span class="arrow-up"></span>' +
    '            <span>商品监控</span>' +
    '        </div>' +
    '        <div class="pageNav hidden">' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品列表</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品编辑</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品报警</div>' +
    '        </div>' +
    '        <div class="solidLine"></div>' +
    '    </div>' +
    '    <div>' +
    '        <div class="leftNav">' +
    '            <span class="arrow-up"></span>' +
    '            <span>商品监控</span>' +
    '        </div>' +
    '        <div class="pageNav hidden">' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品列表</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品编辑</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品报警</div>' +
    '        </div>' +
    '        <div class="solidLine"></div>' +
    '    </div>' +
    '    <div>' +
    '        <div class="leftNav">' +
    '            <span class="arrow-up"></span>' +
    '            <span>商品监控</span>' +
    '        </div>' +
    '        <div class="pageNav hidden">' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品列表</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品编辑</div>' +
    '            <div class="dotLine"></div>' +
    '            <div class="leftNavText">商品报警</div>' +
    '        </div>' +
    '        <div class="solidLine"></div>' +
    '    </div>' +
    '    <div>' +
    '        <div class="leftNav">' +
    '            <span class="arrow-up"></span>' +
    '            <span>商品监控</span>' +
    '        </div>' +
    '        <div class="solidLine"></div>' +
    '    </div>' +
    '    <div>' +
    '        <div class="leftNav">' +
    '            <span class="arrow-up"></span>' +
    '            <span>商品监控</span>' +
    '        </div>' +
    '        <div class="solidLine"></div>' +
    '    </div>' +
    '</div>' +
    '<div class="navText">商品管理 > 商品监控 > 商品列表</div>'

$(function () {
    $("#pageNav").html(pageNav);
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
        $(".leftNavText").removeClass("leftNavSelected");
        $(this).addClass("leftNavSelected");
    });

    $(".topNavText").click(function () {
        $(".topNavText").removeClass("topNavSelected");
        $(this).addClass("topNavSelected");
    });

})
