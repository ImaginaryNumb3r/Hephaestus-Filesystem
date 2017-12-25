import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * Creator: Patrick
 * Created: 03.08.2017
 * Purpose:
 */
public class main {

    public static void main(String[] args) {
        Path path1 = Paths.get("C:\\test");
        Path path2 = Paths.get("C:/test");
        Path path3 = Paths.get("C:\\\\test");
        Path path4 = Paths.get("sub");
        boolean absolute = path1.isAbsolute();

        Path resolve = path1.resolve(path4);


        Function<Path, Path> function = Path::getParent;

        System.out.println("Build Successful!");
        test();
    }


    private static void test(){



    }

}
