package day4.problem1;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day4/input.txt");
    BufferedReader br = parser.tryOpen();

    String line;

    int answer = 0;
    while ((line = br.readLine()) != null) {
      String[] ranges = line.split(",");
      String[] range1 = ranges[0].split("-");
      String[] range2 = ranges[1].split("-");

      int start1 = Integer.parseInt(range1[0]);
      int end1 = Integer.parseInt(range1[1]);

      int start2 = Integer.parseInt(range2[0]);
      int end2 = Integer.parseInt(range2[1]);

      if (start1 <= start2 && end1 >= end2) answer++;
      else if (start2 <= start1 && end2 >= end1) answer++;

    }

    System.out.printf("The answer is %d%n", answer);
  }
}
