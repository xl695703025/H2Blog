$(document).ready(function() {
    $("#updateCategoryBtn").click(function(event) {

        if ($("#newCategoryName").val() == "") {
            alert("分类名不能为空~!");
            return;
        }
        $.ajax({
            url: 'updateCategory',
            type: 'post',
            dataType: 'json',
            data: {
                "categoryId": $("#categoryId").val(),
                "categoryName": $("#newCategoryName").val(),
                "categoryDescription": $("#newCategoryDescription").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("修改成功~!");
                    window.location.href = "manage";
                } else {
                    alert("分类名已经存在，修改失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
    $(".update").click(function(event) {
        $("#updateCategory").show();
        $("#indexContent").hide();
        $tr = $(this).parent().parent();
        $('#categoryId').val($tr.children().eq(0).text());
        if ($tr.children().eq(1).text() != '') {
            $('#newCategoryName').val($tr.children().eq(1).text())
        } else {
            $('#newCategoryName').val($tr.children().eq(2).text())
        }
        $('#newCategoryDescription').val($tr.children().eq(3).text())
    });
    $(".del").click(function(event) {
        var $this = $(this);
        $.ajax({
            url: 'delCategory',
            type: 'post',
            dataType: 'json',
            data: {
                'categoryId': $(this).parent().parent().children().eq(0).text()
            },
            success: function(data) {
                if (data.res > 0) {

                    alert("删除成功~!");
                    for (var k = 0; k < data.res - 1; k++) {
                        $this.parent().parent().next().remove();
                    }
                    $this.parent().parent().remove();
                } else {
                    alert("删除失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
});

   