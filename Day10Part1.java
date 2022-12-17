import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day10Part1 {
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


            int ans = states.stream()
                    .filter(s -> (s.get(0) - 20) % 40 == 0)
                    .map(l -> l.get(0) * l.get(1))
                    .reduce(0, Integer::sum);

            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}