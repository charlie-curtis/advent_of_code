package day3.problem2;

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
      String line2 = br.readLine();
      String line3 = br.readLine();
      Set<Character> set = Arrays.stream(line.split("")).map(s -> s.charAt(0)).collect(Collectors.toSet());
      Set<Character> set2 = Arrays.stream(line2.split("")).map(s -> s.charAt(0)).collect(Collectors.toSet());
      Set<Character> set3 = Arrays.stream(line3.split("")).map(s -> s.charAt(0)).collect(Collectors.toSet());

      set.retainAll(set2);
      set.retainAll(set3);

      if (set.size() != 1) throw new RuntimeException("Invalid set size");

      for (Character c : set) {
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
