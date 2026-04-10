package com.example.snl;

import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        GameConfigReader configReader = new ConsoleGameConfigReader(System.in);
        GameConfig config = configReader.readConfig();

        RandomNumberGenerator rng = new JavaRandomNumberGenerator(new Random());
        BoardGenerator boardGenerator = new RandomBoardGenerator(rng);
        Board board = boardGenerator.generate(config.boardSize(), config.difficultyLevel());

        Dice dice = new SixSidedDice(rng);
        GameEventPublisher publisher = new ConsoleGameEventPublisher(System.out);
        GameEngine engine = new GameEngine(board, dice, publisher);

        List<Player> players = GameEngine.createPlayers(config.numberOfPlayers());
        engine.play(players);
    }
}
