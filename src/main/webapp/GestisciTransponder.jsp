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
<!-- chiamata per controllare i privilegi di accesso dei vari utenti -->
<jsp:include page="PrivilegiAdmin"></jsp:include>
<!--JSTL che a seconda se c'è il messaggio inviato dalle varie servlet lo fa comparire
a schermo -->
    <div id="main">
        <c:if test="${messageAggiungiPlusAdmin != null}">
            <div class="alert success">
                <span class="closebtn">&times;</span>
                <strong>${messageAggiungiPlusAdmin}</strong>
            </div>
        </c:if>

        <c:if test="${messageAggiungiAbbonamentoAdmin != null}">
            <div class="alert success">
                <span class="closebtn">&times;</span>
                <strong>${messageAggiungiAbbonamentoAdmin}</strong>
            </div>
        </c:if>

        <c:if test="${messageInserisci != null}">
            <div class="alert warning">
                <span class="closebtn">&times;</span>
                <strong>${messageInserisci}</strong>
            </div>
        </c:if>

        <c:if test="${messageRimuoviPlusAdmin != null}">
            <div class="alert info">
                <span class="closebtn">&times;</span>
                <strong>${messageRimuoviPlusAdmin}</strong>
            </div>
        </c:if>

        <c:if test="${messageRimuoviAbbonamentoAdmin != null}">
            <div class="alert info">
                <span class="closebtn">&times;</span>
                <strong>${messageRimuoviAbbonamentoAdmin}</strong>
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
        <div class="row" style="height: 90px;">
            <div class="row" style="height: 30px;"></div>
            <center><a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:40px"></a></center>
        </div>
            <div class="row" style="width:100%;">
                <div class="col-sm-6">
                    <center>
                        <h3 class="h2div">Assegna Transponder</h3>
                        <form name="gestisci" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                            <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="nonabbonati">Utenti Senza Transponder</label>
                            <select style="width:50%; margin-bottom: 5px; " class="form-select" id="username" name="username">
                                <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                                   url="jdbc:mysql://localhost:3306/telepass"
                                                   user="ROOT"  password="ROOT"/>

                                <sql:query dataSource="${snapshot}" var="result">
                                    SELECT Username FROM CLIENTE WHERE Amministratore=0 AND TransponderAttivo=0
                                </sql:query>
                                <c:forEach var="row" items="${result.rows}">
                                    <option value=<c:out value="${row.Username}"/>><c:out value="${row.Username}"/></option>
                                </c:forEach>
                            </select><br>
                            <button type="submit" class="btn btn-outline-primary" name="gestisci" value="1">ASSEGNA</button>
                        </form> 
                    </center>
                </div>

                <div class="col-sm-6">
                    <center>
                        <h3 class="h2div">Rimuovi Transponder</h3>
                        <form name="gestisci" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                            <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="abbonati">Utenti Aventi Transponder</label>
                            <select style="width:50%; margin-bottom: 5px; " class="form-select" id="username" name="username">
                                <sql:query dataSource="${snapshot}" var="result">
                                    SELECT Username FROM CLIENTE WHERE Amministratore=0 AND TransponderAttivo=1
                                </sql:query>
                                <c:forEach var="row" items="${result.rows}">
                                    <option value=<c:out value="${row.Username}"/>><c:out value="${row.Username}"/></option>
                                </c:forEach>
                            </select><br>
                            <button type="submit" class="btn btn-outline-primary" name="gestisci" value="0">RIMUOVI</button>
                        </form> 
                    </center>
                </div>

                    <div class="col-sm-6">
                        <center>
                            <h3 class="h2div">Assegna Plus</h3>
                            <form name="gestisci" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                                <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="nonabbonatiplus">Utenti Senza Plus</label>
                                <select style="width:50%; margin-bottom: 5px; " class="form-select" id="username" name="username">
                                    <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                                       url="jdbc:mysql://localhost:3306/telepass"
                                                       user="ROOT"  password="ROOT"/>

                                    <sql:query dataSource="${snapshot}" var="result">
                                        SELECT Username FROM CLIENTE WHERE Amministratore=0 AND TransponderAttivo=1 AND Plus=0
                                    </sql:query>
                                    <c:forEach var="row" items="${result.rows}">
                                        <option value=<c:out value="${row.Username}"/>><c:out value="${row.Username}"/></option>
                                    </c:forEach>
                                </select><br>
                                <button type="submit" class="btn btn-outline-primary" name="gestisci" value="3">ASSEGNA</button>
                            </form>
                        </center>
                    </div>

                    <div class="col-sm-6">
                        <center>
                            <h3 class="h2div">Rimuovi Plus</h3>
                            <form name="gestisci" class="rounded" style="width:100%;" method="POST" action="gestisciAbbonamento">
                                <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="nonabbonati">Utenti Iscritti a Plus</label>
                                <select style="width:50%; margin-bottom: 5px; " class="form-select" id="username" name="username">
                                    <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                                       url="jdbc:mysql://localhost:3306/telepass"
                                                       user="ROOT"  password="ROOT"/>

                                    <sql:query dataSource="${snapshot}" var="result">
                                        SELECT Username FROM CLIENTE WHERE Amministratore=0 AND TransponderAttivo=1 AND Plus=1
                                    </sql:query>
                                    <c:forEach var="row" items="${result.rows}">
                                        <option value=<c:out value="${row.Username}"/>><c:out value="${row.Username}"/></option>
                                    </c:forEach>
                                </select><br>
                                <button type="submit" class="btn btn-outline-primary" name="gestisci" value="2">RIMUOVI</button>
                            </form>
                        </center>
                    </div>
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