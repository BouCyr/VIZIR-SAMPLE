package com.cbo.vizr;

public class Options {
	private boolean responsive = true;
	private boolean maintainAspectRatio=false;
	
	
	
	
	public boolean isResponsive() {
		return responsive;
	}
	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
	}
	public boolean isMaintainAspectRatio() {
		return maintainAspectRatio;
	}
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		this.maintainAspectRatio = maintainAspectRatio;
	}
	
	
}
