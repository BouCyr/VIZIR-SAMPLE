package com.cbo.vizr.front.tags;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ChartTag extends SimpleTagSupport {

	private String url ="/chart";
	private String name = UUID.randomUUID().toString();
	
	@Override
	public void doTag() throws JspException, IOException {
	    JspWriter out = getJspContext().getOut();
	    
	    if(!url.startsWith("/"))
	    	url="/"+url;
	    
	    out.println("<canvas id=\""+name+"\" />");
	    out.println("<script>loadChart('/vizr"+url+"', '"+name+"');</script>");
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
	
	
}
