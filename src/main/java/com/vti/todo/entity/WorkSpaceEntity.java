package com.vti.todo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "work_spaces")
public class WorkSpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private AccountEntity account;

}
