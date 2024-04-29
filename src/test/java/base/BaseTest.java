package base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

//TestInstance to avoid setup be static.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(AfterTestExecutionCallbackBase.class)
public abstract class BaseTest extends WebBaseTestJUnit {
    @BeforeAll
    public void setup() {
        initializeStepdefs();
    }
}
