<#include "base-with-authorization.ftl"/>

<#macro title>news</#macro>
<#macro links>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
                <h2 class="mb-4">Results of searching</h2>
            </div>
        </div>
        <#list  newsList as n>
            <div class="row justify-content-center mt-2">
                <div class="col">
                    <div class="card card-post h-auto">
                        <form action="/post" method="get">
                            <div class="media media-author">
                                <img src="${n.getImgPath()}"
                                     class="mr-3 img-fluid borderImg" style="width: 300px" alt="Пользователь">
                                <div class="media-body media-author-body">
                                    <input name="id" value="${n.getId()}" type="hidden">
                                    <button type="submit" class="btn btn-link text-dark"
                                            style="font-size: 22px">${n.getHeader()}
                                    </button>
                                    <p class="ml-3" style="font-size: 16px">${n.getDate()}</p>
                                    <p style="font-size: 20px">${n.getPreview()}</p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</#macro>

<@main/>

