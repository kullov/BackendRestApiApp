package com.backend.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.backend.domain.Task;
import com.backend.repository.ITaskDAO;

@Component
public class DataInit implements ApplicationRunner {
	private ITaskDAO taskDAO;
	private static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
    public DataInit(ITaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		long count = taskDAO.count();
		String description1, description2; 
        if (count == 0) {
            Task p1 = new Task();
            p1.setTaskName("John");
            Date d11 = df.parse("20-11-2019");
            p1.setStartDate(d11);
            Date d21 = df.parse("23-11-2019");
            p1.setEndDate(d21);
            description1 = "Pending";
            p1.setStatus(description1);
            //
            Task p2 = new Task();
            p2.setTaskName("Johnaaaaa");
            Date d12 = df.parse("21-11-2019");
            p2.setStartDate(d12);
            Date d22 = df.parse("24-11-2019");
            p2.setEndDate(d22);
            description2 = "Done";
            p2.setStatus(description2);
            
            taskDAO.save(p1);
            taskDAO.save(p2);
        }
	}

}
