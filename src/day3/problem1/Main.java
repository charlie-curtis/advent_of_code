package day3.problem1;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day3/problem1/input.txt");
    BufferedReader br = parser.tryOpen();

    String line;

    int answer = 0;
    while ((line = br.readLine()) != null) {
      int length = line.length();
      String s1 = line.substring(0, length/2);
      String s2 = line.substring(length/2);

      Set<Character> set1 = Arrays.stream(s1.split("")).map(s -> s.charAt(0)).collect(Collectors.toSet());
      Set<Character> set2 = Arrays.stream(s2.split("")).map(s -> s.charAt(0)).collect(Collectors.toSet());

      set1.retainAll(set2);

      for (Character c : set1) {
        int priority = 0;
        if (c >= 65 && c <= 90) {
          //capital letter
          priority = ((c - 65) + 1)+26;

        } else if (c >= 97 && c <= 122) {
          //lowercase letter
          priority = c - 97 + 1;
        } else {
          throw new RuntimeException("Invalid letter");
        }

        answer += priority;
      }
    }

    System.out.printf("The answer is %d%n", answer);
  }
}
