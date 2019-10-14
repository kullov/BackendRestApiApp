package com.backend.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
 
    @Column(name = "task_name", nullable = false)
    private String taskName;
 
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private Date endDate;
    
    @Column(name = "status", nullable = false)
	private String status;
    
    public Task() {
    	
    }
    
    public Task(Long id, String taskName, Date startDate, Date endDate, String status) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
 
}
