package com.epam.starbun.todolist.domain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "users")
public class User {
    @Id
    private UUID id;


    @OneToMany(targetEntity = Note.class, orphanRemoval = false)
    @JoinTable(name = "users_note",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"))
    private List<Note> noteList;

    private String nickname;
    private String email;
    private String password;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
