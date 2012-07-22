<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<c:if test="${ adminId ==null }">
	<c:redirect url="login.do?action=toLogin"></c:redirect>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link href="widget/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="widget/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var indexdata = [

	{
		isexpand : "true",
		text : "系统管理",
		children : [ {
			url : "user.do?action=listUser",
			text : "管理员管理"
		}, {
			url : "data.html?action=toImport",
			text : "导入excel数据"
		} ]

	}

	];

	var tab = null;
	var accordion = null;
	var tree = null;
	$(function() {

		//布局
		$("#layout").ligerLayout({
			leftWidth : 190,
			height : '100%',
			heightDiff : -34,
			space : 4,
			onHeightChanged : f_heightChanged
		});

		var height = $(".l-layout-center").height();

		//Tab
		$("#framecenter").ligerTab({
			height : height
		});

		//面板
		$("#accordion1").ligerAccordion({
			height : height - 24,
			speed : null
		});

		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});
		//树
		$("#tree").ligerTree({
			data : indexdata,
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid)
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});

		tab = $("#framecenter").ligerGetTabManager();
		accordion = $("#accordion").ligerGetAccordionManager();
		tree = $("#tree").ligerGetTreeManager();
		$("#pageloading").hide();

	});

	function f_heightChanged(options) {
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 24 > 0)
			accordion.setHeight(options.middleHeight - 24);
	}

	/**
	 *  打开新的tab
	 * @param tabid tab的ID
	 * @param text 要显示的文本
	 * @param url 打开的url地址
	 */
	function f_addTab(tabid, text, url) {
		//tab.removeTabItem(tabid);
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
	}
	/**
	 *  override 本tab
	 * @param tabid tab的ID
	 * @param text 要显示的文本
	 * @param url 打开的url地址
	 */
	function overrideSelectedTab(tabid, text, url) {
		tab.overrideTabItem(tab.getSelectedTabItemID(), {
			text : text,
			url : url
		});
	}

	$(function() {
		$("#toUpdatePersonalPassword").click(
				function() {
					overrideSelectedTab("updatePersonalPassword", "修改个人密码",
							"${ctx }/user.do?action=toUpdatePersonalPassword");
				});
	});
</script>

</head>
<body style="padding: 0px; background: #EAEEF5;">

	<div id="pageloading"></div>
	<div id="topmenu" class="l-topmenu">
		<div class="l-topmenu-logo">后台主页</div>
		<div class="l-topmenu-welcome">
			<span style="color: white;">欢迎您:${adminName }</span> <a href="#"
				id="toUpdatePersonalPassword" class="l-link2">修改个人密码</a> <a href="#"
				class="l-link2">刷新</a> <span class="space">|</span> <a
				href="${ctx }/login.do?action=logoutForAdmin" class="l-link2">注销</a>
		</div>
	</div>
	<div id="layout" style="width: 99.2%; margin: 0 auto; margin-top: 4px;">
		<div position="left" title="系统菜单" id="accordion">
			<div title="系统管理" class="l-scroll">
				<ul id="tree" style="margin-top: 3px;">
			</div>

		</div>
		<div position="center" id="framecenter">
			<div tabid="home" title="我的主页" style="height: 300px">
				<iframe frameborder="0" name="home" id="home" src="${ctx }/main.do?action=toWelcome"></iframe>
			</div>
		</div>

	</div>
	<div style="height: 32px; line-height: 32px; text-align: center;">
		Copyright © 2012-2013</div>
	<div style="display: none"></div>
</body>
</html>
