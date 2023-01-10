<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
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

    <nav class="navbar navbar-expand-lg bg-light">
      <a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px; margin-left: 5px"></a>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
            <a class="nav-link disabled">Ciao, ${sessionScope.username}</a>
          </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Operazioni
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="CreazioneUtenti.jsp">Creazione Utente</a></li>
            <li><a class="dropdown-item" href="GestisciTransponder.jsp">Gestisci Transponder</a></li>
            <li><a class="dropdown-item" href="ListaUtenti.jsp">Lista Utenti</a></li>
          </ul>
        </li>
      </ul>
      <a href="logout" style="margin-right: 5px;"><button type="button" class="btn btn-primary">ESCI</button></a>
    </nav>
<!--JSTL che a seconda se c'è il messaggio inviato dalle varie servlet lo fa comparire
a schermo -->
<c:if test="${messageUtenteInserito != null}">
    <div class="alert success">
        <span class="closebtn">&times;</span>
        <strong>${messageUtenteInserito}</strong>
    </div>
</c:if>
<c:if test="${messageAdmin != null}">
    <div class="alert warning">
        <span class="closebtn">&times;</span>
        <strong>${messageAdmin}</strong>
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

    <div id="main">

        <div class="row">
            <center>
                <br><h3 class="h2div">Classifica utenti con più viaggi</h3>
                <div class="col-sm-9">
                    <table class="table" style="border-width: 3px; border-color:#0d6efd; border-style: solid ">
                        <thead style="background-color: #0d6efd; color:white">
                        <tr>
                            <th scope="col" style="text-align: center;">Nome Utente</th>
                            <th scope="col" style="text-align: center;">Cognome Utente</th>
                            <th scope="col" style="text-align: center;">Codice Transponder</th>
                            <th scope="col" style="text-align: center;">Username</th>
                            <th scope="col" style="text-align: center;">Viaggi fatti con Telepass</th>
                        </tr>
                        </thead>
                            <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                               url="jdbc:mysql://localhost:3306/telepass"
                                               user="ROOT"  password="ROOT"/>

                            <sql:query dataSource="${snapshot}" var="result">
                            SELECT COUNT(*) AS QUANTI, C.CodiceTransponder, V.Username, V.NomeCliente, V.CognomeCliente
                            FROM contaquantiviaggi c join cliente v on c.CodiceTransponder=v.CodiceTransponder
                            GROUP BY C.CodiceTransponder
                            ORDER BY QUANTI DESC
                            LIMIT 3
                            </sql:query>
                            <c:forEach var="row" items="${result.rows}">
                            <tr class="table">
                                <td style="text-align: center;"><c:out value="${row.NomeCliente}"/></td>
                                <td style="text-align: center;"><c:out value="${row.CognomeCliente}"/></td>
                                <td style="text-align: center;"><c:out value="${row.CodiceTransponder}"/></td>
                                <td style="text-align: center;"><c:out value="${row.Username}"/></td>
                                <td style="text-align: center;"><c:out value="${row.QUANTI}"/></td>
                            </tr>
                            </c:forEach>
                            </tbody>
                    </table>
                </div>
            </center>
        </div>


        <div class="row" style="margin-top: 10px">
            <div class="col-sm-6">
                <center>
                <h3 class="h2div">Visualizza le entrate dai caselli per mese</h3>
                <form name="caselli" class="rounded" style="width:100%;" method="POST" action="datiCaselli">
                    <select style="width:50%; margin-bottom: 5px; " class="form-select" id="mese" name="mese">
                        <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                           url="jdbc:mysql://localhost:3306/telepass"
                                           user="ROOT"  password="ROOT"/>

                        <sql:query dataSource="${snapshot}" var="result">
                            SELECT DISTINCT MONTH(OrarioEntrata) AS MESE FROM ENTRA WHERE YEAR(OrarioEntrata)=YEAR(CURRENT_DATE);
                        </sql:query>
                        <c:forEach var="row" items="${result.rows}">
                            <c:if test="${row.MESE == 1}">
                                <option value=<c:out value="${row.MESE}"/>>Gennaio</option>
                            </c:if>

                            <c:if test="${row.MESE == 2}">
                                <option value=<c:out value="${row.MESE}"/>>Febbraio</option>
                            </c:if>

                            <c:if test="${row.MESE == 3}">
                                <option value=<c:out value="${row.MESE}"/>>Marzo</option>
                            </c:if>

                            <c:if test="${row.MESE == 4}">
                                <option value=<c:out value="${row.MESE}"/>>Aprile</option>
                            </c:if>

                            <c:if test="${row.MESE == 5}">
                                <option value=<c:out value="${row.MESE}"/>>Maggio</option>
                            </c:if>

                            <c:if test="${row.MESE == 6}">
                                <option value=<c:out value="${row.MESE}"/>>Giugno</option>
                            </c:if>

                            <c:if test="${row.MESE == 7}">
                                <option value=<c:out value="${row.MESE}"/>>Luglio</option>
                            </c:if>

                            <c:if test="${row.MESE == 8}">
                                <option value=<c:out value="${row.MESE}"/>>Agosto</option>
                            </c:if>

                            <c:if test="${row.MESE == 9}">
                                <option value=<c:out value="${row.MESE}"/>>Settembre</option>
                            </c:if>

                            <c:if test="${row.MESE == 10}">
                                <option value=<c:out value="${row.MESE}"/>>Ottobre</option>
                            </c:if>

                            <c:if test="${row.MESE == 11}">
                                <option value=<c:out value="${row.MESE}"/>>Novembre</option>
                            </c:if>

                            <c:if test="${row.MESE == 12}">
                                <option value=<c:out value="${row.MESE}"/>>Dicembre</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <button name=azione value=1 type="submit" class="btn btn-outline-primary">VISUALIZZA</button>
                </form>
                </center>
            </div>

            <div class="col-sm-6">
                <center>
                <h3 class="h2div">Visualizza le uscite dai caselli per mese</h3>
                <form name="caselli" class="rounded" style="width:100%;" method="POST" action="datiCaselli">
                    <select style="width:50%; margin-bottom: 5px; " class="form-select" id="mese" name="mese">
                        <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                           url="jdbc:mysql://localhost:3306/telepass"
                                           user="ROOT"  password="ROOT"/>

                        <sql:query dataSource="${snapshot}" var="result">
                            SELECT DISTINCT MONTH(OrarioUscita) AS MESE FROM ESCE WHERE YEAR(OrarioUscita)=YEAR(CURRENT_DATE);
                        </sql:query>
                        <c:forEach var="row" items="${result.rows}">
                            <c:if test="${row.MESE == 1}">
                                <option value=<c:out value="${row.MESE}"/>>Gennaio</option>
                            </c:if>

                            <c:if test="${row.MESE == 2}">
                                <option value=<c:out value="${row.MESE}"/>>Febbraio</option>
                            </c:if>

                            <c:if test="${row.MESE == 3}">
                                <option value=<c:out value="${row.MESE}"/>>Marzo</option>
                            </c:if>

                            <c:if test="${row.MESE == 4}">
                                <option value=<c:out value="${row.MESE}"/>>Aprile</option>
                            </c:if>

                            <c:if test="${row.MESE == 5}">
                                <option value=<c:out value="${row.MESE}"/>>Maggio</option>
                            </c:if>

                            <c:if test="${row.MESE == 6}">
                                <option value=<c:out value="${row.MESE}"/>>Giugno</option>
                            </c:if>

                            <c:if test="${row.MESE == 7}">
                                <option value=<c:out value="${row.MESE}"/>>Luglio</option>
                            </c:if>

                            <c:if test="${row.MESE == 8}">
                                <option value=<c:out value="${row.MESE}"/>>Agosto</option>
                            </c:if>

                            <c:if test="${row.MESE == 9}">
                                <option value=<c:out value="${row.MESE}"/>>Settembre</option>
                            </c:if>

                            <c:if test="${row.MESE == 10}">
                                <option value=<c:out value="${row.MESE}"/>>Ottobre</option>
                            </c:if>

                            <c:if test="${row.MESE == 11}">
                                <option value=<c:out value="${row.MESE}"/>>Novembre</option>
                            </c:if>

                            <c:if test="${row.MESE == 12}">
                                <option value=<c:out value="${row.MESE}"/>>Dicembre</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <button name=azione value=2 type="submit" class="btn btn-outline-primary">VISUALIZZA</button>
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
            © 2022 Copyright:
            <a class="text-white" href="#">Telepass.com</a>
          </div>
          <!-- Copyright -->
        </footer>
        <!-- Footer -->
      </section>
</body>
</html>