var scene="";
function echoSceneTree(id,name,no,rank,gtype){
	if(id != "" && id != null){
		document.getElementById("scene_id").value=id;
		jQuery("#sceneName").html("——"+name);
		scene=id;
		loading(id);
	}
}

function loading(id){
	jQuery.ajax({
		   url: "${ctx}/Op_AlarmConfig_configureOpAlarmconfig.action", 
	       type: "POST",  
	       dataType: "json",//xml,html,script,json
	       data:{sceneId : id},
	       error: function(){},  
	       success: function(data){
	    	   var html = "";
	    	   var htmlBuffer = new StringBuffer();
	    	   var chIdBuffer = new StringBuffer();
	    	   var j;
	    	   jQuery.each(data,function(i,alarmconfig){
	    		   var chId = alarmconfig.chId;
	    		   var chName = alarmconfig.chName;
	    		   var isConfigure = alarmconfig.isConfigure;
	    		   var max = alarmconfig.max;
	    		   var min = alarmconfig.min;
	    		   if(i == "0"){
	    			   var email = alarmconfig.email
	    			   var interval = alarmconfig.interval;
	    			   var isConfigure = alarmconfig.isConfigure;
	    			   var phone = alarmconfig.phone;
	    			   var noticeType = alarmconfig.noticeType;
	    			   //var alarmConfigId = alarmconfig.alarmConfigId;
	    			   document.getElementById("al_phone").value = phone;
	    			   document.getElementById("al_email").value = email;
	    			   document.getElementById("al_interval").value = interval;
	    			   //document.getElementById("alarmConfigId").value = alarmConfigId;
	    			   if(isConfigure == "1"){
	    				   document.getElementById("isConfigure").checked = "checked";
	    				   document.getElementById("isEnable").value = "Open";
	    			   }else{
	    				   document.getElementById("isEnable").value = "Stop";
	    			   }
	    			   jQuery("#al_noticeType").val(noticeType);
	    		   }else{
	    			   j=i-1;
	    			   chIdBuffer.append(chId+",");
	    			   //var id = alarmconfig.alarmargumentId;
	    			   if(i%2 == 0){
	    				   htmlBuffer.append("<tr><td style=\"background-color:#fff3fa;border-bottom:1px #90adff solid;\">");
		    		   }else{
		    			   htmlBuffer.append("<tr><td style=\"background-color:#e9efff;border-bottom:1px #90adff solid;\">");
		    		   }
	    			   //htmlBuffer.append("<input name=\"configForm.alarmArgumentForms["+j+"].id\" type=\"hidden\" value='"+id+"' />");
	    			   htmlBuffer.append("<input name=\"configForm.alarmArgumentForms["+j+"].chId\" type=\"hidden\" value='"+chId+"' />");
		    		   htmlBuffer.append("<table id='table"+chId+"' width=\"100%\" height=\"80\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><td rowspan=\"2\" width=\"10\"></td><td align=\"left\">通道名称：</td><td align=\"left\">"+chName+"</td><td align=\"left\">是否开启：</td><td align=\"left\">");
		    		   if(isConfigure == "1"){
		    			   htmlBuffer.append("是&nbsp;<input id='enable"+chId+"' checked=\"checked\" name=\"configForm.alarmArgumentForms["+j+"].enable\" onclick=\"openOrClose(this.id)\" type=\"radio\" value=\"Open\" >&nbsp;&nbsp;&nbsp否&nbsp;<input id='enable"+chId+"' name=\"configForm.alarmArgumentForms["+j+"].enable\" onclick=\"openOrClose(this.id)\" type=\"radio\" value=\"Stop\" ");
		    			   htmlBuffer.append("</td></tr><tr><td align=\"left\">最大值：</td><td align=\"left\"><input id='max"+chId+"' type=\"text\" textType=\"max\" name=\"configForm.alarmArgumentForms["+j+"].max\" value='"+max+"' /></td><td align=\"left\">最小值：</td><td align=\"left\"><input id='min"+chId+"' type=\"text\" textType=\"min\" name=\"configForm.alarmArgumentForms["+j+"].min\" value='"+min+"' /></td></tr></table></td></tr>");
		    		   }else{
		    			   htmlBuffer.append("是&nbsp;<input id='enable"+chId+"' name=\"configForm.alarmArgumentForms["+j+"].enable\" onclick=\"openOrClose(this.id)\" type=\"radio\" value=\"Open\" >&nbsp;&nbsp;&nbsp否&nbsp;<input id='enable"+chId+"' checked=\"checked\" name=\"configForm.alarmArgumentForms["+j+"].enable\" onclick=\"openOrClose(this.id)\" type=\"radio\" value=\"Stop\" ");
		    			   htmlBuffer.append("</td></tr><tr><td align=\"left\">最大值：</td><td align=\"left\"><input disabled=\"disabled\" id='max"+chId+"' type=\"text\" textType=\"max\" name=\"configForm.alarmArgumentForms["+j+"].max\" value='"+max+"' /></td><td align=\"left\">最小值：</td><td align=\"left\"><input disabled=\"disabled\" id='min"+chId+"' type=\"text\" textType=\"min\" name=\"configForm.alarmArgumentForms["+j+"].min\" value='"+min+"' /></td></tr></table></td></tr>");
		    		   }
	    		   }
	    		   
	    	   });
	    	   html = htmlBuffer.toString();
	    	   jQuery("#list").html(html);
	    	   var chIds = chIdBuffer.toString();
	    	   if(chIds.length > 0){
	    		   chIds = chIds.substring(0,chIds.length-1);
	    	   }
	    	   jQuery("#chIdHidden").val(chIds);
  		}  
		});
}

function submitForm(){
	var errorMap = new Map();
	jQuery("#al_phone").css("borderColor","");
	jQuery("#al_email").css("borderColor","");
	jQuery("#al_interval").css("borderColor","");
	var noticeType = jQuery("#al_noticeType").val();
	var ph = false;
	var Ea = false;
	if(noticeType == "All"){
		ph = true;
		Ea = true;
	}else if(noticeType == "Phone"){
		ph = true;
	}else if(noticeType == "Email"){
		Ea = true;
	}
	
	if(ph){
		var phone = jQuery("#al_phone").val();
		if(phone!=""){
			var phoneArray = phone.split(",");
			var p = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var f = false;
			for(var i = 0;i < phoneArray.length;i++){
				if(!p.test(phoneArray[i])){
					f = true;
					errorMap.put("error1","手机号码格式输入有误!");
					jQuery("#al_phone").css("borderColor","#00a200");
				}
				if(f){
					break;
				}
			}
		}else{
			errorMap.put("error2","手机号码不能为空!");
			jQuery("#al_phone").css("borderColor","#00a200");
		}
	}
	
	if(Ea){
		var email = jQuery("#al_email").val();
		if(email!=""){
			var emailArray = email.split(",");
			var e = /^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/;
			var ff = false;
			for(var j = 0;j < emailArray.length;j++){
				if(!e.test(emailArray[j])){
					ff = true;
					errorMap.put("error3","邮箱格式输入输入有误!");
					jQuery("#al_email").css("borderColor","#00a200");
				}
				if(ff){
					break;
				}
			}
			
		}else{
			errorMap.put("error4","邮箱不能为空!");
			jQuery("#al_email").css("borderColor","#00a200");
		}
	}
	var interval = jQuery("#al_interval").val();
	if(interval!=""){
		var i = /^[0-9]*[1-9][0-9]*$/;
		if(!i.test(interval) && interval != 0){
			errorMap.put("error5","通知间隔输入有误,只能为正整数!");
			jQuery("#al_interval").css("borderColor","#00a200");
		}
	}else{
		document.getElementById("al_interval").value="0";
		//errorMap.put("error6","通知间隔不能为空!");
		//jQuery("#al_interval").css("borderColor","#00a200");
	}
	var chIds = jQuery("#chIdHidden").val();
	var chIdArr = chIds.split(",");
	for(var i = 0;i < chIdArr.length;i++){
		var id = chIdArr[i];
		var enable = jQuery("input[id='enable"+id+"']:checked").val();
		var max = jQuery("#max"+id).val();
		var min = jQuery("#min"+id).val();
		var resultMax = numberV(max);
		var resultMin = numberV(min);
		jQuery("#max"+id).css("borderColor","");
		jQuery("#min"+id).css("borderColor","");
		if(!resultMax){
			errorMap.put("error7","最大值只能填写数字！蓝色为有误的行。");
			jQuery("#max"+id).css("borderColor","#0000ff");
		};
		if(!resultMin){
			errorMap.put("error8","最小值只能填写数字！蓝色为有误的行。");
			jQuery("#min"+id).css("borderColor","#0000ff");
		}
		if(enable == "Open"){
			if(max == "" && min == ""){
				jQuery("#max"+id).css("borderColor","#b00008");
				jQuery("#min"+id).css("borderColor","#b00008");
				errorMap.put("error9","如果开启，最大值或者最小值至少填一个！红色为有误的行。");
			}else{
				if(max!=""&&min!=""){
					var v = max-min;
					if(v <= 0){
						jQuery("#max"+id).css("borderColor","#000");
						jQuery("#min"+id).css("borderColor","#000");
						errorMap.put("error10","最大值不能小于或等于最小值！黑色为有误的行。");
					}
				}
			}
		}
	}
	
	var msg = "";
	var k=1;
	var isSub = true;
	for(var i = 1;i < 11;i++){
		var m = errorMap.get("error"+i);
		if(m!=null&&""!=m){
			msg+="填写有误（"+k+"）："+m+"</br>";
			k++;
			
		}
	}
	if(msg!=""&&msg!=null){
		msg = "<p style=\"font-family:宋体;font-size:13px;color:#000;\">"+msg;
		msg += "</p>";
		alertBox(msg);
		isSub = false;
	}
	if(isSub){
		jQuery.ajax({
			   url: "${ctx}/Op_AlarmConfig_save.action", 
		       type: "POST",  
		       dataType: "json",//xml,html,script,json
		       data:$('#myform').serialize(),
		       error: function(){},  
		       success: function(data){
		    	   alert(data.message);
		    	   loading(scene);
	   			}  
			});
	}
	
}

function numberV(num){
	var number = /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/;
	return number.test(num);
}

function isChangeEnable(c){
	if(c.checked){
		document.getElementById("isEnable").value="Open";
		jQuery('input:radio').each(function(){
			jQuery(this).val(["Open"]);
		});
		allDisable("Open");
	}else{
		document.getElementById("isEnable").value="Stop";
		jQuery('input:radio').each(function(){
			jQuery(this).val(["Stop"]);
		});
		allDisable("Stop");
	}
}

function allDisable(s){
	var chIds = jQuery("#chIdHidden").val();
	var chIdArr = chIds.split(",");
	for(var i = 0;i < chIdArr.length;i++){
		var id = chIdArr[i];
		if(s == "Open"){
			jQuery("#max"+id).attr("disabled","");
			jQuery("#min"+id).attr("disabled","");
		}else if(s == "Stop"){
			jQuery("#max"+id).attr("disabled","disabled");
			jQuery("#min"+id).attr("disabled","disabled");
		}
	}
}

function openOrClose(id){
	var value = jQuery("input[id='"+id+"']:checked").val();
	id = id.substring(6);
	if(value == "Open"){
		document.getElementById("isEnable").value="Open";
		jQuery("#max"+id).attr("disabled","");
		jQuery("#min"+id).attr("disabled","");
	}else if(value == "Stop"){
		document.getElementById("isEnable").value="Stop";
		jQuery("#max"+id).attr("disabled","disabled");
		jQuery("#min"+id).attr("disabled","disabled");
	}	
}

function alertBox(msg){
	var dg = new J.dialog({id:'test',title:'提示',autoSize:true,iconTitle:false,cover:false,bgcolor:'#000',height:150,rang: true,link:false,btnBar:false,cancelBtn:false,maxBtn:false,resize:false,html:msg}); 
	dg.ShowDialog();
}