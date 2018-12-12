package com.uttara.mvc;


import java.util.ArrayList;
import java.util.Scanner;

public class StartApp {

	public static void main(String[] args) {
		String categoryName = null;
		String taskName;
		String taskDes;
		String taskStatus;
		int taskPriority;
		String taskCreDate;
		String taskDueDate;
		String taskTags;
		boolean loader=false;
		TaskService ts = new TaskService();
		TaskLogger log = TaskLogger.getInstance();
		log.log("----------------------------------------------------------------------------------------------------------------------------------------------");
		log.log("In main method -->");
		try
		{
			Scanner sc1 = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			int i=0;
			a:while(i!=5)
			{
				if(!loader)
				{
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");					
					System.out.println("Enter 1 to Create Category");
					System.out.println("Enter 2 to Load Category");
					System.out.println("Enter 3 to Search Category");
					System.out.println("Enter 4 to List Category");
					System.out.println("Enter 5 to Exit");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("\nEnter your choice");
					i = sc1.nextInt();
					log.log("In main method --> At Category menu: ");
					log.log("In main method --> Choice Entered: " + i );
				}
				switch(i)
				{
				case 1:
					System.out.println("Enter the Category Name ");
					categoryName = sc2.nextLine();
					log.log("In main method --> Category name Entered: " + categoryName );
					while(!TaskUtil.validateName(categoryName))
					{
						log.log("In main method --> Wrong Category Name: " + categoryName);
						System.out.println("Not a Valid name. Try again\nEnter the name");
						categoryName=sc2.nextLine();
					}
					while(ts.categoryExists(categoryName) && !loader)
					{
						log.log("In main method --> Already Existing Category Name: " + categoryName);
						System.out.println("Category already exists .Try again\nEnter the name");
						categoryName=sc2.nextLine();
					}
					while(!ts.categoryExists(categoryName) && loader)
					{
						log.log("In main method --> Non Existing Category Name: " + categoryName);
						System.out.println("Category doesn't exist .Try again\nEnter the name");
						categoryName=sc2.nextLine();
					}
					int j=0;
					b:while(j!=6)
					{
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("\nEnter 1 to Add Task");
						System.out.println("Enter 2 to Edit a Task");
						System.out.println("Enter 3 to Remove a Task");
						System.out.println("Enter 4 to List the Tasks");
						System.out.println("Enter 5 to Search a Task");
						System.out.println("Enter 6 to Go Back");
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("\nEnter your choice");	
						log.log("In main method --> At Task Menu: ");
						j = sc1.nextInt();
						log.log("In main method --> Choice entered: " + j);
						switch(j)
						{
						case 1:
							log.log("In main method --> Entered Add Task");
							System.out.println("Enter Task Name");
							taskName = sc2.nextLine();
							log.log("In main method --> Task Name: " + taskName);
							while(!(TaskUtil.validateName(taskName)))
							{
								log.log("In main method --> Wrong task Name: " + taskName);
								System.out.println("Not a valid name.Try again\nEnter the name");
								taskName=sc2.nextLine();
							}

							while(ts.taskExists(categoryName,taskName))
							{
								log.log("In main method --> Already Existing Task :" + taskName);
								System.out.println("Task already Exists under category "+categoryName+" Try again\nEnter the name");
								taskName=sc2.nextLine();
							}

							System.out.println("Enter Task Description");
							taskDes = sc2.nextLine();
							log.log("In main method --> Description Entered: " + taskDes);
							while(TaskUtil.isNull(taskDes))
							{
								log.log("In main method --> Description Entered is null: " + taskDes);
								System.out.println("Please Enter Task Description");
								taskDes = sc2.nextLine();
							}
							taskDes = TaskUtil.formatter(taskDes);
							log.log("In main method --> Formatted Description: " + taskDes);

							System.out.println("Enter the Status of the Task. (Pending or Finished)");
							taskStatus = sc2.nextLine();
							log.log("In main method --> Status Entered: " + taskStatus);
							while(!(TaskUtil.validateStatus(taskStatus)) || TaskUtil.isNull(taskStatus))
							{
								log.log("In main method --> Wrong Status Entered: " + taskStatus);
								System.out.println("Only Pending or Finished is allowed\n Try Again");
								taskStatus = sc2.nextLine();
							}

							System.out.println("Enter the Priority of the Task. 1(highest) 10(lowest)");
							taskPriority = sc1.nextInt();
							log.log("In main method --> Priority Entered: " + taskPriority);
							while(!(TaskUtil.validatePriority(taskPriority)))
							{
								log.log("In main method --> Wrong Priority Entered: " + taskPriority);
								System.out.println("Enter priority between 1 to 10");
								taskPriority = sc1.nextInt();
							}
							taskCreDate = TaskUtil.currentTime();
							log.log("In main method --> Task Creation Time : " + taskCreDate);
							System.out.println("Enter the Due Date in the format DD/MM/YYYY");
							taskDueDate = sc2.nextLine();
							log.log("In main method --> Due Date Entered: " + taskDueDate);
							while(!(TaskUtil.dateValidation(taskDueDate)) || TaskUtil.isNull(taskDueDate))
							{
								log.log("In main method --> Wrong Due Date Entered: " + taskDueDate);
								System.out.println("Wrong format\nEnter the due date in the format DD/MM/YYYY");
								taskDueDate = sc2.nextLine();
							}

							System.out.println("Enter ,(comma) Seperated Tags");
							taskTags = sc2.nextLine();
							log.log("In main method --> Tags Entered: " + taskTags);
							while(TaskUtil.isNull(taskTags))
							{
								log.log("In main method --> Wrong Tags Entered: " + taskTags);
								System.out.println("Enter ,(comma) Seperated Tags");
								taskTags = sc2.nextLine();
							}
							taskTags = TaskUtil.formatter(taskTags);
							log.log("In main method --> Formatted Tags: " + taskTags);

							TaskBean t = new TaskBean(taskName,taskDes,taskStatus,taskPriority,taskCreDate,taskDueDate,taskTags);
							
							String s = ts.createTask(t,categoryName);
							System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							System.out.println(s);
							log.log("In main method --> Task Status: " + s);
							System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							break;
						case 2:
							log.log("In main method --> Entered Task Editer");
							System.out.println("\nEnter the Name of the Task which you would like to Edit");
							taskName = sc2.nextLine();
							log.log("In main method --> Task Name Entered: " + taskName);
							while(TaskUtil.isNull(taskName))
							{
								log.log("In main method --> Wrong Task Name Entered: " + taskName);
								System.out.println("\nEnter a Valid Name of the Task which you would like to Edit");
								taskName = sc2.nextLine();	
							}
							while(!ts.taskExists(categoryName, taskName))
							{
								log.log("In main method --> Non-Existing Task Name Entered: " + taskName);
								System.out.println("Task doesn't exist\nEnter the name of the task which you would like to edit");
								taskName = sc2.nextLine();
							}
							TaskBean t1 = ts.getTask(categoryName,taskName);
							int l=0;
							d:while(l!=7)
							{
								log.log("In main method --> At Editer Menu");
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								System.out.println("\nPress 1 to Edit Name");
								System.out.println("Press 2 to Edit Description");
								System.out.println("Press 3 to Edit Status");
								System.out.println("Press 4 to Edit Priority");
								System.out.println("Press 5 to Edit Due Date");
								System.out.println("Press 6 to Edit Tags");
								System.out.println("Press 7 to Go Back");
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								l = sc1.nextInt();
								System.out.println("------------------------------------------------------------------------------------------");
								switch(l)
								{
								case 1:
									log.log("In main method --> At edit Name ");
									System.out.println("\nEnter the new Task Name");
									String oldTaskName = taskName;
									taskName = sc2.nextLine();
									log.log("In main method --> New Task Name Entered: " + taskName);
									while(!(TaskUtil.validateName(taskName)) || TaskUtil.isNull(taskName))
									{
										log.log("In main method -->Wrong New Task Name Entered: " + taskName);
										System.out.println("Not a Valid Name.Try again\nEnter the name");
										taskName=sc2.nextLine();
									}
									while(ts.taskExists(categoryName,taskName))
									{
										log.log("In main method --> New Task Name Already Exists: " + taskName);
										System.out.println("Task already Exists under category "+categoryName+" Try again\nEnter the name");
										taskName=sc2.nextLine();
									}
									t1.taskName = taskName;
									ts.removeTask(categoryName, oldTaskName);
									ts.createTask(t1, categoryName);
									System.out.println("Task Name Edited Successfully");
									log.log("In main method --> Task Name Edited");
									break;
								case 2:
									log.log("In main method --> Editing Task Description");
									System.out.println("\nEnter the new Task Description");
									taskDes = sc2.nextLine();
									log.log("In main method --> New Task Description Entered: " + taskDes);
									while(TaskUtil.isNull(taskDes))
									{
										log.log("In main method --> Wrong New Task Name Entered: " + taskDes);
										System.out.println("\nEnter a Valid new Task Description");
										taskDes = sc2.nextLine();
									}
									taskDes = TaskUtil.formatter(taskDes);
									log.log("In main method --> Formatted Description: " + taskDes);
									t1.taskDes = taskDes;
									ts.removeTask(categoryName, taskName);
									ts.createTask(t1, categoryName);
									System.out.println("Description Successfully Edited");
									log.log("In main method --> Description Successfully Edited");
									break;
								case 3:
									log.log("In main method --> Editing Task Status");
									System.out.println("\nEnter the new Task Status. Pending or Finished");
									taskStatus = sc2.nextLine();
									log.log("In main method --> New Task Status " + taskStatus);
									while(!(TaskUtil.validateStatus(taskStatus)) || TaskUtil.isNull(taskStatus))
									{
										log.log("In main method --> Wrong New Task Status " + taskStatus);
										System.out.println("Only Pending or Finished is allowed\n Try Again");
										taskStatus = sc2.nextLine();
									}
									t1.taskStatus = taskStatus;
									ts.removeTask(categoryName, taskName);
									ts.createTask(t1, categoryName);
									System.out.println("Status Edited Successfully");
									log.log("In main method --> Status Edited Successfully");
									break;
								case 4:
									log.log("In main method --> Editing Priority");
									System.out.println("\nEnter the new priority 1(highest) 10(lowest)");
									taskPriority = sc1.nextInt();
									log.log("In main method --> New Priority Entered: "+taskPriority);
									while(!(TaskUtil.validatePriority(taskPriority)))
									{
										log.log("In main method --> New Wrong Priority Entered: "+taskPriority);
										System.out.println("Enter priority between 1 to 10");
										taskPriority = sc1.nextInt();
									}
									t1.taskPriority = taskPriority;
									ts.removeTask(categoryName, taskName);
									ts.createTask(t1, categoryName);
									log.log("In main method --> Priority Entered Edited Successfully");
									break;
								case 5:
									log.log("In main method --> At Due Date Editer");
									System.out.println("\nEnter the new Due Date in the format DD/MM/YYYY");
									taskDueDate = sc2.nextLine();
									log.log("In main method --> New DueDate Entered: "+taskDueDate);
									while(!(TaskUtil.dateValidation(taskDueDate)))
									{
										log.log("In main method --> Wrong New DueDate Entered: "+taskDueDate);
										System.out.println("Enter the Due Date in the format DD/MM/YYYY");
										taskDueDate = sc2.nextLine();
									}
									t1.taskDueDate = taskDueDate;
									ts.removeTask(categoryName, taskName);
									ts.createTask(t1, categoryName);
									log.log("In main method --> New DueDate Edited Successfully");
									break;
								case 6:
									log.log("In main method --> At Tag Editer");
									System.out.println("\nEnter , Seperated Tags");
									taskTags = sc2.nextLine();
									log.log("In main method --> New Tags Entered: "+taskTags);
									while(TaskUtil.isNull(taskTags))
									{
										log.log("In main method --> New Wrong Tags Entered: "+taskTags);
										System.out.println("\nEnter , Seperated Tags");
										taskTags = sc2.nextLine();
									}
									taskTags = TaskUtil.formatter(taskTags);
									log.log("In main method --> New Formatted Tags: "+taskTags);
									t1.taskTags = taskTags;
									ts.removeTask(categoryName, taskName);
									ts.createTask(t1, categoryName);
									log.log("In main method --> New Tags Edited Sccessfully");
									break;
								case 7:
									log.log("In main method --> Exiting from editer");
									continue b;
								default:
									log.log("In main method --> Wrong input entered");
									System.out.println("Wrong input");			
									continue d;
								}
							}
						case 3:
							log.log("In main method --> At Task Remover");
							System.out.println("Enter the Name of the Task to be Removed");
							taskName = sc2.nextLine();
							log.log("In main method --> Task Name Entered: " + taskName);
							while(TaskUtil.isNull(taskName))
							{
								log.log("In main method --> Wrong Task Name Entered: " + taskName);
								System.out.println("Enter a Correct Name of the Task to be Removed");
								taskName = sc2.nextLine();
							}
							while(!ts.taskExists(categoryName, taskName))
							{
								log.log("In main method --> Non- Existing Task Name Entered: " + taskName);
								System.out.println("Task doesn't Exist\n Enter the task name");
								taskName = sc2.nextLine();
							}
							String s1 = ts.removeTask(categoryName, taskName);
							System.out.println("------------------------------------------------------------------------------------------");
							System.out.println(s1);
							log.log("In main method --> " + s1);
							System.out.println("------------------------------------------------------------------------------------------");
							break;
						case 4:
							log.log("In main method --> At Task Lister");
							int m=0;
							while(m!=5)
							{
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								System.out.println("\nEnter 1 to List Tasks in Alphabetical Order");
								System.out.println("Enter 2 to Sort Task based on Due Date");
								System.out.println("Enter 3 to Sort Task based on Created Date");
								System.out.println("Enter 4 to List Tasks based on Longest Time");
								System.out.println("Enter 5 to Go Back");
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								m = sc1.nextInt();
								log.log("In main method --> Selected Task Lister Option" + m);
								ArrayList<TaskBean> ar = ts.taskSorter(categoryName,m);
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								for(TaskBean a:ar)
								{
									log.log("In main method --> Task Lister Displayed");
									System.out.println("Task Name: " + a.taskName+ ": Task Description: " + a.taskDes + ": Priority: " + a.taskPriority + ": Ststus: " + a.taskStatus + ": Creation Date: " + a.taskCreDate + ": Due Date: " + a.taskDueDate + ": Tags " + a.taskTags);
								}
								System.out.println("------------------------------------------------------------------------------------------");
							}
							break;
						case 5:
							log.log("In main method --> At Task Searcher");
							System.out.println("Enter the Keyword for Searching");
							String sq= sc2.nextLine();
							log.log("In main method --> Task Search name: " + sq);
							while(TaskUtil.isNull(sq))
							{
								log.log("In main method --> Null Task Search name: " + sq);
								System.out.println("Enter the Keyword for Searching");
								sq= sc2.nextLine();
							}
							ArrayList<TaskBean> x = ts.taskSearcher(categoryName,sq);
							int name=0,description=0,status=0,tags=0;
							for(TaskBean a:x)
							{
								if (a.taskName.toLowerCase().contains(sq.toLowerCase()))
									name++;
								if (a.taskDes.toLowerCase().contains(sq.toLowerCase()))
									description++;	
								if (a.taskStatus.toLowerCase().contains(sq.toLowerCase()))
									status++;
								if (a.taskTags.toLowerCase().contains(sq))
									tags++;
							}
							System.out.println("------------------------------------------------------------------------------------------");
							System.out.println("Total Number of Occurances " +(name+description+status+tags) );
							log.log("In main method --> Task Search--> " + "Total Number of Occurances " +(name+description+status+tags));
							System.out.println("------------------------------------------------------------------------------------------");
							System.out.println("Number of Occurrances in Name: " + name);
							log.log("In main method --> Task Search--> " + "Number of Occurrances in Name: " + name);
							for(TaskBean a:x)
							{
								if(a.taskName.toLowerCase().contains(sq.toLowerCase()))
									System.out.println("Task Name: " + a.taskName+ " Task Description: " + a.taskDes);
							}
							System.out.println("------------------------------------------------------------------------------------------");
							System.out.println("Number of Occurances in Description: " + description);
							for(TaskBean a:x)
							{
								if(a.taskDes.toLowerCase().contains(sq.toLowerCase()))
									System.out.println("Task Name: " + a.taskName+ " Task Description: " + a.taskDes);
							}
							System.out.println("------------------------------------------------------------------------------------------");
							System.out.println("Number of Occurances in Status: " + status);
							for(TaskBean a:x)
							{
								if(a.taskStatus.toLowerCase().contains(sq.toLowerCase()))
									System.out.println("Task Name: " + a.taskName+ " Task Description: " + a.taskDes);
							}
							System.out.println("------------------------------------------------------------------------------------------");
							System.out.println("Number of Occurances in Tags: " + tags);
							for(TaskBean a:x)
							{
								if(a.taskTags.toLowerCase().contains(sq.toLowerCase()))
									System.out.println("Task Name: " + a.taskName+ " Task Description: " + a.taskDes);
							}
							System.out.println("------------------------------------------------------------------------------------------");
							log.log("In main method --> Task Search Ended");
							break;
						case 6:
							log.log("In main method --> Back to Category Menu");
							loader = false;
							continue a;
						default:
							log.log("In main method --> Wrong input Entered");
							System.out.println("Wrong input");
							continue;
						}
					}
					break;
				case 2:
					log.log("In main method --> At category loader");
					i=1;
					loader = true;
					continue a;
				case 3:
					log.log("In main method --> At Category Searcher");
					System.out.println("\nEnter the Keyword to Search");
					String temp = sc2.nextLine();
					log.log("In main method --> Keyword Entered for Searching: " + temp);
					while(TaskUtil.isNull(temp))
					{
						log.log("In main method -->Wrong Keyword Entered for Searching: " + temp);
						System.out.println("\nEnter the Keyword to Search");
						temp = sc2.nextLine();
					}
					ArrayList<String> search = ts.categorySearcher(temp);
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Number of Occurances: " + search.size());
					log.log("In main method --> Searching: " + "Number of Occurances: " + search.size());
					if(search.size()>0)
					{
						System.out.println("Search Results");
						for(String a:search)
						{
							log.log("In main method --> Search Result: " + a);
							System.out.println(a);
						}
					}
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					break;
				case 4:
					log.log("In main method --> At Category Lister: ");
					ArrayList<String> a = ts.categoryLister();
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					for(String k:a)
					{
						log.log("In main method --> At Category Lister: List: " + k);
						System.out.println(k);
					}
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					break;
				case 5:
					log.log("In main method --> Exiting");
					System.out.println("Exiting");
					continue a;
				default:
					log.log("In main method --> Wrong Input");
					System.out.println("Wrong Input");
					continue a;
				}
			}
		}
		catch(Throwable a)
		{
			log.log("In main method --> Exception" + a.getMessage());
			a.printStackTrace();
		}
	}
}