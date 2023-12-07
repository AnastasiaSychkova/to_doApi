package com.example.to_do_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "tasks",
        indexes = {
                @Index(name = "task_task_name_indx", columnList = "task_name")
        })
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "task_name")
    private String taskName;
    private String description;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] file;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public Task(String taskName, String description, User author) {
        this.taskName = taskName;
        this.description = description;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", file=" + Arrays.toString(file) +
                ", author=" + author +
                '}';
    }
}
