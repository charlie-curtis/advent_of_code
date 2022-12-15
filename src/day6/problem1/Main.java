package day6.problem1;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day6/input.txt");
    BufferedReader br = parser.tryOpen();

    String line = br.readLine();

    HashMap<Character, Integer> map =  new HashMap<>();
    LinkedList<Character> linkedList = new LinkedList<>();
    int answer = -1;
    for (int i = 0; i < line.length(); i++) {
      Character c = line.charAt(i);
      int count = map.getOrDefault(c, 0);
      count++;
      map.put(c, count);
      linkedList.add(c);

      if (map.size() == 4) {
        answer = i + 1;
        break;
      }

      if (linkedList.size() == 4) {
        Character charToRemove = linkedList.pop();
        count = map.getOrDefault(charToRemove, 0);
        count--;

        if (count == 0) {
          map.remove(charToRemove);
        } else {
          map.put(charToRemove, count);
        }
      }
    }

    System.out.printf("The answer is %d%n", answer);
  }
}
