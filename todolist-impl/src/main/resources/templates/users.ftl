<#import "users.ftl" as ui/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>

<div>
    <fieldset>
        <legend>Добавить лицо</legend>
        <form name="user" action="save" method="POST">
            <@ui.formInput id="t1" name="firstName" label="Имя"/> <br/>
            <@ui.formInput id="t2" name="age" label="Возраст"/> <br/>
            <input type="submit" value="Save" />
        </form>
    </fieldset>
</div>

<div>
    <fieldset>
        <legend>Поиск</legend>
        <form name="searchForm" action="search" method="POST">
            <@ui.formInput id="t3" name="searchName" label="Поиск"/> <br/>
            <input type="submit" value="Search" />
        </form>
    </fieldset>
</div>
<p><#if lastSearch??>Поиск для: ${lastSearch}<#else></#if></p>

<@ui.table id="table1" rows=users![]/>

</body>
</html>