# ToDoList

Для накатки скриптов ликвибейз использовать команду:
mvn liquibase:update -Dliquibase.url="jdbc:postgresql://localhost:5432/todolist" -Dliquibase.changeLogFile="[Absolete path to db.changelog-master.xml]" -Dliquibase.username="postgres" -Dliquibase.password="postgres" -Dliquibase.driver="org.postgresql.Driver" -Pliquibase
