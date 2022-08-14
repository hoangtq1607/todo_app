package com.vti.todo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "task_status")
public class TaskStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
