<#import "ui.ftl" as ui/>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">ToDoList</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/main">Главная</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/new-note">Новая заметка</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/my-notes">Мои заметки</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/setting">Настройки</a>
        </li>
      </ul>
      <p>Current User: ${currentUser!"N/A"}</p>
      <a class="btn btn-outline-success" href="/logout">Выход</a>
    </div>
  </div>
</nav>
</body>
