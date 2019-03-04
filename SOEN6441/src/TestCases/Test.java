package TestCases;




import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	FortificationTest.class,
	PlayerTest.class,
	ReinforcementTest.class,
	TestFileOfMap.class,
	TestOfMapValidition.class,
})
public class Test{
	
}
