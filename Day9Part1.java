import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Day9Part1 {

    public static void main(String args[]) {
        try (Stream<String> linesStream = Files.lines(new File("src/inputs/input9").toPath())) {
            Rope rope = new Rope();
            linesStream.flatMap(s -> {
                String instruction = s.substring(0, 1);
                Integer times = Integer.parseInt(s.substring(2));
                return Stream.iterate(0, i -> i < times, i -> i + 1)
                        .map(i -> instruction);
            }).forEach(rope::move);

            System.out.println(rope.visited.size() + 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class Rope {
    Cords headCords;
    Cords tailCords;
    Set<Cords> visited;

    public Rope() {
        this.headCords = new Cords(0, 0);
        this.tailCords = new Cords(0, 0);
        this.visited = new HashSet<>();
    }

    void move(String instruction) {
        Cords oldHead = this.headCords;
        switch (instruction.charAt(0)) {
            case 'R' -> this.headCords = new Cords(this.headCords.x() + 1, this.headCords.y());
            case 'L' -> this.headCords = new Cords(this.headCords.x() - 1, this.headCords.y());
            case 'U' -> this.headCords = new Cords(this.headCords.x(), this.headCords.y() + 1);
            case 'D' -> this.headCords = new Cords(this.headCords.x(), this.headCords.y() - 1);
        }

        int xDiff = Math.abs(this.headCords.x() - this.tailCords.x());
        int yDiff = Math.abs(this.headCords.y() - this.tailCords.y());

        if (xDiff > 1 || yDiff > 1) {
            this.tailCords = oldHead;
            this.visited.add(this.tailCords);
        }

    }
}

record Cords(int x, int y) {
}
