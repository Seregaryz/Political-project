<#include "base-with-authorization.ftl"/>

<#macro title>debates</#macro>

<#macro links>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/extra-styles.css">
    <style>

        .container img {
            float: left;
            max-width: 60px;
            width: 100%;
            margin-right: 20px;
            border-radius: 50%;
        }

        .container img.right {
            float: right;
            margin-left: 20px;
            margin-right: 0;
        }


        a.profile-dropdown-toggle {
            color: white;
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


        .card a:hover {
            text-decoration: none;
        }
    </style>
</#macro>

<#macro content>
    <div class="row user-profile-row w-100">
        <div class="col-auto ml-5 w-25">
            <p>
            <h2>Debator 1</h2></p>
            <img src="${debates.getCreator().getPhotoPath()}" alt="Фотография"
                 class="img-fluid user-profile-photo" width="100" height="155">
            <p></p>
            <div class="form-group">
                <P><b>${debates.getCreator().getNicname()}</b></P>
            </div>
            <p>
            <h2>Debator 2</h2></p>
            <img src="${debates.getSecondDebater().getPhotoPath()}" alt="Фотография"
                 class="img-fluid user-profile-photo" width="150" height="205">
            <p></p>
            <div class="form-group">
                <p>Name Sername</p>
                <P><b>${debates.getSecondDebater().getNicname()}</b></P>
            </div>
        </div>

    <div class="col-8 ml-4 w-75 mx-auto">

        <div class="d-flex mt-4 border-dark border-top border-left border-right p-3 bg-secondary row"
             style="width: 650px">
            <div class="ml-4 my-auto text-white">
                <h5 class="">${debates.getHeader()}</h5>
            </div>
        </div>

        <div class="chat-block overflow-y-scroll border border-dark p-2 row align-items-end"
             style="height: 450px">
            <#list messages as m>
                <div class="text-right w-100">
                    <span style="font-size: 10pt">${m.getStringDate()}</span>
                    <p class="speech bg-secondary">${m.getText()}</p>
                </div>
            </#list>
        </div>

    <div class=" row border-dark border-bottom border-left border-right p-1 bg-secondary"
         style="width: 650px; height: 79px">
        <div class="d-flex">
            <form action="/sendmessage">
                <div class="float-left">
                                <textarea class="form-control max-h-70px my-auto w-509px"
                                          id="exampleFormControlTextarea1"
                                          rows="3">
                                </textarea>
                </div>
                <div class="float-left">
                    <button type="submit" class="btn btn-light h-50 ml-1 mt-3">Отправить
                    </button>
                </div>
            </form>
        </div>
        <div>

        </div>
    </div>

</#macro>