<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/style.css">
<title>Telepass</title>
<link rel="shortcut icon" href="https://logo.clearbit.com/telepass.com">
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_utente.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px;"></a></nav>

    <div class="main">
        <center>
            <h2 class="h2div">Informazioni sul tuo profilo</h2>
            <div class="rounded" style="width:50%; border-style:solid; border-color:#0d6efd; border-width: 3px">
                <div class="row">
                    <div class="col-lg-12">
                      <div class="card mb-4" style="height: 100%">
                        <div class="card-body">
                          <div class="row">
                            <div class="col-sm-4">
                              <p class="mb-0">Nome Utente</p>
                            </div>
                            <div class="col-sm-8">
                              <p class="text-muted mb-0">Simone Perrotta</p>
                            </div>
                          </div>
                          <hr>
                          <div class="row">
                            <div class="col-sm-4">
                              <p class="mb-0">Email</p>
                            </div>
                            <div class="col-sm-8">
                              <p class="text-muted mb-0">simonePerrotta@gmail.com</p>
                            </div>
                          </div>
                          <hr>
                          <div class="row">
                            <div class="col-sm-4">
                              <p class="mb-0">Data Iscrizione Telepass</p>
                            </div>
                            <div class="col-sm-8">
                              <p class="text-muted mb-0">12/01/2002</p>
                            </div>
                          </div>
                          <hr>
                          <div class="row">
                            <div class="col-sm-4">
                              <p class="mb-0">Data Scadenza Telepass</p>
                            </div>
                            <div class="col-sm-8">
                              <p class="text-muted mb-0">12/01/2023</p>
                            </div>
                          </div>
                          <hr>
                          <div class="row">
                            <div class="col-sm-4">
                              <p class="mb-0">Telepass+</p>
                            </div>
                            <div class="col-sm-8">
                              <p class="text-muted mb-0">NON ATTIVO</p>
                            </div>
                          </div>
                          <hr>
                          <div class="row">
                            <div class="col-sm-12">
                                <a href="protected_area_utente.jsp"><button type="button" class="btn btn-outline-primary">Home</button></a>
                                <button type="submit" class="btn btn-outline-danger" name="veicolonuovo" id="veicolonuovo" value="1">Disdici Abbonamento</button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
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