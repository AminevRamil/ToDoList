package com.epam.starbun.todolist.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private UUID id;

    private String nickname;
    private String email;
    private String password;
}
