package com.epam.starbun.todolist.domain;

import lombok.Data;
import lombok.ToString.Exclude;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "note")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Exclude
    @ManyToMany(targetEntity = UserEntity.class)
    @JoinTable(name = "users_note",
        joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<UserEntity> userEntityList = new ArrayList<>();

    private String title;
    private String body;
    private LocalDate endDate;
    private Boolean isActive;
    private Boolean isNotified;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
