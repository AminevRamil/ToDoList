<#import "ui.ftl" as ui/>
<#include "navbar.ftl">

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

  <title>New Note page</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

<div class="container">
  <div class="col">

  </div>
  <div class="d-flex flex-column">
    <form id="f1" name="saveNote">
        <@ui.formInput id="f1i1" name="title" label="Заголовок"/>
      <label for="f1i2">Заметка</label>
      <textarea id="f1i2" name="body" cols="60" rows="5"></textarea>
      <@ui.formInput id="f1i3" name="endDate" label="Дата завершения" type="date"/>
      <button formaction="/new-note/save" formmethod="post" type="submit" class="btn btn-primary">Сохранить</button>
    </form>
  </div>
  <div class="col">

  </div>
</div>
</body>