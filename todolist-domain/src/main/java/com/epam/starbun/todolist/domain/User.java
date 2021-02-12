package com.epam.starbun.todolist.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private UUID id;


    @OneToMany(targetEntity = Note.class, orphanRemoval = false)
    @JoinTable(name = "user_note",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"))
    private List<Note> noteList;

    private String nickname;
    private String email;
    private String password;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
