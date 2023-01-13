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
  <title>Telepass</title>
  <link rel="stylesheet" href="css/mystyle.css">
  <link rel="shortcut icon" href="https://logo.clearbit.com/telepass.com">
</head>
<body>
<!-- chiamata per controllare i privilegi di accesso dei vari utenti -->
<jsp:include page="PrivilegiUtente"></jsp:include>
<!--JSTL che a seconda se c'è il messaggio inviato dalle varie servlet lo fa comparire
a schermo -->
<div class="row" style="width:100%;">
  <c:if test="${messageUsernameUsato != null}">
    <div class="alert warning">
      <span class="closebtn">&times;</span>
      <strong>${messageUsernameUsato}</strong>
    </div>
  </c:if>
  <!--Questo script serve a far comparire il div per gli alert(successo, fallimento e warning)-->
  <script>
    var close = document.getElementsByClassName("closebtn");
    var i;

    for (i = 0; i < close.length; i++) {
      close[i].onclick = function(){
        var div = this.parentElement;
        div.style.opacity = "0";
        setTimeout(function(){ div.style.display = "none"; }, 600);
      }
    }
  </script>
  <div class="col-sm-6">
    <center>
      <br><br><br><br>
      <a class="navbar-brand" href="protected_area_utente.jsp"><img src="images/Logo_Telepass_2021.png" style="height:40px"></a>
      <br><br>
      <h4 class="h2div">INSERISCI IL NUOVO USERNAME</h4>
      <form action="cambiausername" method="POST" onsubmit="return checkData()">
        <div>
          <div>
            <label><input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" name="username" id="inptxt" required></label>
          </div>
          <br>
          <div>
            <button type="submit" class="btn btn-primary" style="width:175px; height:50px;">CONFERMA</button>
          </div>
        </div>
      </form>
    </center>
  </div>

  <div class="col-sm-6">
    <img src="images/telepass-bip_low.png" width="100%" height="100%">
  </div>
</div>

<script>
  function checkData(){
    if(document.getElementById("inptxt").value.length >=5) return true;
    else {
      alert("L'username deve essere almeno di 5 caratteri.");
      return false;
    }
  }
</script>

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