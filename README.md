# Instrukcja korzystania z backendu:

## Testowanie żądania

```shell
curl -H "X-Authorization: [token]" http://localhost:8080/test
```

Aby uzyskać token należy albo zalogowac się na front i z tamtąd słać żądania, albo wysłać żądanie

```shell
curl --request POST --url 'https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=[KLUCZ]' -- header 'Content-Type: application/json' --data '{
  "email": "[email]",
  "password": "[haslo]",
  "returnSecureToken": true
}'
```

## Firebase
Klucz do api jest w firebase w ustawieniach w **project settings**
zaś po klucz prywatny napiszcie do mnie maila, to go Wam udostępnie.
Klucz nalezy umieścić w repozytorium w katalogu `src/main/resources` i nazwać `ziwg-2023-firebase.json`