<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
	<div class="am-offcanvas-bar admin-offcanvas-bar">
		<ul class="am-list admin-sidebar-list">
			<li><a href="${pageContext.request.contextPath}/welcome.jsp"><span class="am-icon-home"></span>
					首页</a></li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#order-nav'}"><span
					class="am-icon-newspaper-o"></span> 订单模块 <span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub"
					id="order-nav">
					<li><a
						href="${pageContext.request.contextPath}/order/allOrder?isStu=1"><span
							class="am-icon-table"></span> 学生订单</a></li>
					<li><a
						href="${pageContext.request.contextPath}/order/allOrder?isStu=0"><span
							class="am-icon-table"></span> 宿管订单</a></li>
				</ul>
			</li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#admin-nav'}"><span
					class="am-icon-user"></span> 管理模块 <span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub"
					id="admin-nav">
					<li><a href="${pageContext.request.contextPath}/admin/addAdmin"><span
					class="am-icon-user-plus"></span> 新增管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/allAdmin"><span
					class="am-icon-table"></span> 全部管理</a></li>
				</ul>
			</li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#stu-nav'}"><span
					class="am-icon-child"></span> 学生模块 <span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub"
					id="stu-nav">
					<li><a href="${pageContext.request.contextPath}/stu/allStu"><span
					class="am-icon-table"></span> 全部学生</a></li>
				</ul>
			</li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#hmr-nav'}"><span
					class="am-icon-female"></span> 宿管模块 <span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub"
					id="hmr-nav">
					<li><a href="${pageContext.request.contextPath}/hmr/allHmr"><span
					class="am-icon-table"></span> 全部宿管</a></li>
				</ul>
			</li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#repairer-nav'}"><span
					class="am-icon-wrench"></span> 维修员模块 <span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub"
					id="repairer-nav">
					<li><a href="${pageContext.request.contextPath}/repairer/addRepairer"><span
					class="am-icon-user-plus"></span> 新增维修员</a></li>
					<li><a href="${pageContext.request.contextPath}/repairer/allRepairer"><span
					class="am-icon-table"></span> 全部维修员</a></li>
				</ul>
			</li>
		</ul>

		<div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-bookmark"></span> 公告
				</p>
				<p>系统还在建设，测试阶段。—— SongM</p>
			</div>
		</div>

		<div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-tag"></span> wiki
				</p>
				<p>Welcome to the Drepair!</p>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
// js判断是哪个栏目被选中
var href = window.location.href;
if(href.indexOf('order') != -1) {
	document.getElementById('order-nav').setAttribute("class", "am-list am-collapse admin-sidebar-sub am-in");
}
if(href.indexOf('admin') != -1) {
	document.getElementById('admin-nav').setAttribute("class", "am-list am-collapse admin-sidebar-sub am-in");
}
if(href.indexOf('stu') != -1) {
	document.getElementById('stu-nav').setAttribute("class", "am-list am-collapse admin-sidebar-sub am-in");
}
if(href.indexOf('hmr') != -1) {
	document.getElementById('hmr-nav').setAttribute("class", "am-list am-collapse admin-sidebar-sub am-in");
}
if(href.indexOf('admin') != -1) {
	document.getElementById('admin-nav').setAttribute("class", "am-list am-collapse admin-sidebar-sub am-in");
}
if(href.indexOf('repairer') != -1) {
	document.getElementById('repairer-nav').setAttribute("class", "am-list am-collapse admin-sidebar-sub am-in");
}
</script>