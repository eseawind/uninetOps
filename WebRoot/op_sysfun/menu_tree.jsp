<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

	<link rel="stylesheet" type="text/css" href="${ctx }/css/dtree.css">
	<script type="text/javascript" src="${ctx }/js/dtree.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
		//var xx = [{node:"100",pnode:"0",text:"设备状态监视分析",url:""},{node:"101",pnode:"100",text:"智能设备状态",url:"gm_devsts_toGm_Devsts.action"},{node:"102",pnode:"100",text:"智能设备状态历史",url:""},{node:"103",pnode:"100",text:"控制设备状态历史",url:"gm_devctrlstshistory_page.action?page.pageSize=12&hid_condition=-1"},{node:"104",pnode:"100",text:"控制设备操作历史",url:"gm_devctrloperatehistory_page.action?page.pageSize=12&hid_condition=-1"},{node:"200",pnode:"0",text:"设备数据监视分析",url:""},{node:"201",pnode:"200",text:"实时监控",url:"op_scene_toSceneSO_0715.action"},{node:"202",pnode:"200",text:"数据汇总",url:"op_chtypeoperate_toData_collect_0708.action"},{node:"203",pnode:"200",text:"曲线分析",url:"gm_historydata_toCharts_0609_run.action"},{node:"204",pnode:"200",text:"数据查询",url:"gm_historydata_toCharts_0601_run.action"},{node:"205",pnode:"200",text:"历史数据分析",url:"gm_historydata_tofindHistorydata.action"},{node:"300",pnode:"0",text:"项目管理",url:""},{node:"301",pnode:"300",text:"场景信息管理",url:"op_scene_page.action?page.pageSize=12&op_Scene.scene_pid="},{node:"302",pnode:"300",text:"设备信息管理",url:"gm_device_page.action?page.pageSize=12&gm_Device.dev_id="},{node:"303",pnode:"300",text:"网络信息管理",url:"gm_devnet_page.action?page.pageSize=12&gm_DevNet.net_addr="},{node:"304",pnode:"300",text:"采集通道信息管理",url:"Gm_Channel_findAll.action"},{node:"305",pnode:"300",text:"设备上报通道配置管理",url:"Gm_DevChannel_toManage.action"},{node:"306",pnode:"300",text:"控制设备信息管理",url:"Gm_DevCtrl_findAll.action"},{node:"307",pnode:"300",text:"控制设备按钮管理",url:""},{node:"308",pnode:"300",text:"控制设备状态配置管理",url:""},{node:"309",pnode:"300",text:"用户信息管理",url:"Op_UserInfo_findAll.action"},{node:"310",pnode:"300",text:"用户场景关联管理",url:"op_userinfo_scene_toManage.action"},{node:"311",pnode:"300",text:"设备配置展示",url:"devSetup_list.action"},{node:"312",pnode:"300",text:"设备维护",url:"gm_device_toManage.action"},{node:"500",pnode:"0",text:"故障管理",url:""},{node:"501",pnode:"500",text:"故障信息管理",url:"Gm_DevFault_findAllDevFault.action?page.pageSize=10"},{node:"600",pnode:"0",text:"辅助功能",url:""},{node:"601",pnode:"600",text:"短信接收管理",url:""},{node:"602",pnode:"600",text:"短信发送管理",url:""},{node:"700",pnode:"0",text:"数据导入",url:""},{node:"702",pnode:"700",text:"项目信息导入",url:"import/importProject.jsp"}];
		//alert(xx.length);
		var menu_tree=new dTree('menu_tree',"${ctx}");
		menu_tree.add(0,-1,'运维系统','');
	    $.ajaxSettings.async = false;
		$.getJSON("${ctx}/op_sysfun_menuTree.action",{
			random:Math.random()
		},function(list){//alert(11);
			$.each(list,function(i,object){
				if(object.pnode == 0){
					menu_tree.add(object.node,object.pnode,object.text,object.url,"","centerFrame","${ctx}/images/dtree/folder.gif");
				}else{
					menu_tree.add(object.node,object.pnode,object.text,object.url,"","centerFrame");
				}	
				//d.add(object.id,object.p_id,object.name,'','','','../images/dtree/'+object.imgurl,'../images/dtree/'+object.openimg);
			})		
		});
		document.write(menu_tree);
	</script>
 
  
  

