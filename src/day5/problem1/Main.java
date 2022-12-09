package day5.problem1;

import helpers.FileParser;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) throws Exception {
    FileParser parser = new FileParser("src/day5/input.txt");
    BufferedReader br = parser.tryOpen();

    List<Stack<Character>> holder = new ArrayList<>();


    String string1 = "JHGMZNTF";
    String string2 = "VWJ";
    String string3 = "GVLJBTH";
    String string4 = "BPJNCDVL";
    String string5 = "FWSMPRG";
    String string6 = "GHCFBNVM";
    String string7 = "DHGMR";
    String string8 = "HNMVZD";
    String string9 = "GNFH";

    ArrayList<String> strings = new ArrayList<>(List.of(string1, string2, string3, string4, string5, string6, string7,string8,string9));

    for (int i = 0; i < 9; i++) {
      Stack<Character> stack = new Stack<>();
      String s = strings.get(i);
      Arrays.stream(s.split("")).map(s1->s1.charAt(0)).forEach(stack::push);
      holder.add(stack);
    }

    String line;

    Pattern p = Pattern.compile("\\s(\\d+)");
    while ((line = br.readLine()) != null) {
      Matcher m = p.matcher(line);

      m.find();
      int moveCount = Integer.parseInt(line.substring(m.start(), m.end()).trim());
      m.find();
      int start = Integer.parseInt(line.substring(m.start(), m.end()).trim());
      m.find();
      int end = Integer.parseInt(line.substring(m.start(), m.end()).trim());

      Stack<Character> from = holder.get(start-1);
      Stack<Character> to = holder.get(end-1);

      for (int i = 0; i < moveCount; i++) {
        to.push(from.pop());
      }
    }


    StringBuilder answer = new StringBuilder();
    for (int i = 0; i < holder.size(); i++) {
      answer.append(holder.get(i).peek());
    }

    System.out.println(answer);
  }
}
