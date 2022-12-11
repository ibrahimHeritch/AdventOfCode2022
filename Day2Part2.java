import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Day2Part2 {
    public static void main(String args[]) {
        try(Stream<String> linesStream = Files.lines(new File("src/inputs/input2").toPath())) {
            long ans = linesStream.map(line->line.split(" "))
                    .map(split -> switch (split[1]){
                        case "X" -> ((((split[0].charAt(0)-'A')-1+3)%3)+1);
                        case "Y" ->  (3 + (split[0].charAt(0)-'A' + 1));
                        case "Z" ->  (6 + (((split[0].charAt(0)-'A') + 1) %  3)+1);
                        default -> throw new IllegalArgumentException();
                    })
                    .reduce(0,Integer::sum);

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}