package com.epam.starbun.todolist.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "note")
public class NoteEntity {

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AttachmentEntity.class)
    @JoinColumn(name = "note_id")
    private List<AttachmentEntity> attachmentList;

    @OneToMany(targetEntity = UserEntity.class, orphanRemoval = true)
    @JoinTable(name = "users_note",
        joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<UserEntity> userEntityList;

    private String title;
    private String body;
    private OffsetDateTime endDate;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
