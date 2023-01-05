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
<%
    if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "") && (session.getAttribute("ruolo") == null) || (session.getAttribute("ruolo") == "")) {
        response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/Accedi.jsp");
    }
    int ruolo= (int) session.getAttribute("ruolo");
    if(ruolo == 0) {response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_utente.jsp");}
%>
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

<c:if test="${messageUtenteInserito != null}">
    <div class="alert success">
        <span class="closebtn">&times;</span>
        <strong>${messageUtenteInserito}</strong>
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
                        <c>

                            <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                                               url="jdbc:mysql://localhost:3306/telepass"
                                               user="ROOT"  password="ROOT"/>

                            <sql:query dataSource="${snapshot}" var="result">
                            SELECT COUNT(*) AS QUANTI, C.CodiceTransponder, V.Username, V.NomeCliente, V.CognomeCliente
                            FROM contaquantiviaggi c join cliente v on c.CodiceTransponder=v.CodiceTransponder
                            GROUP BY C.CodiceTransponder
                            ORDER BY QUANTI DESC
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

      <p id="pbody">Telepass è un marchio registrato di proprietà di Atlantia S.p.A. (di cui Telepass S.p.A. è licenziataria) atto a contraddistinguere un sistema di riscossione
            del pedaggio autostradale con l'utilizzo del telepedaggio, introdotto in Italia nel 1989 da Società Autostrade Concessioni e Costruzioni S.p.A. (oggi Telepass S.p.A.).
            <br>Inizialmente installato sulla tratta tra Calenzano - Sesto Fiorentino e Firenze nord in via sperimentale, è stato installato in un primo tempo 
            sull'Autostrada A1 nei caselli delle principali città italiane (Milano, Roma e Napoli) in occasione del Mondiale di calcio 1990, e in seguito 
            l'implementazione si è estesa a tutti i caselli della rete autostradale nazionale. In origine vincolato al singolo veicolo e destinato all'utenza business, 
            dal 1998 è stato esteso alla clientela privata e dal 2005 il servizio è stato abilitato anche per le motociclette.
            <br>Secondo i dati aggiornati all'aprile 2016, circolano 8 milioni di apparecchi Telepass, per un totale di circa 2 milioni di transiti al giorno. 
            Attualmente con il Telepass è possibile pagare anche il pedaggio per il traforo ordinario dello Zovo, per i parcheggi degli aeroporti di Napoli Capodichino, 
            di Milano Malpensa, Linate, Pisa, Torino Caselle, Roma Fiumicino, Bologna e Catania, la fiera di Bologna, l'ospedale "Dell'Angelo" di Mestre e per la ZTL 
            di Milano. 
            <br>Negli ultimi anni, molti parcheggi a pagamento in diverse città italiane hanno iniziato ad accettare il Telepass, sia nel caso di autorimesse 
            che di parcheggi su strada.
            <br>Dal 2015 è possibile possibile utilizzare Telepass per acquistare e ritirare contestualmente i biglietti dei traghetti Caronte & Tourist sullo Stretto di Messina
            tra Messina e Villa San Giovanni tramite piste dedicate e senza passaggio in biglietteria.
            <br>Dal 2016 è attivo il servizio Telepass europeo per i mezzi pesanti.
            Inizialmente abilitato in Italia, Francia, Spagna, Portogallo e Belgio, al 2022 il servizio risulta attivo in Italia, Francia, Spagna, Portogallo, Belgio, Polonia, 
            Austria, Germania, Danimarca, Svezia, Norvegia, Ungheria, Svizzera e Bulgaria, oltre che utilizzabile per i parcheggi convenzionati in Italia, Francia e Spagna e 
            per il traghettamento sullo stretto di Messina.
            <br>Dal 2018 è attivo il servizio Telepass europeo per le auto, valido in Italia, Francia, Spagna e Portogallo ed anche nei parcheggi delle principali città, 
            da Parigi a Barcellona e Madrid</p>
                
            <center>
              <div><img src="images/telepass-bip_low.png" style="height: 250px; width: 400px;"></div>
              <div>
                <h2 class="h2div">TRANSPONDER</h2>
                <p id="pdiv">Il nostro mostro di satan fa questo e questo e quello<br> senza considerare questo e quello</p>
              </div>
            </center> 

    </div>

    <section class="">
        <!-- Footer -->
        <footer class="text-center text-white" style="background-color: #002752;" id="indexfooter">
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