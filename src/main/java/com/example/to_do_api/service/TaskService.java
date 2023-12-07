package com.example.to_do_api.service;

import com.example.to_do_api.dto.CreateTaskDto;
import com.example.to_do_api.dto.UpdateTaskDto;
import com.example.to_do_api.entity.Task;
import com.example.to_do_api.entity.User;
import com.example.to_do_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public Task addTask(CreateTaskDto createOrUpdateTaskDto, String mail, MultipartFile file) throws IOException {
        User user = userService.getUserByMail(mail);

        Task task = new Task(createOrUpdateTaskDto.getTaskName(), createOrUpdateTaskDto.getDescription(), user);
        if (file != null && !file.isEmpty()) {
            task.setFile(file.getBytes());
        }
        return taskRepository.save(task);
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }
    public List<Task> getTask(String taskName) {
        return taskRepository.findAllByTaskNameLike(taskName);
    }
    public Task getTask(Long id) throws IllegalAccessException {
        Task task = taskRepository.findTaskById(id);
        if(task != null){
            return task;
        } else {
            throw new IllegalAccessException("task not found");
        }
    }

    public Task updateTask(UpdateTaskDto updateTaskDto) throws IOException, IllegalAccessException {
        Optional<Task> taskOptional = taskRepository.findById(updateTaskDto.getId());

        if(taskOptional.isEmpty()){
            throw new IllegalAccessException("task not found");
        } else {
            Task task = taskOptional.get();
            if (updateTaskDto.getFile() != null && !updateTaskDto.getFile().isEmpty()) {
                task.setFile(updateTaskDto.getFile().getBytes());
            }
            if (updateTaskDto.getTaskName() != null) {
                task.setTaskName(updateTaskDto.getTaskName());
            }
            if (updateTaskDto.getDescription() != null) {
                task.setDescription(updateTaskDto.getDescription());
            }

            return taskRepository.save(task);
        }
    }

    public boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }

}
