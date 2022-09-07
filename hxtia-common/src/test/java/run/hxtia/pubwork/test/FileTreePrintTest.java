package run.hxtia.pubwork.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import run.hxtia.pubwork.common.utils.FileTreePrint;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootTest
@ContextConfiguration
@SpringBootConfiguration
public class FileTreePrintTest {

    @Test
    public void showFileDif() throws Exception {
        String path = "D:\\JaVa\\IdeaProjects\\pub_homework";
        String fileDir = FileTreePrint.generate(
            path,
            new HashSet<>(Arrays.asList(".idea", "target", ".git", "test")), true, true
        );
        System.out.print(fileDir);
    }
}
