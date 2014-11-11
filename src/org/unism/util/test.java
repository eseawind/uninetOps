package org.unism.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.unism.op.domain.Op_Scene;
import org.unism.util.StaticDataManage;

public class test {

	public static void main(String[] args){		
//		try {
//			new test().daochuProjectExcel(new FileOutputStream("E:/fine.xls"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(DecimalUtils.subDecimal(334.3312,0));
//		StaticDataManage ss=new StaticDataManage();
//		String aa=ss.getDevFaultInfo(1, 1, 5);
//		System.out.println(aa);
//		List<Def_symptom> list=StaticDataManage.getDef_symptomList(1, 1);
//		Iterator iterator=list.iterator();
//		while (iterator.hasNext()) {
//			Def_symptom def_symptom = (Def_symptom)iterator.next();
//			System.out.println(def_symptom.getId()+":"+def_symptom.getName());
//		}
		float corrValue = 5023;
		int reset = (int) (corrValue / 1000);
		int sig = (int) (corrValue % 1000);
	}
	
	/**
	 * 项目导出
	 * @return
	 * @author Wang_Yuliang
	 */
	public String daochuProjectExcel(OutputStream outputStream){
		String result = "操作失败!未知错误";
		HSSFWorkbook wb = new HSSFWorkbook();
		//场景信息表
		wb = daochuOpScene(wb);
		
		try {
			wb.write(outputStream);
			result = "suc";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 导出场景信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public HSSFWorkbook daochuOpScene(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("场景信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    cs_head.setWrapText(true);     
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景名称\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属场景父编号\r\n(如果为空则不填)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型细类\r\n(下拉选择)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型子类\r\n(下拉选择)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属等级\r\n(整数)"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue("区划编码");
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("创建者\r\n(必填)"));
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue("场景说明");
        cell = row.createCell((short)10);
        cell.setCellStyle(cs_head);
        cell.setCellValue("关键字");
        cell = row.createCell((short)11);
        cell.setCellStyle(cs_head);
        cell.setCellValue("场景所在地");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        //List<Op_Scene> op_Scene_list = this.sceneDao.findAll();
        int i = 1;
        Map op_Scene_map = new HashMap();        	
        //for(Op_Scene op_Scene : op_Scene_list){
        //	op_Scene_map.put(op_Scene.getScene_id(), op_Scene);
       // }
        /**
        for(Op_Scene op_Scene : op_Scene_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);            
            cell.setCellValue(op_Scene.getScene_no()!=null?op_Scene.getScene_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_name()!=null?op_Scene.getScene_name():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            Op_Scene op_Scene_p = op_Scene.getScene_pid()!=null?(Op_Scene)op_Scene_map.get(op_Scene.getScene_pid()):null;
            cell.setCellValue((op_Scene_p!=null&&op_Scene_p.getScene_no()!=null)?op_Scene_p.getScene_no():"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            String scene_type_name = Scene_type.findNameById(op_Scene.getScene_type());
            cell.setCellValue(scene_type_name!=null?scene_type_name:"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            String scene_gtype_name = Scene_gtype.findNameById(op_Scene.getScene_gtype());
            cell.setCellValue(scene_gtype_name!=null?scene_gtype_name:"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            String scene_ctype_name = Scene_ctype.findNameById(op_Scene.getScene_ctype());
            cell.setCellValue(scene_ctype_name!=null?scene_ctype_name:"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_rank()!=null?op_Scene.getScene_rank()+"":"");
            cell = row.createCell((short)7);
            cell.setCellStyle(cs_data);           
            cell.setCellValue((op_Scene.getArea_id()!=null&&op_Scene.getArea_id().getArea_id()!=null)?op_Scene.getArea_id().getArea_id():"");
            cell = row.createCell((short)8);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_creater()!=null?op_Scene.getScene_creater():"");
            cell = row.createCell((short)9);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_desc()!=null?op_Scene.getScene_desc():"");
            cell = row.createCell((short)10);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_keyWord()!=null?op_Scene.getScene_keyWord():"");
            cell = row.createCell((short)11);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_addr()!=null?op_Scene.getScene_addr():"");
        }
        */
        //for(Op_Scene op_Scene : op_Scene_list){
         while(i<126){
        	//row = sheet.ge
        	HSSFRow row_date = sheet.createRow(i);
        	i++;
        	HSSFCell cell1 = row_date.createCell((short)0);
        	cell1.setCellStyle(cs_data);            
        	cell1.setCellValue("a");
            HSSFCell cell2 = row_date.createCell((short)1);
            cell2.setCellStyle(cs_data);
            cell2.setCellValue("a");
            HSSFCell cell3 = row_date.createCell((short)2);
            cell3.setCellStyle(cs_data);
            //Op_Scene op_Scene_p = op_Scene.getScene_pid()!=null?(Op_Scene)op_Scene_map.get(op_Scene.getScene_pid()):null;
            cell3.setCellValue("a");
            HSSFCell cell4 = row_date.createCell((short)3);
            cell4.setCellStyle(cs_data);
           //String scene_type_name = Scene_type.findNameById(op_Scene.getScene_type());
            cell4.setCellValue("a");
            HSSFCell cell5 = row_date.createCell((short)4);
            cell5.setCellStyle(cs_data);
            //String scene_gtype_name = Scene_gtype.findNameById(op_Scene.getScene_gtype());
            cell5.setCellValue("a");
            HSSFCell cell6 = row_date.createCell((short)5);
            cell6.setCellStyle(cs_data);
           // String scene_ctype_name = Scene_ctype.findNameById(op_Scene.getScene_ctype());
            cell6.setCellValue("a");
            HSSFCell cell7 = row_date.createCell((short)6);
            cell7.setCellStyle(cs_data);
            cell7.setCellValue("a");
            HSSFCell cell8 = row_date.createCell((short)7);
            cell8.setCellStyle(cs_data);           
            cell8.setCellValue("a");
            HSSFCell cell9 = row_date.createCell((short)8);
            cell9.setCellStyle(cs_data);
            cell9.setCellValue("a");
            HSSFCell cell10 = row_date.createCell((short)9);
            cell10.setCellStyle(cs_data);
            cell10.setCellValue("a");
            HSSFCell cell11 = row_date.createCell((short)10);
            cell.setCellStyle(cs_data);
            cell.setCellValue("a");
            HSSFCell cell12 = row_date.createCell((short)11);
            cell12.setCellStyle(cs_data);
            cell12.setCellValue("aa");
        }
		return wb;
	}
}
