<#import "ui.ftl" as ui/>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

  <title>Users page</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

<div class="container">
  <div>
    <fieldset>
      <legend>Добавить пользователя</legend>
      <form name="user" action="save" method="POST">
          <@ui.formInput id="t1" name="nickname" label="Никнейкм"/> <br/>
          <@ui.formInput id="t2" name="email" label="Е-почта"/> <br/>
          <@ui.formInput id="t3" name="password" label="Пароль"/> <br/>
        <input class="btn btn-primary" type="submit" value="Save"/>
      </form>
    </fieldset>
  </div>

  <div>
    <fieldset>
      <legend>Поиск по нику</legend>
      <form name="searchForm" action="search">
          <@ui.formInput id="t4" name="searchName" label="Поиск"/> <br/>
        <input class="btn btn-primary" type="submit" value="Search" formmethod="POST"/>
        <a href="/" class="btn btn-primary" role="button">Reset</a>
      </form>
      <form name="resetButton" method="GET">
      </form>

    </fieldset>
  </div>
  <p><#if lastSearch??>Поиск для: ${lastSearch}<#else></#if></p>

  <div>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">id</th>
        <th scope="col">nickname</th>
        <th scope="col">password</th>
        <th scope="col">email</th>
      </tr>
      </thead>
      <tbody>
      <#list users as user>
        <tr>
          <td>${user.id}</td>
          <td>${user.nickname}</td>
          <td>${user.password}</td>
          <td>${user.email}</td>
        </tr>
      </#list>
      </tbody>
    </table>
  </div>

</div>
</body>
