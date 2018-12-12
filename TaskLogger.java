package com.uttara.mvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class TaskLogger {
	private static TaskLogger t = null;
	
	public static TaskLogger getInstance() {
		if(t==null)
			t = new TaskLogger();
		return t;
	}
	
	public void clearLog()
	{
		BufferedWriter bw=null;
		try {
			bw = new BufferedWriter(new FileWriter("C:\\Task\\Log.txt"));
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void log(String msg)
	{
		if(msg==null || msg.isEmpty())
			return;
		BufferedWriter bw=null;
		try {
			Date dt = new Date();
			bw = new BufferedWriter(new FileWriter("C:\\Task\\Log.txt",true));
			bw.write(dt + ": " + msg);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	private TaskLogger() {
	}

}
