Enescu Maria 321CA

# Tema 1 PA:

Toate problemele au structura scheletului problemelor din laboratoare,
logica de baza fiind implementata in metoda `getResult()`.

## Servere:

Pentru aceasta problema am folosit cautarea binara pentru a determina
valoarea minima a discrepantei de putere, prin reducerea progresiva a intervalului de cautare, astfel:
- Am initializat capetele intervalului pentru cautare: 0 in partea inferioara si
valoarea maxima a limitei de alimentare a serverelor gasita la citire in partea superioara;
- Cat timp diferenta dintre capetele intervalului este mai mare decat preciazia aleasa:
  - calculez mijlocul intervalului;
  - calculez mismatch-ul de putere pentru capetele intervalului ajustate de precizie, cat si in mijloc,
  folosind metoda `getMinimumPowerMismatch`;
  - daca algoritmul a gasit valoarea de varf a puterii:
    - o returneaza si programul se opreste;
    - altfel, se continua cautarea in jumatatea stanga sau dreapta a intervalului;

Complexitate: O(N * log(N))

## Colorare:

Initial am creat un nou obiect `Pair`, iar pe parcursul citirii inputului
am adaugat in lista de obiecte de tip `Pair` fiecare pereche (X T) citita, reprezentand
numarul de zone din tablou si tipul acestor.
Pentru aceasta problema am scos 4 cazuri de baza ce implica 4 formule secventiale diferite, asadar:
- Cand am o singura zona:
  - (X H) => formula: 6 * (3 ^ (X - 1))
  - (X V) => formula: 3 * (2 ^ (X - 1))
- Cand 2 zone consecutive sunt de acelasi tip este o continuare a formulelor anterioare, 
deoarece daca le-am lasa pe aceleasi fara modificari puterea ar fi eronata (^ (X1 - 1 + X2 - 1)):
  - (X1 H) (X2 H) <=> (X1 + X2)H  => formula: 6 * (3 ^ (X1 + X2 - 1))
  - (X1 V) (X2 V) <=> (X1 + X2)V  => formula: 3 * (2 ^ (X1 + X2 - 1))
- Cand 2 zone consecutive sunt de tipuri diferite:
  - V si restul (X H) => formula: 2 * (3 ^ (X - 1))
  - H si restul (X V) => formula: 1 * (2 ^ (X - 1))

Complexitate: O(X * K)

## Compresie:

Pentru aceasta problema am ales o rezolvare iterativa care parcurge simultan
ambele siruri `A` si `B`.
- `i`, `j` = utili pentru a itera prin cele 2 siruri;
- `count` = contor pentru numararea elementelor egale de dupa comprimari;
- daca sumele subsecventelor din `A` si `B` devin egale, nu este nevoie de comprimare,
deci resetam sumele, continuam iteratia prin siruri si actualizam contorul, semn de egalitate al
elementelor gasite;
- daca nu sunt egale, construim sumele subsecventelor pentru comprimare;
- in final, daca un sir s-a terminat si celalalt nu, inseamna ca nu avem siruri egale, returnam -1,
altfel count-ul de elemente egale gasite.

Complexitate: O(n + m)

## Criptare:

Ideea de baza pentru aceasta problema este de a construi o parola pentru
fiecare litera si de a alege cea mai lunga parola valida care indeplineste
criteriul de litera dominanta (algoritmul folosit este Greedy), asadar:
- Prin metoda `addAllLetters()` am populat lista de caractere `allLetters` cu toate literele
distincte gasite in lista de cuvinte `words`;
- Am parcurs fiecarei litere din `allLetters`, astfel:
  - Fiecare litera isi are propriul portofoliu de cuvinte (`portfolioWords`)
  ce reprezinta o lista de obiecte de tipul `WordRatio` in care retine cuvantul si raportul
  dintre numarul de aparitii a literei curente in actualul cuvant (prin metoda
  `countDominantLetter()`) si numarul total de litere din acel cuvant;
  - Sortez descrescator `portfolioWords` dupa raport;
  - Construiesc parola pentru litera curenta in asa fel:
    - adaug la `password` fiecare cuvant din potofoliul de cuvinte al literei curente;
    - apoi incep a elimina cuvantul din `password` daca nu indeplineste conditia
    de litera dominanta;
  - Din toate parolele valide create, o selectez pe cea mai lunga;

Complexitate: O(N * log(N))

## Oferta:

Rezolvarea cuprinde doar cazul cand K = 1, fara bonus.
Pentru aceasta problema folosesc un vector `dp` tipic programarii dinamice,
in care tin costul minim pentru produse (in dp[1] este costul pentru primul produs).
Calculez apoi costul minim pentru primele produse pe cazurile:
- daca produsul curent este cumparat singur;
- daca grupam produsul curent cu produsul precedent, gasesc minimul dintre pretul lor si aplic
reducere de 50% pentru cel mai ieftin;
- daca grupam produsul curent cu 2 precedente lui, gasesc minimul dintre pretul produselor si cel
mai ieftin va fi gratuit, deci il scad din pretul total;
Ulterior returnez ultima pozitie din dp unde s-a acumulat suma toata.

Complexitate: O(N)