package pij.test.java.scrabble;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AITest.class, DictionaryTest.class, TileBagTest.class, BonusChecker.class })
public class AllTests {
}
