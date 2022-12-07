import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3Part1 {
    public static void main(String args[]) {
        try(Stream<String> linesStream = Files.lines(new File("src/inputs/input3").toPath())) {
            long ans = linesStream.map(line->{
                        Set<Character> charSet = getCharsSetOfSecondHalf(line);
                        for (char c:line.toCharArray()) {
                            if (charSet.contains(c)) {
                                return priority(c);
                            }
                        }
                        throw new IllegalArgumentException("must have to have 1 dup");
                    })
                    .reduce(0,Integer::sum);

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Set<Character> getCharsSetOfSecondHalf(String line) {
        return line.substring(line.length() / 2).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
    }

    private static int priority(char c) {
        if (Character.isUpperCase(c)) {
            return 27 + (c - 'A');
        }
        return 1 + c - 'a';
    }
}