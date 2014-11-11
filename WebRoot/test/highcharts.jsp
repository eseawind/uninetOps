<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>菜单树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>
	<script type="text/javascript">
			function showTempChart() {

					chart = new Highcharts.Chart({
						chart: {
							renderTo: 'container',
							defaultSeriesType: 'spline'
							/**,
							events: {
								load: function() {								
									// set up the updating of the chart each second
									var series = this.series[0];
									setInterval(function() {
										$.getJSON("${ctx}/gm_historydata_tt.action",{
											random:Math.random()
										},function(pojo){
											var x = (new Date()).getTime(), // current time
												y = pojo[1];
											series.addPoint([x, y], true, true);
										});
									}, 5000);
								}
							}
							*/
						},
						title: {
							text: '气温曲线图'
						},
						subtitle: {
							text: '近期气温与历史平均比价'
						},
						xAxis: {
				        	type: 'datetime',
					        dateTimeLabelFormats: {
					            day: '%m-%d',
					            hour: '%m-%d'			               
					        }
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
									enabled: false,
									radius: 4,
									lineColor: '#666666',
									lineWidth: 1
								}
							}
						},
						series: [{
							name: '当前值1',
							visible: false,						
							data: [[1306231806000,13.2559024976647],[1306268466000,19.22297737634],[1306397267000,7.77787671232877],[1306452266000,4.68496688741722],[1306519553000,4.9668275862069],[1306606337000,12.6522377622378],[1306748104000,4.3833566433566],[1306815115000,5.45434482758621],[1306915308000,3.79671328671329],[1306972141000,4.79]]
						},{
							name: '当前值2',
							data: [[1306231806000,14.2559024976647],[1306268466000,13.22297737634],[1306397267000,7.77787671232877],[1306452266000,6.68496688741722],[1306519553000,7.8668275862069],[1306606337000,6.6522377622378],[1306748104000,7.3833566433566],[1306815115000,7.45434482758621],[1306915308000,7.79671328671329],[1306972141000,7.79]]
						},{
							name: '当前值3',
							data: [[1306231806000,1.2559024976647],[1306268466000,2.22297737634],[1306397267000,7.77787671232877],[1306452266000,3.68496688741722],[1306519553000,4.8668275862069],[1306606337000,5.6522377622378],[1306748104000,6.3833566433566],[1306815115000,7.45434482758621],[1306915308000,7.79671328671329],[1306972141000,8.79]]
						}]
					});
			}			
			
	</script>
  </head>
  
  <body style="font-size: 12px;">
	<table>
		<tr>
			<td id="td_container">
				<div id="container" style="position:absolute; top:1px; left:2px; width: 1000px; height: 400px; text-align: center;">&nbsp;</div>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		//showTempChart();
		//chart.series.hide();
		var time1 = setInterval(function() {
			alert(11);
		}, 3000);
		var time2 = setInterval(function() {
			alert(22);
		}, 8000);
	</script>
  </body>
</html>
