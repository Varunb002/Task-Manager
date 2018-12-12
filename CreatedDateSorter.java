package com.uttara.mvc;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class CreatedDateSorter implements Comparator<TaskBean> {
	TaskLogger l = TaskLogger.getInstance();
	
	@Override
	public int compare(TaskBean o1, TaskBean o2) {
		l.log("At Created Date Sorter");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_HH;mm;ss");
		try {
			Date d1 = sdf.parse(o1.taskCreDate);
			Date d2 = sdf.parse(o2.taskCreDate);
			l.log("End of Created Date Sorter");
			return d1.compareTo(d2);
		} catch (ParseException e) {
			l.log("Exception in Created Date Sorter: " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
}
