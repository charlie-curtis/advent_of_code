package day7.problem1;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day7/input.txt");
    BufferedReader br = parser.tryOpen();

    String line;

    Stack<String> stack = new Stack<>();
    HashMap<String, Integer> map  = new HashMap<>();
    Set<Integer> seen = new HashSet<>();

    boolean alreadyProcessed = false;
    while ((line = br.readLine()) != null) {
      if (line.equals("$ ls"))  {
        Integer i = generateHash(stack);
        alreadyProcessed = seen.contains(i);
        seen.add(generateHash(stack));
        String path = stack.toString();
        System.out.printf("Path is %s%n", path);
        continue;
      }


      if (line.startsWith("dir")) continue;

      if (line.startsWith("$ cd")) {

        if (line.equals("$ cd /")) {
          stack.clear();
          stack.push("/");
        } else if (line.equals("$ cd ..")) {
          stack.pop();
        } else if (line.startsWith("$ cd")) {
          String dir = line.split(" ")[2];
          stack.push(dir);
        }
      }

      if (alreadyProcessed) {
        continue;
      }


      Character c = line.charAt(0);
      if (Character.isDigit(c)) {
        int value = Integer.parseInt(line.split(" ")[0]);

        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
          sb.insert(0, s);
          String temp = sb.toString();
          int newValue = map.getOrDefault(temp, 0) + value;
          map.put(temp, newValue);
        }
      }
    }

    List<Map.Entry<String, Integer>> filtered = map
      .entrySet()
      .stream()
      .filter(element -> element.getValue() <= 100_000)
      .collect(Collectors.toList());

    int answer = filtered.stream().mapToInt(Map.Entry::getValue).sum();
    System.out.printf("The answer is %d%n", answer);

  }

  private static Integer generateHash(Stack<String> s)
  {
    return s.stream().mapToInt(String::hashCode).boxed().reduce(0, (a,b) -> a^b);
  }
}
