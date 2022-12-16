package day8.problem2;

import helpers.FileParser;

import java.io.BufferedReader;

public class Main {

  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day8/input.txt");
    BufferedReader br = parser.tryOpen();

    int size = 99;
    int[][] trees = new int[size][size];

    boolean[][] visible = new boolean[size][size];

    for (int i = 0; i < visible.length; i++) {
      String line = br.readLine();
      String[] stringArray = line.split("");
      int[] digits = new int[size];
      for (int j = 0; j < digits.length; j++) {
        digits[j] = Integer.parseInt(stringArray[j]);
      }
      trees[i] = digits;

    }

    int answer = 0;

    for (int i = 1; i < trees.length-1; i++) {
      for (int j = 1; j < trees.length-1; j++) {
        if (i == 3 && j == 2) {
          System.out.println("Test");
        }
        int product = 1;
        int counter = 0;
        int pointer = j-1;
        while (isInBounds(i, pointer, trees.length)) {
          counter++;

          if (trees[i][j] <= trees[i][pointer]) break;

          pointer--;
        }
        product *= counter;

        counter = 0;
        pointer = j + 1;

        while (isInBounds(i, pointer, trees.length)) {
          counter++;

          if (trees[i][j] <= trees[i][pointer]) break;

          pointer++;
        }
        product *= counter;

        counter = 0;
        pointer = i-1;
        while (isInBounds(pointer, j, trees.length)) {
          counter++;

          if (trees[i][j] <= trees[pointer][j]) break;

          pointer--;
        }
        product *= counter;

        counter = 0;
        pointer = i+1;
        while (isInBounds(pointer, j, trees.length)) {
          counter++;

          if (trees[i][j] <= trees[pointer][j]) break;

          pointer++;
        }
        product *= counter;

        if (product > answer) {
          System.out.printf("Found better at (%d, %d)%n", i, j);
        }
        answer = Math.max(answer, product);
      }
    }

    System.out.printf("The answer is %d%n", answer);
  }

  private static boolean isInBounds(int i, int j, int length)
  {
    return (i >= 0 && j >= 0 && i < length && j < length);
  }
}
