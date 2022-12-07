import java.io.File;  // Import the File class
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Day2Part1 {

    public static void main(String args[]) {
        try(Stream<String> linesStream = Files.lines(new File("src/inputs/input2").toPath())) {
            long ans = linesStream.map(line->line.split(" "))
                    .map(Day2Part1::getRoundScore)
                    .reduce(0,Integer::sum);

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getRoundScore(String[] splitLine) {
        //turn A B C to 0 1 2
        int opp = splitLine[0].charAt(0) - 'A';
        // turn X Y Z to 0 1 2
        int me = splitLine[1].charAt(0) - 'X';

        return (me + 1) + resultScore(opp, me);
    }

    private static int resultScore(int opp, int me) {
        if (opp == me) {
            return 3;
        }
        if (me == (opp + 1) % 3) {
            return 6;
        }

        return 0;
    }
}