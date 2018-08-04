
function imgPreview(fileDom) {
	//判断是否支持FileReader
	if (window.FileReader) {
		var reader = new FileReader();
	} else {
		alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
	}

	//获取文件
	var file = fileDom.files[0];
	var imageType = /^image\//;
	//是否是图片
	if (!imageType.test(file.type)) {
		alert("请选择图片！");
		return;
	}
	//读取完成
	reader.onload = function(e) {
		//获取图片dom
		var img = document.getElementById("avatar");
		//图片路径设置为读取的图片
		img.src = e.target.result;
	};
	$("#avatarMessage").text("");
	reader.readAsDataURL(file);
}

$(document).ready(function() {
	$("#loginBtn").click(function() {
		$.ajax({
			type : "post",
			url : "login",
			dataType : "json", //data传递的是一个json类型的值，而不是字符串，且必须标明dataType的类型，否则会出现400错误或者其他错误。
			data : {
				"userName" : $("#userName").val(),
				"password" : $("#password").val()
			},
			success : function(data) {
				if (data.result == "success") {
					window.location.href = '../admin/index?pageNow=1&pageSize=10';
				} else
					$("#errorMessage").text(data.error);
			},
			error : function() {
				$("#errorMessage").text("网络错误");
			}
		});
	});

	checkUserName = function(event) {
		if ($("#regUserName").val() == '') {
			$("#userNameMessage").text("用户名不能为空").addClass('red').removeClass('green');
			return false;
		}
		$.ajax({
			url : 'checkUserName',
			type : 'post',
			dataType : 'JSON',
			data : {
				"userName" : $("#regUserName").val()
			},
			success : function(data) {
				$("#userNameMessage").text(data.message);
				if (data.result == "success") {
					$("#userNameMessage").addClass('green').removeClass('red');
				} else {
					$("#userNameMessage").addClass('red').removeClass('green');
				}
			},
			error : function() {
				$("#userNameMessage").text("网络错误").addClass('red').removeClass('green');
			}
		});
		if ($("#userNameMessage").text() == "用户名可用")
			return true;
		else
			return false;
	}
	checkEmail = function() {
		var str = $("#regEmail").val();
		if (str == '') {
			$("#emailMessage").text('邮箱不能为空').addClass('red').removeClass('green');
			return false;
		}
		var re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
		if (re.test(str)) {
			$("#emailMessage").text('').addClass('green').removeClass('red');
			return true;
		} else {
			$("#emailMessage").text('邮箱格式不正确').addClass('red').removeClass('green');
			return false;
		}
	}
	checkPassword = function() {
		var str = $("#regPassword").val();
		if (str.length < 6) {
			$("#passwordMessage").text('密码不能小于6位').addClass('red');
			return false;
		} else if (str.length > 16) {
			$("#passwordMessage").text('密码不能大于16位').addClass('red');
			return false;
		} else {
			$("#passwordMessage").text('');
			return true;
		}
	}
	checkNickName = function() {
		var str = $("#regNickName").val();
		if (str == "") {
			$("#nickNameMessage").text('昵称不能为空').addClass('red');
			return false;
		} else {
			$("#nickNameMessage").text('');
			return true;
		}
	}
	checkAvatar = function() {
		var str = $("#regAvatar").val();
		if (str == "") {
			$("#avatarMessage").text("请选择头像").addClass('red');
			return false;
		} else {
			$("#avatarMessage").text("");
			return true;
		}
	}
	checkAll = function() {
		b1 = checkUserName();
		b2 = checkNickName();
		b3 = checkPassword();
		b4 = checkEmail();
		b5 = checkAvatar();
		if (b1 && b2 && b3 && b4 && b5) {
			var form1 = new FormData($("#form1")[0]);
			$.ajax({
				url : "register",
				type : "post",
				data : form1,
				dataType : "JSON",
				cache : false,
				processData : false,
				contentType : false,
				success : function(data) {
					if (data.result == "success") {
						alert("注册成功");
						$(location).attr('href', 'login');
					} else
						alert(data.error);
				},
				error : function() {
					$("#userNameMessage").text("网络错误");
				}
			});
		}
	}
	$("#regUserName").blur(checkUserName);
	$("#regEmail").blur(checkEmail);
	$("#regPassword").blur(checkPassword);
	$("#regNickName").blur(checkNickName);
	$("#registerBtn").click(checkAll);
})