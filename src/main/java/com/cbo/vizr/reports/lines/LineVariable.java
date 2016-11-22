package com.cbo.vizr.reports.lines;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LineVariable {

	String value();
	VariableType type() default VariableType.PICK;
}
