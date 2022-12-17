import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day10Part2 {
    public static void main(String[] args) {
        try (Stream<String> linesStream = Files.lines(new File("src/inputs/input10").toPath())) {
            int register = 1;
            int cycle = 1;
            List<List<Integer>> states = new ArrayList<>();
            for (String s : linesStream.toList()) {
                if (!s.equals("noop")) {
                    int amount = Integer.parseInt(s.split(" ")[1]);
                    states.addAll(List.of(List.of(cycle++, register), List.of(cycle++, register)));
                    register += amount;

                } else {
                    states.add(List.of(cycle++, register));
                }
            }

            states.forEach(
                    s -> {
                        if ((s.get(0) - 1) % 40 > s.get(1) - 2 && (s.get(0) - 1) % 40 < s.get(1) + 2) {
                            System.out.print("#");
                        } else {
                            System.out.print(".");
                        }
                        if (s.get(0) % 40 == 0) {
                            System.out.println();
                        }

                    }
            );


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}