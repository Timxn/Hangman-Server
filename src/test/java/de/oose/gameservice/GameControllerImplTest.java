package de.oose.gameservice;

import de.oose.gameservice.gamelogic.GameControllerImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(Lifecycle.PER_CLASS)
class GameControllerImplTest {
    GameControllerImpl gameController;

    @BeforeEach
    void setUp() {
        gameController = new GameControllerImpl();
    }

    @Nested
    @DisplayName("possible calls while in Startpage")
    class StartpageFunctions {
        @Nested
        class createGame {
            @DisplayName("Test valid cases in createGame")
            @ParameterizedTest
            @ValueSource(strings = {"t", "Test"})
            void createGameSuccessful(String strings) {
                assertDoesNotThrow(() -> gameController.createGame(strings));
            }
            @Test
            @DisplayName("Test invalid cases in createGame")
            void createGameException() {
            }
        }
        @Nested
        class joinGame {
            @Test
            @DisplayName("Test valid cases in joinGame")
            void joinGame() {
                String gameID = gameController.createGame("Test");
                assertDoesNotThrow(() -> gameController.joinGame(gameID, "test"));
                assertDoesNotThrow(() -> gameController.joinGame(gameID, "ffff"));
                assertDoesNotThrow(() -> gameController.joinGame(gameID, "daa"));
            }
            @Test
            @DisplayName("Test invalid cases in createGame")
            void createGameSuccessful() {
                assertThrows(Exception.class, () -> gameController.joinGame("122d", "test"));
                String gameID = gameController.createGame("Test");
                assertThrows(Exception.class, () -> gameController.joinGame(gameID, "Test"));
            }
        }
    }

    @Nested
    @DisplayName("possible calls while in Lobby")
    class LobbyFunctions {

        @Nested
        class getStarted {
            @Test
            @DisplayName("Test valid cases in getStarted")
            void getStarted() {
                try {
                    String gameID = gameController.createGame("Tester");
                    gameController.joinGame(gameID, "anotherTester");
                    assertFalse(gameController.getStarted(gameID));
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in getStarted")
            void getStartedException() {
                try {
                    assertThrows(Exception.class, () -> gameController.getStarted("asdas"));
                    assertThrows(Exception.class, () -> gameController.getStarted(""));
                } catch (Exception e) {
                }
            }
        }

        @Nested
        class getPlayers {
            @Test
            @DisplayName("Test valid cases in getPlayers")
            void getPlayers() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    assertDoesNotThrow(() -> gameController.getPlayers(gameID));
                    gameController.startGame(gameID);
                    assertDoesNotThrow(() -> gameController.getPlayers(gameID));
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in getPlayers")
            void getPlayersException() {
                assertThrows(Exception.class, () -> gameController.getPlayers("asas"));
                assertThrows(Exception.class, () -> gameController.getPlayers("as"));
            }
        }

        @Nested
        class leaveGame {
            @Test
            @DisplayName("Test valid cases in leaveGame")
            void leaveGame() {
                try {
                    String gameID = gameController.createGame("Test");
                    assertDoesNotThrow(() -> gameController.leaveGame(gameID, "Test"));
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in leaveGame")
            void leaveGameException() {
                assertThrows(Exception.class, () -> gameController.leaveGame("asas", "aa"));
                try {
                    String gameID = gameController.createGame("Test");
                    assertThrows(Exception.class, () -> gameController.leaveGame("aaaa", "Test"));
                    assertThrows(Exception.class, () -> gameController.leaveGame(gameID, "test"));
                } catch (Exception e) {
                }
            }
        }

        @Nested
        class startGame {
            @Test
            @DisplayName("Test valid cases in startGame")
            void startGame() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    assertDoesNotThrow(() -> gameController.startGame(gameID));
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in startGame")
            void startGameException() {
                try {
                    String gameID = gameController.createGame("Test");
                    assertThrows(Exception.class, () -> gameController.startGame(gameID));
                    assertThrows(Exception.class, () -> gameController.startGame("lalaa"));
                } catch (Exception e) {
                }
            }
        }
    }

    @Nested
    @DisplayName("possible calls while in Gamepage")
    class GameFunctions {
        @Nested
        class isGod {
            @Test
            @DisplayName("Test valid cases in isGod")
            void isGod() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    gameController.startGame(gameID);
                    assertDoesNotThrow(() -> gameController.isGod(gameID, "Test"));
                } catch (Exception ex) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in isGod")
            void isGodException() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    gameController.startGame(gameID);
                    assertThrows(Exception.class, () -> gameController.isGod("aa", "Test"));
                    assertThrows(Exception.class, () -> gameController.isGod(gameID, "notATest"));
                } catch (Exception ex) {
                }
            }
        }
        @Nested
        class setWord {
            @Test
            @DisplayName("Test valid cases in setWord")
            void setWord() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    gameController.startGame(gameID);
                    if (gameController.isGod(gameID,"Test")) assertDoesNotThrow(() -> gameController.setWord(gameID, "Wort", "Test"));
                    if (gameController.isGod(gameID,"AnotherTest")) assertDoesNotThrow(() -> gameController.setWord(gameID, "Wort", "AnotherTest"));
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in setWord")
            void setWordException() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    assertThrows(Exception.class, () -> gameController.setWord(gameID, "Wort", "Test"));
                    gameController.startGame(gameID);
                    assertThrows(Exception.class, () -> gameController.setWord(gameID, "", "Test"));
                    assertThrows(Exception.class, () -> gameController.setWord(gameID, "Wort", "aaa"));
                    assertThrows(Exception.class, () -> gameController.setWord("aaaaa", "Wort", "Test"));
                } catch (Exception e) {
                }
            }
        }
        @Nested
        class hasWord {
            @DisplayName("Test valid cases in hasWord")
            @ParameterizedTest
            @ValueSource(strings = {"t", "Test"})
            void hasWord(String strings) {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    gameController.startGame(gameID);
                    assertFalse(gameController.getWorded(gameID));
                    if (gameController.isGod(gameID,"Test")) assertDoesNotThrow(() -> gameController.setWord(gameID, "Wort", "Test"));
                    if (gameController.isGod(gameID,"AnotherTest")) assertDoesNotThrow(() -> gameController.setWord(gameID, "Wort", "AnotherTest"));
                    assertTrue(gameController.getWorded(gameID));
                    assertDoesNotThrow(() -> gameController.getWorded(gameID));
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in hasWord")
            void hasWordException() {
                String gameID = gameController.createGame("Test");
                assertThrows(Exception.class, () -> gameController.getWorded("aaaa"));
            }
        }
        @Nested
        class guessWord {
            @Test
            @DisplayName("Test valid cases in guessWord")
            void guessWord() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    gameController.startGame(gameID);
                    assertFalse(gameController.getWorded(gameID));
                    if (gameController.isGod(gameID,"Test")) {
                        gameController.setWord(gameID, "Wort", "Test");
                        assertDoesNotThrow(() -> gameController.guessLetter(gameID, 'a',"AnotherTest"));
                    } else if (gameController.isGod(gameID,"AnotherTest")) {
                        gameController.setWord(gameID, "Wort", "AnotherTest");
                        assertDoesNotThrow(() -> gameController.guessLetter(gameID, "a".charAt(0),"Test"));
                    }
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in guessWord")
            void guessWordException() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    gameController.startGame(gameID);
                    assertFalse(gameController.getWorded(gameID));
                    if (gameController.isGod(gameID,"Test")) gameController.setWord(gameID, "Wort", "Test");
                    if (gameController.isGod(gameID,"AnotherTest")) gameController.setWord(gameID, "Wort", "AnotherTest");
                    if (!gameController.isGod(gameID, "Test")) {
                    }
                    assertThrows(Exception.class, () -> gameController.guessLetter(gameID, "a".charAt(0),"Ttest"));
                    assertThrows(Exception.class, () -> gameController.guessLetter("aaaaa", "a".charAt(0),"Test"));
                    assertThrows(Exception.class, () -> gameController.guessLetter("aaaaa", "a".charAt(0),"AnotherTest"));
                } catch (Exception e) {
                }
            }
        }
    }

    @Nested
    @DisplayName("possible calls while in AfterGamePage")
    class AfterGame {
        @Nested
        class getWinner {
            @Test
            @DisplayName("Test valid cases in getWinner")
            void getWinnerSuccessful() {
                try {
                    String gameID = gameController.createGame("Test");
                    gameController.joinGame(gameID, "AnotherTest");
                    gameController.startGame(gameID);
                    assertFalse(gameController.getWorded(gameID));
                    if (gameController.isGod(gameID,"Test")) {
                        gameController.setWord(gameID, "ww", "Test");
                        gameController.guessLetter(gameID, 'w', "AnotherTest");
                        // prepare Environment -> play one round and then get the winner
                        assertEquals("AnotherTest", gameController.getWinnerOfGame(gameID));
                    } else if (gameController.isGod(gameID,"AnotherTest")) {
                        gameController.setWord(gameID, "ww", "AnotherTest");
                        gameController.guessLetter(gameID, 'w', "Test");
                        // prepare Environment -> play one round and then get the winner
                        assertEquals("Test", gameController.getWinnerOfGame(gameID));
                    }
                } catch (Exception e) {
                }
            }
            @Test
            @DisplayName("Test invalid cases in getWinner")
            void getWinnerException() {
                try {
                    assertThrows(Exception.class, () -> gameController.getWinnerOfGame("aa"));
                    String gameID = gameController.createGame("Test");
                    assertEquals(gameController.getWinnerOfGame(gameID), null);
                } catch (Exception e) {

                }
            }
        }
    }
    @Test
    @DisplayName("Test valid cases in getScoreboard")
    void getScoreboardSuccessful() {
        try {
            String gameID = gameController.createGame("Test");
            gameController.joinGame(gameID, "AnotherTest");
            gameController.startGame(gameID);
            assertFalse(gameController.getWorded(gameID));
            if (gameController.isGod(gameID,"Test")) {
                gameController.setWord(gameID, "ww", "Test");
                gameController.guessLetter(gameID, 'w', "AnotherTest");
                // prepare Environment -> play one round and then check if the player who won got a point on scoreboard
                assertArrayEquals(gameController.getScoreboard().toArray(), new Object[]{"AnotherTest: 1"});
            } else if (gameController.isGod(gameID,"AnotherTest")) {
                gameController.setWord(gameID, "ww", "AnotherTest");
                gameController.guessLetter(gameID, 'w', "Test");
                // prepare Environment -> play one round and then check if the player who won got a point on scoreboard
                assertArrayEquals(gameController.getScoreboard().toArray(), new Object[]{"Test: 1"});
            }
        } catch (Exception e) {
        }
    }
}