package com.cbo.vizr.charts.palettes;

import java.util.ArrayList;

import com.cbo.vizr.DataSet;
import com.cbo.vizr.charts.Palette;

public class PlainPalette extends Palette {

private Color c;
	


	public PlainPalette(Color c) {
		super();
		this.c =c ;
	}



	@Override
	public void applyPalette(DataSet ds) {

		
		ds.setBackgroundColor(new ArrayList<>());
		ds.setBorderColor(new ArrayList<>());
		
		
		ds.getData().forEach(d -> ds.getBackgroundColor().add(c.toRgba()));
		ds.getData().forEach(d -> ds.getBorderColor().add(c.toRgb()));
	}
	
	
}
