<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

  <title>Login page</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

<div class="container"></div>
<div>
  <div class="container">
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <div class="row">
          <div class="col">
            <legend>Регистрация</legend>
            <div class="mb-3">
              <label for="exampleFormControlInput1" class="form-label">Никнейм/Логин</label>
              <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Name">
            </div>
            <div class="mb-3">
              <label for="exampleFormControlInput1" class="form-label">Адрес электронной почты</label>
              <input type="email" class="form-control" id="exampleFormControlInput2" placeholder="name@example.com">
            </div>
            <div class="mb-3">
              <label for="exampleFormControlInput1" class="form-label">Пароль</label>
              <input type="password" class="form-control" id="exampleFormControlInput3">
            </div>
          </div>
          <div class="col">
            <legend>Авторизация</legend>
            <div class="mb-3">
              <label for="exampleFormControlInput1" class="form-label">Никнейм/Логин</label>
              <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Name">
            </div>
            <div class="mb-3">
              <label for="exampleFormControlInput1" class="form-label">Пароль</label>
              <input type="password" class="form-control" id="exampleFormControlInput3">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <div class="d-grid">
              <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </div>
          </div>
          <div class="col">
            <div class="d-grid">
              <button formmethod="post" formaction="" type="submit" class="btn btn-primary">Вход</button>
            </div>
          </div>

        </div>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</div>
</body>