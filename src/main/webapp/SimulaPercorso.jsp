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
    <nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_utente.html"><img src="Logo_Telepass_2021.png" style="height:30px;"></a></nav>

    <div class="main">
        <center>
            <div style="width:50%;">
                <center><h2 class="h2div">Inserisci il Percorso e la classe dell'auto</h2></center>
                <form name="addVeicolo" class="rounded" style="width:100%;" method="POST" action="">
                  <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="">Casello di Entrata</label>
                  <select style="width:50%; margin-bottom: 5px; " class="form-select" id="caselloEntrata" name="caselloEntrata">
                      <option value="A">Napoli</option>
                      <option value="B">Milano</option>
                      <option value="1">Bologna</option>
                      <option value="2">Bari</option>
                      <option value="3">Roma</option>
                  </select><br>
                  <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="classe">Casello di Uscita</label>
                  <select style="width:50%; margin-bottom: 5px; " class="form-select" id="caselloUscita" name="caselloUscita">
                      <option value="A">Napoli</option>
                      <option value="B">Milano</option>
                      <option value="1">Bologna</option>
                      <option value="2">Bari</option>
                      <option value="3">Roma</option>
                  </select><br>
                  <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="classe">Classe</label>
                    <select style="width:50%; margin-bottom: 5px; " class="form-select" id="classe" name="classe">
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select><br>
                  <a href="protected_area_utente.html"><button type="button" class="btn btn-outline-primary">Indietro</button></a>
                  <button type="submit" class="btn btn-outline-primary" name="calcolaCosti" id="calcolaCosti" value="">Calcola Costi</button>
                </form>
            </div>
        </center>
    </div>

    <section class="pageform">
      <!-- Footer -->
      <footer class="text-center text-white" style="background-color: #002752;" id="indexfooter">
        <!-- Grid container -->
        <div class="container p-4 pb-0">
          <!-- Section: CTA -->
          <section class="">
            <img src="Logo_Telepass_2021.png" style="height:50px;">
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