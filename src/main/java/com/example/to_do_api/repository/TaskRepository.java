package com.example.to_do_api.repository;

import com.example.to_do_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM tasks WHERE task_name like %:taskName%", nativeQuery = true)
    List<Task> findAllByTaskNameLike(@Param("taskName")String taskName);

    Task findTaskById(Long id);
}
