<p>${nickname}</p>
<p>${path}</p>
<p>${news.getHeader()}</p>
<img src="${news.getImgPath()}" width="300px">
<p>${news.getDate()}</p>
<p>${news.getText()}</p>

<form action="http://localhost:8080/post" method="post">
    <input name="id" value="${news.getId()}" type="hidden">
    <div class="form-group">
        <label for="exampleFormControlTextarea1">Example textarea</label>
        <textarea name="text" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
    </div>
    <input type="submit" value="send">
</form>
<#list comments as c>
<p><a href="/profile">${c.getSender().getUsername()}</a></p>
<p>${c.getText()}</p>
<p>${c.getStringDate()}</p>
</#list>