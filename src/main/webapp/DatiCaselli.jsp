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
<jsp:include page="PrivilegiAdmin"></jsp:include>

<nav class="navbar navbar-expand-lg bg-light"><a class="navbar-brand" href="protected_area_admin.jsp"><img src="images/Logo_Telepass_2021.png" style="height:30px; margin-left: 5px;"></a></nav>
<center>
    <div class="row">
        <center>
        <div class="col-md-8">
            <c:choose>
                <c:when test="${azione==1}">
                    <h2 class="h2div">Dati Entrate Caselli ${mese} 2023</h2>
                </c:when>

                <c:otherwise>
                    <h2 class="h2div">Dati Uscite Caselli  ${mese} 2023</h2>
                </c:otherwise>
            </c:choose>
            <table class="table" style="border-width: 3px; border-color:#0d6efd; border-style: solid ">
                <thead style="background-color: #0d6efd; color:white">
                <tr>
                    <th scope="col" style="text-align: center;">Nome Casello</th>
                    <c:choose>
                        <c:when test="${azione==1}">
                            <th scope="col" style="text-align: center;">Passanti Entranti</th>
                        </c:when>

                        <c:otherwise>
                            <th scope="col" style="text-align: center;">Passanti Uscenti</th>
                        </c:otherwise>
                    </c:choose>
                </tr>
                </thead>
                <tbody>

                <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                   url="jdbc:mysql://localhost:3306/telepass"
                                   user="ROOT"  password="ROOT"/>

                <c:choose>
                    <c:when test="${azione==1}">
                        <sql:query dataSource="${snapshot}" var="result">
                            SELECT NomeCasello, COUNT(NomeCasello) AS QUANTI
                            FROM entra
                            WHERE YEAR(OrarioEntrata)=YEAR(CURRENT_DATE) AND MONTH(OrarioEntrata)=${numMese}
                            GROUP BY NomeCasello
                            ORDER BY NomeCasello
                        </sql:query>
                    </c:when>

                    <c:otherwise>
                        <sql:query dataSource="${snapshot}" var="result">
                            SELECT NomeCasello, COUNT(NomeCasello) AS QUANTI
                            FROM esce
                            WHERE YEAR(OrarioUscita)=YEAR(CURRENT_DATE) AND MONTH(OrarioUscita)=${numMese}
                            GROUP BY NomeCasello
                            ORDER BY NomeCasello
                        </sql:query>
                    </c:otherwise>
                </c:choose>


                <c:forEach var="row" items="${result.rows}">
                    <tr class="table">
                        <td style="text-align: center;"><c:out value="${row.NomeCasello}"/></td>
                        <td style="text-align: center;"><c:out value="${row.QUANTI}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </center>
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
