package day8.problem1;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

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

    for (int i = 0; i < trees.length; i++) {
      int maxSoFar = -1;
      for (int j = 0; j < trees.length; j++) {
        if (trees[i][j] > maxSoFar) visible[i][j] = true;

        maxSoFar = Math.max(maxSoFar,trees[i][j]);
      }
    }

    for (int i = 0; i < trees.length; i++) {
      int maxSoFar = -1;
      for (int j = trees.length -1 ; j >= 0; j--) {
        if (trees[i][j] > maxSoFar) visible[i][j] = true;

        maxSoFar = Math.max(maxSoFar,trees[i][j]);
      }
    }

    for (int j = 0; j < trees.length; j++) {
      int maxSoFar = -1;
      for (int i = 0; i < trees.length; i++) {
        if (trees[i][j] > maxSoFar) visible[i][j] = true;

        maxSoFar = Math.max(maxSoFar,trees[i][j]);
      }
    }

    for (int j = 0; j < trees.length; j++) {
      int maxSoFar = -1;
      for (int i = trees.length-1; i >= 0; i--) {
        if (trees[i][j] > maxSoFar) visible[i][j] = true;

        maxSoFar = Math.max(maxSoFar,trees[i][j]);
      }
    }

    int count = 0;
    for (int i = 0; i < visible.length; i++) {
      for (int j = 0; j < visible.length; j++) {
        count += visible[i][j] ? 1 : 0;
      }
    }
    System.out.printf("The answer is %d%n", count);
  }
}
