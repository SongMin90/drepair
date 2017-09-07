<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="alternate icon" type="image/png" href="assets/i/favicon.ico">
	<link rel="stylesheet" href="assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="assets/css/tr.css"/>
</head>
<body>
	<div class="am-g tr-yunpan">
		<div class="tr-yunpan-bg-container am-container am-container-collapse">
			<div data-am-widget="slider" class="tr-yunpan-bg-slider am-hide-sm-only am-slider am-slider-a1 am-no-layout" data-am-slider="{animation:'slide',directionNav:false,pauseOnHover: false}">
				<div class="am-viewport" style="overflow: hidden; position: relative;">
					<ul class="am-slides" style="width: 800%; transition-duration: 0s; transform: translate3d(-1000px, 0px, 0px);">
						<li class="clone" aria-hidden="true" style="width: 1000px; float: left; display: block;">
							<img src="assets/images/netdisk-slider-p2.png" draggable="false">
						</li>
						<li class="am-active-slide" style="width: 1000px; float: left; display: block;">
							<img src="assets/images/netdisk-slider-p1.png" draggable="false">
						</li>
						<li class="" style="width: 1000px; float: left; display: block;">
							<img src="assets/images/netdisk-slider-p2.png" draggable="false">
						</li>
						<li class="clone" aria-hidden="true" style="width: 1000px; float: left; display: block;">
							<img src="assets/images/netdisk-slider-p1.png" draggable="false">
						</li>
					</ul>
				</div>		
				<ol class="am-control-nav am-control-paging">
					<li>
						<a class="am-active">1</a><i></i>
					</li>
					<li>
						<a class="">2</a><i></i>
					</li>
				</ol>
			</div>
		</div>
		<div class="tr-yunpan-container am-container am-container-collapse am-vertical-align">
			<div class="am-vertical-align-middle am-g am-g-collapse" style="width:100%;">
  		
				<div class="tr-yunpan-login-container am-container am-u-md-4 am-u-md-push-8 am-u-sm-10 am-u-sm-push-1">
					<form id="yunpan-login-form" class="am-form am-padding-xl am-padding-bottom-sm" method="post" action="" novalidate="novalidate">
						<div class="am-g am-container">
							<h2>登录寝室报修系统</h2>
						</div>
						<div class="am-g am-padding-sm">
							<div class="am-form-group am-form-icon am-form-error">
								<span class="am-icon-user"></span>
								<input type="text" name="idOrPhone" value="${idOrPhone}" class="am-form-field am-radius am-field-error am-active" placeholder="工号/手机号" required/>
							</div>
							<div class="am-form-group am-form-icon">
								<span class="am-icon-lock"></span>
								<input type="password" name="password" class="am-form-field am-radius" placeholder="密码" minlength="6" required/>
							</div>
							<div class="am-form-group am-form-icon am-g">
								<div class="am-u-sm-7">
									<span class="am-icon-check"></span>
									<input type="text" name="captcha" class="am-form-field am-radius" placeholder="验证码" minlength="4" maxlength="4" required/>
								</div>
								<div class="am-u-sm-5 am-text-right">
									<img onclick="this.setAttribute('src','http://www.trcloud.cn/netdisk/captcha/?nocache='+Math.random());" src="${pageContext.request.contextPath}/admin/getImgCode" alt="验证码" class="am-img-thumbnail">
								</div>
							</div>
							<button type="submit" class="am-btn am-btn-danger am-btn-block am-radius">登 录</button>
							<div class="am-form-group am-margin-top-sm am-text-sm">
							  <div class="am-fl">
								<div class="checkbox">
								  <label>
									<input type="checkbox" name="remember"> 记住账号
								  </label>
								</div>
							  </div>
							  <div class="am-fr"><a href="javascript:void(0)" id="forgotwosswordModalBtn">忘记密码?</a></div>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	
	<!-- 提示信息 -->
	<div id="tr-login-alert-dialog" class="am-modal am-modal-alert" tabindex="-1">
		<div class="am-modal-dialog">
		    <div class="am-modal-hd">${message}</div>
		    <div class="am-modal-bd" id="tr-login-alert-body"></div>
		    <div class="am-modal-footer">
		      <span class="am-modal-btn">关闭</span>
		    </div>
		</div>
	</div>
	
	<!-- 忘记密码弹窗 -->
	<div class="am-modal am-modal-no-btn am-modal-active" tabindex="-1" id="yunpan-forgotpassword-modal" style="display: hide; margin-top: -107px;">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">忘记密码
			  <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="">×</a>
			</div>
			<div class="am-modal-bd">
				<div id="yunpan-forgotpassword-form" class="am-form am-padding" novalidate="novalidate">
					<div class="am-form-group am-input-group am-form-error">
					  <span class="am-input-group-label"><i class="am-icon am-icon-phone"></i></span>
					  <input type="text" id="fp_phone" name="phone" class="am-form-field am-radius js-pattern-mobile am-active am-field-error" placeholder="手机号码" required="" pattern="^1((3|5|8){1}\d{1}|70|77)\d{8}$">
					</div>
					<p><button id="sendSMSCodeBtn" type="button" class="am-btn am-btn-secondary am-btn-block">获取短信验证码</button></p>
					<div id="fp_secondstep_div" style="display:none;">
						<div class="am-form-group am-input-group">
						  <span class="am-input-group-label"><i class="am-icon am-icon-mobile"></i></span>
						  <input type="text" id="fp_smscode" name="smscode" class="am-form-field am-radius js-pattern-valitecode" placeholder="手机短信验证码" required="" pattern="^\d{4}$">
						</div>
						<div class="am-form-group am-input-group">
						  <span class="am-input-group-label"><i class="am-icon am-icon-lock"></i></span>
						  <input type="password" id="fp_new_password" name="new_password" class="am-form-field am-radius am-field-error am-active" placeholder="密码" minlength="6" required="">
						</div>
						<div class="am-form-group am-input-group">
						  <span class="am-input-group-label"><i class="am-icon am-icon-lock"></i></span>
						  <input type="password" id="fp_confirm_password" name="confirm_password" class="am-form-field am-radius am-field-error am-active" placeholder="确认密码" data-validation-message="请确认前后输入密码一致" required="" data-equal-to="#fp_new_password" pattern="^$">
						</div>
						<p><button id="forgotPasswordBtn" type="button" class="am-btn am-btn-warning am-btn-block">提 交</button></p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 底部信息关于栏 -->
	<footer class="footer2 am-padding-top-lg">
		<div class="am-container tr-telemail">
			<div class="am-g">
				<div class="am-u-md-6"><h2>最实用的寝室快修系统</h2></div>
				<div class="am-u-md-3"><span class="am-icon am-icon-envelope-o">&nbsp;1044453961@qq.com</span></div>
				<div class="am-u-md-3"><span class="am-icon am-icon-phone"></span>&nbsp;13037232106</div>
			</div>
			<hr>
			<div class="am-g am-container am-text-sm">
				<p>现代校园，我们都需要一套完善的寝室物品报修系统方便在校生。<br>
				版权所有 © SongM All Rights Reserved.<br>
				Theme by <a href="http://amazeui.org">Amaze</a></p>
			</div>
		</div>
	</footer>
	
	<!-- js -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/tr.js"></script>
	<script type="text/javascript">
		$(function() {
		  // 登录表单验证
		  var $form = $('#yunpan-login-form');
		  var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
		  $tooltip.appendTo(document.body);
		  $form.validator();
		  var validator = $form.data('amui.validator');
		  $form.on('focusin focusout', '.am-form-error input', function(e) {
		    if (e.type === 'focusin') {
		      var $this = $(this);
		      var offset = $this.offset();
		      var width = $this.width();
		      var msg = $this.data('validationMessage') || validator.getValidationMessage($this.data('validity'));
		      $tooltip.text(msg).show().css({
		        left: offset.left + width + 30,
		        top: offset.top
		      });
		    } else {
		      $tooltip.hide();
		    }
		  });
		  
		  // 呼出忘记密码弹窗
		  $('#forgotwosswordModalBtn').on('click',function(){
		  		$('#fp_phone').val('');
  				$('#fp_smscode').val('');
  				$('#fp_new_password').val('');
  				$('#fp_confirm_password').val('');
  				$('#sendSMSCodeBtn').show();
		  		$('#fp_secondstep_div').hide();
		  		var $modal = $('#yunpan-forgotpassword-modal');
		  		$modal.modal();
		  });
		  
		  // 忘记密码表单验证
		  var $form = $('#yunpan-forgotpassword-form');
		  var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
		  $tooltip.appendTo(document.body);
		  $form.validator();
		  var validator = $form.data('amui.validator');
		  $form.on('focusin focusout', '.am-form-error input', function(e) {
			if (e.type === 'focusin') {
			  var $this = $(this);
			  var offset = $this.offset();
			  var width = $this.width();
			  var msg = $this.data('validationMessage') || validator.getValidationMessage($this.data('validity'));
			  $tooltip.text(msg).show().css({
				left: offset.left + width + 30,
				top: offset.top
			  });
			} else {
			  $tooltip.hide();
			}
		  });
			  
		  // 发送验证码
		  $('#sendSMSCodeBtn').on('click',function(){
			$.ajax({
				type:'POST',
				async:false,
				url:'/netdisk/send_resetpassword_sms/',
				data:{'phone':$('#fp_phone').val()},
				dataType:'json',
				success:function(data){
					console.log(data);
					if(data.result == 'fail'){
						alert(data.message);
					}else if(data.result == 'success'){
						$('#sendSMSCodeBtn').hide();
						$('#fp_secondstep_div').show();
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(textStatus+XMLHttpRequest.status);  
				}
			});
		  });
		  
		  // 设置新密码
		  $('#forgotPasswordBtn').on('click',function(){
			if($form.validator('isFormValid') == true){
				$.ajax({
					type:'POST',
					async:false,
					url:'/netdisk/forgotpassword/',
					data:{phone:$('#fp_phone').val(),new_password:$('#fp_new_password').val(),'sms_code':$('#fp_smscode').val()},
					dataType:'json',
					success:function(data){
						if(data.result == 'fail'){
							alert(data.message);
						}else if(data.result == 'success'){
							alert('修改成功');
							$('#yunpan-forgotpassword-modal').modal('close');
							$('#fp_phone').val('');
							$('#fp_smscode').val('');
							$('#fp_new_password').val('');
							$('#fp_confirm_password').val('');
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						alert(textStatus+XMLHttpRequest.status);  
					}
				});
			}else{
				alert("请验证手机号码或密码输入格式是否合法");
			}
		  });
		});
	</script>
	
	<!-- 是否弹窗提示 -->
	<c:if test="${message != null}">
		<script type="text/javascript">
			$('#tr-login-alert-body').text('不存在该账号');
			$('#tr-login-alert-dialog').modal();
		</script>
	</c:if>
</body>
</html>
