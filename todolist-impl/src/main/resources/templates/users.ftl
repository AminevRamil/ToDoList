<#import "ui.ftl" as ui/>
<#include "common.ftl"/>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

<div class="container">
  <div>
      <fieldset>
          <legend>Добавить пользователя</legend>
          <form name="user" action="save" method="POST">
              <@ui.formInput id="t1" name="nickname" label="Никнейкм"/> <br/>
              <@ui.formInput id="t2" name="email" label="Е-почта"/> <br/>
              <@ui.formInput id="t3" name="password" label="Пароль"/> <br/>
              <input type="submit" value="Save"/>
          </form>
      </fieldset>
  </div>

  <div>
      <fieldset>
          <legend>Добавить пользователя</legend>
          <form name="user" action="save" method="POST">
              <@ui.formInput id="t1" name="nickname" label="Никнейкм"/> <br/>
              <@ui.formInput id="t2" name="email" label="Е-почта"/> <br/>
              <@ui.formInput id="t3" name="password" label="Пароль"/> <br/>
              <input type="submit" value="Save" />
          </form>
      </fieldset>
  </div>

  <div>
      <fieldset>
          <legend>Поиск по нику</legend>
          <form name="searchForm" action="search" method="POST">
              <@ui.formInput id="t4" name="searchName" label="Поиск"/> <br/>
              <input type="submit" value="Search"/>
          </form>
      </fieldset>
  </div>
  <p><#if lastSearch??>Поиск для: ${lastSearch}<#else></#if></p>

  <div>
    <table border="1">
      <tr>
        <th>id</th>
        <th>nickname</th>
        <th>password</th>
        <th>email</th>
      </tr>
      <#list users as user>
        <tr>
          <td>${user.id}</td>
          <td>${user.nickname}</td>
          <td>${user.password}</td>
          <td>${user.email}</td>
        </tr>
      </#list>
    </table>
  </div>

</div>
</body>
