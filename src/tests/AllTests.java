package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * JUnit test suite runner
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-07
 */
@RunWith(Suite.class)
@SuiteClasses({ BrailleCircleTest.class, BrailleTextTranslatorTest.class, SimulatorCoreTest.class })
public class AllTests {
}