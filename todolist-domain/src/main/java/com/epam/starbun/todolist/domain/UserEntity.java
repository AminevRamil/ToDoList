package com.epam.starbun.todolist.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(targetEntity = NoteEntity.class, orphanRemoval = false)
    @JoinTable(name = "users_note",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"))
    private List<NoteEntity> noteList;

    private String nickname;
    private String email;
    private String password;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
