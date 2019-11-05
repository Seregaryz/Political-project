<body>
<form action="/profileRedaction" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input name="password" type="password" class="form-control" id="exampleInputPassword1"
               placeholder="Password">
    </div>
    <div class="form-group">
        <label for="nickname">Nickname</label>
        <input name="nickname" type="text" class="form-control" id="nickname" aria-describedby="emailHelp"
               placeholder="Enter nickname" value="${user.getNickname()}"></div>
    <div class="form-group">
        <label for="name">Name</label>
        <input name="name" type="text" class="form-control" id="name" aria-describedby="emailHelp"
               placeholder="Enter name" value="${user.getUsername()}"></div>
    <div class="form-group">
        <label for="surname">Surname</label>
        <input name="surname" type="text" class="form-control" id="surname" aria-describedby="emailHelp"
               placeholder="Enter surname" value="${user.getSurname()}"></div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="sex" id="exampleRadios1" value="male" checked>
        <label class="form-check-label" for="exampleRadios1">
            Male
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="sex" id="exampleRadios2" value="female">
        <label class="form-check-label" for="exampleRadios2">
            Female
        </label>
    </div>
    <div><input type="file" name="photo" value="${user.getPhotoPath()}"/></div>
    <input type="submit" class="btn btn-primary">
</form>
</body>