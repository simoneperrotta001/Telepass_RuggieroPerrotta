<%--
  Created by IntelliJ IDEA.
  User: UTENTE
  Date: 20/12/2022
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    <style>
        #main{margin-left: 150px;
            margin-right: 150px;
        }
        #pbody{
            font-family: inherit;
            font-size:medium;
            text-align: justify;
            margin-top: 10px;
        }
        #pdiv{
            font-family: inherit;
            font-size:medium;
        }
        #indexfooter{
            position:absolute;
            bottom:0px;
            left: 0px;
            right:0px;
            margin-bottom:0px;
            width: 100%;
            overflow: hidden;
            color:#fff;
            margin-top:5px;
        }
        #mydiv{
            align-items:self-end;
        }
        #h2div{
            color:#0d6efd;
            font-family: inherit;
        }
        #inptxt{
            height: 50px;
            width: 350px;
        }
        a{
            font-family: inherit;
        }
        button{
            font-family: inherit;
        }

    </style>
    <title>Telepass</title>
    <link rel="shortcut icon" href="https://logo.clearbit.com/telepass.com">
</head>
<body>
<div class="row" style="width:100%;">
    <div class="col-sm-6">
        <center>
            <br><br><br><br>
            <a class="navbar-brand" href="index.jsp"><img src="images/Logo_Telepass_2021.png" style="height:40px"></a>
            <br><br>
            <p style="font-size: x-large; color:#002752">INSERISCI LE TUE CREDENZIALI</p>
            <form action="#" method="POST">
                <div>
                    <div>
                        <label><input type="text" class="form-control" placeholder="Nome utente" aria-label="Username" aria-describedby="basic-addon1" id="inptxt"></label>
                    </div>
                    <br>
                    <div>
                        <label><input type="password" class="form-control" placeholder="Password" aria-label="Username" aria-describedby="basic-addon1" id="inptxt"></label>
                    </div>
                    <br>
                    <div>
                        <button type="submit" class="btn btn-primary" style="width:175px; height:50px;">ACCEDI</button>
                    </div>
                </div>
            </form>
        </center>
    </div>

    <div class="col-sm-6">
        <img src="images/telepass-bip_low.png" width="100%" height="100%">
    </div>
</div>

<section class="">
    <!-- Footer -->
    <footer class="text-center text-white" style="background-color: #002752;" id="indexfooter">
        <!-- Grid container -->
        <div class="container p-4 pb-0">
            <!-- Section: CTA -->
            <section class="">
                <img src="images/Logo_Telepass_2021.png" style="height:50px;">
            </section>
            <!-- Section: CTA -->
        </div>
        <!-- Grid container -->

        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: #002752;">
            <a class="text-white" href="#">© 2022 Copyright:Telepass.com</a>
        </div>
        <!-- Copyright -->
    </footer>
    <!-- Footer -->
</section>
</body>
</html>