package com.fidelity.balance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class BalanceTest {

	@Test
	public void testGetDenominations() {
		
		final List<Denomination> us   = Arrays.asList(new Denomination("Dollar", 100), new Denomination("Quarter", 25), new Denomination("Dime", 10), new Denomination("Nickel", 5), new Denomination("Penny", 1));
		final List<Denomination> euro = Arrays.asList(new Denomination("Euro", 100), new Denomination("50c", 50), new Denomination("20c", 20), new Denomination("10c", 10), new Denomination("5c", 5), new Denomination("1c", 1));
		
		final Map<String, List<Denomination>> currencyToDenominationMap = new HashMap<>(2);
		currencyToDenominationMap.put("US", us); 
		currencyToDenominationMap.put("EURO", euro); 
		int balance = 287;
		String currencyName = "US";
		final String output = Balance.getDenominations(new Currency(currencyName, balance), currencyToDenominationMap);
		assert(output.equals("2 Dollar, 3 Quarter, 1 Dime, 2 Penny"));
		
		currencyName = "EURO";
		final String output2 = Balance.getDenominations(new Currency(currencyName, balance), currencyToDenominationMap);
		assert(output2.equals("2 Euro, 1 50c, 1 20c, 1 10c, 1 5c, 2 1c"));
	}

}
