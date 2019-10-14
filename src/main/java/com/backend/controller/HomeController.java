package com.backend.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.domain.Task;
import com.backend.service.ITaskService;

@RestController
public class HomeController {
	
	@Autowired
	private ITaskService taskService;
	private static final DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
	
	@GetMapping("api/task/detail/{id}")
	public Task getTaskById(@PathVariable("id") Integer id) {
		Task task = taskService.getTaskById(id);
		return task;
	}
	
	@GetMapping("api/task/start_date/{date}")
	public List<Task> getAllTasksByStartDate(@PathVariable("date") String date) {
		Date d = null;
		try {
			d = DF.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Task> tasks = taskService.getTaskByStartDate(d);
		return tasks;
	}

	@GetMapping("api/task/end_date/{date}")
	public List<Task> getAllTasksByEndDate(@PathVariable("date") String date) {
		Date d = null;
		try {
			d = DF.parse(date);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Task> tasks = taskService.getTaskByEndDate(d);
		return tasks;
	}
	
	@RequestMapping(
			  value = "/api/task/taskName", 
			  method = RequestMethod.GET)
	public List<Task> getByTaskName(@RequestParam(value="taskName", required = false) String taskName) {
		List<Task> tasks = taskService.getTaskByTaskName(taskName);
		return tasks;
	}
	
	@RequestMapping(
			  value = "/api/task/status", 
			  method = RequestMethod.GET)
	public List<Task> getByStatus(@RequestParam(value="status", required = false) String status) {
		List<Task> tasks = taskService.findAllByStatus(status);
		return tasks;
	}
	
	@GetMapping("api/task/all")
	public List<Task> getAllTasks() {
		List<Task> list = taskService.getAllTasks();
		return list;
	}
	
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
	public Task updateArticle(@RequestBody Task task) {
		taskService.updateTask(task);
		return task;
	}
	
	@DeleteMapping("api/task/delete/{id}")
	public void deleteArticle(@PathVariable("id") Integer id) {
		taskService.deleteTask(id);
	}
	
	@RequestMapping(
			  value = "/api/task/findAll", 
			  method = RequestMethod.GET)
//	@PostMapping("/api/task/find")
	@ResponseBody
//	@PostMapping("api/task/findBy/name={taskName}/startDate={startDate}/endDate={endDate}/status={status}")
	public List<Task> findAllBy( @RequestParam(value="taskName", required = false) String taskName,  @RequestParam(value="startDate", required = false) String startDate, @RequestParam(value="endDate", required = false) String endDate, @RequestParam(value="status", required = false) String status) {
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
		List<Task> list = taskService.findByAllParams(taskName, startD, endD, status);
		return list;
	}
 
}
