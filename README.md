# Projekt-System-Glosowania-Online-w-Javie

# Opis projektu:
Spis treści
1. Wprowadzenie
2. Opis klas:
PollingServer.java
PollingClientInteractive.java
PollingClient.java
Poll.java
3. Instrukcja uruchomienia
4. Opis protokołów i narzędzi
5. Zakończenie

# 1. Wprowadzenie
Celem tego projektu jest stworzenie aplikacji rozproszonej umożliwiającej organizację głosowań. Aplikacja składa się z serwera oraz klientów. Klienci mogą tworzyć głosowania, oddawać głosy i pobierać wyniki głosowań w trybie interaktywnym.
Aplikacja została zaprojektowana w języku Java z wykorzystaniem gniazd sieciowych (sockets), co umożliwia komunikację pomiędzy klientem a serwerem w sposób rozproszony.

# 2. Opis klas

# PollingServer.java
Klasa PollingServer.java jest odpowiedzialna za nasłuchiwanie połączeń od klientów oraz przetwarzanie zapytań wysyłanych przez klientów. Działa w sposób wielowątkowy, dzięki czemu może obsługiwać wielu klientów równocześnie.
Zadania serwera:
1. Nasłuchuje na porcie 12345 na połączenia od klientów.
2. Po połączeniu z klientem, tworzy nowy wątek (PollingThread), który obsługuje zapytania od tego konkretnego klienta.
3. Serwer przechowuje informacje o wszystkich głosowaniach w mapie polls (ID głosowania jako klucz, a Poll jako wartość).
4. Odpowiada na zapytania od klientów dotyczące tworzenia głosowań, oddawania głosów oraz pobierania wyników.
Podstawowe metody:
processMessage(String message) - Metoda analizująca wiadomość od klienta i wykonująca odpowiednią akcję (tworzenie głosowania, oddanie głosu, pobranie wyników).
createPoll(String options) - Tworzy nowe głosowanie na podstawie opcji przekazanych przez klienta.
vote(String voteData) - Rejestruje głos oddany przez użytkownika w określonym głosowaniu.
getResults(int pollId) - Zwraca wyniki głosowania dla określonego ID głosowania.


# PollingClientInteractive.java
Klasa PollingClientInteractive.java to interaktywny klient, który umożliwia użytkownikowi wybór opcji za pomocą menu. Klient komunikuje się z serwerem, wysyłając zapytania dotyczące tworzenia głosowań, oddawania głosów oraz pobierania wyników.
Zadania klienta:
1. Wyświetla interaktywne menu dla użytkownika.
2. Pozwala na wybór opcji takich jak:
Tworzenie głosowania.
Oddawanie głosu.
Pobieranie wyników głosowania.
3. Na podstawie wyboru użytkownika, wysyła odpowiednie zapytanie do serwera i wyświetla odpowiedź.
Podstawowe metody:
showMenu() - Wyświetla dostępne opcje w interaktywnym menu.
main(String[] args) - Główna metoda klienta, która umożliwia interakcję z użytkownikiem i wysyłanie zapytań do serwera.


# PollingClient.java
Klasa PollingClient.java to podstawowy klient, który nie jest interaktywny. Wysyła on zapytania do serwera w sposób "na sztywno", bez interakcji z użytkownikiem.
Zadania klienta:
1. Klient wysyła zapytania o tworzenie głosowań, oddawanie głosów i pobieranie wyników, ale w sposób statyczny.
2. Może być używany do automatycznych testów lub symulacji.
Podstawowe metody:
main(String[] args) - Główna metoda klienta, która wysyła zapytania do serwera.


# Poll.java
Klasa Poll.java reprezentuje pojedyncze głosowanie. Zawiera opcje głosowania, rejestruje oddane głosy i umożliwia pobranie wyników głosowania.
Zadania klasy Poll:
1. Przechowuje dostępne opcje głosowania.
2. Zlicza głosy oddane na poszczególne opcje.
3. Zwraca wyniki głosowania.
Podstawowe metody:
vote(String option) - Zlicza głos oddany na określoną opcję.
getResults() - Zwraca wyniki głosowania (liczba głosów na każdą opcję).

# 3. Instrukcja uruchomienia
Aby uruchomić aplikację, należy wykonać poniższe kroki:

Przygotowanie środowiska:
Upewnij się, że masz zainstalowaną Javę (preferowana wersja 8 lub wyższa).
Zainstaluj IntelliJ IDEA (lub inny IDE, który obsługuje JDK).

Uruchomienie serwera:
Otwórz projekt w IntelliJ IDEA.
Wybierz klasę PollingServer.java i uruchom ją. Serwer będzie nasłuchiwał na porcie 12345.

Uruchomienie klienta:
Otwórz klasę PollingClientInteractive.java lub PollingClient.java w IntelliJ IDEA.
Uruchom klienta. Jeśli uruchamiasz klienta interaktywnego, pojawi się menu do wyboru opcji.
Klient połączy się z serwerem i będzie w stanie wykonywać operacje na głosowaniach.

Interakcja z aplikacją:
W przypadku klienta interaktywnego, wybieraj odpowiednie opcje z menu, aby tworzyć głosowania, oddawać głosy i sprawdzać wyniki.

# 4. Opis protokołów i narzędzi
Protokół komunikacji:
Socket TCP: Aplikacja korzysta z protokołu TCP do komunikacji między klientem a serwerem. Gniazda sieciowe (sockets) są wykorzystywane do wysyłania i odbierania danych w formie tekstowej (string).

Biblioteki:
java.net.Socket: Używane do tworzenia gniazd TCP.
java.io.BufferedReader / PrintWriter: Służą do odbierania i wysyłania danych przez gniazda.

# 5. Zakończenie
Projekt Systemu Głosowania to aplikacja rozproszona, która umożliwia użytkownikom tworzenie głosowań, oddawanie głosów i pobieranie wyników w czasie rzeczywistym. Serwer i klienci komunikują się za pomocą gniazd sieciowych w języku Java. Aplikacja jest modularna, co pozwala na łatwą rozbudowę o nowe funkcjonalności.

