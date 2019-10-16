package com.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.domain.Task;

@Repository
public interface ITaskDAO extends JpaRepository<Task, Long> {
	
	public Page<Task> findAll(Pageable pageable);
	
	public List<Task> findByTaskName(String name);
	 
    public Page<Task> findByStartDate(Date date, Pageable pageable);
    
    public Page<Task> findByEndDate(Date date, Pageable pageable);
    
    @Query("SELECT t FROM Task t WHERE " + "t.status LIKE %:status%")
    public Page<Task> findAllByStatus(@Param("status") String status, Pageable pageable);
    
    @Query("SELECT t FROM Task t WHERE " + "t.taskName LIKE %:taskName%")
    public Page<Task> findAllByTaskName(@Param("taskName") String taskName, Pageable pageable);
    
    @Query("SELECT t FROM Task t WHERE " + "t.taskName LIKE %:taskName% AND " + "t.startDate LIKE :startDate AND " + "t.endDate LIKE :endDate AND " + "t.status LIKE :status")
    public Page<Task> findByAllParams(@Param("taskName") String taskName, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("status") String status, Pageable pageable);
}
