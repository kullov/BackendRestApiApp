package com.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.backend.domain.Task;

public interface ITaskService {
	Page<Task> getAllTasks(int page, int limit);

	Task getTaskById(long id);

	Page<Task> getAllByTaskName(String taskName, int page, int limit);

	Page<Task> getTaskByStartDate(Date date, int page, int limit);

	Page<Task> getTaskByEndDate(Date date, int page, int limit);

	boolean addTask(Task task);

	void updateTask(Task task);

	void deleteTask(int id);
	
	Page<Task> findByAllParams(String taskName, Date startDate, Date endDate, String description, int page, int limit);

	Page<Task> findAllByStatus(String status, int page, int limit);

//	List<Task> findAllByST(String taskName, Date startDate, Date endDate, String description);

}
