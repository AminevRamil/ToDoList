package com.epam.starbun.todolist.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    private String filename;

    @Lob
    private Blob blob;

    @Enumerated(EnumType.STRING)
    private LobType type;

    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
