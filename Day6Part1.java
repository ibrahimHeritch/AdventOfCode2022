import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6Part1 {
    public static void main(String args[]) {
        try(Stream<String> linesStream = Files.lines(new File("src/inputs/input6").toPath())) {
            long ans = linesStream
                    .findFirst().map(s->{
                        for(int i=0;i<s.length()-4;i++){
                            if(s.substring(i,i+4).chars().boxed().collect(Collectors.toSet()).size() == 4){
                                return i+4;
                            }
                        }
                        return -1;
                    }).get();

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
