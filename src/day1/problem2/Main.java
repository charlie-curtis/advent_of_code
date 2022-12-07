package day1.problem2;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day1/problem2/input.txt");
    BufferedReader br = parser.tryOpen();

    String line;
    int current = 0;
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
    while ((line = br.readLine()) != null) {
      if (line.length() == 0) { // new elf
        priorityQueue.add(current);
        current = 0;
        continue;
      }
      current += Integer.parseInt(line);
    }

    int answer = 0;
    for (int i = 0; i < 3; i++) {
      Integer num = priorityQueue.poll();
      System.out.printf("Popped %d from the queue.%n", num);
      answer += num;
    }

    System.out.printf("sum of top3 is %d", answer);
  }


}
