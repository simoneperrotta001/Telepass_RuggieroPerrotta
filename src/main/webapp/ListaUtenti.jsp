<%@ page import="java.sql.*" %>
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
<body>
<% if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "") && (session.getAttribute("ruolo") == null) || (session.getAttribute("ruolo") == "")) {
  response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/Accedi.jsp");
}
  int ruolo= (int) session.getAttribute("ruolo");
  if(ruolo == 0) {response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_utente.jsp");}
%>
<div>
<nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px;"></a></nav>
<center>
  <div>
    <div class="col-md-8">
      <table class="table" style="border-width: 3px; border-color:#0d6efd; border-style: solid ">
        <thead style="background-color: #0d6efd; color:white">
        <tr>
          <th scope="col">Nome Utente</th>
          <th scope="col">Cognome Utente</th>
          <th scope="col">Nascita Cliente</th>
          <th scope="col">Codice Transponder</th>
          <th scope="col">Username</th>
          <th scope="col">Codice Conto Corrente</th>
          <th scope="col">Abbonamento</th>
          <th scope="col">Telepass+</th>
          <th scope="col">Modifica</th>
          <th scope="col">Elimina</th>
        </tr>
        </thead>
        <tbody>
        <%
          int abbonamento, plus;
          Connection connection=null;
          Statement stm=null;
          ResultSet rs=null;
          try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            stm= connection.createStatement();
            rs= stm.executeQuery("SELECT * FROM CLIENTE");
            while(rs.next()){
              abbonamento = rs.getInt("TransponderAttivo");
              plus = rs.getInt("Plus");
              if(abbonamento == 1 && plus == 1){%>
                  <tr class="table-success">
              <%}
              else if (abbonamento ==1 && plus==0) {%>
                  <tr class="table-primary">
              <%}
              else {%>
                  <tr class="table-danger">
              <%}%>
                  <td><%=rs.getString("NomeCliente")%></td>
                  <td><%=rs.getString("CognomeCliente")%></td>
                  <td><%=rs.getString("NascitaCliente")%></td>
                  <td><%=rs.getString("CodiceTransponder")%></td>
                  <td><%=rs.getString("Username")%></td>
                  <td><%=rs.getString("CodiceContoCorrente")%></td>
                  <%if(abbonamento == 1){%>
                    <td><span class="badge rounded-pill text-bg-success">Attivo</span></td>
                  <% }else {%>
                    <td><span class="badge rounded-pill text-bg-danger">Disattivo</span></td><% }%>
                  <%if(plus == 1){%>
                    <td><span class="badge rounded-pill text-bg-success">Attivo</span></td>
                  <% }else {%>
                    <td><span class="badge rounded-pill text-bg-danger">Disattivo</span></td><% }%>
                  <td><a href="GestisciTransponder.jsp"><button class="btn btn-sm btn-primary"><i class="bi bi-pencil-square"></i></button></a></td>
                  <td><button class="btn btn-sm btn-danger"><i class="bi bi-trash3-fill"></i></button></td>
                  </tr>
        <%  }
          }
          catch (Exception e) {
            System.out.println("errore nella connessione");
          }
          finally {
            if (rs != null) {
              try {
                rs.close();
              } catch (Exception e) {System.out.println("rs non chiuso");}
            }
            if (stm != null) {
              try {
                stm.close();
              } catch (Exception e) { System.out.println("stm non chiuso");}
            }
            if (connection != null) {
              try {
                connection.close();
              } catch (Exception e) { System.out.println("connection non chiuso");}
            }
          }
        %>
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