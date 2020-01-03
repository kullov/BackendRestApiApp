package com.backend.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.backend.domain.AppRole;
import com.backend.domain.AppUser;
import com.backend.domain.Task;
import com.backend.repository.IRoleDAO;
import com.backend.repository.ITaskDAO;
import com.backend.repository.IUserDAO;
import com.backend.service.IUserService;

@Component
public class DataInit implements ApplicationRunner {
	private ITaskDAO taskDAO;
	private IUserDAO userDAO;
	private IRoleDAO roleDAO;
	
	@Autowired
	IUserService userService;
	
	private static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
    public DataInit(ITaskDAO taskDAO, IUserDAO userDAO, IRoleDAO roleDAO) {
        this.taskDAO = taskDAO;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = taskDAO.count();
		long countUser = userDAO.count();
		long countRole = roleDAO.count();
		String description1, description2;
		if (countRole == 0) {
			AppRole userRole = new AppRole();
			userRole.setRoleName("USER");
			roleDAO.save(userRole);
			AppRole adminRole = new AppRole();
			adminRole.setRoleName("ADMIN");
			roleDAO.save(adminRole);
		}
//		System.out.println(roleDAO.findAll());
		if (countUser == 0) {
			AppUser user = new AppUser();
			user.setUserName("admin");
			user.setPassword("123456");
			user.setEmail("abc@gmail.com");
			boolean a = userService.addUser(user, "ADMIN");
			
			AppUser user2 = new AppUser();
			user2.setUserName("nga123");
			user2.setPassword("123456");
			boolean b = userService.addUser(user2, "USER");
			
			AppUser user3 = new AppUser();
			user3.setUserName("user");
			user3.setPassword("123456");
			boolean c = userService.addUser(user3, "USER");
		}
		
        if (count == 0) {
            Task p1 = new Task();
            p1.setTaskName("Learn English");
            Date d11 = df.parse("20-11-2019");
            p1.setStartDate(d11);
            Date d21 = df.parse("23-11-2019");
            p1.setEndDate(d21);
            description1 = "In-progress";
            p1.setStatus(description1);
            //
            Task p2 = new Task();
            p2.setTaskName("Learn Chinese");
            Date d12 = df.parse("21-11-2019");
            p2.setStartDate(d12);
            Date d22 = df.parse("24-11-2019");
            p2.setEndDate(d22);
            description2 = "Pending";
            p2.setStatus(description2);
            
            Task p3 = new Task();
            p3.setTaskName("Learn VueJS");
            Date d13 = df.parse("26-01-2019");
            p3.setStartDate(d13);
            Date d23 = df.parse("29-01-2019");
            p3.setEndDate(d23);
            description2 = "Done";
            p3.setStatus(description2);
            
            Task p4 = new Task();
            p4.setTaskName("Learn Java");
            Date d14 = df.parse("02-10-2019");
            p4.setStartDate(d14);
            Date d24 = df.parse("04-10-2019");
            p4.setEndDate(d24);
            description2 = "In-progress";
            p4.setStatus(description2);
            
            Task p5 = new Task();
            p5.setTaskName("Learn Spring");
            Date d15 = df.parse("19-12-2019");
            p5.setStartDate(d12);
            Date d25 = df.parse("21-12-2019");
            p5.setEndDate(d25);
            description2 = "Delay";
            p5.setStatus(description2);
            
            Task p6 = new Task();
            p6.setTaskName("Learn Spring");
            Date d16 = df.parse("19-12-2019");
            p6.setStartDate(d16);
            Date d26 = df.parse("21-12-2019");
            p6.setEndDate(d26);
            description2 = "Delay";
            p6.setStatus(description2);
            
            Task p7 = new Task();
            p7.setTaskName("Learn Spring");
            Date d17 = df.parse("19-12-2019");
            p7.setStartDate(d12);
            Date d27 = df.parse("21-12-2019");
            p7.setEndDate(d27);
            description2 = "Delay";
            p7.setStatus(description2);
            
            taskDAO.save(p1);
            taskDAO.save(p3);
            taskDAO.save(p4);
            taskDAO.save(p5);
            taskDAO.save(p6);
            taskDAO.save(p7);
        }
        for (Task task : taskDAO.findAll()) {
			System.out.println(task.toString());
		}
	}

}
