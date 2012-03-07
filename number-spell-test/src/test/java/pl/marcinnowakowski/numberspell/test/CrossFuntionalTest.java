package pl.marcinnowakowski.numberspell.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import number_spell.WordifiedNumberForEnglishCardinals;
import number_spell.IWordifiedNumber;

/**
 * Simple test checking chosen examples
 * 
 * @author macbeeth
 *
 */
public class CrossFuntionalTest {

	IWordifiedNumber wordified;
	
	@Before
	public void setUp() {
		wordified = new WordifiedNumberForEnglishCardinals();
	}
	
	@Test
	public void test() {
		
		assertEquals("Not equal for number: 1", 
		    "one", wordified.toWords(1));
		
		assertEquals("Not equal for number: 21", 
			"twenty-one", wordified.toWords(21));
		
		assertEquals("Not equal for number: 105",
			"one hundred five",
			//"one hundred and five", 
			wordified.toWords(105));
		
		assertEquals("Not equal for number: 56945781", 
			//"fifty six million nine hundred and forty five thousand seven " +
			//"hundred and eighty one", 
			"fifty-six million nine hundred forty-five thousand seven " +
			"hundred eighty-one",
			wordified.toWords(56945781));
	}

}
