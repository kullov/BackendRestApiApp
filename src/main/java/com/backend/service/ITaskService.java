package com.backend.service;

import java.util.Date;
import java.util.List;

import com.backend.domain.Task;

public interface ITaskService {
	List<Task> getAllTasks();

	Task getTaskById(long id);
	
	List<Task> getTaskByTaskName(String taskName);

	List<Task> getAllByTaskName(String taskName);

	List<Task> getTaskByStartDate(Date date);

	List<Task> getTaskByEndDate(Date date);

	boolean addTask(Task task);

	void updateTask(Task task);

	void deleteTask(int id);
	
	List<Task> findByAllParams(String taskName, Date startDate, Date endDate, String description);

//	List<Task> findAllByST(String taskName, Date startDate, Date endDate, String description);

}
