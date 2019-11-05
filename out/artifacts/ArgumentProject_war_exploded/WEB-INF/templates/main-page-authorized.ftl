<html>
<head>
    <script type="application/javascript" src="/js/jquery-3.4.1.min.js"></script>
</head>

<body>
<p>${nickname}</p>
<p><label for="query"></label><input id="query" oninput="f()"/></p>


<div id="res"></div>

<script type="application/javascript">
    function f() {
        if ($("#query").val().length >= 1) {
            $.ajax({
                url: "/dosearch",
                data: {"query": $("#query").val()},
                dataType: "json",
                success: function (msg) {
                    if (msg.objects.length > 0) {
                        $("#res").html("");
                        for (var i = 0; i < msg.objects.length; i++) {
                            $("#res").append("<li>" + msg.objects[i].header + "</li>");
                        }
                    } else {
                        $("#res").html("No results..");
                    }
                }
            })
        }
        else {
            $("#res").html("");
        }
    }
</script>

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
</body>
</html>