package com.backend.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.domain.Task;
import com.backend.service.ITaskService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TaskController {
	
	@Autowired
	private ITaskService taskService;
	private static final DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
	
	@GetMapping("api/task/detail/{id}")
	public Task getTaskById(@PathVariable("id") Integer id) {
		Task task = taskService.getTaskById(id);
		return task;
	}
	
	@GetMapping("api/task/start_date")
	public Page<Task> getAllTasksByStartDate(@RequestParam(value="startDate") String date, @RequestParam(value="page") int page) {
		Date d = null;
		try {
			d = DF.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Page<Task> tasks = taskService.getTaskByStartDate(d, page-1, 5);
		return tasks;
	}

	@GetMapping("api/task/end_date")
	public Page<Task> getAllTasksByEndDate(@RequestParam(value="endDate") String date, @RequestParam(value="page") int page) {
		Date d = null;
		try {
			d = DF.parse(date);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Page<Task> tasks = taskService.getTaskByEndDate(d, page-1, 5);
		return tasks;
	}
	
	@GetMapping("/api/task/taskName")
	public Page<Task> getByTaskName(@RequestParam(value="taskName", required = false) String taskName, @RequestParam(value="page") int page) {
		Page<Task> tasks = taskService.getAllByTaskName(taskName, page-1, 5);
		return tasks;
	}
	
	@GetMapping("/api/task/status")
	public Page<Task> getByStatus(@RequestParam(value="status", required = false) String status, @RequestParam(value="page") int page) {
		Page<Task> tasks = taskService.findAllByStatus(status, page-1, 5);
		return tasks;
	}
	
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("api/task/all")
	public Page<Task> getAllTasks(@RequestParam(value="page") int page) {
		Page<Task> list = taskService.getAllTasks(page-1, 5);
		return list;
	}
	
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping("api/task/create")
	public Task addTask( @RequestBody Task task, UriComponentsBuilder builder) {
		boolean flag = taskService.addTask(task);
		if (flag == true) {
			return task;
		} else {
			return null;
		}
	}
	
	@PutMapping("api/task/update/{id}")
	public Task updateTask(@RequestBody Task task) {
		taskService.updateTask(task);
		return task;
	}
	
	@DeleteMapping("api/task/delete/{id}")
	public void deleteTask(@PathVariable("id") Integer id) {
		taskService.deleteTask(id);
	}
	
	@GetMapping("/api/task/findAll")
	public Page<Task> findAllBy( @RequestParam(value="taskName", required = false) String taskName,  @RequestParam(value="startDate", required = false) String startDate, @RequestParam(value="endDate", required = false) String endDate, @RequestParam(value="status", required = false) String status, @RequestParam(value="page") int page) {
		Date startD = null;
		Date endD = null;
		try {
			if (startDate != "") {
				startD = DF.parse(startDate);
			}
			if (endDate != "") {
				endD = DF.parse(endDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Page<Task> list = taskService.findByAllParams(taskName, startD, endD, status, page-1, 5);
		return list;
	}
 
}
