package com.cbo.vizr.front.tags;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.cbo.vizr.ChartType;

public class ChartTag extends SimpleTagSupport {

	private String url ="/chart";
	private String name = UUID.randomUUID().toString();
	private String type = ChartType.LINE.getType();
	
	@Override
	public void doTag() throws JspException, IOException {
	    JspWriter out = getJspContext().getOut();
	    
	    if(!url.startsWith("/"))
	    	url="/"+url;
	    
	    out.println("<canvas id=\""+name+"\" />");
	    out.println("<script>loadChart('/vizr"+url+"/"+type+"', '"+name+"');</script>");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
