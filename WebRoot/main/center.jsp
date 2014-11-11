<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>welcome to OPS!</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,gtype){
			//无响应
			sceneId = id;
			sceneGtype = gtype;
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
  
<body onLoad="MM_preloadImages('${ctx}/images/fisheries/jy-01.jpg','${ctx}/images/fisheries/nyqs-01.jpg','${ctx}/images/fisheries/sqjc-01.jpg','${ctx}/images/fisheries/tz-01.jpg','${ctx}/images/fisheries/qd-1.jpg')">
<table width="100%" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="6">
      <tr>
        <td class="xie-1"><table width="100%" border="0" cellspacing="2" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09">养殖池编号：00001</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09">养殖户：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01">面　积：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01">水　深：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="wz-01">水草种类：</td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09">养殖对象：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01">品  种：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01">批  次：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01">剩余数：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01">生长阶段：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="wz-01">溶解氧阀值：</td>
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
                    <td width="57%"><div align="center"><img id="img_state" src="${ctx}/images/fisheries/deng-2.jpg" width="29" height="26"></div></td>
                  </tr>
                </table></td>
                </tr>
              <tr>
                <td height="39"><table width="81%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="32" align="center" valign="middle"><a href="http://www.baidu.com" disable onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image14','','${ctx}/images/fisheries/qd-1.jpg',1)"><img src="${ctx}/images/fisheries/qd-01.jpg" name="Image14" width="53" height="27" border="0"></a></td>
                    <td align="center" valign="middle"><a href="http://www.baidu.com" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image7','','${ctx}/images/fisheries/tz-01.jpg',1)"><img src="${ctx}/images/fisheries/tz-1.jpg" name="Image7" width="53" height="27" border="0"></a></td>
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
                                    <td class="wz-01">数据：5.49(℃)</td>
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
                                    <td class="wz-01">数据：11.42(mg/l)</td>
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
                  <%if(false){ %>
                  <tr id="">
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
                  <%} %> 
                  <%if(true){ %>
                  <tr>
                    <td height="154" colspan="2"><img src="${ctx}/images/fisheries/dh-1_r2_c1.jpg" width="460" height="154"></td>
                  </tr> 
                  <%} %>                
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
            <td><img src="${ctx}/images/fisheries/qux.jpg" width="621" height="243"></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
