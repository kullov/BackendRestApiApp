package com.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Page<Task> getAllTasks(int pageN, int limit) {
//		List<Task> list = new ArrayList<>();
//		taskDAO.findAll().forEach(e -> list.add(e));
		// Lấy ra 5 user đầu tiên
        // PageRequest.of(0,5) tương đương với lấy ra page đầu tiên, và mỗi page sẽ có 5 phần tử
		Page<Task> page = taskDAO.findAll(PageRequest.of(pageN, limit));
		return page;
	}

	@Override
	public Task getTaskById(long id) {
		Task task = taskDAO.findById(id).get();
		return task;
	}

	@Override
	public boolean addTask(Task task) {
		List<Task> list = taskDAO.findByTaskName(task.getTaskName());
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
	public Page<Task> getTaskByStartDate(Date date, int page, int limit) {
		Page<Task> tasks = taskDAO.findByStartDate(date, PageRequest.of(page, limit));
		return tasks;
	}

	@Override
	public Page<Task> getTaskByEndDate(Date date, int page, int limit) {
		Page<Task> tasks = taskDAO.findByEndDate(date, PageRequest.of(page, limit));
		return tasks;
	}
	
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Task> findAllByST(String taskName, Date startDate, Date endDate, String status) {
//		return entityManager.createQuery("SELECT t FROM Task t WHERE t.task_name LIKE '%" + taskName + "%' AND t.start_date LIKE '%" + startDate + "%' AND t.end_date LIKE '%" + endDate+ "%' AND t.status LIKE %'" + status + "%'").getResultList();
//	}

	@Override
	public Page<Task> findByAllParams(String taskName, Date startDate, Date endDate, String status, int page, int limit) {
		Page<Task> list = taskDAO.findByAllParams(taskName, startDate, endDate, status, PageRequest.of(page, limit));
		return list;
	}

	@Override
	public Page<Task> getAllByTaskName(String taskName, int page, int limit) {
		Page<Task> task = taskDAO.findAllByTaskName(taskName, PageRequest.of(page, limit));
		return task;
	}

	@Override
	public Page<Task> findAllByStatus(String status, int page, int limit) {
		Page<Task> task = taskDAO.findAllByStatus(status, PageRequest.of(page, limit));
		return task;
	}

}
