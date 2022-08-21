package com.vti.todo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
