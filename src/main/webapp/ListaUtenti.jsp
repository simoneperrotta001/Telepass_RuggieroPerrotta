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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

</head>
<div>
<nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_utente.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px;"></a></nav>
<center>
  <div>
    <div class="col-md-8">
      <table class="table">
        <thead style="background-color: #0d6efd; color:white">
        <tr>
          <th scope="col">#</th>
          <th scope="col">Nome Utente</th>
          <th scope="col">Email</th>
          <th scope="col">Data Iscrizione</th>
          <th scope="col">Scadenza Abbonamento</th>
          <th scope="col">Status</th>
          <th scope="col">Telepass+</th>
          <th scope="col">Modifica</th>
          <th scope="col">Elimina</th>
        </tr>
        </thead>
        <tbody>
        <tr class="table-primary">
          <th scope="row">1</th>
          <td>jhon</td>
          <td>Jon@gmail.com</td>
          <td>10/10/1995</td>
          <td>10/10/1995</td>
          <td><span class="badge rounded-pill text-bg-success">Attivo</span></td>
          <td><span class="badge rounded-pill text-bg-danger">Disattivo</span></td>
          <td><button class="btn btn-sm btn-primary"><i class="bi bi-pencil-square"></i></button></td>
          <td><button class="btn btn-sm btn-danger"><i class="bi bi-trash3-fill"></i></button></td>
        </tr>
        <tr class="table-success">
          <th scope="row">2</th>
          <td>mark</td>
          <td>mark@gmail.com</td>
          <td>10/10/1996</td>
          <td>10/10/1996</td>
          <td><span class="badge rounded-pill text-bg-success">Attivo</span></td>
          <td><span class="badge rounded-pill text-bg-success">Attivo</span></td>
          <td><button class="btn btn-sm btn-primary"><i class="bi bi-pencil-square"></i></button></td>
          <td><button class="btn btn-sm btn-danger"><i class="bi bi-trash3-fill"></i></button></td>
        </tr>
        <tr class="table-danger">
          <th scope="row">3</th>
          <td>Raj</td>
          <td>raj@gmail.com</td>
          <td>10/10/1997</td>
          <td>10/10/1997</td>
          <td><span class="badge rounded-pill text-bg-danger">Disattivo</span></td>
          <td><span class="badge rounded-pill text-bg-danger">Disattivo</span></td>
          <td><button class="btn btn-sm btn-primary"><i class="bi bi-pencil-square"></i></button></td>
          <td><button class="btn btn-sm btn-danger"><i class="bi bi-trash3-fill"></i></button></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</center>

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