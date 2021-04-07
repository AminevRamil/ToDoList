package com.epam.starbun.todolist.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @ManyToMany(targetEntity = NoteEntity.class)
  @JoinTable(name = "users_note",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"))
  private List<NoteEntity> noteList = new ArrayList<>();

    private String nickname;
    private String email;
    private String password;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
