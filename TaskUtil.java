package com.uttara.mvc;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TaskUtil {
	
	private static TaskLogger l = TaskLogger.getInstance();
	
	public static boolean isNull(String s)
	{
		l.log("At TaskUtil-->In isNull");
		if(s==null)
			return true;
		if(s.isEmpty())
			return true;
		return false;
	}
	
	public static boolean validateName(String s)
	{
		l.log("At TaskUtil-->In Valid name");
		if(s.isEmpty())
			return false;
		if(s.split(" ").length>1)
			return false;
		if(s == null || s.trim().equals(""))
			return false;
		if(!(Character.isLetter(s.charAt(0))))
			return false;
		for(int i=0;i<s.length();i++)
		{
			if(!(Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i))))
				return false;
		}
		return true;
	}
	
	public static boolean dateValidation(String s)
	{
		l.log("At TaskUtil-->In Date Validation");
		try
		{
		SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		Date d = a.parse(s);
		if((d.getTime()-currentDate.getTime())<0)
			return false;
		return true;
		}
		catch(Exception o)
		{
			return false;
		}
	}
	
	public static String currentTime()
	{
		l.log("At TaskUtil-->In currentTime");
		return new SimpleDateFormat("dd/MM/yyyy_HH;mm;ss").format(Calendar.getInstance().getTime());
	}
	
	public static String formatter(String s)
	{
		l.log("At TaskUtil-->At formatter");
		return s.replaceAll(":", ";");
	}
	
	public static boolean validateStatus(String taskStatus)
	{
		l.log("At TaskUtil-->In Validate Status");
		if(taskStatus.equals("Pending") || taskStatus.equals("Finished"))
			return true;
		return false;
	}
	
	public static boolean validatePriority(int taskPriority)
	{
		l.log("At TaskUtil-->In Validate priority");
		if(taskPriority>0 && taskPriority<=10)
			return true;
		return false;
	}

}
