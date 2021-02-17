package com.epam.starbun.todolist.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "note")
public class Note {

    @Id
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Attachment.class)
    @JoinColumn(name = "note_id")
    private List<Attachment> attachmentList;

    @OneToMany(targetEntity = User.class, orphanRemoval = true)
    @JoinTable(name = "user_note",
            joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> userList;

    private String title;
    private String body;
    private OffsetDateTime endDate;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
