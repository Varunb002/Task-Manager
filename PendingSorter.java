package com.uttara.mvc;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class PendingSorter implements Comparator<TaskBean> {
	
	TaskLogger t = TaskLogger.getInstance();
	@Override
	public int compare(TaskBean o1, TaskBean o2) {
		t.log("At Pending Date Sorter");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			Date d1 = sdf.parse(o1.taskDueDate);
			Date d2 = sdf.parse(o2.taskDueDate);
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy_HH;mm;ss");
			Date d3 = sdf2.parse(o1.taskCreDate);
			Date d4 = sdf2.parse(o2.taskCreDate);
			t.log("End of Pending Date Sorter");
			return (int) -((d1.getTime()-d3.getTime())-(d2.getTime()-d4.getTime()));
		}
		catch (ParseException e) {
			t.log("Exception in Pending Date Sorter: " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
}
