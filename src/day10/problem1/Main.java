package day10.problem1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.UnexpectedException;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception
  {


    FileReader fr = new FileReader("src/day10/problem1/puzzle.txt");

    BufferedReader br = new BufferedReader(fr);


    String line = "";
    int total = 1;
    int answer = 0;
    int cycleCount = 0;
    var list = new ArrayList<Integer>();
    list.add(total);
    while ((line = br.readLine()) != null) {

      String[] pieces = line.split(" ");


      if (pieces[0].equals("noop")) {
        list.add(total);
        cycleCount++;
      } else if (pieces[0].equals("addx")) {
        list.add(total);
        list.add(total);
        cycleCount+= 2;
        int value = Integer.parseInt(pieces[1]);
        total += value;
        System.out.printf("CycleCount is %d and value is %d%n", cycleCount, total * cycleCount);
      } else {
        throw new RuntimeException("invalid piece");
      }

    }

    for (int i = 0; i < list.size(); i++) {
      if (shouldRecord(i)) {
        int val = list.get(i);
        answer += val * i;
        System.out.printf("cycle is %d... adding %d to answer%n", i, val*i);
      }
    }
    System.out.println(answer);
  }

  private static boolean shouldRecord(int cycleCount)
  {
    return (cycleCount + 20) % 40 == 0;
  }
}
