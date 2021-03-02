<#import "ui.ftl" as ui/>
<#include "common.ftl"/>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>

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
            <legend>Поиск</legend>
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
                <th>nickname</th>
                <th>email</th>
            </tr>
            <#list users as user>
                <tr>
                    <td>${user.nickname}</td>
                    <td>${user.email}</td>
                </tr>
            </#list>
        </table>
    </div>

</div>
</body>
