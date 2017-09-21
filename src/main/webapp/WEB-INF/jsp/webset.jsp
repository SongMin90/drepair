<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<strong class="am-text-primary am-text-lg">HOME</strong> / <small>网站设置</small>
					</div>
				</div>

				<hr>

				<div class="am-tabs am-margin" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active"><a href="#tab1">网站设置</a></li>
						<li><a href="#tab2">客户端</a></li>
						<li><a href="#tab3">数据库备份</a></li>
						<li><a href="#tab4">网站更新</a></li>
						<li><a href="#tab5">API</a></li>
					</ul>

					<div class="am-tabs-bd">
					
						<!-- 网站设置 -->
						<div class="am-tab-panel am-fade am-in am-active" id="tab1">
						<form action="${pageContext.request.contextPath}/webset/setWebInfo" method="post">
						
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">主题</div>
								<div class="am-u-sm-8 am-u-md-10">
									<div class="am-btn-group" data-am-button>
										<label class="am-btn am-btn-primary am-btn-xs"> <input
											type="radio" name="topbarColor" value="#0e90d2"> 蓝色
										</label> <label class="am-btn am-btn-secondary am-btn-xs"> <input
											type="radio" name="topbarColor" value="#3BB4F2"> 浅蓝
										</label> <label class="am-btn am-btn-success am-btn-xs"> <input
											type="radio" name="topbarColor" value="#5EB95E"> 绿色
										</label> <label class="am-btn am-btn-warning am-btn-xs"> <input
											type="radio" name="topbarColor" value="#F37B1D"> 橙色
										</label> <label class="am-btn am-btn-danger am-btn-xs"> <input
											type="radio" name="topbarColor" value="#DD514C"> 红色
										</label>
									</div>
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">网站名称</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input name="webName" value="${webName}" required>
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">Cookie保存时间（秒）</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input name="cookieSaveTime" value="${cookieSaveTime}" required>
								</div>
							</div>
							
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">客户端存放位置</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input value="${apkPath}" disabled>
								</div>
							</div>
							
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">报修订单图片存放位置</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input value="${imgPath}" disabled>
								</div>
							</div>
							
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">网站存放位置</div>
								<div class="am-u-sm-8 am-u-md-10">
									<input name="web_path" value="${web_path}" required>
								</div>
							</div>
						
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交更新</button>
								</div>
							</div>
							
						</form>
						</div>
						
						<!-- 客户端 -->
						<div class="am-tab-panel am-fade" id="tab2">
							<!-- 报修客户端 -->
							<form class="am-form" action="${pageContext.request.contextPath}/webset/updateBaoxiu" method="post" enctype="multipart/form-data">
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">报修客户端</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<input name="file_baoxiu" type="file" class="am-input-sm" required>
									</div>
								</div>
								
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">当前版本</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<a href="">${version_baoxiu}</a>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">更新版本</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<input name="updateVersion_baoxiu" type="text" class="am-input-sm" required>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">更新内容</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<textarea name="updateInfo_baoxiu" rows="5" cols="44"></textarea>
									</div>
								</div>
								
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">
										<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交更新</button>
									</div>
								</div>
							</form>
							<hr>
							<!-- 抢修客户端 -->
							<form class="am-form" action="${pageContext.request.contextPath}/webset/updateQiangxiu" method="post" enctype="multipart/form-data">
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">抢修客户端</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<input name="file_qiangxiu" type="file" class="am-input-sm" required>
									</div>
								</div>
								
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">当前版本</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<a href="">${version_qiangxiu}</a>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">更新版本</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<input name="updateVersion_qiangxiu" type="text" class="am-input-sm" required>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">更新内容</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<textarea name="updateInfo_qiangxiu" rows="5" cols="44"></textarea>
									</div>
								</div>
								
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">
										<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交更新</button>
									</div>
								</div>
							</form>
						</div>

						<!-- 数据库备份 -->
						<div class="am-tab-panel am-fade" id="tab3">
							<form class="am-form" action="${pageContext.request.contextPath}/webset/sqlBackup" method="post">
								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">备份位置</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<input name="sqlBackupPath" type="text" class="am-input-sm" value="${sqlBackupPath}" required>
									</div>
								</div>

								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">备份定时（秒）</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<input name="sqlBackupTime" type="text" class="am-input-sm" value="${sqlBackupTime}" required>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">
										<c:if test="${isStart_sqlBackup == 'off'}">
											<button type="submit" class="am-btn am-btn-primary am-btn-xs"><span class="am-icon-play"></span> 立即启动</button>
										</c:if>
										<c:if test="${isStart_sqlBackup == 'on'}">
											<button type="submit" class="am-btn am-btn-danger am-btn-xs"><span class="am-icon-stop"></span> 立即停止</button>
										</c:if>
									</div>
								</div>
							</form>
							<hr />
							<!-- 所有sql记录 -->
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
							</div>
							<div class="am-g">
								<div class="am-u-sm-12">
									<form id="sqls" class="am-form">
										<table
											class="am-table am-table-striped am-table-hover table-main">
											<thead>
												<tr>
													<th class="table-check"><input id="checkall" type="checkbox" /></th>
													<th>ID</th>
													<th>Name</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${sqlList}" var="sql">
													<tr>
														<td><input name="ids" type="checkbox" value="${sql.sqlId}" /></td>
														<td>${sql.sqlId}</td>
														<td>${sql.sqlPath}</td>
														<td>
															<div class="am-btn-toolbar">
																<div class="am-btn-group am-btn-group-xs">
																	<button type="button" onclick="return update('${sql.sqlId}')"
																		class="am-btn am-btn-default am-btn-xs am-text-secondary" title="恢复">
																		<span class="am-icon-mail-reply-all"></span>
																	</button>
																</div>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										
										<!-- 弹窗js -->
										<script type="text/javascript">
											// 单条恢复js
											function update(sqlId) {
												$('#update-confirm').modal({
											      relatedTarget: this,
											      onConfirm: function(e) {
											    	  $.ajax({
														type:'POST',
														async:false,
														url:'${pageContext.request.contextPath}/sql/update',
														data:"sqlId="+sqlId,
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
														url:'${pageContext.request.contextPath}/sql/delSqls',
														data:$('#sqls').serialize(),
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
											
											// 判断是否为数据库操作
											var href = window.location.href;
											if(href.indexOf('size') != -1) {
												document.getElementById('tab1').setAttribute("class", "am-tab-panel am-fade");
												document.getElementById('tab3').setAttribute("class", "am-tab-panel am-fade am-in am-active");
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
													<div data-am-page="{pages:${pageCount},curr:${nowPage},jump:'${pageContext.request.contextPath}/webset/setting?nowPage=%page%&size=${size}'}"></div>
												</ul>
											</div>
										</div>
										<!-- 每页显示大小下拉 -->
										<script type="text/javascript">
											$("#tableSize option[value="+${size}+"]").attr("selected",true); // 选中当前状态
											$("#tableSize").change(function () {  
									            var size = $(this).children('option:selected').val();  
									            window.location.href='${pageContext.request.contextPath}/webset/setting?size='+size;
									        });
										</script>
									</form>
								</div>
							</div>
						</div>
						
						<!-- 网站更新 -->
						<div class="am-tab-panel am-fade" id="tab4">
							<form class="am-form" action="${pageContext.request.contextPath}/webset/updateWeb" method="post" enctype="multipart/form-data">
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">历史更新内容</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<textarea rows="5" cols="44" disabled>${updateInfo_web}</textarea>
									</div>
								</div>
								
								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">新网站war包</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<input name="file_web" type="file" class="am-input-sm" required>
									</div>
								</div>
								
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">更新内容</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
										<textarea name="updateInfo_web" rows="5" cols="44"></textarea>
									</div>
								</div>
								
								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">
										<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交更新</button>
									</div>
								</div>
							</form>
						</div>

						<!-- API -->
						<div class="am-tab-panel am-fade" id="tab5">
							<form class="am-form" action="${pageContext.request.contextPath}/webset/api" method="post">
								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">学生详细信息API</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<textarea rows="2" cols="44" disabled>http://47.94.252.54:8080/dormitory/External/studentInfo.action?studentId=1</textarea>
									</div>
								</div>

								<div class="am-g am-margin-top-sm">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">宿管详细信息API</div>
									<div class="am-u-sm-8 am-u-md-4 am-u-end">
										<textarea rows="2" cols="44" disabled>http://47.94.252.54:8080/dormitory/External//dormitoryManagerInfo.action?managerId=3</textarea>
									</div>
								</div>

								<div class="am-g am-margin-top">
									<div class="am-u-sm-4 am-u-md-2 am-text-right">
										<c:if test="${apiState == 'off'}">
											<button type="submit" class="am-btn am-btn-primary am-btn-xs"><span class="am-icon-play"></span> 启用</button>
										</c:if>
										<c:if test="${apiState == 'on'}">
											<button type="submit" class="am-btn am-btn-danger am-btn-xs"><span class="am-icon-stop"></span> 停用</button>
										</c:if>
									</div>
								</div>
							</form>
						</div>
					
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
			<!-- 设置sql恢复弹窗 -->
			<div class="am-modal am-modal-confirm" tabindex="1" id="update-confirm">
			  <div class="am-modal-dialog">
			    <div class="am-modal-hd">是否恢复此记录</div>
			    <div class="am-modal-footer">
			      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
			      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
			    </div>
			  </div>
			</div>
			
			<footer class="admin-content-footer">
				<hr>
				<p class="am-padding-left">© SongM</p>
			</footer>
			
			<!-- 表单验证 -->
			<script type="text/javascript">
				$(function() {
					  var $form = $('.am-form');
					  var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
					  $tooltip.appendTo(document.body);
					  $form.validator();
				});
			</script>
			
			<!-- 是否弹窗提示 -->
			<div id="tr-login-alert-dialog" class="am-modal am-modal-alert" tabindex="-1">
				<div class="am-modal-dialog">
				    <div class="am-modal-hd">${message}</div>
				    <div class="am-modal-bd" id="tr-login-alert-body"></div>
				    <div class="am-modal-footer">
				      <span class="am-modal-btn">关闭</span>
				    </div>
				</div>
			</div>
			<c:if test="${message != null}">
				<script type="text/javascript">
					$('#tr-login-alert-dialog').modal();
				</script>
			</c:if>
			
		</div>
		<!-- content end -->
		
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
