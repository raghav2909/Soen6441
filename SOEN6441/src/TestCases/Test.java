package TestCases;




import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	PlayerTest.class,
	TestFileOfMap.class,
	TestOfMapValidition.class,
	MapConnected.class,
	MapContainUnique.class,
	MapDoesNotContainUnique.class,
	GameDriverTest.class,
	MapTest.class,
	TestMapWrite.class,
	AStrategyTest.class,
	BStrategyTest.class,
	CStrategyTest.class,
	RStrategyTest.class,
})
public class Test{
	
}
