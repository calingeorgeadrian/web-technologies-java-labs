# Board Games Journal Shop

Proiectul este format din 3 aplicatii: 
**Admin Dashboard**: BGJ-Shop-Admin
**Public Client**: BGJ-Shop-Public
**Backend**: BGJ-Shop-Backend

Dashboard-ul pentru Admin si Clientul Public sunt reprezinta aplicatii front-end construite cu Angular ce trimit request-uri catre aplicatia de back-end

Cele doua aplicatii sunt configurate sa poata fi pornite in paralel, pe porturi diferite. Pentru a le porni, se executa urmatorii pasi pentru fiecare dintre acestea:
1. Deschideti proiectul in Visual Studio
2. In Solution Explorer se face click dreapta pe titlul proiectului > Open Developer Command Prompt
3. npm install
4. Dupa ce se termina de instalat dependintele, se executa "npm start"

In acest moment cele doua aplicatii ar trebui sa fie pornite la urmatoarele adrese:
Dashboard: http://localhost:4401/
Public Client: http://localhost:4200/

Aplicatia de Backend reprezinta o aplicatie Java Spring si se poate porni direct din IntelliJ. Datele sunt stocate intr-o baza de date MySQL. Pentru crearea acesteia, se executa script-ul din fisierul schema.sql in MySQL Workbench.


#### Admin Dashboard
La pornire, aplicatia va prezenta utilizatorului pagina de Login. Acesta trebuie sa introduca username-ul si parola corespunzatoare Administratorului pentru a putea accesa Dashboard-ul:

**Username**: admin
**Password**: parola12

Acestea pot fi gasite si modificate in fisierul application.properties din aplicatia de back-end.

Dupa ce s-a efectuat logarea cu succes, utilizatorul va fi redirectionat catre Dashboard. Aici poate vedea diferite statistici despre magazin, precum rapoarte de vanzari zilnice, saptamanale si lunare, cele mai populare produse si ultimele comenzi plasate.
De o parte si de alta a paginii se afla cate un meniu ce faciliteaza navigarea catre diferite pagini ale aplicatiei.

Meniul din dreapta ofera scurtaturi catre paginile de creare a unui produs, cod promotional, a unei promotii sau de import a catalogului de produse. De asemenea, ofera raportul zilnic si posibilitatea de deconectare.
Catalogul de produse este importat folosind API-ul site-ului BoardGameGeek si preia colectia de jocuri a unui utilizator (in cazul de fata, colectia mea).

La crearea unui produs, utilizatorul poate completa manual toate campurile necesare sau poate importa un joc de pe BoardGameGeek completand doar id-ul acestuia de pe site.

Meniul din partea stanga ofera accesul catre diferite tabele si liste de comenzi, produse, promotii sau coduri active. In aceste tabele pot fi vazute diferite informatii despre respectivele entitati si se poate naviga catre entitati individuale pentru a vedea mai multe detalii sau pentru a le edita.

#### Public Client
Acesta este magazinul online propriu-zis unde clientii pot plasa comenzi de jocuri. Un utilizator isi poate crea un cont la care mai apoi sa se conecteze pentru a plasa comenzi sau pentru a adauga produse in wishlist-ul personal.
In partea de sus a aplicatiei se gaseste unu meniu cu doua scurtaturi, Home, ce redirectioneaza catre pagina principala, si Shop, ce duce catre catalogul de produse. De asemenea, in partea dreapta a meniului exista doua iconite ce redirectioneaza catre cosul de cumparaturi respectiv catre contul de utilizator.

Pagina principala prezinta 4 scurtaturi pentru diferite categorii de produse: Board games, Expansions, Under $50 si Under $100. La click pe oricare dintre acestea, utilizatorul va fi trimis catre catalogul de produse unde vor fi active diferite filtre in functie de categoria aleasa.

Catalogul de produse prezinta o galerie de produse disponibile in stoc, alaturi de pretul si o imagine de prezentare a produsului. Acestea pot fi filtrate folosind criteriile din partea stanga. La click pe titlul sau pe imaginea unui produs, utilizatorul va fi redirectionat catre pagina acelui produs de unde va putea sa il adauge in cosul de cumparaturi sau in wishlist.

Pe pagina contului de utilizator se poate vedea o lista de comenzi plasate in trecut de catre acesta. La click pe id-ul unei comenzi, acesta va fi trimis catre o pagina cu mai multe detalii despre aceasta.
Tot aici utilizatorul isi poate vedea datele de livrare si le poate modifica.

Cosul de cumparaturi contine toate produsele pe care utilizatorul doreste sa le cumpere. Aici vor fi completate automat datele de livrare ale utilizatorului. Acesta poate totusi sa schimbe aceste date, in cazul in care doreste sa plaseze comanda pe alt nume sau la o alta adresa.
De asemenea, utilizatorul poate vedea informatii precum costul total al comenzii si poate introduce un cod promotional pentru a obtine o reducere.

In wishlist utilizatorul poate vedea o lista de produse pe care le poate urmari mai usor. De aici poate accesa pagina produsului respectiv sau il poate adauga in cosul de cumparaturi.

#### Backend
Aplicatia de back-end se ocupa de buna functionare a intregului proiect. Aceasta contine controllere, service-uri si repository-uri pentru toate entitatile principale si diferite endpoint-uri pentru fiecare dintre acestea.

