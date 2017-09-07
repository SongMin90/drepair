<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js">
<jsp:include page="head.jsp"></jsp:include>
<style>
  #vld-tooltip {
    position: absolute;
    z-index: 1000;
    padding: 5px 10px;
    background: #F37B1D;
    min-width: 150px;
    color: #fff;
    transition: all 0.15s;
    box-shadow: 0 0 5px rgba(0,0,0,.15);
    display: none;
  }

  #vld-tooltip:before {
    position: absolute;
    top: -8px;
    left: 50%;
    width: 0;
    height: 0;
    margin-left: -8px;
    content: "";
    border-width: 0 8px 8px;
    border-color: transparent transparent #F37B1D;
    border-style: none inset solid;
  }
</style>
	<jsp:include page="top.jsp"></jsp:include>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<jsp:include page="left.jsp"></jsp:include>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">HOME</strong> / <small>新增管理</small>
					</div>
				</div>

				<hr />

				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8"></div>
					<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
						<form action="${pageContext.request.contextPath}/admin/addAdmin" method="post" class="am-form am-form-horizontal" id="form-with-tooltip">
							<div class="am-form-group">
								<label class="am-u-sm-3 am-form-label">姓名 / Name</label>
								<div class="am-u-sm-9">
									<input type="text" name="adminName" placeholder="姓名 / Name" value="${admin.adminName}" required>
								</div>
							</div>

							<div class="am-form-group">
								<label class="am-u-sm-3 am-form-label">身份证号 / Idcard</label>
								<div class="am-u-sm-9">
									<input type="text" name="adminIcard" value="${admin.adminIcard}" placeholder="输入你的身份证号 / Idcard"
										minlength="18" maxlength="18" required>
								</div>
							</div>

							<div class="am-form-group">
								<label class="am-u-sm-3 am-form-label">手机号 / Phone</label>
								<div class="am-u-sm-9">
									<input type="number" name="adminPhone" value="${admin.adminPhone}" minlength="11" maxlength="11" required>
								</div>
							</div>

							<div class="am-form-group">
								<label class="am-u-sm-3 am-form-label">密码 / Password</label>
								<div class="am-u-sm-9">
									<input type="password" name="adminPwd" id="adminPwd"
										 value="${admin.adminPwd}" placeholder="设置密码" minlength="6" required>
								</div>
							</div>

							<div class="am-form-group">
								<label class="am-u-sm-3 am-form-label">确认密码 / CheckPassword</label>
								<div class="am-u-sm-9">
									<input type="password" placeholder="确认密码"
										data-equal-to="#adminPwd" required>
								</div>
							</div>

							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<button type="submit" class="am-btn am-btn-primary">提交</button>
								</div>
							</div>
						</form>
						<script type="text/javascript">
						$(function() {
							  var $form = $('#form-with-tooltip');
							  var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
							  $tooltip.appendTo(document.body);

							  $form.validator();

							  var validator = $form.data('amui.validator');

							  $form.on('focusin focusout', '.am-form-error input', function(e) {
							    if (e.type === 'focusin') {
							      var $this = $(this);
							      var offset = $this.offset();
							      var msg = $this.data('foolishMsg') || validator.getValidationMessage($this.data('validity'));

							      $tooltip.text(msg).show().css({
							        left: offset.left + 10,
							        top: offset.top + $(this).outerHeight() + 10
							      });
							    } else {
							      $tooltip.hide();
							    }
							  });
							});
						</script>
					</div>
				</div>
			</div>

			<footer class="admin-content-footer">
				<hr>
				<p class="am-padding-left">© SongM</p>
			</footer>

		</div>
		<!-- content end -->
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

	<!-- 是否弹窗提示 -->
	<c:if test="${message != null}">
		<script type="text/javascript">
			$('#tr-login-alert-dialog').modal();
		</script>
	</c:if>
	
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
