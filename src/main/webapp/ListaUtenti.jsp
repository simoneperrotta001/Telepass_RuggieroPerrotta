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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

</head>
<body>
<%
  if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "") && (session.getAttribute("ruolo") == null) || (session.getAttribute("ruolo") == "")) {
    response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/Accedi.jsp");
  }
  int ruolo= (int) session.getAttribute("ruolo");
  if(ruolo == 0) {response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_utente.jsp");}
%>
<nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px; margin-left: 5px;"></a></nav>
<center>
  <div>
    <div class="col-md-8">
      <table class="table" style="border-width: 3px; border-color:#0d6efd; border-style: solid ">
        <thead style="background-color: #0d6efd; color:white">
        <tr>
          <th scope="col" style="text-align: center;">Nome Utente</th>
          <th scope="col" style="text-align: center;">Cognome Utente</th>
          <th scope="col" style="text-align: center;">Nascita Cliente</th>
          <th scope="col" style="text-align: center;">Codice Transponder</th>
          <th scope="col" style="text-align: center;">Username</th>
          <th scope="col" style="text-align: center;">Codice Conto Corrente</th>
          <th scope="col" style="text-align: center;">Abbonamento</th>
          <th scope="col" style="text-align: center;">Telepass+</th>
          <th scope="col" style="text-align: center;">Modifica</th>
        </tr>
        </thead>
        <tbody>

        <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/telepass"
                           user="ROOT"  password="ROOT"/>

        <sql:query dataSource="${snapshot}" var="result">
          SELECT * FROM CLIENTE WHERE Amministratore=0;
        </sql:query>
        <c:forEach var="row" items="${result.rows}">
          <c:choose>
            <c:when test="${row.TransponderAttivo==1  && row.Plus==1}">
                <tr class="table-success">
            </c:when>

            <c:when test="${row.TransponderAttivo==1  && row.Plus==0}">
                <tr class="table-primary">
            </c:when>
            <c:otherwise>
              <tr class="table-danger">
            </c:otherwise>
          </c:choose>

          <td style="text-align: center;"><c:out value="${row.NomeCliente}"/></td>
          <td style="text-align: center;"><c:out value="${row.CognomeCliente}"/></td>
          <td style="text-align: center;"><c:out value="${row.NascitaCliente}"/></td>
          <td style="text-align: center;"><c:out value="${row.CodiceTransponder}"/></td>
          <td style="text-align: center;"><c:out value="${row.Username}"/></td>
          <td style="text-align: center;"><c:out value="${row.CodiceContoCorrente}"/></td>
          <c:choose>
            <c:when test="${row.TransponderAttivo==1}">
              <td style="text-align: center;"><span class="badge rounded-pill text-bg-success">Attivo</span></td>
            </c:when>
            <c:otherwise>
              <td style="text-align: center;"><span class="badge rounded-pill text-bg-danger">Disattivo</span></td>
            </c:otherwise>
          </c:choose>

          <c:choose>
            <c:when test="${row.Plus==1}">
              <td style="text-align: center;"><span class="badge rounded-pill text-bg-success">Attivo</span></td>
            </c:when>

            <c:otherwise>
              <td style="text-align: center;"><span class="badge rounded-pill text-bg-danger">Disattivo</span></td>
            </c:otherwise>
          </c:choose>
          <form action="modificaUtenteSelezionato" method="POST">
            <td style="text-align: center;"><a href="#"><button class="btn btn-sm btn-primary" name="username" value=${row.Username}><i class="bi bi-pencil-square"></i></button></a></td>
          </form>

          </tr>
        </c:forEach>
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