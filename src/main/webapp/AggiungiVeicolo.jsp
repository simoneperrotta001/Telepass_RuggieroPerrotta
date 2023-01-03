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
<sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/telepass"
                   user="ROOT"  password="ROOT"/>

<sql:query dataSource="${snapshot}" var="result">
    SELECT COUNT(*) AS QUANTI FROM VEICOLO WHERE CodiceTransponder=${sessionScope.codice}
</sql:query>
<c:forEach var="row" items="${result.rows}">
    <c:if test="${row.QUANTI==2}">
        <c:redirect url="http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_utente.jsp"/>
    </c:if>
</c:forEach>
    <nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_utente.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px;"></a></nav>

    <div class="main">
        <center>
            <div style="width:50%;">
                <center><h2 class="h2div">Inserisci i dati del veicolo</h2></center>
                <form name="addVeicolo" class="rounded" style="width:100%;" method="POST" action="aggiungiVeicolo">
                    <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text" for="targa">Inserisci Targa</label>
                    <input style="width:50%; margin-bottom: 5px; " type="text" class="form-control" id="targa" name="targa" value="" placeholder="AA111AA"><br>
                    <label style="width:50%; margin-top: 10px; background-color: #0d6efd; color:white" class="input-group-text primary" for="classe">Classe</label>
                    <select style="width:50%; margin-bottom: 5px; " class="form-select" id="classe" name="classe">
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select><br>
                    <a href="protected_area_utente.jsp"><button type="button" class="btn btn-outline-primary">Indietro</button></a>
                    <button type="submit" class="btn btn-outline-primary" name="veicolonuovo" id="veicolonuovo">Aggiungi</button>
                </form>
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