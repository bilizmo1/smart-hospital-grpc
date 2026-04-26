# Smart Hospital - middleware project

Projekt zrealizowany w technologii gRPC, który przedstawia  system zdalnego zarządzania urządzeniami szpitala.

## Opis

Aplikacja umożliwia zdalne sterowanie urządzeniami takimi jak:
- Monitor 
- Łóżko szpitalne 
- Kamera, w pokoju pacjenta

System został podzielony na dwa oddziały:

- **Odział Kardiologii**
- **Oddział Chirurgii**

Każdy oddział udostępnia zestaw urządzeń:
- monitory pacjenta
- łóżka szpitalne
- kamery monitoringu

## Technologia

- **Serwer:** Java
- **Klient:** Python
- **Middleware:** gRPC
- **Build tool:** Maven

## Typy urządzeń

### 1. Monitor Pacjenta
- pobranie stanu
- włączenie/wyłączenie
- włączenie/wyłączenie alarmu
- ustawienie progów alarmowych(HR, saturacja)


### 2. Łóżko
- pobranie stanu
- włączenie/wyłączenie
- ustawienie wysokości
- blokada/odblokowanie kółek
- oznaczenie jako zajęte/wolne
- włączenie/wyłączenie trybu awaryjnego

### 3. Kamera
Obsługiwane operacje:
- pobranie stanu
- włączenie/wyłączenie
- włączenie/wyłączenie nagrywania
- zmiana pozycji kamery w pionie, poziomie, zoom


