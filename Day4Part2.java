import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4Part2 {
    public static void main(String args[]) {
        try (Stream<String> linesStream = Files.lines(new File("src/inputs/input4").toPath())) {
            long ans = linesStream
                    .map(l -> Arrays.stream(l.split("-|,")).map(Integer::parseInt).collect(Collectors.toList()))
                    .filter(l -> !(l.get(0) > l.get(3) || l.get(2) > l.get(1)))
                    .count();

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}