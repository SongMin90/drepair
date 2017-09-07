<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html class="no-js">
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/assets/css/amazeui.datetimepicker.css">
<script src="${pageContext.request.contextPath}/admin/assets/js/amazeui.datetimepicker.min.js"></script>
<body>

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
						<strong class="am-text-primary am-text-lg">HOME</strong> / <small>编辑报修订单</small>
					</div>
				</div>

				<hr>

				<div class="am-tabs am-margin" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active"><a href="#tab1">订单信息</a></li>
						<li><a href="#tab2">创建人信息</a></li>
						<li><a href="#tab3">维修员信息</a></li>
						<li><a href="#tab4">订单评价</a></li>
					</ul>

					<div class="am-tabs-bd">
						<!-- 订单信息页 -->
						<div class="am-tab-panel am-fade am-in am-active" id="tab1">
						<form id="form_orderInfo" method="post">
						<input type="hidden" name="orderId" value="${order.orderId}">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">订单状态</div>
								<div class="am-u-sm-8 am-u-md-10">
									<div class="am-btn-group" data-am-button>
										<label class="am-btn am-btn-default am-btn-xs"> <input
											type="radio" name="orderState" id="option1" value="1"> 待审核
										</label> <label class="am-btn am-btn-default am-btn-xs"> <input
											type="radio" name="orderState" id="option2" value="2"> 审核通过
										</label> <label class="am-btn am-btn-default am-btn-xs"> <input
											type="radio" name="orderState" id="option3" value="3"> 审核不通过
										</label> <label class="am-btn am-btn-default am-btn-xs"> <input
											type="radio" name="orderState" id="option11" value="11"> 待维修
										</label> <label class="am-btn am-btn-default am-btn-xs"> <input
											type="radio" name="orderState" id="option12" value="12"> 维修成功
										</label> <label class="am-btn am-btn-default am-btn-xs"> <input
											type="radio" name="orderState" id="option13" value="13"> 维修失败
										</label>
									</div>
								</div>
								<script type="text/javascript">
									document.getElementById("option"+${order.orderState}).checked=true;
								</script>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">订单内容</div>
								<div class="am-u-sm-8 am-u-md-10">
									<div class="am-form-group">
								      <textarea rows="5" cols="44" name="orderInfo">${order.orderInfo}</textarea>
								    </div>
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">订单图片</div>
								<div class="am-u-sm-8 am-u-md-10">
									<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
									  am-avg-md-3 am-avg-lg-4 am-gallery-default" data-am-gallery="{ pureview: true }" >
									  	<c:forEach var="img" items="${imglist}">
									      <li>
									        <div class="am-gallery-item">
									            <a href="${pageContext.request.contextPath}/${img.imgUrl}" class="">
									              <img src="${pageContext.request.contextPath}/${img.imgUrl}"  alt="订单图片"/>
									                <h3 class="am-gallery-title">订单图片</h3>
									                <div class="am-gallery-desc"><fmt:formatDate value="${img.imgDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
									            </a>
									        </div>
									      </li>
									    </c:forEach>
								  	</ul>
								</div>
							</div>
							
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">楼层及寝室</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input name="orderRoom" value="${order.orderRoom}">
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">发布时间</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input size="16" type="text" value="<fmt:formatDate value="${order.orderStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly class="form-datetime am-form-field">
								</div>
							</div>
						
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">结束时间</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input size="16" type="text" value="<fmt:formatDate value="${order.orderOverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly class="form-datetime am-form-field">
								</div>
							</div>
							
							<!-- 时间格式转换 -->
							<script type="text/javascript">
								$('.form-datetime').datetimepicker({
									format: 'yyyy-mm-dd hh:ii'
								});
							</script>
							
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">级别</div>
								<div class="am-u-sm-8 am-u-md-10">
									<select id="orderSort" name="orderSort">
										<option value="0">未评定</option>
								      	<option value="1">普通</option>
								      	<option value="2">急</option>
								      	<option value="3">加急</option>
							      	</select>
								</div>
							</div>
							
							<!-- 级别js -->
							<script type="text/javascript">
								$("#orderSort option[value="+${order.orderSort}+"]").attr("selected",true); // 选中当前状态
							</script>
						</form>
						</div>
						<!-- 创建人信息页 -->
						<div class="am-tab-panel am-fade" id="tab2">
							<form class="am-form">
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">学号/工号</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<input type="text" class="am-input-sm" value="${id}" disabled>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">姓名</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<input type="text" class="am-input-sm" value="${name}" disabled>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">手机号</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<input type="text" class="am-input-sm" value="${phone}" disabled>
									</div>
								</div>

							</form>
						</div>

						<!-- 维修员信息页 -->
						<div class="am-tab-panel am-fade" id="tab3">
							<form class="am-form">
								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">工号</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<input type="text" class="am-input-sm" value="${repairerId}" disabled>
									</div>
								</div>

								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">姓名</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<input type="text" class="am-input-sm" value="${repairerName}" disabled>
									</div>
								</div>

								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">手机号</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<input type="text" class="am-input-sm" value="${repairerPhone}" disabled>
									</div>
								</div>
							</form>
						</div>

						<!-- 订单评价 -->
						<div class="am-tab-panel am-fade" id="tab4">
							<form class="am-form">
								<c:forEach items="${evalsList}" var="evals">
									<div class="am-g am-margin-top-sm">
										<div class="am-u-sm-4 am-u-md-2 am-text-right">${evals.evalName}：</div>
										<div class="am-u-sm-8 am-u-md-4 am-u-end">
											<input type="text" class="am-input-sm" value="${evals.evalContent}" disabled>
										</div>
									</div>
									<hr>
								</c:forEach>
							</form>
						</div>
						
					</div>
				</div>

				<div class="am-margin">
					<button onclick="return update()" type="button" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
				</div>
			</div>

			<footer class="admin-content-footer">
				<hr>
				<p class="am-padding-left">© SongM</p>
			</footer>
		</div>
		<script type="text/javascript">
			function update() {
				$.ajax({
					type:'POST',
					async:false,
					url:'${pageContext.request.contextPath}/order/update',
					data:$("#form_orderInfo").serialize(),
					dataType:'json',
					success:function(data){
						alert(data.result);
						window.location.reload();
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						alert(textStatus+XMLHttpRequest.status);  
					}
				});
			}
		</script>
		<!-- content end -->
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
