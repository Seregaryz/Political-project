<p>${user.getNickname()}</p>
<img src="${user.getPhotoPath()}" width="300px">
<p>${user.getUsername()}</p>
<p>${user.getSurname()}</p>
<p>${user.getSex()}</p>
<form action="/profileRedaction" method="get">
    <input type="submit" value="redaction"></form>