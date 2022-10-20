package com.epam.starbun.todolist.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ConditionalOnProperty(prefix = "app", name = "emailer", havingValue = "true")
public class EmailNotifyConfig {

}
