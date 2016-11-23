package com.cbo.vizr.front;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FirstController{


	@GetMapping("/index")
	public ModelAndView welcome(Map<String, Object> model, 
			@RequestParam(value="name", required=false, defaultValue="World") String name){

		ModelAndView mav = new ModelAndView("First") ;
		
		
		

		mav.addObject("name", name);

		return mav;
	}


}
