<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/mystyle.css">

    <title>Telepass</title>
    <link rel="shortcut icon" href="https://logo.clearbit.com/telepass.com">
</head>
<body>
<%
    if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "") && (session.getAttribute("ruolo") == null) || (session.getAttribute("ruolo") == "")) {
        response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/Accedi.jsp");
    }
    int ruolo= (int) session.getAttribute("ruolo");
    if(ruolo == 0) {response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_utente.jsp");}
%>
<div id="main">
    <div class="row" style="height: 90px;">
        <div class="row" style="height: 30px;"></div>
        <center><a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:40px"></a><br>
            <h3 class="h2div">Operazioni disponibile per l'utente selezionato: ${username}</h3>
        </center>
    </div>
    <br>
    <div class="row" style="width:100%;">
        <center>
        <div class="col-sm-9">
                <c:choose>
                    <c:when test="${attivo==1 && plus==1}">
                        <h3 class="h2div">${username} possiede l'abbonamento e il plus</h3>
                        <form name="gestisci" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                            <input type="hidden" name="username" value=${username}>
                            <button type="submit" class="btn btn-outline-danger" name="gestisci"  value="0">Rimuovi Abbonamento</button></a>
                            <button type="submit" class="btn btn-outline-danger" name="gestisci"  value="2">Rimuovi Plus</button></a>
                        </form>
                    </c:when>

                    <c:when test="${attivo==1 && plus==0}">
                        <h3 class="h2div">${username} possiede l'abbonamento ma non il plus</h3>
                        <form name="gestisci" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                            <input type="hidden" name="username" value=${username}>
                            <button type="submit" class="btn btn-outline-danger" name="gestisci"  value="0">Rimuovi Abbonamento</button></a>
                            <button type="submit" class="btn btn-outline-success" name="gestisci" value="3">Assegna Plus</button></a>
                        </form>
                    </c:when>

                    <c:otherwise>
                        <h3 class="h2div">${username} non possiede l'abbonamento</h3>
                        <form name="gestisci" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                            <input type="hidden" name="username" value=${username}>
                            <button type="submit" class="btn btn-outline-success" name="gestisci" value="1">Abbonati</button></a>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </center>
    </div>
</div>
<section class="">
    <!-- Footer -->
    <footer class="text-center text-white" style="background-color: #002752;" id="staticfooter">
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