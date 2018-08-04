$(document).ready(function() {
	$("#addTagBtn").click(function(event) {
        $.ajax({
            url: 'addTag',
            type: 'post',
            dataType: 'json',
            data: {
                "tagName": $("#tagName").val(),
                "tagDescription": $("#tagDescription").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("添加成功~!");
                    window.location.href = "manage";
                } else {
                    alert("标签已经存在，添加失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
})