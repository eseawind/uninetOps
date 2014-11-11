<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>江苏宜兴地图</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script src="http://maps.google.com/maps?file=api&v=2&sensor=true&key=ABQIAAAA_g4dEkmLIY923VtPirYU4BTqYscCqQGGc8n-HDiJg6mT0_n49RRutY6TK3wSIEdxPcHyI9L5HUqIBg" type="text/javascript"></script>
<script type="text/javascript" src="MyControl.js"></script>
<!--  <script src="googlemap.js" type="text/javascript"></script>-->
<script type="text/javascript" src="../ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" type="text/css" href="../ymPrompt/ymPrompt.css">




<script type="text/javascript">

//<![CDATA[
var Lat=parseFloat(31.48582661228892);
var Lng=parseFloat(119.78745460510254);
function mapload() 
{
  if (GBrowserIsCompatible()) 
  {
    var map = new GMap2(document.getElementById("agriMap"));
    //map.setCenter(new GLatLng(31.48582661228892,119.78745460510254),17);
   
	
    map.setCenter(new GLatLng(31.5073988892,119.6128640325),11);
    // 添加控件
    map.addControl(new GLargeMapControl());
    map.addControl(new GMapTypeControl());
    map.addControl(new GScaleControl());
    map.addControl(new GOverviewMapControl());
    map.enableScrollWheelZoom();
    map.setMapType(G_HYBRID_MAP);
    // ICO类
    var icon = new GIcon();
    icon.image = "green.png";
    icon.shadow = "http://labs.google.com/ridefinder/images/mm_20_shadow.png";
    icon.iconSize = new GSize(25, 25);
    icon.shadowSize = new GSize(10, 10);
    icon.iconAnchor = new GPoint(6, 20);
    icon.infoWindowAnchor = new GPoint(15, 1);
    
    function MakerPoint()
    {
    	var low = 0;
    	var normal = 0;
    	var good = 0;
    	$.getJSON("${ctx}/Pro_Fisheries_mapFishInfo.action",{
			random:Math.random()
			},function(list){			
				jQuery.each(list, function(i, data) {
					var corrVal = data.value;
					if(corrVal >= 5) good++;
					if(corrVal >= 3 && corrVal < 5) normal++;
					if(corrVal < 3) low++;
					var lng = data.lng;
					var lat = data.lat;
					var id = data.id;
					var no = data.no;
					var name = data.userName;
					var thumb = data.thumb;
					var gType = data.gType;
					var flag = data.flag;
					var area = data.area;
					var aquaticType = data.aquaticType;
					var cultureObject = data.cultureObject;
					var putTime = data.putTime;
					var dectState = data.dectState;
					var value = data.value;
					var mpoint = new GLatLng(lat, lng);
    				map.addOverlay(createFishMarker(mpoint, id, no, name, thumb, gType, flag, aquaticType, cultureObject, putTime,dectState, value,area));
				})
				
				var fishInfo = "严重缺氧：" + low + "\n\n溶氧偏低："+ normal + "\n\n溶氧正常："+ good + "\n\n点击查看详情";
				function TextualZoomControl() { };
				TextualZoomControl.prototype = new GControl();
				TextualZoomControl.prototype.initialize = function(map) {
					var container = document.createElement("div");
					var zoomInDiv = document.createElement("div");
					this.setButtonStyle_(zoomInDiv);
					container.appendChild(zoomInDiv);
					zoomInDiv.appendChild(document.createTextNode(fishInfo));
					GEvent.addDomListener(zoomInDiv, "click", function() {
						//alert(111123);
						ymPrompt.win({title:'',width:650,height:400,fixPosition:true,iframe:{id:'myId',name:'myName',src:'fisheries.jsp'}});
					 });
					map.getContainer().appendChild(container);
					return container;
				}
				
				// 默认情况下，该控件将在地图的左上角显示，边距为 7 像素。
				TextualZoomControl.prototype.getDefaultPosition = function() {
					return new GControlPosition(G_ANCHOR_TOP_RIGHT, new GSize(7, 60));
				}
				// 设置给定按钮元素的正确 CSS。
				TextualZoomControl.prototype.setButtonStyle_ = function(button) {
					button.style.color = "#ffffff";
					button.style.backgroundColor = "#84a4ef";
					button.style.font.weight="bold";
					button.style.font = "small Arial";
					button.style.border = "1px solid black";
					button.style.padding = "4px";
					button.style.marginBottom = "20px";
					button.style.textAlign = "center";
					button.style.width = "120px";
				}
				map.addControl(new TextualZoomControl());
		});
		
		
		$.getJSON("${ctx}/Pro_Fisheries_mapWetherVidioInfo.action",{
			random:Math.random()
			},function(list){			
				jQuery.each(list, function(i, data) {
					var lng = data.lng;
					var lat = data.lat;
					var id = data.id;
					var no = data.no;
					var name = data.name;
					var thumb = data.thumb;
					var gtype = data.gtype;
					var url = data.url;
					var mpoint = new GLatLng(lat, lng);
					if(gtype==97)//摄像头
						map.addOverlay(createVideoMarker(mpoint, url));
					if(gtype==98)//气象站
						map.addOverlay(createWeatherMarker(mpoint, id, no, name, thumb, gtype));
				})
		});
    }
    
    function createFishMarker(point, id, no, name, thumb, gtype, flag, aquaticType, cultureObject, putTime,dectState, value, area) {
    	if(flag == 'red'){
    		icon.image = "red.png";
    	}
    	if(flag == 'green'){
    		icon.image = "green.png";
    	}
    	if(flag == 'yellow'){
    		icon.image = "yellow.png";
    	}
    	if(flag == 'gray'){
    		icon.image = "gray.png";
    	}
    	var info = "养殖品种："+cultureObject+"<br/>水草种类："+aquaticType+"<br/>投放时间："+putTime+"<br/>增氧机状态："+dectState+"<br/>溶解氧 ："+value+" mg/l";
    	var marker = new GMarker(point,icon);
		GEvent.addListener(marker,"click",function(){
			if(thumb=='')
          		thumb='<img src="noimg.gif"/>';
        	else
          		thumb='<img src='+thumb+' width="150" height="120"/>';
          	var gourl = 'javascript:showRealtime(\''+id+'\')';
          	var infoHtml='<div class="mapinfoHtml"><h1>'+name+'-'+area+'亩(编号：'+no+')</h1><div class="clearfix"><div class="l w120 mr">'+thumb+'</div><div class="r w200">'+info+'</div></div><div><a href="'+gourl+'">实时监控 >>><a></div>';
          	marker.openInfoWindowHtml(infoHtml);
		});
		return marker;
    } 
    
    function createWeatherMarker(point, id, no, name, thumb, gtype) {
    	icon.image = "weather.png";
    	var marker = new GMarker(point,icon);
    	var info = "风速：???<br/>风向：???<br/>温度：???<br/>湿度：???<br/>太阳辐射：???<br/>";
    	var fs = "";
    	var fx = "";
    	var wd = "";
    	var sd = "";
    	var tyfs = "";
    	if(thumb=='')
			thumb='<img src="noimg.gif"/>';
		else
			thumb='<img src='+thumb+' width="150" height="120"/>';
		var gourl = 'javascript:showRealtime(\''+id+'\')';
			
    	GEvent.addListener(marker,"click",function(){
    		$.getJSON("${ctx}/Pro_Fisheries_getWetherInfoBySceneId.action?sceneId="+id,{
				random:Math.random()
				},function(list){	
					jQuery.each(list, function(i, data) {
						var sceneId = data.sceneId;
						var corrvalue = data.value;
						var unit = data.unit;
						var typeNo = data.typeNo;
						if(unit == null) unit = "";
						if(typeNo == '1200-D')
							fx = corrvalue + unit;
						if(typeNo == '1200-S')
							fs = corrvalue + unit;
						if(typeNo == 'KQSD1201-T')
							wd = corrvalue + unit;
						if(typeNo == 'KQSD1201-H')
							sd = corrvalue + unit;
						if(typeNo == 'CMP6-P')
							tyfs = corrvalue + unit;
					})
				info = "风速："+fs+"<br/>风向："+fx+"<br/>温度："+wd+"<br/>湿度："+sd+"<br/>太阳辐射："+tyfs+"<br/>";				
				var infoHtml='<div class="mapinfoHtml"><h1>'+name+'(编号：'+no+')</h1><div class="clearfix"><div class="l w120 mr">'+thumb+'</div><div class="r w200">'+info+'</div></div><div><a href="'+gourl+'">实时监控 >>><a></div>';
          		marker.openInfoWindowHtml(infoHtml);
			});
          	
		});
    	return marker;
    } 
    
    function createVideoMarker(point, url) {
    	icon.image = "video.png";
    	var marker = new GMarker(point,icon);
    	GEvent.addListener(marker,"click",function(){
    		window.open(url);
    	});
    	return marker;
    } 
    
    
    function createMarker(point,id,no,name,thumb,mdescription,gType,videourl){
    		if(gType == 1.1){
    			icon.image = "red.png";
    		}
    		if(gType == 1.3){
    			icon.image = "green.png";
    		}
    		if(gType == 1.2){
    			icon.image = "yellow.png";
    		}
    	if(gType==98){
    		icon.image = "weather.png";
    	}
    	if(gType==97){
    		icon.image = "video.png";
    	}
      var marker = new GMarker(point,icon);
      GEvent.addListener(marker,"click",function(){
        if(thumb=='')
          thumb='<img src="noimg.gif"/>';
        else
          thumb='<img src='+thumb+' width="150" height="120"/>';
       var gourl = 'javascript:showRealtime(\''+id+'\')';
        var m = mdescription.split(",");
        var x = "";
        for(var i =0;i < m.length;i++){
        	x += m[i]+"<br/>"
        }
        var infoHtml='<div class="mapinfoHtml"><h1>'+name+'(编号：'+no+')</h1><div class="clearfix"><div class="l w120 mr">'+thumb+'</div><div class="r w200">'+x+'</div></div><div><a href="'+gourl+'">实时监控 >>><a></div>';
        if(gType == 97){
        	window.open(videourl);
        }else{
        	marker.openInfoWindowHtml(infoHtml);
        }
      });
      return marker;
    }	
    
    //  初始化数据
    MakerPoint()
    // 添加拖动事件
    GEvent.addListener(map,"moveend",function(){MakerPoint()});
    
  }
}

	function showRealtime(id){
		//var mid = "s_"+id;
		//alert(mid);
		//this.parent.scene_tree.selectById(mid);
		window.location.href="${ctx }/op_scene_toSceneSO_0616.action?op_Scene.scene_id=" + id;
	}
	

//]]>
</script>
</head>
<body onload="mapload();">
<div id="agriMap" class="map"></div>
</body>
</html>
