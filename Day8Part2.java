import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class Day8Part2 {

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
                    System.out.print("{" + score(i, j) + "}");
                    ans = Math.max(ans, score(i, j));

                }
                System.out.println();
            }

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long score(int i, int j) {
        return scoreFromTop(i, j) * scoreFromLeft(i, j) * scoreFromRight(i, j) * scoreFromBottom(i, j);
    }

    private static long scoreFromTop(int i, int j) {
        long count = 0;
        for (int dex = i - 1; dex >= 0; dex--) {
            if (forest.get(i).get(j) > forest.get(dex).get(j)) {
                count++;
            } else {
                count++;
                break;
            }
        }
        return count;
    }

    private static long scoreFromLeft(int i, int j) {
        long count = 0;
        for (int dex = j - 1; dex >= 0; dex--) {
            if (forest.get(i).get(j) > forest.get(i).get(dex)) {
                count++;
            } else {
                count++;
                break;
            }
        }
        return count;
    }

    private static long scoreFromRight(int i, int j) {
        long count = 0;
        for (int dex = j + 1; dex < forest.get(0).size(); dex++) {
            if (forest.get(i).get(j) > forest.get(i).get(dex)) {
                count++;
            } else {
                count++;
                break;
            }
        }
        return count;
    }

    private static long scoreFromBottom(int i, int j) {
        long count = 0;
        for (int dex = i + 1; dex < forest.size(); dex++) {
            if (forest.get(i).get(j) > forest.get(dex).get(j)) {
                count++;
            } else {
                count++;
                break;
            }
        }
        return count;
    }

}
