import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3Part2 {
    public static void main(String args[]) {
        try {

            File myObj = new File("src/inputs/input3");
            Scanner myReader = new Scanner(myObj);
            int sum = 0;
            while (myReader.hasNextLine()) {
                Set<Integer> charSet1 = readLineAsSet(myReader);
                Set<Integer> charSet2 = readLineAsSet(myReader);
                sum+=myReader.nextLine().chars().boxed()
                        .filter(c -> charSet1.contains(c) && charSet2.contains(c))
                        .map(Day3Part2::priority)
                        .findFirst()
                        .get();

            }

            myReader.close();
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Set<Integer> readLineAsSet(Scanner myReader) {
        return myReader.nextLine().chars().boxed().collect(Collectors.toSet());
    }

    private static int priority(int c) {
        if (Character.isUpperCase(c)) {
            return 27 + (c - 'A');
        }
        return 1 + c - 'a';
    }
}