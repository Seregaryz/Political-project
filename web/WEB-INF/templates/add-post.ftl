<#include "base-with-authorization.ftl"/>

<#macro title>add news</#macro>

<#macro links>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <style>
        a.profile-dropdown-toggle {
            color: white;
        }

        .container-post {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .post-photo {
            max-width: 400px;
        }

        .post-photo-upload {
            margin-top: 20px;
        }
    </style>
</#macro>

<#macro content>
    <form action="/addpost" method="post" enctype="multipart/form-data">
        <div class="row w-100">
            <div class="col-auto ml-5 mt-5">
                <img src="/img/poster.jpg" alt="Пост" class="img-fluid post-photo">
                <input type="file" class="form-control-file post-photo-upload" name="photo">
            </div>
            <div class="col mx-5 mt-5">
                <h2>Add new</h2>
                <div class="form-group">
                    <label for="place">Add header</label>
                    <input type="text" class="form-control" id="place" name="header">
                </div>
                <div class="form-group">
                    <label for="time">Add preview</label>
                    <input type="text" class="form-control" id="time" name="preview">
                </div>
                <div class="form-group">
                    <label for="desc">Add text of news</label>
                    <textarea class="form-control" id="desc" rows="3" name="text"></textarea>
                </div>
                <button type="submit" class="btn btn-dark">Publish</button>
            </div>
        </div>
    </form>

</#macro>

<@main/>
