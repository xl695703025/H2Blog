$(document).ready(function() {
	$(".delBtn").click(function(){
		var $this = $(this);
        $.ajax({
            url: 'delTag',
            type: 'post',
            dataType: 'json',
            data: {
                'tagId': $(this).parent().parent().children().eq(0).text()
            },
            success: function(data) {
                if (data.res) {
                    alert("删除成功~!");
                    $this.parent().parent().hide();
                } else {
                    alert("删除失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
	})

    $(".update").click(function(event) {
        $tr = $(this).parent().parent();
        $('#tagId').val($tr.children().eq(0).text());
        $('#newtagName').val($tr.children().eq(1).text())
        $('#newtagDescription').val($tr.children().eq(2).text())
        $("#updateTag").show();
        $("#tagManageContent").hide();
    });
    $(".tagManage").click(function(event) {
        $("#updateTag").hide();
        $("#tagManageContent").show();
    });
    $('#updateTagBtn').click(function(event) {
        $.ajax({
            url: 'updateTag',
            type: 'post',
            dataType: 'json',
            data: {
                "tagId": $('#tagId').val(),
                "tagName": $('#newtagName').val(),
                "tagDescription": $('#newtagDescription').val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("修改成功~!");
                    location.reload();
                } else {
                    alert("修改失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });

    });
})