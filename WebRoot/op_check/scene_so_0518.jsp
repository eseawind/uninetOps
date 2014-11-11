<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>场景信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	<!--		
		td{
			font-size: 12px;
		}
	-->
	</style>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>	
	<script type="text/javascript">
		var timer_showDevCtrl;
		//响应场景树
		function echoSceneTree(id,name,no,rank){
			if(rank == 0){
				showFisheries(id);
				showChannel(id);
				//showDevCtrl(id);				
				showHistory(id);
				timer_devCtrl = window.setInterval("alert('xx')",6*1000);
			}
		}
		
		//加载养殖池信息
		function showFisheries(id){
			var sp_fi_pondNo = document.getElementById("sp_fi_pondNo");
			sp_fi_pondNo.innerHTML = "养殖池编号：";
			var sp_fi_pondName = document.getElementById("sp_fi_pondName");
			sp_fi_pondName.innerHTML = "养殖户：";
			var sp_fi_area = document.getElementById("sp_fi_area");
			sp_fi_area.innerHTML = "面积：";
			var sp_fi_depth = document.getElementById("sp_fi_depth");
			sp_fi_depth.innerHTML = "水深：";
			var sp_fi_aquaticType = document.getElementById("sp_fi_aquaticType");
			sp_fi_aquaticType.innerHTML = "水草种类：";			
			var sp_fi_cultureObject = document.getElementById("sp_fi_cultureObject");
			sp_fi_cultureObject.innerHTML = "养殖对象：";
			var sp_fi_species = document.getElementById("sp_fi_species");
			sp_fi_species.innerHTML = "品种：";
			var sp_fi_batch = document.getElementById("sp_fi_batch");
			sp_fi_batch.innerHTML = "批次：";
			var sp_fi_remainNum = document.getElementById("sp_fi_remainNum");
			sp_fi_remainNum.innerHTML = "剩余数：";
			var sp_fi_productionStage = document.getElementById("sp_fi_productionStage");
			sp_fi_productionStage.innerHTML = "生长阶段：";			
			var sp_fi_do = document.getElementById("sp_fi_do");
			sp_fi_do.innerHTML = "溶解氧阀值：";
			
			$.getJSON("${ctx}/fisherirs_findBySceneId.action?sceneId="+id,{
					random:Math.random()
				},function(fisheries){alert("xx");
					//alert(fisheries.fi_pondName+"="+fisheries.fi_depth);					
					if(fisheries.fi_pondNo!=null && fisheries.fi_pondNo!="null")
					{sp_fi_pondNo.innerHTML = "养殖池编号："+fisheries.fi_pondNo;}
					if(fisheries.fi_pondName!=null && fisheries.fi_pondName!="null")
					{sp_fi_pondName.innerHTML = "养殖户："+fisheries.fi_pondName;}
					if(fisheries.fi_area!=null && fisheries.fi_area!="null")
					{sp_fi_area.innerHTML = "面积："+fisheries.fi_area;}
					if(fisheries.ffi_depth!=null && fisheries.fi_depth!="null")
					{sp_fi_depth.innerHTML = "水深："+fisheries.fi_depth;}
					if(fisheries.fi_aquaticType!=null && fisheries.fi_aquaticType!="null")
					{sp_fi_aquaticType.innerHTML = "水草种类："+fisheries.fi_aquaticType;}
					if(fisheries.fi_cultureObject!=null && fisheries.fi_cultureObject!="null")
					{sp_fi_cultureObject.innerHTML = "养殖对象："+fisheries.fi_cultureObject;}
					if(fisheries.fi_species!=null && fisheries.fi_species!="null")
					{sp_fi_species.innerHTML = "品种："+fisheries.fi_species;}
					if(fisheries.fi_batch!=null && fisheries.fi_batch!="null")
					{sp_fi_batch.innerHTML = "批次："+fisheries.fi_batch;}
					if(fisheries.fi_remainNum!=null && fisheries.fi_remainNum!="null")
					{sp_fi_remainNum.innerHTML = "剩余数："+fisheries.fi_remainNum;}
					if(fisheries.fi_productionStage!=null && fisheries.fi_productionStage!="null")
					{sp_fi_productionStage.innerHTML = "剩余数："+fisheries.fi_productionStage;}
					if(fisheries.fi_do!=null && fisheries.fi_do!="null")
					{sp_fi_do.innerHTML = "溶解氧阀值："+fisheries.fi_do;}					
				}
			);			
		}
		
		//查询通道当前值
		function showChannel(id){
			var td_ST10_11 = document.getElementById("td_ST10_11"); 
			td_ST10_11.innerHTML = "正在加载，请等待..."
			var td_DO10_O = document.getElementById("td_DO10_O");
			td_DO10_O.innerHTML	= "正在加载，请等待...";	
			$.getJSON("${ctx}/op_scene_json_findChannelBySceneID.action?op_Scene.scene_id="+id,{
					random:Math.random()
				},function(list){
					var val_ST10_11 = "数据:未知";
					var val_DO10_O = "数据:未知";
					$.each(list,function(i,channel){
						if(channel.chtype_no == "ST10-11"){
							value = channel.hida_corrValue+"";
							value = value.substr(0,(value.indexOf(".")+2));
							val_ST10_11 = "数据:"+value+channel.ch_corrUnit;
						}else if(channel.chtype_no == "DO10-O"){
							value = channel.hida_corrValue+"";
							value = value.substr(0,(value.indexOf(".")+2));
							val_DO10_O = "数据:"+value+channel.ch_corrUnit;
						}
					})
					td_ST10_11.innerHTML = val_ST10_11;
					td_DO10_O.innerHTML = val_DO10_O;
				}
			);
		}
		
		//曲线图，实时曲线
		function showHistory(id){
			$.getJSON("${ctx}/op_scene_json_findHistoryBySceneID_0518.action?op_Scene.scene_id="+id,{
					random:Math.random()
				},function(lines){
					document.getElementById("container").innerHTML = "";
					if(lines.length>0)
					{
						chart = new Highcharts.Chart({
							chart: {
								renderTo: 'container',
								defaultSeriesType: 'spline',
								events: {
									load: function() {
										setInterval(function() {
											for(var ii=0;ii<chart.series.length;ii++){								
												// set up the updating of the chart each second
												var series = chart.series[ii];
												var ch_no = series.name.substr(0,series.name.indexOf("("))
												$.getJSON("${ctx}/gm_latestdata_json_findHida_corrValueByCh_noByNo.action?ch_no="+ch_no+"&no="+ii,{
													random:Math.random()
												},function(pojo){//alert(pojo.no);
													/**
													 pojo {no:ii,value:value}
													*/
													var x = (new Date()).getTime(), // current time
														y = pojo.value;
													chart.series[pojo.no].addPoint([x, y], true, true);
												});
											}
										}, 30*1000);	
									}
								}
							},
							title: {
								text: '实时数据'
							},
							subtitle: {
								text: ''
							},
							xAxis: {
					        	type: 'datetime'
					        },
							yAxis: {
								title: {
									text: '当前值'
								},
								labels: {
									formatter: function() {
										return this.value //+'°'
									}
								}
							},
							tooltip: {
								crosshairs: true,
								shared: true
							},
							plotOptions: {
								spline: {
									marker: {
										radius: 4,
										lineColor: '#666666',
										lineWidth: 1
									}
								}
							},
							series: lines
						});
					}else{
						document.getElementById("container").innerHTML = "没有找到指定的数据";
					}
				}
			);
		}
		
		//查询控制设备状态
		function showDevCtrl(id){
			var src = "${ctx}/images/fisheries/deng-3.jpg";			
			$.getJSON("${ctx}/op_scene_json_findDevCtrlBySceneID.action?op_Scene.scene_id="+id,{
					random:Math.random()
				},function(list){
					//alert(list.length);
					/**
						json format
						[
							{
								dect_id:'',
								desc_no:'',
								dect_name:'',
								decttype_id:
									{
										decttype_No:'SV-1',
										decttype_Img:'null'
									},
								dev_id:'402880fd2f7b7184012f7bbac9aa0001',
								dect_state:2,
								decst_valid:1,
								btns:
									[
										{
											dectbtn_id:'kz1-1',
											Dectbtn_Name:'开启',
											deco_type:'1',
											dect_id:'kz1',
											dev_id:'402880fd2f7b7184012f7bbac9aa0001',
											dect_ChlNo:'0',
											dect_ctlType:'1',
											dect_ctlDelay:'null'
										},
										...
									]
							},
							...
						]
						
					*/					
					if(list.length>0){
						$.each(list,function(i,devCtrl){	
							if(devCtrl.decttype_id.decttype_No == 'SV-1'){			
								if(devCtrl.dect_state == 1){
									src = "${ctx}/images/fisheries/deng-2.jpg";
									document.getElementById("tr_state_3").style.display = "none";
									document.getElementById("tr_state_1").style.display = "block";
								}else if(devCtrl.dect_state == 3){
									src = "${ctx}/images/fisheries/deng-1.jpg";	
									document.getElementById("tr_state_1").style.display = "none";
									document.getElementById("tr_state_3").style.display = "block";																					
								}
							}
							document.getElementById("img_state").src = src;
						})
					}					
				}
			);			
		}
	</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url();
}
-->
</style>
<script type="text/javascript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<link href="${ctx}/css/fisheries/css.css" rel="stylesheet" type="text/css">
<script src="${ctx}/js/AC_RunActiveContent.js" type="text/javascript"></script>
  </head>
  
<body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;" onLoad="MM_preloadImages('${ctx}/images/fisheries/jy-01.jpg','${ctx}/images/fisheries/nyqs-01.jpg','${ctx}/images/fisheries/sqjc-01.jpg','${ctx}/images/fisheries/tz-01.jpg','${ctx}/images/fisheries/qd-1.jpg')">
<table width="100%" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="6">
      <tr>
        <td class="xie-1"><table width="100%" border="0" cellspacing="2" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_fi_pondNo">养殖池编号：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_fi_pondName">养殖户：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_area">面　积：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_depth">水　深：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="wz-01" id="sp_fi_aquaticType">水草种类：</td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_fi_cultureObject">养殖对象：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_species">品  种：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_batch">批  次：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_remainNum">剩余数：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_productionStage">生长阶段：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="wz-01" id="sp_fi_do">溶解氧阀值：</td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="628" class="xie-1"><table width="628" border="0" cellspacing="2" cellpadding="0">
          <tr>
            <td width="162" valign="top" class="xie-1"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="145"><div align="center"><img src="${ctx}/images/fisheries/kzq-1.jpg" width="134" height="139"></div></td>
                </tr>
              <tr>
                <td height="40"><table width="72%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="43%" height="33" class="wz-01">状态：</td>
                    <td width="57%"><div align="center"><img id="img_state" src="${ctx}/images/fisheries/deng-3.jpg" width="29" height="26"></div></td>
                  </tr>
                </table></td>
                </tr>
              <tr>
                <td height="39"><table width="81%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="32" align="center" valign="middle"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image14','','${ctx}/images/fisheries/qd-1.jpg',1)"><img src="${ctx}/images/fisheries/qd-01.jpg" name="Image14" width="53" height="27" border="0"></a></td>
                    <td align="center" valign="middle"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image7','','${ctx}/images/fisheries/tz-01.jpg',1)"><img src="${ctx}/images/fisheries/tz-1.jpg" name="Image7" width="53" height="27" border="0"></a></td>
                  </tr>
                </table></td>
                </tr>
              <tr>
                <td height="54"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="xie">
                  <tr>
                    <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="wz-01"><div align="center">远程手动控制</div></td>
                  </tr>
                </table></td>
                </tr>
            </table></td>
            <td width="460" height="293"><table width="460" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="460" height="291" valign="bottom"><table width="460" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="337" height="137" align="center" valign="top"><table width="331" border="0" cellpadding="0" cellspacing="0" class="xie-1">
                      <tr>
                        <td height="114"><table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="30" class="wz-09"><div align="right">实时监测信息</div></td>
                                <td class="wz-01">设备：101设备</td>
                              </tr>
                            </table></td>
                            </tr>
                          <tr>
                            <td height="11" align="center" valign="top"><img src="${ctx}/images/fisheries/xie-1.jpg" width="297" height="1"></td>
                            </tr>
                          <tr>
                            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td class="wz-01">通道：水温 <br></td>
                                  </tr>
                                  <tr>
                                    <td id="td_ST10_11" class="wz-01">数据：</td>
                                  </tr>
                                </table></td>
                                <td width="25" valign="top" background="${ctx}/images/fisheries/wdt-1.jpg"><table width="25" border="0" align="left" cellpadding="0" cellspacing="0">
                                  <tr>
                                    <td width="15">&nbsp;</td>
                                    <td height="45" valign="bottom"><img src="${ctx}/images/fisheries/wdt-2.jpg" width="5" height="2"><br>
                                      <img src="${ctx}/images/fisheries/wdt-2.jpg" width="5" height="2"><br>
                                      <img src="${ctx}/images/fisheries/wdt-2.jpg" width="5" height="2"></td>
                                    </tr>
                                  
                                </table></td>
                                <td width="2">&nbsp;</td>
                                <td><table width="107%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td class="wz-01"> 通道：溶解氧<br></td>
                                  </tr>
                                  <tr>
                                    <td class="wz-01" id="td_DO10_O">数据：</td>
                                  </tr>
                                </table></td>
                                <td width="36" height="56"><img src="${ctx}/images/fisheries/ryt-2.jpg" width="36" height="56"></td>
                              </tr>
                            </table></td>
                            </tr>
                        </table></td>
                      </tr>
                    </table></td>
                    <td width="123"><img src="${ctx}/images/fisheries/dongh-1.gif" width="123" height="137"></td>
                  </tr>
                  <tr id="tr_state_1" style="display: none;">
                    <td height="154" colspan="2" background="${ctx}/images/dh-1_r2_c1.jpg">
                    <script type="text/javascript">
                      AC_FL_RunContent( 'codebase','http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0','width','460','height','154','src','images/dongh-2','quality','high','pluginspage','http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash','movie','${ctx}/images/fisheries/dongh-2' ); //end AC code
                    </script>
                    <noscript>
	                    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="460" height="154">
	                      <param name="movie" value="images/dongh-2.swf">
	                      <param name="quality" value="high">
	                      <embed src="${ctx}/images/fisheries/dongh-2.swf" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="460" height="154"></embed>
	                    </object>
                    </noscript>
                    </td>
                  </tr>
                  <tr id="tr_state_3">
                    <td height="154" colspan="2"><img src="${ctx}/images/fisheries/dh-1_r2_c1.jpg" width="460" height="154"></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td class="xie-1">&nbsp;</td>
        <td width="628" class="xie-1"><table width="100%" border="0" cellspacing="2" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td width="75%" class="wz-09">曲线图：</td>
                <td width="11%"><img src="${ctx}/images/fisheries/ry-1.jpg" width="70" height="22"></td>
                <td width="2%">&nbsp;</td>
                <td width="12%"><img src="${ctx}/images/fisheries/wd-1.jpg" width="64" height="22"></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="245" style="position: relative;">
            	<div id="container" style="width: 621px;height: 243px; position: absolute; top:0px; left:0px;">
            		请选择场景...
            	</div>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
