function moveOpt(fromObj, toObj) {// 当点击分配按钮时，获取左右的控件名
	if(jQuery("#sel_childScene option:selected").length > 0){
		var toObjLength = toObj.length;
		if (fromObj.selectedIndex != -1 && fromObj.length > 0) {// 如果有被选中的
			for (i = 0; i < fromObj.length; i++) {// 获取控件下所有元素个数
				if (fromObj.options[i].selected) {// 如果某一个元素被选中
					if (toObjLength > 0) {
						var bool = false;
						for (j = 0; j < toObjLength; j++) {
							if (fromObj.options[i].value == toObj.options[j].value) {
								bool = true;
							}
						}
						if (bool == false) {
							addOpt(toObj, fromObj.options[i].value, fromObj.options[i].text);
						}
					} else {
						addOpt(toObj, fromObj.options[i].value, fromObj.options[i].text);
					}
				}
			}
		}
	} else {
		alert("请选择要关联的子场景！");
	}
}
function selectAll(selObj) {
	for (a = 0; a < selObj.length; a++) {
		selObj.options[a].selected = true;
	}
}
function addOpt(list, val, text, idx, selected) {
	if (selected == null) {
		selected = false;
	}
	if (idx != null) {
		list.options[idx] = new Option(text, val, false, selected);
	} else {
		list.options[list.length] = new Option(text, val, false, selected);
	}
}
function del(){
	if(jQuery("#sel_childedScene option:selected").length > 0){
		jQuery("#sel_childedScene option:selected").remove();
	}else{
		alert("请选择要删除的子场景！");
	}
}
function delAllOtherOption(){
	jQuery("#sel_childedScene option").remove();
}
