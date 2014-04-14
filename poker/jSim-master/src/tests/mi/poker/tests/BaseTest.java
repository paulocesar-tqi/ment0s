package mi.poker.tests;

import mi.poker.calculation.EquityCalculation;
import mi.poker.calculation.HandInfo;
import mi.poker.calculation.Result;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Every test should extend this class
 * @author m1
 */
public class BaseTest extends TestCase{

	@Test
	public void test1(){
		Result result = EquityCalculation.calculate("JcJh,XxXx,XxXx", 
				"JsAhAc", "");
		HandInfo p1 = result.getHandInfo(0);
		
		System.out.println(p1.getWin());
	}
}
