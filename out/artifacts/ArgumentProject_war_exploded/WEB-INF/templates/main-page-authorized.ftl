<p>${nickname}</p>
<form action="/logout" method="get">
    <input type="submit" value="exit"></form>
<form action="/profile" method="get">
    <input type="submit" value="profile"></form>
<#list newsList as n>
    <p>${n.getHeader()}</p>
    <img src="${n.getImgPath()}" width="300px">
    <p>${n.getDate()}</p>
    <p>${n.getText()}</p>
    <form action="http://localhost:8080/post" method="get">
        <input name="id" value="${n.getId()}" type="hidden">
        <input type="submit" value="open">
    </form>
</#list>