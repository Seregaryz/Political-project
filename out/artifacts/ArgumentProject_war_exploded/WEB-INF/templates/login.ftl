<#include "base-non-authorized.ftl"/>

<#macro title>login</#macro>

<#macro links>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
</#macro>

<#macro content>
    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100 p-b-160 p-t-50">
                <form class="login100-form validate-form" action="/login" method="post">
					<span class="login100-form-title p-b-43">
						Login
					</span>

                    <div class="wrap-input100 rs1 validate-input" data-validate = "Login or E-mail is required">
                        <input class="input100" type="text" name="login">
                        <span class="label-input100"></span>
                    </div>


                    <div class="wrap-input100 rs2 validate-input" data-validate="Password is required">
                        <input class="input100" type="password" name="password">
                        <span class="label-input100"></span>
                    </div>

                    <div class="container-login100-form-btn">
                        <button type="submit" class="login100-form-btn">Login</button>
                    </div>

                    <div class="text-center w-full p-t-23">
                        <input type="checkbox" id="check" name="remember">
                        <span class="txt1">Remember me</span>
                    </div>
                </form>
                <form>
                    <div class="text-center w-full p-t-23">
                        <a href="/registration" class="txt1">
                            Don`t have an account? Registration!
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/animsition.min.js"></script>
    <script src="../js/popper.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/select2.min.js"></script>
    <script src="../js/moment.min.js"></script>
    <script src="../js/daterangepicker.js"></script>
    <script src="../js/countdowntime.js"></script>
    <script src="../js/login.js"></script>
</#macro>

<@main/>
