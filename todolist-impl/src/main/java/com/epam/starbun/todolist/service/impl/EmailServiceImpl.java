package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.service.EmailService;
import com.epam.starbun.todolist.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender emailSender;

  private final NoteService noteService;

  @EventListener(ApplicationReadyEvent.class)
  @Transactional(readOnly = true)
  //TODO оповещение не позже чем за сутки
  public void test() {
    List<NoteEntity> notes = noteService.getAllNotNotifiedNotes().stream()
      .filter(noteEntity -> noteEntity.getEndDate().equals(LocalDate.now().plusDays(1)))
      .peek(this::sendNotification)
      .collect(Collectors.toList());

    System.out.println(notes);
  }

  @SneakyThrows
  public void sendNotification(NoteEntity noteEntity) {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(emailSender.createMimeMessage(), true);
    messageHelper.addTo(noteEntity.getUserEntityList().get(0).getEmail());
//    messageHelper.add
  }

}
