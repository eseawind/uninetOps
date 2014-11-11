<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>菜单树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${ctx }/zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript">
		var menu_tree;
		var setting_menu_tree;
		setting_menu_tree = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			expandSpeed: "",
			callback: {
				rightClick: tt
			}
		};
		function tt(e,treeid,treeNode){
			//alert(treeNode.click);
		}
		
		var zNodes_menu_tree = null;
		if("${sessionScope.role.role_id}" == "role-1"){
			zNodes_menu_tree = [
{id:"1-0",pid:"0",name:"设备状态监视分析",url:"",target:"centerFrame"},
{id:"1-1",pid:"1-0",name:"智能设备状态",url:"gm_devsts_toGm_Devsts.action",target:"centerFrame"},
{id:"1-2",pid:"1-0",name:"智能设备状态历史",url:"gm_devstshis_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"2-0",pid:"0",name:"设备数据监视分析",url:"",target:"centerFrame"},
{id:"2-1",pid:"2-0",name:"数据汇总",url:"op_chtypeoperate_toData_collect_0708.action",target:"centerFrame"},
{id:"2-2",pid:"2-0",name:"曲线分析",url:"gm_historydata_toCharts_0609_run.action",target:"centerFrame"},
{id:"2-4",pid:"2-0",name:"历史数据分析",url:"gm_historydata_tofindHistorydata.action",target:"centerFrame"},
{id:"2-5",pid:"2-0",name:"最新数据查询",url:"gm_latestdata_toLatestdata.action",target:"centerFrame"},
{id:"3-0",pid:"0",name:"控制设备监视分析",url:"",target:"centerFrame"},
{id:"3-1",pid:"3-0",name:"控制设备状态历史",url:"gm_devctrlstshistory_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"3-2",pid:"3-0",name:"控制设备操作历史",url:"gm_devctrloperatehistory_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"3-3",pid:"3-0",name:"网页控制设备",url:"Gm_DevCtrlSts_page.action",target:"centerFrame"},
{id:"3-4",pid:"3-0",name:"自动控制",url:"autoCtrlConfig_list.action",target:"centerFrame"},
{id:"4-0",pid:"0",name:"系统故障监视分析",url:"",target:"centerFrame"},
{id:"4-1",pid:"4-0",name:"故障信息管理",url:"Gm_DevFault_findAllDevFault.action",target:"centerFrame"},
{id:"4-2",pid:"4-0",name:"报表统计",url:"gm_devsts_findAllDest_WorkSts.action",target:"centerFrame"},
{id:"4-3",pid:"4-0",name:"数据分析",url:"gm_historydata_toDataAnalysis.action",target:"centerFrame"},
{id:"4-4",pid:"4-0",name:"上报数据统计",url:"Gm_DevFault_reportDataStatic.action",target:"centerFrame"},
{id:"4-5",pid:"4-0",name:"捕获故障汇总",url:"Pro_FaultTaskAction_findUserNeed.action",target:"centerFrame"},
{id:"5-0",pid:"0",name:"项目多表管理",url:"",target:"centerFrame"},
{id:"5-1",pid:"5-0",name:"基本信息导入导出",url:"import/importBasic.jsp",target:"centerFrame"},
{id:"5-2",pid:"5-0",name:"项目信息导入导出",url:"import/importProject.jsp",target:"centerFrame"},
{id:"5-3",pid:"5-0",name:"在线配置查看",url:"devSetup_list.action",target:"centerFrame"},
{id:"5-4",pid:"5-0",name:"通道批量修改",url:"Gm_Channel_toBatchUpdateChannel.action",target:"centerFrame"},
{id:"5-5",pid:"5-0",name:"历史数据校正",url:"gm_historydata_toHistorydataRevise.action",target:"centerFrame"},
{id:"6-0",pid:"0",name:"项目单表管理",url:"",target:"centerFrame"},
{id:"6-1",pid:"6-0",name:"场景信息管理",url:"op_scene_page.action?page.pageSize=12&op_Scene.scene_pid=",target:"centerFrame"},
{id:"6-2",pid:"6-0",name:"设备信息管理",url:"gm_device_page.action?page.pageSize=12&gm_Device.dev_id=",target:"centerFrame"},
{id:"6-3",pid:"6-0",name:"网络信息管理",url:"gm_devnet_page.action?page.pageSize=12&gm_DevNet.net_addr=",target:"centerFrame"},
{id:"6-4",pid:"6-0",name:"采集通道信息管理",url:"Gm_Channel_findAll.action?page.pageSize=12",target:"centerFrame"},
{id:"6-5",pid:"6-0",name:"设备上报通道配置管理",url:"Gm_DevChannel_toManage.action",target:"centerFrame"},
{id:"6-6",pid:"6-0",name:"控制设备信息管理",url:"Gm_DevCtrl_findAll.action",target:"centerFrame"},
{id:"6-7",pid:"6-0",name:"供应商信息管理",url:"Op_Supplier_findAll.action",target:"centerFrame"},
{id:"6-8",pid:"6-0",name:"采集通道应用类型管理",url:"Op_ChannelType_findAll.action",target:"centerFrame"},
{id:"6-9",pid:"6-0",name:"控制设备应用类型管理",url:"Op_DevCtrlType_findAll.action",target:"centerFrame"},
{id:"6-10",pid:"6-0",name:"数据汇总配置管理",url:"op_chtypeoperate_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"6-11",pid:"6-0",name:"项目简介",url:"systemInfo_show.action",target:"centerFrame"},
{id:"6-12",pid:"6-0",name:"报警配置",url:"Op_AlarmConfig_config.action",target:"centerFrame"},
{id:"7-0",pid:"0",name:"系统用户管理",url:"",target:"centerFrame"},
{id:"7-1",pid:"7-0",name:"角色信息管理",url:"Op_RoleInfo_findAll.action",target:"centerFrame"},
{id:"7-2",pid:"7-0",name:"用户信息管理",url:"Op_UserInfo_findAll.action",target:"centerFrame"},
{id:"7-3",pid:"7-0",name:"用户场景关联管理",url:"op_userinfo_scene_toManage.action",target:"centerFrame"}
];
		}else{
			zNodes_menu_tree = [
{id:"1-0",pid:"0",name:"设备状态监视分析",url:"",target:"centerFrame"},
{id:"1-1",pid:"1-0",name:"智能设备状态",url:"gm_devsts_toGm_Devsts.action",target:"centerFrame"},
{id:"1-2",pid:"1-0",name:"智能设备状态历史",url:"gm_devstshis_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"2-0",pid:"0",name:"设备数据监视分析",url:"",target:"centerFrame"},
{id:"2-1",pid:"2-0",name:"数据汇总",url:"op_chtypeoperate_toData_collect_0708.action",target:"centerFrame"},
{id:"2-2",pid:"2-0",name:"曲线分析",url:"gm_historydata_toCharts_0609_run.action",target:"centerFrame"},
{id:"2-4",pid:"2-0",name:"历史数据分析",url:"gm_historydata_tofindHistorydata.action",target:"centerFrame"},
{id:"2-5",pid:"2-0",name:"最新数据查询",url:"gm_latestdata_toLatestdata.action",target:"centerFrame"},
{id:"3-0",pid:"0",name:"控制设备监视分析",url:"",target:"centerFrame"},
{id:"3-1",pid:"3-0",name:"控制设备状态历史",url:"gm_devctrlstshistory_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"3-2",pid:"3-0",name:"控制设备操作历史",url:"gm_devctrloperatehistory_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"3-3",pid:"3-0",name:"网页控制设备",url:"Gm_DevCtrlSts_page.action",target:"centerFrame"},
{id:"3-4",pid:"3-0",name:"自动控制",url:"autoCtrlConfig_list.action",target:"centerFrame"},
{id:"4-0",pid:"0",name:"系统故障监视分析",url:"",target:"centerFrame"},
{id:"4-1",pid:"4-0",name:"故障信息管理",url:"Gm_DevFault_findAllDevFault.action",target:"centerFrame"},
{id:"4-2",pid:"4-0",name:"报表统计",url:"gm_devsts_findAllDest_WorkSts.action",target:"centerFrame"},
{id:"4-3",pid:"4-0",name:"数据分析",url:"gm_historydata_toDataAnalysis.action",target:"centerFrame"},
{id:"4-4",pid:"4-0",name:"上报数据统计",url:"Gm_DevFault_reportDataStatic.action",target:"centerFrame"},
{id:"4-5",pid:"4-0",name:"捕获故障汇总",url:"Pro_FaultTaskAction_findUserNeed.action",target:"centerFrame"},

{id:"5-0",pid:"0",name:"项目多表管理",url:"",target:"centerFrame"},
//{id:"5-1",pid:"5-0",name:"基本信息导入导出",url:"import/importBasic.jsp",target:"centerFrame"},
{id:"5-2",pid:"5-0",name:"项目信息导入导出",url:"import/importProject.jsp",target:"centerFrame"},
{id:"5-3",pid:"5-0",name:"在线配置查看",url:"devSetup_list.action",target:"centerFrame"},
{id:"5-4",pid:"5-0",name:"通道批量修改",url:"Gm_Channel_toBatchUpdateChannel.action",target:"centerFrame"},
{id:"5-5",pid:"5-0",name:"历史数据校正",url:"gm_historydata_toHistorydataRevise.action",target:"centerFrame"},
{id:"6-0",pid:"0",name:"项目单表管理",url:"",target:"centerFrame"},
{id:"6-1",pid:"6-0",name:"场景信息管理",url:"op_scene_page.action?page.pageSize=12&op_Scene.scene_pid=",target:"centerFrame"},
{id:"6-2",pid:"6-0",name:"设备信息管理",url:"gm_device_page.action?page.pageSize=12&gm_Device.dev_id=",target:"centerFrame"},
{id:"6-3",pid:"6-0",name:"网络信息管理",url:"gm_devnet_page.action?page.pageSize=12&gm_DevNet.net_addr=",target:"centerFrame"},
{id:"6-4",pid:"6-0",name:"采集通道信息管理",url:"Gm_Channel_findAll.action?page.pageSize=12",target:"centerFrame"},
{id:"6-5",pid:"6-0",name:"设备上报通道配置管理",url:"Gm_DevChannel_toManage.action",target:"centerFrame"},
{id:"6-6",pid:"6-0",name:"控制设备信息管理",url:"Gm_DevCtrl_findAll.action",target:"centerFrame"},
{id:"6-7",pid:"6-0",name:"供应商信息管理",url:"Op_Supplier_findAll.action",target:"centerFrame"},
//{id:"6-8",pid:"6-0",name:"采集通道应用类型管理",url:"Op_ChannelType_findAll.action",target:"centerFrame"},zzzz
//{id:"6-9",pid:"6-0",name:"控制设备应用类型管理",url:"Op_DevCtrlType_findAll.action",target:"centerFrame"},
{id:"6-10",pid:"6-0",name:"数据汇总配置管理",url:"op_chtypeoperate_page.action?page.pageSize=12&hid_condition=-1",target:"centerFrame"},
{id:"6-11",pid:"6-0",name:"项目简介",url:"systemInfo_show.action",target:"centerFrame"},
{id:"6-12",pid:"6-0",name:"报警配置",url:"Op_AlarmConfig_config.action",target:"centerFrame"},
{id:"7-0",pid:"0",name:"系统用户管理",url:"",target:"centerFrame"},
//{id:"7-1",pid:"7-0",name:"角色信息管理",url:"Op_RoleInfo_findAll.action",target:"centerFrame"},
{id:"7-2",pid:"7-0",name:"用户信息管理",url:"Op_UserInfo_findAll.action",target:"centerFrame"},
{id:"7-3",pid:"7-0",name:"用户场景关联管理",url:"op_userinfo_scene_toManage.action",target:"centerFrame"}
]
		}
	</script>
  </head>
  
  <body>
      <ul id="ul_menu_tree" class="tree"></ul>
      <script type="text/javascript">
      	//alert($("#ul_menu_tree"));
		menu_tree = $("#ul_menu_tree").zTree(setting_menu_tree, zNodes_menu_tree);
		menu_tree.expandAll(true);
	  </script>
  </body>
</html>
