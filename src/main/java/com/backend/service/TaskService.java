package com.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.domain.Task;
import com.backend.repository.ITaskDAO;

@Service
public class TaskService implements ITaskService {
	@PersistenceContext	
	private EntityManager entityManager;

	@Autowired
	private ITaskDAO taskDAO;
	
	@Override
	public List<Task> getAllTasks() {
		List<Task> list = new ArrayList<>();
		taskDAO.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Task getTaskById(long id) {
		Task task = taskDAO.findById(id).get();
		return task;
	}

	@Override
	public boolean addTask(Task task) {
		List<Task> list = taskDAO.findAllByTaskName(task.getTaskName());
		if (list.size() > 0) {
			return false;
		} else {
			taskDAO.save(task);
			return true;
		}
	}

	@Override
	public void updateTask(Task task) {
		taskDAO.save(task);
	}

	@Override
	public void deleteTask(int id) {
		taskDAO.delete(getTaskById(id));
	}

	@Override
	public List<Task> getTaskByTaskName(String taskName) {
		List<Task> task = taskDAO.findByTaskName(taskName);
		return task;
	}

	@Override
	public List<Task> getTaskByStartDate(Date date) {
		List<Task> task = taskDAO.findByStartDate(date);
		return task;
	}

	@Override
	public List<Task> getTaskByEndDate(Date date) {
		List<Task> task = taskDAO.findByEndDate(date);
		return task;
	}
	
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Task> findAllByST(String taskName, Date startDate, Date endDate, String status) {
//		return entityManager.createQuery("SELECT t FROM Task t WHERE t.task_name LIKE '%" + taskName + "%' AND t.start_date LIKE '%" + startDate + "%' AND t.end_date LIKE '%" + endDate+ "%' AND t.status LIKE %'" + status + "%'").getResultList();
//	}

	@Override
	public List<Task> findByAllParams(String taskName, Date startDate, Date endDate, String status) {
		List<Task> list = taskDAO.findByAllParams(taskName, startDate, endDate, status);
		return list;
	}

	@Override
	public List<Task> getAllByTaskName(String taskName) {
		List<Task> task = taskDAO.findAllByTaskName(taskName);
		return task;
	}

}
