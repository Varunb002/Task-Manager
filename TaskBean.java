package com.uttara.mvc;


public class TaskBean implements Comparable<TaskBean> {
	String taskName;
	String taskDes;
	String taskStatus;
	int taskPriority;
	String taskCreDate;
	String taskDueDate;
	String taskTags;
	
	@Override
	public int compareTo(TaskBean o) {
		return this.taskName.compareTo(o.taskName);
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDes() {
		return taskDes;
	}
	public void setTaskDes(String taskDes) {
		this.taskDes = taskDes;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public int getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;
	}
	public String getTaskCreDate() {
		return taskCreDate;
	}
	public void setTaskCreDate(String taskCreDate) {
		this.taskCreDate = taskCreDate;
	}
	public String getTaskDueDate() {
		return taskDueDate;
	}
	public void setTaskDueDate(String taskDueDate) {
		this.taskDueDate = taskDueDate;
	}
	public String getTaskTags() {
		return taskTags;
	}
	public void setTaskTags(String taskTags) {
		this.taskTags = taskTags;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskCreDate == null) ? 0 : taskCreDate.hashCode());
		result = prime * result + ((taskDes == null) ? 0 : taskDes.hashCode());
		result = prime * result + ((taskDueDate == null) ? 0 : taskDueDate.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + taskPriority;
		result = prime * result + ((taskStatus == null) ? 0 : taskStatus.hashCode());
		result = prime * result + ((taskTags == null) ? 0 : taskTags.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		if (taskCreDate == null) {
			if (other.taskCreDate != null)
				return false;
		} else if (!taskCreDate.equals(other.taskCreDate))
			return false;
		if (taskDes == null) {
			if (other.taskDes != null)
				return false;
		} else if (!taskDes.equals(other.taskDes))
			return false;
		if (taskDueDate == null) {
			if (other.taskDueDate != null)
				return false;
		} else if (!taskDueDate.equals(other.taskDueDate))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (taskPriority != other.taskPriority)
			return false;
		if (taskStatus == null) {
			if (other.taskStatus != null)
				return false;
		} else if (!taskStatus.equals(other.taskStatus))
			return false;
		if (taskTags == null) {
			if (other.taskTags != null)
				return false;
		} else if (!taskTags.equals(other.taskTags))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TaskBean [taskName=" + taskName + ", taskDes=" + taskDes + ", taskStatus=" + taskStatus
				+ ", taskPriority=" + taskPriority + ", taskCreDate=" + taskCreDate + ", taskDueDate=" + taskDueDate
				+ ", taskTags=" + taskTags + "]";
	}
	public TaskBean(String taskName, String taskDes, String taskStatus, int taskPriority, String taskCreDate,
			String taskDueDate, String taskTags) {
		super();
		this.taskName = taskName;
		this.taskDes = taskDes;
		this.taskStatus = taskStatus;
		this.taskPriority = taskPriority;
		this.taskCreDate = taskCreDate;
		this.taskDueDate = taskDueDate;
		this.taskTags = taskTags;
	}
	public TaskBean() {
		super();
	}
}
