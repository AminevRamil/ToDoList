package com.epam.starbun.todolist.domain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

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
    @JoinTable(name = "users_note",
        joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> userList;

    private String title;
    private String body;
    private OffsetDateTime endDate;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
