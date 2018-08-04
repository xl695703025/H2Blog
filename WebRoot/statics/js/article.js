$(document).ready(function($) {
    $commentHide = $("#comment").clone();
    var k = 0;

    getComment = function(articleId) {
        $("#comments").empty();
        $.ajax({
            url: '../../comment/getCommentByArticleId',
            type: 'post',
            dataType: 'json',
            data: { "articleId": articleId },
            success: function(data) {
                commentList = data.commentList;
                for (k = 0; k < commentList.length; k++) {
                    $comment = $commentHide.clone();
                    $comment.appendTo('#comments').show();
                    $comment.children().eq(0).children().eq(0).text(k + 1);
                    $comment.children().eq(0).children().eq(1).text(commentList[k].commentCreateTime);
                    $comment.children().eq(0).children().eq(2).text(commentList[k].commentAuthorName);
                    $comment.children().eq(1).text(commentList[k].commentContent);
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    }
    getComment($("#articleId").val());
    $("#submitComment").click(function(event) {
        if ($("#nickName").val() == "") {
            alert("昵称不能为空~！");
            return false;
        }
        if ($("#email").val() == "") {
            alert("邮箱不能为空~！");
            return false;
        }
        if ($("#commentContent").val() == "") {
            alert("评论不能为空~！");
            return false;
        }
        var re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
        if (!re.test($("#email").val())) {
            alert("邮箱格式不正确~!");
            return false;
        }
        $.ajax({
            url: '../../comment/addComment',
            type: 'post',
            dataType: 'json',
            data: {
                "articleId": $("#articleId").val(),
                "nickName": $("#nickName").val(),
                "email": $("#email").val(),
                "commentContent": $("#commentContent").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("评论成功~！");
                    $comment = $commentHide.clone();
                    $comment.children().eq(0).children().eq(0).text(k++ + 1);
                    $comment.children().eq(0).children().eq(1).text(data.date);
                    $comment.children().eq(0).children().eq(2).text($("#nickName").val());
                    $comment.children().eq(1).text($("#commentContent").val());
                    $comment.appendTo('#comments').show();
                } else {
                    alert("评论失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
    $("#likeBtn").click(function(event) {
        $.ajax({
            url: '../../article/like',
            type: 'post',
            dataType: 'json',
            data: {
                "articleId": $("#articleId").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("点赞成功~!");
                } else {
                    alert("已经点赞了~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
});