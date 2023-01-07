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
<jsp:include page="PrivilegiAdmin"></jsp:include>

    <div id="main">
        <c:if test="${messageConto != null}">
            <div class="alert info">
                <span class="closebtn">&times;</span>
                <strong>${messageConto}</strong>
            </div>
        </c:if>

        <c:if test="${messageContoUsato != null}">
            <div class="alert info">
                <span class="closebtn">&times;</span>
                <strong>${messageContoUsato}</strong>
            </div>
        </c:if>

        <c:if test="${messageUsernameUsato != null}">
            <div class="alert info">
                <span class="closebtn">&times;</span>
                <strong>${messageUsernameUsato}</strong>
            </div>
        </c:if>

        <c:if test="${messageUsernameCorto != null}">
            <div class="alert info">
                <span class="closebtn">&times;</span>
                <strong>${messageUsernameCorto}</strong>
            </div>
        </c:if>

        <c:if test="${messageData != null}">
            <div class="alert info">
                <span class="closebtn">&times;</span>
                <strong>${messageData}</strong>
            </div>
        </c:if>


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
        <div class="row" style="height: 90px;">
            <div class="row" style="height: 30px;"></div>
            <center><a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:40px"></a></center>
        </div>
        <center><h3 class="h2div">Inserisci i dati dell'utente da creare</h3></center><br>
        <form style="margin-top: 10px;" action="creazioneutente" method="POST">
            <div class="row" style="width:100%;">
                <div class="col-sm-6">
                    <center>
                            <div>
                                <div>
                                    <label><input type="text" class="form-control" placeholder="Nome utente" aria-label="Nome Utente" aria-describedby="basic-addon1" name="nomeutente" id="inptxt" required></label>
                                </div>
                                <br>
                                <div>
                                    <label><input type="text" class="form-control" placeholder="Cognome utente" aria-label="Cognome" aria-describedby="basic-addon1" name="cognomeutente" id="inptxt" required></label>
                                </div>
                                <br>
                                <div>
                                    <label><input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" name="username" id="inptxt" required></label>
                                </div>
                                <br>
                                <div>
                                    <label><input type="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" name="password" id="inptxt" required></label>
                                </div>
                            </div>
                    </center>
                </div>

                <div class="col-sm-6">
                    <center>
                            <div>
                                <div>
                                    <label><input type="date" class="form-control" placeholder="Nascita" aria-label="Nascita" aria-describedby="basic-addon1" name="nascita" id="inptxt" required></label>
                                </div>
                                <br>
                                <div>
                                    <label><input type="text" class="form-control" placeholder="Codice Conto Corrente" aria-label="ContoCorrente" aria-describedby="basic-addon1" name="contocorrente" id="inptxt" required></label>
                                </div>
                                <br>
                                <select class="form-select" id="inptxt" name="abbonamento" required>
                                    <option value="1">ABBONAMENTO ATTIVO</option>
                                    <option value="0">ABBONAMENTO NON ATTIVO</option>
                                </select>
                                <br>
                                <select class="form-select" id="inptxt" name="plus" required>
                                    <option value="1">PLUS ATTIVO</option>
                                    <option value="0">PLUS NON ATTIVO</option>
                                </select>
                            </div>
                    </center>
                </div>
            </div>
            <center><br><button type="submit" class="btn btn-primary" style="width:175px; height:50px;">CREA UTENTE</button></center>
        </form>
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