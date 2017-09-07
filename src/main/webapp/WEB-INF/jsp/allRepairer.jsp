<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
						<strong class="am-text-primary am-text-lg">HOME</strong> / <small>全部维修员</small>
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
						<div class="am-input-group am-input-group-sm">
							<input type="text" class="am-form-field"> <span
								class="am-input-group-btn">
								<button class="am-btn am-btn-default" type="button">搜索</button>
							</span>
						</div>
					</div>
				</div>

				<div class="am-g">
					<div class="am-u-sm-12">
						<form id="repairers" class="am-form">
							<table
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>
										<th class="table-check"><input id="checkall" type="checkbox" /></th>
										<th>工号</th>
										<th>姓名</th>
										<th>密码</th>
										<th class="am-hide-sm-only">手机号</th>
										<th class="am-hide-sm-only">身份证号</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${repairerList}" var="repairer">
										<tr>
											<td><input name="ids" type="checkbox" value="${repairer.repairerId}" /></td>
											<td>${repairer.repairerId}</td>
											<td>${repairer.repairerName}</td>
											<td>${repairer.repairerPwd}</td>
											<td class="am-hide-sm-only">${repairer.repairerPhone}</td>
											<td class="am-hide-sm-only">${repairer.repairerIcard}</td>
											<td>
												<div class="am-btn-toolbar">
													<div class="am-btn-group am-btn-group-xs">
														<button type="button" onclick="location.href='${pageContext.request.contextPath}/repairer/editRepairer?repairerId=${repairer.repairerId}'"
															class="am-btn am-btn-default am-btn-xs am-text-secondary" title="编辑">
															<span class="am-icon-pencil-square-o"></span>
														</button>
														<button type="button" onclick="return delRepairer('${repairer.repairerId}')"
															class="am-btn am-btn-default am-btn-xs am-text-danger" title="删除">
															×
														</button>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- 设置删除的弹窗 -->
							<div class="am-modal am-modal-confirm" tabindex="-1" id="del-confirm">
							  <div class="am-modal-dialog">
							    <div class="am-modal-hd">是否删除</div>
							    <div class="am-modal-footer">
							      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
							      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
							    </div>
							  </div>
							</div>
							<!-- 设置批量删除的弹窗 -->
							<div class="am-modal am-modal-confirm" tabindex="-1" id="delAll-confirm">
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
								// 单条删除js
								function delRepairer(repairerId) {
									$('#del-confirm').modal({
									      relatedTarget: this,
									      onConfirm: function(e) {
									    	  $.ajax({
												type:'POST',
												async:false,
												url:'${pageContext.request.contextPath}/repairer/delRepairer',
												data:"repairerId="+repairerId,
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
								
								
								// 批量删除js
								function delAll() {
									$('#delAll-confirm').modal({
									relatedTarget: this,
									onConfirm: function(e) {
										$.ajax({
											type:'POST',
											async:false,
											url:'${pageContext.request.contextPath}/repairer/delAllRepairers',
											data:$('#repairers').serialize(),
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
										<div data-am-page="{pages:${pageCount},curr:${nowPage},jump:'${pageContext.request.contextPath}/repairer/allRepairer?nowPage=%page%&size=${size}'}"></div>
									</ul>
								</div>
							</div>
							<!-- 每页显示大小下拉 -->
							<script type="text/javascript">
								$("#tableSize option[value="+${size}+"]").attr("selected",true); // 选中当前状态
								$("#tableSize").change(function () {  
						            var size = $(this).children('option:selected').val();  
						            window.location.href='${pageContext.request.contextPath}/repairer/allRepairer?size='+size;
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
