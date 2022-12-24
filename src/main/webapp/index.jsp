<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    <style>
        #main{margin-left: 150px;
            margin-right: 150px;
        }
        #pbody{
            font-family: inherit;
            font-size:medium;
            text-align: justify;
            margin-top: 10px;
        }
        #pdiv{
            font-family: inherit;
            font-size:medium;
        }
        #indexfooter{
            position:relative;
            bottom:0px;
            left: 0px;
            right:0px;
            margin-bottom:0px;
            width: 100%;
            overflow: hidden;
            color:#fff;
            margin-top:5px;
        }
        #mydiv{
            align-items:self-end;
        }
        #h2div{
            color:#0d6efd;
            font-family: inherit;
        }
        a{
            font-family: inherit;
        }
        button{
            font-family: inherit;
        }

    </style>
    <title>Telepass</title>
    <link rel="shortcut icon" href="https://logo.clearbit.com/telepass.com">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
    <a class="navbar-brand" href="index.html"><img src="images/Logo_Telepass_2021.png" style="height:30px;"></a>
    <div class="pagination justify-content-end" style="width:100%; height:100%">
        <div id="mydiv">
            <a href="Accedi.jsp"><button type="button" class="btn btn-primary">ACCEDI</button></a>
        </div>
    </div>
</nav>

<img src="images/Autostrada.jpg" style="width: 100%; height:500px; border-radius: 15%;">
<div id="main">
    ${inputServlet}
    ${classe}
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
            <h2 id="h2div">TRANSPONDER</h2>
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