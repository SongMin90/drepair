<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html class="no-js">
<jsp:include page="head.jsp"></jsp:include>
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
						<strong class="am-text-primary am-text-lg">HOME</strong> / <small><c:if test="${isStu == 1}">学生</c:if><c:if test="${isStu == 0}">宿管</c:if>报修订单</small>
					</div>
				</div>

				<hr>

				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-6">
						<div class="am-btn-toolbar">
							<div class="am-btn-group am-btn-group-xs">
								<button type="button" onclick="return delAll()" class="am-btn am-btn-default">
									<span class="am-icon-trash-o"></span> 删除
								</button>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-3">
						<div class="am-form-group">
							<select id="orderState" data-am-selected="{btnSize: 'sm'}">
								<option value="1">审核中</option>
								<option value="2">审核通过</option>
								<option value="3">审核不通过</option>
								<option value="11">维修中</option>
								<option value="12">维修完成</option>
								<option value="13">维修失败</option>
							</select>
						</div>
					</div>
					<!-- 订单状态下拉 -->
					<script type="text/javascript">
						$("#orderState option[value="+${orderState}+"]").attr("selected",true); // 选中当前状态
						$("#orderState").change(function () {  
				            var state = $(this).children('option:selected').val();  
				            window.location.href='${pageContext.request.contextPath}/order/allOrder?isStu='+${isStu}+'&orderState='+state;
				        });
					</script>
					<div class="am-u-sm-12 am-u-md-3">
						<div class="am-input-group am-input-group-sm">
							<input type="text" id="searchContent" class="am-form-field"> <span
								class="am-input-group-btn">
								<button class="am-btn am-btn-default" onclick="return search()" type="button">搜索</button>
							</span>
						</div>
					</div>
				</div>
				
				<!-- 搜索js -->
				<script type="text/javascript">
					function search() {
						var searchContent = document.getElementById('searchContent').value;
						window.location.href='${pageContext.request.contextPath}/order/allOrder?isStu='+${isStu}+'&orderState='+${orderState}+'&searchContent='+searchContent;
					}
				</script>
				
				<div class="am-g">
					<div class="am-u-sm-12">
						<form id="orders" class="am-form">
							<table
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>
										<th class="table-check"><input id="checkall" type="checkbox" /></th>
										<th>ID</th>
										<th>内容</th>
										<th>寝室</th>
										<th class="am-hide-sm-only">创建时间</th>
										<th class="am-hide-sm-only">结束时间</th>
										<th>级别</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orderList}" var="order">
										<tr>
											<td><input name="ids" type="checkbox" value="${order.orderId}" /></td>
											<td>${order.orderId}</td>
											<td>${order.orderInfo}</td>
											<th>${order.orderRoom}</th>
											<th class="am-hide-sm-only"><fmt:formatDate value="${order.orderStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
											<th class="am-hide-sm-only"><fmt:formatDate value="${order.orderOverTime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
											<th>
												<c:choose>
													<c:when test="${order.orderSort == 0}">
													未评定
													</c:when>
													<c:when test="${order.orderSort == 1}">
													普通
													</c:when>
													<c:when test="${order.orderSort == 2}">
													急
													</c:when>
													<c:when test="${order.orderSort == 3}">
													加急
													</c:when>
												</c:choose>
											</th>
											<td>
												<div class="am-btn-toolbar">
													<div class="am-btn-group am-btn-group-xs">
														<button type="button" onclick="location.href='${pageContext.request.contextPath}/order/editOrder?orderId=${order.orderId}'"
															class="am-btn am-btn-default am-btn-xs am-text-secondary" title="编辑">
															<span class="am-icon-pencil-square-o"></span>
														</button>
														<button type="button" onclick="return passOrder('${order.orderId}')"
															class="am-btn am-btn-default am-btn-xs am-text-success" title="通过审核">
															√
														</button>
														<button type="button" onclick="return failOrder('${order.orderId}')"
															class="am-btn am-btn-default am-btn-xs am-text-danger" title="不通过审核">
															×
														</button>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- 设置订单通过审核的弹窗 -->
							<div class="am-modal am-modal-prompt" tabindex="-1" id="pass-prompt">
							  <div class="am-modal-dialog">
							    <div class="am-modal-hd">通过审核</div>
							    <div class="am-modal-bd">
							            设置报修等级
							      <select class="am-modal-prompt-input">
							      	<option value="1">普通</option>
							      	<option value="2">急</option>
							      	<option value="3">加急</option>
							      </select>
							    </div>
							    <div class="am-modal-footer">
							      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
							      <span class="am-modal-btn" data-am-modal-confirm>提交</span>
							    </div>
							  </div>
							</div>
							<!-- 设置订单不通过的弹窗 -->
							<div class="am-modal am-modal-confirm" tabindex="-1" id="fail-confirm">
							  <div class="am-modal-dialog">
							    <div class="am-modal-hd">审核不通过</div>
							    <div class="am-modal-bd">
							            请输入结束原因
							      <input class="am-modal-prompt-input">
							    </div>
							    <div class="am-modal-footer">
							      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
							      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
							    </div>
							  </div>
							</div>
							<!-- 是否批量删除的弹窗 -->
							<div class="am-modal am-modal-confirm" tabindex="1" id="delAll-confirm">
							  <div class="am-modal-dialog">
							    <div class="am-modal-hd">是否删除已选中的选项？</div>
							    <div class="am-modal-footer">
							      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
							      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
							    </div>
							  </div>
							</div>
							<!-- 弹窗js -->
							<script type="text/javascript">
								// 全选
								$("#checkall").click(function(){ 
								  $("input[name='ids']").each(function(){
									  if (this.checked) {
										  this.checked = false;
									  }
									  else {
										  this.checked = true;
									  }
								  });
								})
								
								// 批量删除
								function delAll() {
									$('#delAll-confirm').modal({
									relatedTarget: this,
									onConfirm: function(e) {
										$.ajax({
											type:'POST',
											async:false,
											url:'${pageContext.request.contextPath}/order/delAllOrders',
											data:$('#orders').serialize(),
											dataType:'json',
											success:function(data){
												alert(data.result);
												window.location.reload();
											},
											error:function(XMLHttpRequest, textStatus, errorThrown){
												alert(textStatus+XMLHttpRequest.status);  
											}
										});
									},
								      onCancel: function(e) {
								        
								      }
								    });
								}
								
								// 通过审核
								function passOrder(orderId) {
									$('#pass-prompt').modal({
								      relatedTarget: this,
								      onConfirm: function(e) {
								    	  $.ajax({
											type:'POST',
											async:false,
											url:'${pageContext.request.contextPath}/order/passOrder',
											data:"orderId="+orderId+"&orderSort="+e.data,
											dataType:'json',
											success:function(data){
												alert(data.result);
												window.location.reload();
											},
											error:function(XMLHttpRequest, textStatus, errorThrown){
												alert(textStatus+XMLHttpRequest.status);  
											}
										});
								      },
								      onCancel: function(e) {
								        
								      }
								    });
								}
								
								// 不通过审核
								function failOrder(orderId) {
									$('#fail-confirm').modal({
									      relatedTarget: this,
									      onConfirm: function(e) {
									    	  $.ajax({
												type:'POST',
												async:false,
												url:'${pageContext.request.contextPath}/order/failOrder',
												data:"orderId="+orderId+"&evalContent="+e.data,
												dataType:'json',
												success:function(data){
													alert(data.result);
													window.location.reload();
												},
												error:function(XMLHttpRequest, textStatus, errorThrown){
													alert(textStatus+XMLHttpRequest.status);  
												}
											});
									      },
									      onCancel: function(e) {
									        
									      }
									    });
								}
							</script>
							<div class="am-cf">
								每页显示<select id="tableSize" data-am-selected="{maxHeight: 100, btnWidth: 50}">
								  <option value="2">2</option>
								  <option value="5">5</option>
								  <option value="10">10</option>
								  <option value="20">20</option>
								  <option value="30">30</option>
								</select>条
								<div class="am-fr">
									<ul class="am-pagination">
										<div data-am-page="{pages:${pageCount},curr:${nowPage},jump:'${pageContext.request.contextPath}/order/allOrder?nowPage=%page%&isStu='+${isStu}+'&orderState=${orderState}&size=${size}+&searchContent='+${searchContent}}"></div>
									</ul>
								</div>
							</div>
							<!-- 每页显示大小下拉 -->
							<script type="text/javascript">
								$("#tableSize option[value="+${size}+"]").attr("selected",true); // 选中当前状态
								$("#tableSize").change(function () {  
						            var size = $(this).children('option:selected').val();  
						            window.location.href='${pageContext.request.contextPath}/order/allOrder?isStu='+${isStu}+'&orderState='+${orderState}+'&size='+size;
						        });
							</script>
							<hr />
							<p>注：.....</p>
						</form>
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

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
