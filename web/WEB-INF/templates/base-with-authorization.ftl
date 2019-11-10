<#macro content>
</#macro>

<#macro title></#macro>
<#macro links></#macro>

<#macro main>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title><@title/></title>
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap-grid.css">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap-reboot.css">
        <script src="/js/bootstrap.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <@links/>
    </head>
    <body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a href="/" class="navbar-brand">POLITIS</a>
        <div class="collapse navbar-collapse text-white" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/newsList">News</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/debatesList">Debates</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Archive</a>
                </li>
            </ul>

            <form class="form-inline my-2 my-lg-0 mr-2" action="/searchNews" method="get">
                <input class="form-control mr-sm-2" type="search" placeholder="Search news..." aria-label="Search" name="pattern">
                <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
            </form>
            <ul class="navbar-nav mr-0">
                <li class="nav-item mx-1">
                    <a class="nav-link" href="/profile">${nickname}</a>
                </li>
                <li class="nav-item mx-1">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <@content/>
    </body>
    </html>
</#macro>