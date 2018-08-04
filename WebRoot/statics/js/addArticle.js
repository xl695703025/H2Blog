$(document).ready(function($) {
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
    $("#submitArticleBtn").click(function(event) {
        if ($("#newArticleTitle").val() == '') {
            alert("标题不能为空~！");
            return false;
        }
        if ($("#newPCategory").val() == '') {
            alert("一级分类不能为空");
            return false;
        }
        if ($("#newCCategory").val() == '') {
            alert("二级分类不能为空");
            return false;
        }
        $.ajax({
            url: 'addArticle',
            type: 'post',
            dataType: 'json',
            data: {
                "articleTitle": $("#newArticleTitle").val(),
                "pCategory": $("#newPCategory").val(),
                "articleTagIds": $("#newTagName").val(),
                "cCategory": $("#newCCategory").val(),
                "articleContent": editor1.txt.html()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("提交成功~!");
                    location.reload();
                } else {
                    alert("提交失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
});