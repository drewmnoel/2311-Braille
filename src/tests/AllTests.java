package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BrailleCircleTest.class, BrailleTextTranslatorTest.class, SimulatorCoreTest.class })
public class AllTests {
}
