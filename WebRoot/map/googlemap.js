//<![CDATA[
var Lat=parseFloat(31.48582661228892);
var Lng=parseFloat(119.78745460510254);
function mapload() 
{
  if (GBrowserIsCompatible()) 
  {
    var map = new GMap2(document.getElementById("agriMap"));
    map.setCenter(new GLatLng(31.48582661228892,119.78745460510254),17);
    // 添加控件
    map.addControl(new GLargeMapControl());
    map.addControl(new GMapTypeControl());
    map.addControl(new GScaleControl());
    map.addControl(new GOverviewMapControl());
    map.enableScrollWheelZoom();
    map.setMapType(G_HYBRID_MAP);
    // ICO类
    var icon = new GIcon();
    icon.image = "beachflag.png";
    icon.shadow = "http://labs.google.com/ridefinder/images/mm_20_shadow.png";
    icon.iconSize = new GSize(30, 30);
    icon.shadowSize = new GSize(22, 20);
    icon.iconAnchor = new GPoint(6, 20);
    icon.infoWindowAnchor = new GPoint(15, 1);
    //  初始化数据
    MakerPoint()
    // 添加拖动事件
    GEvent.addListener(map,"moveend",function(){MakerPoint()});
    function MakerPoint()
    {
      var xmlurl="data.xml";
      GDownloadUrl(xmlurl, function(data, responseCode){
        var xml = GXml.parse(data);
        var markers = xml.documentElement.getElementsByTagName("marker");
        for (var i=0;i<markers.length;i++) {
          var mlat=parseFloat(markers[i].getAttribute("lat"));
          var mlng=parseFloat(markers[i].getAttribute("lng"));
          var mpoint = new GLatLng(mlat,mlng);
          var mid=markers[i].getAttribute("id");
          var mname=markers[i].getAttribute("name");
          var mthumb=markers[i].getAttribute("thumb");
          var mdescription=markers[i].getAttribute("description");
          var maddtime=markers[i].getAttribute("addtime");
          map.addOverlay(createMarker(mpoint,mid,mname,mthumb,mdescription,maddtime));
        }
      });
    }
    function createMarker(point,id,name,thumb,description,addtime){
      var marker = new GMarker(point,icon);
      GEvent.addListener(marker,"click",function(){
        if(thumb=='')
          thumb='<img src="noimg.gif"/>';
        else
          thumb='<img src='+thumb+' width="150" height="120"/>';
        gourl='detail.php?id='+id;
        infoHtml='<div class="mapinfoHtml"><h1>'+name+'(编号：'+id+')</h1><div class="clearfix"><div class="l w120 mr">'+thumb+'</div><div class="r w200"><strong>详情</strong>：'+description+'<br/><strong>登记</strong>：'+addtime+'</div></div><div><a href="'+gourl+'" target="_blank">详细介绍 >>><a></div>';
        marker.openInfoWindowHtml(infoHtml);

      });
      return marker;
    }
  }
}
//]]>