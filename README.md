# Server Documentation

## Authors
Timon Stronczek, Justus Pfaue and Bonnie Jo Mayer

## API
chaches the username and the gameid until the game was left or client is closed
| command | function |
| ------ | ------ |
| createRoom(String username) | creates a game, adds the user and returns the gameid |
| joinRoom(String username, String gameid) | joins the game with the given username |
| startGame() | starts the game |
| updateWaiting() | returns if game started and all players |
| isGod() | returns if player is god |
| setWord() | sets given word |
| isStarted() | returns if the game started already |
| hasWord() | retruns if the wor is set |
| guess() | guesses the letter |
| getMistakes() | all mistakes that were made |
| updateGame() | return all neaded values |
| getWinner() | returns the winner |
| quitGame() | removes the player from the game |
| restartGame() | restarts the game |
| close() | removes user from game |
| getScoreboard() | returns top 10 user with most wins |


## used packages (API)
- org.json.*
- java.io.*
- java.net.*
- java.util.*


## usesd packages (Logic)
- java.io.*
- java.util.*


## usesd packages (Utils)
- none
