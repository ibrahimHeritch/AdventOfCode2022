import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class Day8Part1 {

    static List<List<Integer>> forest;

    public static void main(String args[]) {
        try (Stream<String> linesStream = Files.lines(new File("src/inputs/input8").toPath())) {
            forest = linesStream.map(l -> l.chars()
                            .mapToObj(e -> String.valueOf((char) e))
                            .map(Integer::parseInt)
                            .toList())
                    .toList();

            long ans = 0;

            for (int i = 0; i < forest.size(); i++) {
                for (int j = 0; j < forest.get(0).size(); j++) {
                    System.out.print(isVisible(i, j) ? forest.get(i).get(j) : "*");
                    if (isVisible(i, j)) {
                        ans++;
                    }
                }
                System.out.println();
            }

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isVisible(int i, int j) {
        return isVisibleFromTop(i, j) | isVisibleFromLeft(i, j) | isVisibleFromRight(i, j) | isVisibleFromBottom(i, j);
    }

    private static boolean isVisibleFromTop(int i, int j) {
        return forest.subList(0, i)
                .stream()
                .map(l -> l.get(j))
                .max(Integer::compareTo)
                .map(max -> max < forest.get(i).get(j))
                .orElse(true);
    }

    private static boolean isVisibleFromLeft(int i, int j) {
        return forest.get(i)
                .subList(0, j)
                .stream()
                .max(Integer::compareTo)
                .map(max -> max < forest.get(i).get(j))
                .orElse(true);
    }

    private static boolean isVisibleFromRight(int i, int j) {
        return forest.get(i)
                .subList(j + 1, forest.get(0).size())
                .stream()
                .max(Integer::compareTo)
                .map(max -> max < forest.get(i).get(j))
                .orElse(true);
    }

    private static boolean isVisibleFromBottom(int i, int j) {
        return forest.subList(i + 1, forest.size())
                .stream()
                .map(l -> l.get(j))
                .max(Integer::compareTo)
                .map(max -> max < forest.get(i).get(j))
                .orElse(true);
    }

}
