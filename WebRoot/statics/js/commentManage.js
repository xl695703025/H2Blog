$(document).ready(function() {
	$(".delBtn").click(function(){
		var $this = $(this);
	    $.ajax({
	        url: 'delComment',
	        type: 'post',
	        dataType: 'json',
	        data: {
	            'commentId': $(this).parent().parent().children().eq(0).text()
	        },
	        success: function(data) {
	            if (data.res) {
	                alert('删除成功~!');
	                $this.parent().parent().hide();
	            } else {
	                alert('删除失败~!');
	            }
	        },
	        error: function(data) {
	            alert('网络错误~!');
	        }
	    });
	})
})