package day1.problem1;

import helpers.FileParser;

import java.io.BufferedReader;

public class Main {
  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day1/problem1/input.txt");
    BufferedReader br = parser.tryOpen();

    String line;
    int current = 0;
    int max = 0;
    while ((line = br.readLine()) != null) {
      if (line.length() == 0) { // new elf
        current = 0;
        continue;
      }

      current += Integer.parseInt(line);
      max = Math.max(max, current);
    }

    System.out.printf("Max is %d", max);
  }


}
