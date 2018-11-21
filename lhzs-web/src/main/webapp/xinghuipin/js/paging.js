/**
 * Created by ZHX on 2017/12/19.
 */
function splitPage(totalPage, currPage, node, prePage, nextPage, fixPage) {
    var pageNum = "";
    if (totalPage < 10) {
        pageNum += '<span class="pre-page">上一页</span>';
        for (var i = 0; i < totalPage; i++) {
            if ((i + 1) == currPage) {
                pageNum += '<span id="curr-page" index="' + (i + 1) + '">' + (i + 1) + '</span>';
            } else {
                pageNum += '<span index="' + (i + 1) + '">' + (i + 1) + '</span>';
            }
        }
        pageNum += '<span class="next-page">下一页</span>';
    } else if (totalPage > 10) {
        if (currPage < 5) {
            pageNum += '<span class="pre-page">上一页</span>';
            for (var i = 0; i < 5; i++) {
                if ((i + 1) == currPage) {
                    pageNum += '<span id="curr-page" index="' + (i + 1) + '">' + (i + 1) + '</span>';
                } else {
                    pageNum += '<span index="' + (i + 1) + '">' + (i + 1) + '</span>';
                }
            }
            pageNum += '<span>...</span>' +
                '<span index="' + totalPage + '">' + totalPage + '</span>' +
                '<span class="next-page">下一页</span>';
        } else if (currPage > totalPage - 5) {
            pageNum += '<span class="pre-page">上一页</span><span index="1">1</span><span>...</span>';
            for (var i = totalPage - 5; i < totalPage; i++) {
                if ((i + 1) == currPage) {
                    pageNum += '<span id="curr-page" index="' + (i + 1) + '">' + (i + 1) + '</span>';
                } else {
                    pageNum += '<span index="' + (i + 1) + '">' + (i + 1) + '</span>';
                }
            }
            pageNum += '<span class="next-page">下一页</span>';
        } else {
            pageNum += '<span class="pre-page">上一页</span>' +
                '<span index="1">1</span>' +
                '<span>...</span>' +
                '<span index="' + (currPage - 1) + '">' + (currPage - 1) + '</span>' +
                '<span id="curr-page" index="' + currPage + '">' + currPage + '</span>' +
                '<span index="' + (currPage + 1) + '">' + (currPage + 1) + '</span>' +
                '<span index="' + (currPage + 2) + '">' + (currPage + 2) + '</span>' +
                '<span>...</span>' +
                '<span index="' + totalPage + '">' + totalPage + '</span>' +
                '<span class="next-page">下一页</span>';
        }
    }
    $(node).html(pageNum);
    $(".page-num span").click(function () {
        fixPage($(this).attr("index"));
    });
    $(".pre-page").click(function () {
        prePage($("#curr-page").attr("index"));
    });
    $(".next-page").click(function () {
        nextPage($("#curr-page").attr("index"));
    });
}