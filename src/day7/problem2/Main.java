package day7.problem2;

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

    int mustBeBelow = 40_000_000;
    int currentValue = map.get("/");
    int requiredDeletion = currentValue - mustBeBelow;
    System.out.printf("required to delete = %d%n", requiredDeletion);

    Map.Entry<String, Integer> entryToDelete = map.entrySet().stream()
      .filter(entry -> entry.getValue() >= requiredDeletion)
      .sorted(Map.Entry.comparingByValue())
      .findFirst().orElseThrow(RuntimeException::new);

    System.out.printf("The smallest value to be deleted is %d%n", entryToDelete.getValue());
  }

  private static Integer generateHash(Stack<String> s)
  {
    return s.stream().mapToInt(String::hashCode).boxed().reduce(0, (a,b) -> a^b);
  }
}
