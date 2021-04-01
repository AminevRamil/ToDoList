<#import "ui.ftl" as ui/>

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

<div class="container">
  <div class="row">
    <div class="col-md-3">
      <#if violations??>
        <#list violations as violation>
          <p>${violation}</p>
        </#list>
      <#else></#if>
    </div>
    <div class="col-md-6">
      <div class="row">
        <div class="col">
          <legend>Регистрация</legend>
          <form id="regForm" name="user">
            <div class="mb-3">
                <@ui.formInput id="a1" name="nickname" label="Никнейкм/Логин"/><br/>
            </div>
            <div class="mb-3">
                <@ui.formInput id="a2" name="email" label="Адрес электронной почты"/> <br/>
            </div>
            <div class="mb-3">
                <@ui.formInput id="a3" name="password" label="Пароль" type="password"/> <br/>
            </div>
          </form>
        </div>
        <div class="col">
          <legend>Авторизация</legend>
          <form id="authForm" name="authData">
            <div class="mb-3">
                <@ui.formInput id="a4" name="login" label="Никнейкм/Логин"/><br/>
            </div>
            <div class="mb-3">
                <@ui.formInput id="a5" name="password" label="Пароль" type="password"/> <br/>
            </div>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="d-grid">
            <button form="regForm" formaction="/login/save" formmethod="post" type="submit" class="btn btn-primary">
              Зарегистрироваться
            </button>
          </div>
            <#if result??>
            <p>${result}</p>
          <#else></#if>
        </div>
        <div class="col">
          <div class="d-grid">
            <button form="authForm" formaction="/login/logon" formmethod="post" type="submit" class="btn btn-primary">
              Вход
            </button>
          </div>
        </div>

      </div>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>


<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</body>