<#include "base-non-authorized.ftl"/>

<#macro title>registration</#macro>

<#macro links>
    <style>
        .form-width {
            max-width: 25rem;
        }

        .has-float-label {
            position: relative;
        }

        .has-float-label label {
            position: absolute;
            left: 0;
            top: 0;
            cursor: text;
            font-size: 75%;
            opacity: 1;
            -webkit-transition: all .2s;
            transition: all .2s;
            top: -.5em;
            left: 0.75rem;
            z-index: 3;
            line-height: 1;
            padding: 0 1px;
        }

        .has-float-label label::after {
            content: " ";
            display: block;
            position: absolute;
            background: white;
            height: 2px;
            top: 50%;
            left: -.2em;
            right: -.2em;
            z-index: -1;
        }

        .has-float-label .form-control::-webkit-input-placeholder {
            opacity: 1;
            -webkit-transition: all .2s;
            transition: all .2s;
        }

        .has-float-label .form-control:placeholder-shown:not(:focus)::-webkit-input-placeholder {
            opacity: 0;
        }

        .has-float-label .form-control:placeholder-shown:not(:focus) + label {
            font-size: 150%;
            opacity: .5;
            top: .3em;
        }

        .input-group .has-float-label {
            display: table-cell;
        }

        .input-group .has-float-label .form-control {
            border-radius: 0.25rem;
        }

        .input-group .has-float-label:not(:last-child) .form-control {
            border-bottom-right-radius: 0;
            border-top-right-radius: 0;
        }

        .input-group .has-float-label:not(:first-child) .form-control {
            border-bottom-left-radius: 0;
            border-top-left-radius: 0;
            margin-left: -1px;
        }
    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <link rel="stylesheet" type="text/css" href="../css/animate.css">
    <link rel="stylesheet" type="text/css" href="../css/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="../css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="../css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="../css/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="../css/loginUtil.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/js/bootstrap-filestyle.min.js"></script>
    <script src="http://code.jquery.com/jquery-3.3.1.js"></script>
</#macro>

<#macro content>
    <div class="container-login100">
        <form class="card card-block m-x-auto bg-faded form-width" action="/registration" method="post" enctype="multipart/form-data">
            <legend class="m-b-1 text-xs-center">Registration</legend>
            <div class="form-group input-group">
						<span class="has-float-label">
							<input class="form-control" id="name" type="text" placeholder="name" name="name"/>
							<label for="name">Name</label>
						</span>
                <span class="has-float-label">
							<input class="form-control" id="surname" type="text" placeholder="surname" name="surname"/>
							<label for="surname">Surname</label>
						</span>
            </div>
            <div class="form-group text-center">
                <div class="my-2 mx-auto">
                    <label for="my-file-selector" style="font-size: 20px">Аватар</label>
                    <input type="file" name="photo" class="filestyle" id="my-file-selector"
                           data-classButton="btn btn-dark ml-2"
                           data-classIcon="icon-plus"
                           data-buttonText="Выбрать файлы" multiple accept="image/*,image/jpeg">
                </div>
                <span class='label label-info' id="upload-file-info"></span>

                <div class="form-group has-float-label my-2">
                    <select class="form-control custom-select" id="sex" name="sex">
                        <option selected>male</option>
                        <option>female</option>
                    </select>
                    <label for="sex">Sex</label>
                </div>
                <div class="form-group input-group">
						<span class="has-float-label">
							<input class="form-control" id="nickname" type="text" placeholder="nickname"
                                   name="nickname"/>
							<label for="nickname">Nickname</label>
						</span>
                </div>
                <div class="form-group input-group">
						<span class="has-float-label">
							<input class="form-control" id="email" type="email" placeholder="name@example.com"
                                   name="login"/>
							<label for="email">E-mail</label>
						</span>
                </div>
                <div class="form-group has-float-label">
                    <input class="form-control" id="password" type="password" placeholder="••••••••" name="password"/>
                    <label for="password">Password</label>
                </div>
                <div class="container-login100-form-btn">
                        <button type="submit" class="login100-form-btn">Sign up</button>
                </div>
                <p>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        <a href="/login"></a>have an account? login
                    </button>
                </div>
            </div>
        </form>
    </div>
</#macro>

<@main/>
