package day3.problem1;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

      HashMap<Character, Integer> map1 = new HashMap<>();
      HashMap<Character, Integer> map2 = new HashMap<>();

      Arrays.stream(s1.split("")).map(s -> s.charAt(0)).forEach(c -> {

        int count = map1.getOrDefault(c, 0);
        count++;
        map1.put(c, count);

      });

      Arrays.stream(s2.split("")).map(s -> s.charAt(0)).forEach(c -> {

        int count = map2.getOrDefault(c, 0);
        count++;
        map2.put(c, count);

      });

      for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
        Character c = entry.getKey();
        int count1 = entry.getValue();
        if (!map2.containsKey(c)) {
          continue;
        }

        int count2 = map2.get(c);

        int overlap = Math.min(count1, count2);

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

        //priority *= overlap;

        answer += priority;
      }
    }

    System.out.printf("The answer is %d%n", answer);
  }
}
