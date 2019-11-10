<#include "base-with-authorization.ftl"/>

<#macro title>Edit profile</#macro>

<#macro links>
    <meta charset="UTF-8" xmlns="http://www.w3.org/1999/html">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>EditProfile</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../css/StyleForMain.css">
    <link rel="stylesheet" href="../css/extra-styles.css">
    <style>

        .borderImg {
            border: 2px solid #000000;
        }

        a.profile-dropdown-toggle {
            color: white;
        }

        .container-post {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .card-post {
            padding: 16px;
        }

        .card-like-button-wrap {
            display: flex;
            justify-content: flex-end;
        }

        .card-like-button {
            margin-left: 12px;
            padding: 0;
            width: 30px;
            background: transparent;
            border: 0;
        }

        .card-like-icon {
            max-width: 24px;
            height: auto;
        }

        .media-author {
            padding: 16px 0;
        }

        .media-author-photo {
            max-width: 80px;
        }

        .media-author-body {
            color: black;
            font-size: small;
        }

        .card-text {
            color: black;
            font-size: 14px;
        }

        .card a:hover {
            text-decoration: none;
        }
    </style>
</#macro>

<#macro content>
    <div class="container">
        <form action="/profileRedaction" method="post" enctype="multipart/form-data">
            <div class="row user-profile-row">
                <div class="col">
                    <p></p>
                    <img src="${user.getPhotoPath()}" alt="Фотография"
                         class="img-fluid user-profile-photo" width="200" height="255">
                    <p></p>
                    <input type="file" class="form-control-file user-profile-photo-upload" name="photo">
                </div>
                <div class="col">
                    <h2>Edit profile</h2>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" value="${user.getUsername()}" name="name">
                    </div>
                    <div class="form-group">
                        <label for="sername">Sername</label>
                        <input type="text" class="form-control" id="sername" value="${user.getSurname()}"
                               name="surname">
                    </div>
                    <div class="form-group has-float-label">
                        <label for="sex">Sex</label>
                        <select class="form-control custom-select" id="sex" name="sex">
                            <option selected>male</option>
                            <option>female</option>
                        </select>
                    <div class="form-group">
                        <label for="login">Nickname</label>
                        <input type="text" class="form-control" id="login" value="${user.getNickname()}"
                               name="nickname">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="form-group">
                        <label for="password-repeat">Confirm password</label>
                        <input type="password" class="form-control" id="password-repeat" name="password-repeat">
                    </div>
                    <div class="btns-wrapper">
                        <button type="submit" class="btn btn-dark">Save</button>
                        <a href="/profile" class="btn btn-outline-dark">Drop</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</#macro>

<@main/>