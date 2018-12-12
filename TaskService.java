package com.uttara.mvc;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class TaskService {
	
	TaskLogger t = TaskLogger.getInstance();
	
	public ArrayList<String> categoryLister()
	{
		t.log("At Task Services --> In category Lister");
		File f = new File("C:\\Task");
		ArrayList<String> a = new ArrayList<>();
		if(f.exists())
		{
			File[] k = f.listFiles();
			for(File i:k)
			{
				if(i.getName().endsWith("todo"))
					a.add(i.getName().substring(0,i.getName().indexOf(".")));
			}
		}
		t.log("At Task Services --> End of category Lister");
		return a;
	}
	
	public ArrayList<String> categorySearcher(String temp)
	{
		t.log("At Task Services --> In category searcher");
		File f = new File("C:\\Task");
		ArrayList<String> a = new ArrayList<>();
		if(f.exists())
		{
			File[] k = f.listFiles();
			for(File i:k)
			{
				if(i.getName().toLowerCase().contains(temp) && i.getName().endsWith("todo"))
					a.add(i.getName().substring(0,i.getName().indexOf(".todo")));
			}
		}
		t.log("At Task Services --> End of category searcher");
		return a;
		
	}
	
	public ArrayList<TaskBean> taskSearcher(String categoryName,String sq)
	{
		t.log("At Task Services --> In task searcher");
		BufferedReader br=null;
		ArrayList<TaskBean> a = new ArrayList<>(); 
		try
		{
			br = new BufferedReader(new FileReader("C:\\Task\\" +categoryName+".todo"));
			String s;
			while((s=br.readLine())!=null)
			{
				if(s.toLowerCase().contains(sq.toLowerCase()))
				{
					String[] s1 = s.split(":");
					int i = Integer.parseInt(s1[3]);
					TaskBean n = new TaskBean(s1[0], s1[1], s1[2], i, s1[4], s1[5], s1[6]);
					a.add(n);
				}
			}
		}
		catch(IOException o)
		{
			o.printStackTrace();
		}
		finally
		{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		t.log("At Task Services --> End of task Lister");
		return a;
	}
	
	public ArrayList<TaskBean> taskSorter(String categoryName,int m)
	{
		t.log("At Task Services --> In task Sorter");
		BufferedReader br=null;
		ArrayList<TaskBean> a = new ArrayList<>(); 
		try
		{
			br = new BufferedReader(new FileReader("C:\\Task\\"+categoryName+".todo"));
			String s;
			while((s=br.readLine())!=null)
			{
				String[] s1 = s.split(":");
				int i = Integer.parseInt(s1[3]);
				TaskBean n = new TaskBean(s1[0], s1[1], s1[2], i, s1[4], s1[5], s1[6]);
				a.add(n);
			}
			if(m==1)
				Collections.sort(a);
			if(m==2)
				Collections.sort(a,new DueDateSorter());
			if(m==3)
				Collections.sort(a,new CreatedDateSorter());
			if(m==4)
				Collections.sort(a,new PendingSorter());
			t.log("At Task Services --> End of Task sorter");
			return a;
		}
		catch(IOException o)
		{
			o.printStackTrace();
		}
		finally
		{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return a;
	}
	
	public boolean categoryExists(String s)
	{
		t.log("At Task Services --> In category Exists?");
		t.log("At Task Services --> End of category Exists?");
		return new File("C:\\Task\\" + s + ".todo").exists();
	}
	
	public boolean taskExists(String category,String taskName)
	{
		t.log("At Task Services --> In Task Exists");
		BufferedReader bi = null;
		try
		{
			if(new File("C:\\Task\\" +category+".todo").exists())
			{
				bi = new BufferedReader(new FileReader("C:\\Task\\" +category+".todo"));
				String line;
				while((line = bi.readLine())!=null)
				{
					if(line.split(":")[0].equals(taskName))
						return true;
				}
			}
			return false;
		}
		catch(IOException o)
		{
			o.printStackTrace();
			return false;
		}
		finally
		{
			if(bi!=null)
				try {
					bi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			t.log("At Task Services --> End of Task Exists?");
		}
	}
	
	public TaskBean getTask(String categoryName,String taskName)
	{
		t.log("At Task Services --> In Get Task");
		BufferedReader br=null;
		TaskBean a = new TaskBean();
		try
		{
			br = new BufferedReader(new FileReader("C:\\Task\\" +categoryName+".todo"));
			String s;
			while((s=br.readLine())!=null)
			{
				if(s.split(":")[0].equals(taskName))
				{
					String[] s1 = s.split(":");
					int i = Integer.parseInt(s1[3]);
					a = new TaskBean(s1[0], s1[1], s1[2], i, s1[4], s1[5], s1[6]);
					return a;
				}
			}
		}
		catch(IOException o)
		{
			o.printStackTrace();
		}
		finally
		{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		t.log("At Task Services --> End of get Tasks");
		return a;
	}
	
	
	public String removeTask(String categoryName,String taskName)
	{
		t.log("At Task Services --> In remove Task");
		BufferedReader br = null;
		BufferedWriter bw = null;
		boolean b = false;
		try
		{
			br = new BufferedReader(new FileReader("C:\\Task\\"+categoryName+".todo"));
			ArrayList<String> a = new ArrayList<>();
			String s;
			while((s=br.readLine())!=null)
			{
				if(!s.split(":")[0].equals(taskName))
				{
					a.add(s);
					b = true;
				}
			}
			br.close();
			br=null;
			bw = new BufferedWriter(new FileWriter("C:\\Task\\"+categoryName+".todo"));
			for(String i:a)
			{
				bw.write(i);
				bw.newLine();
			}
		}
		catch(IOException o)
		{
			o.printStackTrace();
		}
		finally
		{
			if(br!=null)
			{
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bw!=null)
			{
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		t.log("At Task Services --> End of Task Delete");
		if(b)
			return "Task delete successful";
		else 
			return "Task delete failed";
	}

	public String createTask(TaskBean tb,String catName)
	{
		t.log("At Task Services --> Create Task");
		BufferedWriter bw =null;
		try
		{
			bw = new BufferedWriter(new FileWriter("C:\\Task\\"+catName+".todo",true));
			bw.write(tb.taskName+":"+tb.taskDes+":"+tb.taskStatus+":"+tb.taskPriority+":"+tb.taskCreDate+":"+tb.taskDueDate + ":"+tb.taskTags);
			bw.newLine();
			t.log("At Task Services --> Task Added Successfully");
			return "Task addition success";
		}
		catch(IOException o)
		{
			t.log("At Task Services --> Task addition failed");
			return "Task addition failed";
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
	
	static {
		File f = new File("C:\\Task");
		f.mkdir();		
	}
}
