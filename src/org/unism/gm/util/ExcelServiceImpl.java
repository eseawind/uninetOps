package org.unism.gm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.unism.gm.service.IExcelService;

public class ExcelServiceImpl implements IExcelService
{

	public InputStream getExcelInputStream(List<Object[]> dataList)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();   
		putDataOnOutputStream(out,dataList);   
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private void putDataOnOutputStream(OutputStream os,List<Object[]> dataList){
		//构造Workbook（工作薄）对象    
		WritableWorkbook workbook=null; 
		//创建工作表 
		WritableSheet ws=null;
		try
		{
			//首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象 
			workbook = Workbook.createWorkbook(os); 
			/* 
			             * 创建一个可写入的工作表  
			             * Workbook的createSheet方法有两个参数， 
			             * 第一个是工作表的名称，第二个是工作表在工作薄中的位置 
			             * 生成名为"第一页"的工作表，参数0表示这是第一页 
			             */ 
			ws= workbook.createSheet("offerUp", 0); 
			//创建列名 
			// 定义格式, 字体, 下划线, 斜体, 粗体, 颜色 
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
			
			// 创建格式化对象实例    
			WritableCellFormat totalx2Format = new WritableCellFormat(wf);  
			
			// 垂直居中    
			totalx2Format.setVerticalAlignment(VerticalAlignment.CENTRE);    
			// 水平居中    
			totalx2Format.setAlignment(Alignment.CENTRE); 
			// 设置标题.ws.addCell(new jxl.write.Label(列(从0开始), 行(从1开始), 内容.)); 
//			ws.addCell(new Label(0, 0, "编号",totalx2Format)); 
//			ws.addCell(new Label(1, 0, "客户名称",totalx2Format)); 
//			ws.addCell(new Label(2, 0, "订单金额（元）",totalx2Format)); 
			// 合并单元格，参数格式（开始列，开始行，结束列，结束行） 
//			ws.mergeCells(0, 0, 0, 0); 
//			ws.mergeCells(1, 0, 1, 0); 
//			ws.mergeCells(2, 0, 2, 0); 
			// 设置单元格的宽度 
//			ws.setColumnView(0, 10); 
//			ws.setColumnView(1, 40); 
//			ws.setColumnView(2, 20); 
			
			
			
			//添加数据 
			if (null!=workbook) { 
				//totalx2Format        
			//下面开始添加单元格数据    
			            for(int i=0;i<dataList.size();i++){    
			                for(int j=0;j<dataList.get(0).length;j++){    
			                    //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行    
//			                    Label labelC = new Label(j, i,dataList.get(i)[j].toString(),totalx2Format);    
			                	if(j == 0){
	                    			ws.setColumnView(j, 30);
	                    		}else{
	                    			ws.setColumnView(j, 20);
	                    		}
			                    try {  
			                    	if(i != 0){
				                    	if(j==0){
//					                    	DateTime dateTime = new DateTime(j, i,DateUtils.getDate(dataList.get(i)[j].toString(), "yyyy-MM-dd HH:mm:ss"),totalx2Format);
//					                    	ws.addCell(dateTime);
				                    		Label labelC = new Label(j, i,dataList.get(i)[j].toString(),totalx2Format);
					                    	ws.addCell(labelC); 
					                    }else{
					                    	Number num = new Number(j, i,Double.parseDouble(dataList.get(i)[j]+""),totalx2Format);
					                    	ws.addCell(num);
					                    }
				                    }else{
				                    	Label labelC = new Label(j, i,dataList.get(i)[j].toString(),totalx2Format);
				                    	ws.addCell(labelC); 
				                    }
			                        //将生成的单元格添加到工作表中    
//			                    ws.addCell(labelC);    
			                    } catch (Exception e) {    
			                        e.printStackTrace();    
			                    }  
			                }    
			            }    
			} 
			 //从内存中写入文件中   
			workbook.write();    
			               //关闭资源，释放内存    
			workbook.close();    



		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
		
	}

}
