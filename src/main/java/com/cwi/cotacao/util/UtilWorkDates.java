package com.cwi.cotacao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component("workDate")
public class UtilWorkDates {

	public String dateToDayToString() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_WEEK) == 1){
       	 cal.add (Calendar.DATE, -2); 
        }else if (cal.get(Calendar.DAY_OF_WEEK) == 2){
       	 cal.add (Calendar.DATE, -3); 
        }else{
       	 cal.add (Calendar.DATE, -1); 
        }
		String data = df.format(cal.getTime());
		return data;
	}
	
	
	public String stringFormatForString(String str_date) {
		
		try {  
			 
			
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String[] data = str_date.split("/");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(data[1]) - 1);
			cal.set(Calendar.YEAR, Integer.parseInt(data[2]));
			
			//Verifico se é dia util
			if (cal.get(Calendar.DAY_OF_WEEK) == 1){
	       	 cal.add (Calendar.DATE, -2); 
	        }else if (cal.get(Calendar.DAY_OF_WEEK) == 2){
	       	 cal.add (Calendar.DATE, -3); 
	        }else{
	       	 cal.add (Calendar.DATE, -1); 
	        }
			
			return df.format(cal.getTime());
		  } catch (Exception e){
			  System.out.println("Exception :"+e);  
			  return null;
		  } 
	}
}
