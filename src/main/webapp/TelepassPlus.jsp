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
<jsp:include page="PrivilegiUtentePlus"></jsp:include>

    <nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_utente.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px; margin-left: 5px"></a></nav>

    <div id="main">
        <center>
            <br><br><br><br>
        <div class="rounded" style="width:70%; border-style: solid; border-color: #0d6efd; border-width: 3px;">
            <h2 class="h2div">Passa a Telepass+</h2>
            <h5>Telepass Plus è l’offerta che ti permette di avere tutti i servizi base (pedaggio, parcheggi convenzionati, Area C di Milano e
                il traghetto per lo stretto di Messina) più tutti i servizi per occuparti del tuo veicolo (ricarica elettrica, carburante, bollo e
                revisione) e per muoverti in Italia e in città con la mobilità condivisa (monopattino, treni, taxi, mezzi pubblici).<br>
                <strong>Inoltre offriamo il servizio assistenza 24/7 per tutto l'anno.</strong>
            </h5>
            <form name="TelepassPlus" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                <a href="protected_area_utente.jsp"><button type="button" class="btn btn-outline-primary">Indietro</button></a>
                <button type="submit" class="btn btn-outline-success" name="gestisci" value="3">Abbonati a Plus</button></a>
            </form>
            <br>
        </div>
        </center>
    </div>

    <section class="pageform">
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
          © 2022 Copyright:
          <a class="text-white" href="#">Telepass.com</a>
        </div>
        <!-- Copyright -->
      </footer>
      <!-- Footer -->
    </section>
</body>
</html>