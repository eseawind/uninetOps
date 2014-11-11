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
	
	<link rel="stylesheet" type="text/css" href="${ctx }/css/dtree.css">
	<script type="text/javascript" src="${ctx }/js/dtree.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
		var menu_tree=new dTree('menu_tree',"${ctx}");
		menu_tree.add(0,-1,'运维系统','');		
	    $.ajaxSettings.async = false;
		$.getJSON("${ctx}/Op_RoleRegith_menuTree.action",{
			random:Math.random()
		},function(list){
			$.each(list,function(i,object){				
				if(object.pnode == 0){					
					menu_tree.add(object.node,object.pnode,object.text+"<input type='checkbox' name='check' value='"+object.text+"' class='"+object.pnode+"' id='"+object.node+"' onClick='checkStatus(this)'>","#","","centerFrame","${ctx}/images/dtree/folder.gif");
				}else{					
					menu_tree.add(object.node,object.pnode,object.text+"<input type='checkbox' name='check' value='"+object.text+"' class='"+object.pnode+"' id='"+object.node+"' onClick='checkStatus(this)'>","#","","centerFrame");
				}				
			})		
		});
		document.write(menu_tree);		
		
		 function checkStatus(chkBox){
	      	checkPar(chkBox);//当子目录选中时,父目录也选中
	     	var chks = document.getElementsByTagName('input');//得到所有input
	     	for(var i=0;i <chks.length;i++){
		        if(chks[i].name.toLowerCase() == 'check'){//得到所名为check的input
		          if(chks[i].className == chkBox.id){//ID等于传进父目录ID时
		            chks[i].checked = chkBox.checked;//保持选中状态和父目录一致
		            checkStatus(chks[i]);//递归保持所有的子目录选中
		          }
		        }
	      	}
	    }
		 function checkPar(chkBox) {
	     	if(chkBox.name.toLowerCase() == 'check' && chkBox.checked && chkBox.className != 0) {//判断本单击为选中,并且不是根目录,则选中父目录
	        	var chkObject = document.getElementById(chkBox.className);//得到父目录对象
	        	chkObject.checked=true;
	       	 	checkPar(chkObject);
	      }
	    }
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">

  </body>
</html>
