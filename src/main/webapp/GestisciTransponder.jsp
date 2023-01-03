<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
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
<% if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "") && (session.getAttribute("ruolo") == null) || (session.getAttribute("ruolo") == "")) {
    response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/Accedi.jsp");
}
    int ruolo= (int) session.getAttribute("ruolo");
    if(ruolo == 0) {response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_utente.jsp");}
%>
    <div id="main">
        <div class="row" style="height: 90px;">
            <div class="row" style="height: 30px;"></div>
            <center><a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:40px"></a></center>
        </div>
            <div class="row" style="width:100%;">
                <div class="col-sm-6">
                    <center>
                        <h3 class="h2div">Assegna Transponder</h3><br>
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
                        <h3 class="h2div">Rimuovi Transponder</h3><br>
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