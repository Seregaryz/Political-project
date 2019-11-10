<#include "base-with-authorization.ftl"/>

<#macro title>news</#macro>
<#macro links>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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

    <div class="container container-post">
    <div class="row">
        <div class="col">
            <h2 class="mb-4">Debates</h2>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col">
            <#list debates as d>
                <div class="card card-post">
                    <div class="media media-author">
                        <div class="media-body media-author-body">
                            <h5 class="mt-0">${d.getTopic()}</h5>
                            <h5 class="my-3">Creator: </h5>
                            <div class="d-flex mt-4 p-1">
                                <img class="img-circle" src="${d.getCreator().getPhotoPath()}"
                                     style="width: 50px; height: 50px">
                                <div class="ml-4 my-auto">
                                    <h5>${d.getCreator().getNickname()}</h5>
                                </div>
                            </div>
                            <form>
                                <input name="id" value="${d.getId()}" type="hidden">
                                <button type="submit" class="btn btn-dark mt-3"
                                        style="font-size: 22px">Join
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <p></p>
</#macro>

<@main/>