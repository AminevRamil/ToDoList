package com.epam.starbun.todolist.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "note")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AttachmentEntity.class)
    @JoinColumn(name = "note_id")
    private List<AttachmentEntity> attachmentList;

    @ManyToMany(targetEntity = UserEntity.class, mappedBy = "noteList")
    private List<UserEntity> userEntityList = new ArrayList<>();

    private String title;
    private String body;
    private OffsetDateTime endDate;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
