import jdk.incubator.concurrent.StructuredTaskScope;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5Part2 {
    public static void main(String args[]) {
        try (Stream<String> linesStream = Files.lines(new File("src/inputs/input5").toPath());
             var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Map<Boolean, List<String>> inputs = linesStream.collect(Collectors.partitioningBy(s -> s.startsWith("m")));

            //Doing this concurrently is probably slower than serially for this input size but YOLO
            //JDK 19 to run +Use incubator module: jdk.incubator.concurrent

            Future<Stack<Character>[]> stacks = scope.fork(() -> generateStacks(inputs.get(false)));
            Future<List<Day5Part1.CraneMovement>> movements = scope.fork(() -> generateMovements(inputs.get(true)));

            scope.join();
            scope.throwIfFailed();

            movements.resultNow().forEach(move -> {
                Stack<Character> temp = new Stack<>();
                for (int i = 0; i < move.amount(); i++) {
                    temp.add(stacks.resultNow()[move.start() - 1].pop());
                }
                for (int i = 0; i < move.amount(); i++) {
                    stacks.resultNow()[move.end() - 1].add(temp.pop());
                }
            });

            Arrays.stream(stacks.resultNow()).forEach(stack -> System.out.print(stack.pop()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Day5Part1.CraneMovement> generateMovements(List<String> strings) {
        return strings.stream()
                .map(s -> Arrays.stream(s.split(" ")).filter(str -> Character.isDigit(str.charAt(0))).map(Integer::parseInt).toList())
                .map(arr -> new Day5Part1.CraneMovement(arr.get(1), arr.get(2), arr.get(0)))
                .toList();
    }

    private static Stack<Character>[] generateStacks(List<String> strings) {
        //before last row determines the number of stacks
        final int numberOfStacks = (strings.get(strings.size() - 2).length() + 1) / 4;

        Stack<Character>[] stacks = new Stack[numberOfStacks];
        Collections.reverse(strings);
        strings.stream().skip(2).forEach(s -> {
            for (int i = numberOfStacks - 1; i >= 0; i--) {
                if (stacks[i] == null) {
                    stacks[i] = new Stack<>();
                }
                Character c = s.charAt(1 + (i * 4));
                if (!c.equals(' ')) {
                    stacks[i].add(c);
                }
            }
        });

        return stacks;
    }

    record CraneMovement(int start, int end, int amount) {
    }
}