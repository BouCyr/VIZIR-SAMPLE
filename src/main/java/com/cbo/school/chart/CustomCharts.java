package com.cbo.school.chart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbo.school.data.Gender;
import com.cbo.school.data.Grade;
import com.cbo.school.data.GradesRepo;
import com.cbo.vizr.ChartProvider;
import com.cbo.vizr.ChartType;
import com.cbo.vizr.DataSet;
import com.cbo.vizr.RestChart;
import com.cbo.vizr.charts.ChartCreationException;
import com.cbo.vizr.charts.palettes.Color;
import com.cbo.vizr.charts.palettes.PlainPalette;

@Component
public class CustomCharts implements ChartProvider {

	private static final String COURBE_EN_S = "Courbe en S";
	private static final String GENDER_RADAR = "Gender Radar";
	@Autowired
	GradesRepo repo;
	
	@Override
	public String getName() {
		return "School charts";
	}

	@Override
	public Collection<RestChart> getCharts() throws ChartCreationException {
		return Arrays.asList(new RestChart[]{getSCurve()});
	}
	
	
	@Override
	public List<String> getChartNames() {
		
		return Arrays.asList(new String[]{COURBE_EN_S, GENDER_RADAR});
		
	}

	@Override
	public RestChart getChart(String name) throws ChartCreationException {
		if(COURBE_EN_S.equals(name))
			return getSCurve();
		if(GENDER_RADAR.equals(name))
			return getGenderRadar();
		
		return getSCurve();
		
	}
	

	
	private RestChart getSCurve(){
		
		
		List<Grade> allGrades = new ArrayList<>();
		repo.findAll().forEach(g -> allGrades.add(g));
		
		Collections.sort(allGrades);
		RestChart sCurve = new RestChart(ChartType.LINE);
		sCurve.setName(COURBE_EN_S);
		DataSet ds = new DataSet();
		ds.setLabel("S Curve");
		
		
		sCurve.getData().getDatasets().add(ds);
		
		
		
		for(Float i = 0.0f ; i <=20.0f ; i++){
		
		
			sCurve.getData().getLabels().add(i+"");
		
			ds.getData().add((double) repo.countByAverageGreaterThan( i ));
		}
		
		PlainPalette palette = new PlainPalette(new Color(244, 121, 32, 0.8f));
		palette.applyPalette(ds);
		

		
		return sCurve;
		
	}

	private RestChart getGenderRadar(){
		
		//select gender, AVG(math), AVG(geo), AVG(history), AVG(sport) from grades group by gender 
		List<Object> result = repo.avgByGender();
		
		RestChart sCurve = new RestChart(ChartType.RADAR);
		sCurve.setName(GENDER_RADAR);
		
		sCurve.getData().getLabels().addAll(Arrays.asList(new String[]{"Math","Geographie","Histoire","Sport","MOYENNE"}));
		
		for(Object o : result){
			DataSet ds = new DataSet();
			
			Object[] os = (Object[])o;
			Gender gender= (Gender)os[0];
			Double math = (Double)os[1];
			Double geo = (Double)os[2];
			Double history = (Double)os[3];
			Double sport = (Double)os[4];
			Double overall = (Double)os[5];
			
			ds.setLabel(gender.name());
			ds.getData().add(math);
			ds.getData().add(geo);
			ds.getData().add(history);
			ds.getData().add(sport);
			ds.getData().add(overall);
			
			sCurve.getData().getDatasets().add(ds);
				
			if(gender == Gender.MALE)
				new PlainPalette(new Color(244, 121, 32, 0.2f)).applyPalette(ds);
			else
				new PlainPalette(new Color(161, 32, 244, 0.2f)).applyPalette(ds);
				
		}
		
		return sCurve;
		
		
		
	}


}
