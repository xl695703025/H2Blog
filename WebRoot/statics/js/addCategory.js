$(document).ready(function() {
	$("#addCategoryBtn").click(function(event) {
        if ($("#pCategoryName").val() == "") {
            alert('一级分类不能为空~！');
            return false;
        }
        $.ajax({
            url: 'addCategory',
            type: 'post',
            dataType: 'json',
            data: {
                "pCategoryName": $("#pCategoryName").val(),
                "cCategoryName": $("#cCategoryName").val(),
                "categoryDescription": $("#categoryDescription").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("添加成功~!");
                    window.location.href = "manage";
                } else {
                    alert("添加失败，一级分类或二级分类已经存在~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });

})