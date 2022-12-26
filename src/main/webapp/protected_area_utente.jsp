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
    <nav class="navbar navbar-expand-lg bg-light">
      <a class="navbar-brand" href="protected_area_utente.html"><img src="Logo_Telepass_2021.png" style="height:30px;"></a>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
            <a class="nav-link disabled">Ciao, NomeUtente</a>
          </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Operazioni
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="TelepassPlus.html">Passa a Telepass+</a></li>
            <li><a class="dropdown-item" href="AggiungiVeicolo.html">Aggiungi Veicolo</a></li>
            <li><a class="dropdown-item" href="ProfiloUtente.html">Visualizza Profilo</a></li>
            <li><a class="dropdown-item" href="SimulaPercorso.html">Simula Percorso</a></li>
          </ul>
        </li>
      </ul>
      <a href="index.html"><button type="button" class="btn btn-primary">ESCI</button></a> 
    </nav>

    <div id="carouselExampleDark" class="carousel carousel-dark slide rounded" data-bs-ride="carousel" style="height: 500px;">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner rounded">
        <div class="carousel-item active" data-bs-interval="8000">
          <img src="Autostrada.jpg" class="d-block w-100" alt="..." style="height: 500px;">
          <div class="carousel-caption d-none d-md-block">
            <h5>First slide label</h5>
            <p>Some representative placeholder content for the first slide.</p>
          </div>
        </div>
        <div class="carousel-item rounded" data-bs-interval="8000">
          <img src="Autostrada.jpg" class="d-block w-100" alt="..." style="height: 500px;">
          <div class="carousel-caption d-none d-md-block">
            <h5>Second slide label</h5>
            <p>Some representative placeholder content for the second slide.</p>
          </div>
        </div>
        <div class="carousel-item rounded" data-bs-interval="8000">
          <img src="Autostrada.jpg" class="d-block w-100" alt="..." style="height: 500px;">
          <div class="carousel-caption d-none d-md-block">
            <h5>Third slide label</h5>
            <p>Some representative placeholder content for the third slide.</p>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>

    <div id="main">
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
              <div><img src="telepass-bip_low.png" style="height: 250px; width: 400px;"></div>
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
              <img src="Logo_Telepass_2021.png" style="height:50px;">
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