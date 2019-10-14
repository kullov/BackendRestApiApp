package com.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.domain.Task;

@Repository
public interface ITaskDAO extends CrudRepository<Task, Long> {
	
	public List<Task> findByTaskName(String name);
	 
    public List<Task> findByStartDate(Date date);
    
    public List<Task> findByEndDate(Date date);
    
    @Query("SELECT t FROM Task t WHERE " + "t.status LIKE %:status%")
    public List<Task> findAllByStatus(@Param("status") String status);
    
    @Query("SELECT t FROM Task t WHERE " + "t.taskName LIKE %:taskName%")
    public List<Task> findAllByTaskName(@Param("taskName") String taskName);
    
    @Query("SELECT t FROM Task t WHERE " + "t.taskName LIKE %:taskName% AND " + "t.startDate LIKE :startDate AND " + "t.endDate LIKE :endDate AND " + "t.status LIKE :status")
    public List<Task> findByAllParams(@Param("taskName") String taskName, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("status") String status);
}
