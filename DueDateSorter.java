package com.uttara.mvc;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DueDateSorter implements Comparator<TaskBean> {
	TaskLogger t = TaskLogger.getInstance();
	@Override
	public int compare(TaskBean o1, TaskBean o2) {
		t.log("At Due Date Sorter");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d1 = sdf.parse(o1.taskDueDate);
			Date d2 = sdf.parse(o2.taskDueDate);
			t.log("End of Created Date Sorter");
			return d1.compareTo(d2);
		} catch (ParseException e) {
			t.log("Exception in Created Date Sorter: " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
}
