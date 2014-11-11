package org.unism.web.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.wangzz.core.web.struts2.Struts2Utils;

public class ImageExportAction implements ServletRequestAware,ServletResponseAware {

	private String svg;
	private String type;
	private String extName;
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	@SuppressWarnings("unused")
	private HttpServletResponse response;
	private String filename;

	 private InputStream inputStream;
	 
	public void chartsExport() {
		try {
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputStream out = Struts2Utils.getResponse().getOutputStream();
			svg = svg.replaceAll(":rect", "rect");
			String ext = "";
			Transcoder t = null;

			if (type.equals("image/png")) {
				ext = "png";
				t = new PNGTranscoder();

			} else if (type.equals("image/jpeg")) {
				ext = "jpg";
				t = new JPEGTranscoder();

			} else if (type.equals("application/pdf")) {
				ext = "pdf";
				t = new PDFTranscoder();

			} else if (type.equals("image/svg+xml")) {
				ext = "svg";
			}

			setExtName(ext);
			String name = filename == null?"chart":filename;
			
//			File f = new File("D:\\"+filename+"."+ext);
//			OutputStream out = new FileOutputStream(f);
			Struts2Utils.getResponse().setHeader("Content-Disposition","attachment;filename="+ new String(name.getBytes("GBK"),"ISO-8859-1") + "." + ext);
			if (null != t) {
				TranscoderInput input = new TranscoderInput(new StringReader(
						svg));
				TranscoderOutput output = new TranscoderOutput(out);
				try {
					t.transcode(input, output);
				} catch (TranscoderException e) {
					e.printStackTrace();
				}
			}else{
				OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
				writer.append(svg);
				writer.flush();
				writer.close();
			}
			out.flush();
			out.close();
//			byte[] data = out.toByteArray();
//			inputStream = new ByteArrayInputStream(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSvg() {
		return svg;
	}

	public void setSvg(String svg) {
		this.svg = svg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	
}
