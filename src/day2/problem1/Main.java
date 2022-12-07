package day2.problem1;

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

    Map<String, String> converter = new HashMap<>();
    converter.put("A", "X");
    converter.put("B", "Y");
    converter.put("C", "Z");

    Map<String, Integer> pointsMap = new HashMap<>();
    pointsMap.put("X", 1);
    pointsMap.put("Y", 2);
    pointsMap.put("Z", 3);

    while ((line = br.readLine()) != null) {

      String[] duel = line.split(" ");
      String opponentMove = converter.get(duel[0]);
      String myMove = duel[1];

      points += pointsMap.get(myMove);

      if (opponentMove.equals(myMove)) {
        //draw
        points+=3;
      } else if (myMove.equals("X") && opponentMove.equals("Z")) {
        //win
        points+=6;
      } else if (opponentMove.equals("X") && myMove.equals("Z")) {
        //loss
      } else if (myMove.compareTo(opponentMove) > 0) {
        //win
        points+=6;
      }

    }

    System.out.printf("points are %d%n", points);
  }

}
