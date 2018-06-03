/**
 * Created by ZHX on 2017/12/22.
 */
var swiper = {
    swiperTopFixed: function () {
        var topMargin = 48;
        var views = $("#swiper-container .swiper-slide").length > 4 && 4 || $("#swiper-container .swiper-slide").length;
        var swiperHC = new Swiper('#swiper-container', {
            slidesPerView: views,
            slidesPerColumn: 1,
            paginationClickable: true,
            spaceBetween: 0
        });
        $(".swiper-slide").css("width", 100 / views + "%");
        $("#swiper-container .swiper-slide").click(function () {
            var index = $(this).index();
            $(this).addClass("cur").siblings().removeClass("cur");

            $("#swiper-container").css("position", "fixed");
            $("#swiper-container").css("top", topMargin + "px");

            var goTo = $(".iba-act-area:eq(" + index + ")").offset().top - 44 - topMargin;
            $("html, body").animate({scrollTop: goTo}, 50);

        });
        $(window).scroll(function () {
            var tabTop = $("#iba-act-container").offset().top;
            var scrollTop = $(this).scrollTop() + 111;
            var curTabId = $("#swiper-container .swiper-slide.cur").index();
            var $curTab = $(".iba-act-area:eq(" + curTabId + ")");
            var curTabTop = $curTab.offset().top;
            var curTabBottom = curTabTop + $curTab.height();
            var index = curTabId;
            if (scrollTop < curTabTop && Number(index) > 0) {
                index--;
                $("#swiper-container .swiper-slide.cur").removeClass("cur");
                $("#swiper-container .swiper-slide:eq(" + index + ")").addClass("cur");
                swiperHC.slideTo(index, 1000, false);
            } else if (scrollTop > curTabBottom && Number(index) < ( $("#swiper-container .swiper-slide").length - 1)) {
                index++;
                $("#swiper-container .swiper-slide.cur").removeClass("cur");
                $("#swiper-container .swiper-slide:eq(" + index + ")").addClass("cur");
                swiperHC.slideTo(index, 1000, false);
            }

            if (scrollTop > tabTop + 50) {
                $("#swiper-container").css("position", "fixed");
                $("#swiper-container").css("top", topMargin + "px");
            } else {
                $("#swiper-container").css("position", "absolute");
                $("#swiper-container").css("top", "0px");
            }
        });
    }
}
