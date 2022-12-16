package day9.problem1;

import helpers.FileParser;

import java.io.BufferedReader;

public class Main {

  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day9/input.txt");
    BufferedReader br = parser.tryOpen();

    boolean[][] visited = new boolean[1000][1000];
    String line;

    int[] headPos = {500, 500};
    int[] tailPos = {500, 500};
    visited[tailPos[0]][tailPos[1]] = true;
    while ((line = br.readLine()) != null) {
      String[] split = line.split(" ");
      String move = split[0].trim();
      int amount = Integer.parseInt(split[1]);

      int yMultiplier = 1;
      int xMultiplier = 1;
      if (move.equals("U")) {
        yMultiplier = 1;
        xMultiplier = 0;
      }
      if (move.equals("L")) {
        yMultiplier = 0;
        xMultiplier = -1;
      }
      if (move.equals("R")) {
        yMultiplier = 0;
        xMultiplier = 1;
      }
      if (move.equals("D")) {
        yMultiplier = -1;
        xMultiplier = 0;
      }
      while (amount > 0) {
        headPos[0] += xMultiplier;
        headPos[1] += yMultiplier;

        System.out.printf("pos is (%d, %d) compared to (%d, %d)%n", headPos[0], headPos[1], tailPos[0], tailPos[1]);

          if (headPos[0] == tailPos[0]){
            //same row
            if (Math.abs(headPos[1] - tailPos[1]) > 1) {
              System.out.println("moving left/right");
              tailPos[1] += yMultiplier;
            }
          } else if (headPos[1] == tailPos[1]) {
            if (Math.abs(headPos[0] - tailPos[0]) > 1) {
              System.out.println("moving up/down");
              tailPos[0] += xMultiplier;
              //same column
            }
          } else {
            if (!isDiagonal(headPos, tailPos)) {
              System.out.println("moving diagonal");
              //different row, different column
              tailPos[0] = headPos[0];
              tailPos[1] = headPos[1];

              tailPos[0] += xMultiplier*-1;
              tailPos[1] += yMultiplier*-1;
            }
          }

        visited[tailPos[0]][tailPos[1]] = true;
        amount--;
      }
    }

    int answer = 0;
    for (int i = 0; i < visited.length; i++) {
      for (int j = 0; j < visited.length; j++) {
        if (visited[i][j]) answer++;
      }

    }

    System.out.printf("The answer is %d%n", answer);
  }

  private static boolean isDiagonal(int[] headPos, int[] tailPos)
  {
    if (headPos[0] == tailPos[0] + 1 && headPos[1] == tailPos[1] + 1) return true;
    if (headPos[0] == tailPos[0] - 1 && headPos[1] == tailPos[1] - 1) return true;
    if (headPos[0] == tailPos[0] + 1 && headPos[1] == tailPos[1] - 1) return true;
    if (headPos[0] == tailPos[0] - 1 && headPos[1] == tailPos[1] + 1) return true;

    return false;
  }
}
