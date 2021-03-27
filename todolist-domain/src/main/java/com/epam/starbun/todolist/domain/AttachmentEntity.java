package com.epam.starbun.todolist.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "attachment")
public class AttachmentEntity {
    @Id
    private Long id;

    private String filename;

    @Lob
    private Blob blob;

    @Enumerated(EnumType.STRING)
    private LobType type;

    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
