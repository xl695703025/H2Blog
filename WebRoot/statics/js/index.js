$(document).ready(function() {
	$("#updateBlog").hide();
	$(".del").click(function(){
		$this = $(this);
	    $.ajax({
	        url: '../article/delArticle',
	        type: 'post',
	        dataType: 'json',
	        data: {
	            'articleId': $(this).parent().parent().children().eq(0).text()
	        },
	        success: function(data) {
	            if (data.res > 0) {
	                alert("删除成功~!");
	                $this.parent().parent().remove();
	            } else {
	                alert("删除失败~!");
	            }
	        },
	        error: function() {
	            alert("网络错误~!");
	        }
	    });
	})


	var E = window.wangEditor;
    var editor1 = new E('#div1', '#div2'); // 两个参数也可以传入 elem 对象，class 选择器
    editor1.customConfig.menus = [
        'head', // 标题
        'bold', // 粗体
        'fontSize', // 字号
        'fontName', // 字体
        'italic', // 斜体
        'underline', // 下划线
        'strikeThrough', // 删除线
        'foreColor', // 文字颜色
        'backColor', // 背景颜色
        'link', // 插入链接
        'list', // 列表
        'justify', // 对齐方式
        'quote', // 引用
        'emoticon', // 表情
        'image', // 插入图片
        'table', // 表格
        'code', // 插入代码
        'undo', // 撤销
        'redo' // 重复
    ];
    editor1.customConfig.uploadFileName = 'img';
    editor1.customConfig.uploadImgServer = '../article/uploadImg';
    editor1.customConfig.uploadImgTimeout = 600000;
    editor1.create();
	$(".update").click(function(event) {
		$("#indexContent").hide();
		$("#updateBlog").show();

		var $tds = $(this).parent().parent().children();
        $("#articleId").val($tds.eq(0).text());
        $("#updateArticleTitle").val($tds.eq(1).text());
        $("#updatePCategory").val($tds.eq(3).text());
        $("#updateTagName").val($tds.eq(5).text());
        $("#updateCCategory").val($tds.eq(4).text());
        $.ajax({
            url: '../article/getArticleById',
            type: 'post',
            dataType: 'json',
            data: { "id": $tds.eq(0).text() },
            success: function(data) {
                editor1.txt.html(data.article.articleContent);
            },
            error: function() {

            }
        });
	});
	$("#updateArticleBtn").click(function(event) {
        if ($("#updateArticleTitle").val() == '') {
            alert("标题不能为空~！");
            return false;
        }
        if ($("#updatePCategory").val() == '') {
            alert("一级分类不能为空");
            return false;
        }
        if ($("#updateCCategory").val() == '') {
            alert("二级分类不能为空");
            return false;
        }
        $.ajax({
            url: '../article/updateArticle',
            type: 'post',
            dataType: 'json',
            data: {
                "articleId": $("#articleId").val(),
                "articleTitle": $("#updateArticleTitle").val(),
                "pCategory": $("#updatePCategory").val(),
                "articleTagIds": $("#updateTagName").val(),
                "cCategory": $("#updateCCategory").val(),
                "articleContent": editor1.txt.html()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("修改成功~!");
                    location.reload();
                } else {
                    alert("修改失败:分类可能不存在，您可以尝试先创建新的分类~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
})