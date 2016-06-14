package org.sys;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSkyMile {

	@Before
	public void setUp() throws Exception {
		System.out.println("Setting up");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tearing down");
	}

	@Test
	public void test() {
		
		SkyMile sm = new NumberMile();
		int[] arr = {1,2,3,4,5};

		sm.getCarzyBig(arr);
		Assert.assertEquals("Passed ", ((NumberMile) sm).getResult(), 18);
	}
}
