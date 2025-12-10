import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Отключен, чтобы не занимать время при обычных запусках тестов")
    void mainMethodIsRunningLess22seconds() throws Exception {
        Main.main(new String[]{});
    }
}
