package day2.problem2;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day2/problem1/input.txt");
    BufferedReader br = parser.tryOpen();

    String line;
    int points = 0;

    Map<String, String> winMap = new HashMap<>();
    winMap.put("A", "B");
    winMap.put("B", "C");
    winMap.put("C", "A");

    Map<String, String> drawMap = new HashMap<>();
    drawMap.put("A", "A");
    drawMap.put("B", "B");
    drawMap.put("C", "C");

    Map<String, String> lossMap = new HashMap<>();
    lossMap.put("A", "C");
    lossMap.put("B", "A");
    lossMap.put("C", "B");

    Map<String, Integer> outcomeMap = new HashMap<>();
    outcomeMap.put("X", 0);
    outcomeMap.put("Y", 3);
    outcomeMap.put("Z", 6);

    Map<String, Integer> pointsMap = new HashMap<>();
    pointsMap.put("A", 1);
    pointsMap.put("B", 2);
    pointsMap.put("C", 3);

    while ((line = br.readLine()) != null) {

      String[] duel = line.split(" ");
      String opponentMove = duel[0];
      String outcome = duel[1];

      String myMove = "";
      if (outcome.equals("X")) myMove = lossMap.get(opponentMove);
      if (outcome.equals("Y")) myMove = drawMap.get(opponentMove);
      if (outcome.equals("Z")) myMove = winMap.get(opponentMove);

      points += pointsMap.get(myMove) + outcomeMap.get(outcome);
    }

    System.out.printf("points are %d%n", points);
  }

}
