<#import "ui.ftl" as ui/>
<#import "spring.ftl" as spring/>

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
          <a class="nav-link active" aria-current="page" href="/main"><@spring.message 'navbar.main'/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/new-note"><@spring.message 'navbar.new-note'/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/my-notes"><@spring.message 'navbar.my-notes'/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/setting"><@spring.message 'navbar.settings'/></a>
        </li>
      </ul>
      <p><@spring.message 'navbar.user'/> ${currentUser!"N/A"}</p>
      <a class="btn btn-outline-success" href="/logout"><@spring.message 'navbar.logout'/></a>
    </div>
  </div>
</nav>
</body>
