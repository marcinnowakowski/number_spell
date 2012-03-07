package pl.marcinnowakowski.numberspell.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import number_spell.IWordifiedNumber;
import number_spell.WordifiedNumberForEnglishCardinals;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

/**
 * Reference test comparing results obtained with clojure code
 * with those prodided by service http://www.tools4noobs.com/online_tools/number_spell_words/ 
 * 
 * @author marcin.nowakowski
 *
 */
public class HttpBlackBoxTest {

	private static final String SERVICE_URL = "http://www.tools4noobs.com/online_tools/" +
			"number_spell_words/number_word.php";
	private static final String FIELD_NAME_TEXT = "text"; 
	private IWordifiedNumber wordified;
	private HttpClient client;
	private Random random;
	
	@Before
	public void setUp() {
		wordified = new WordifiedNumberForEnglishCardinals();
		// Create an instance of HttpClient.
		client = new HttpClient();
		random = new Random();
	}
	
	/**
	 * Generates 100 random numbers in range 0-999,999,999
	 * and checks if result of number spelling is the same
	 * for our clojure library and reference service
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void test() {
	
		//create test number set and test
		int[] testNumbers = new int[100];
		String[] testResults = new String[100];
		String[] references = new String[100];
		for(int i = 0; i < 100; i++) {
			testNumbers[i] = random.nextInt(999999999);
			testResults[i] = toWordsFromWordifiedNumber(testNumbers[i]);
			references[i] = toWordsFromReferenceService(testNumbers[i]);
			System.out.println("{" + testNumbers[i] + ", " + testResults[i] + ", " + references[i] + "}");
		}
		
		assertEquals("Not equal with reference service", references, testResults); 
	}
	
	/**
	 * Get the number spelling using clojure code
	 * 
	 * @param number - given number
	 * @return - number spelled with words
	 */
    private String toWordsFromWordifiedNumber(int number) {
    	return wordified.toWords(number);
    }
	
	/**
	 * Get the number spelling using reference service
	 * http://www.tools4noobs.com/online_tools/number_spell_words/
	 * 
	 * @param number - given number
	 * @return - number spelled with words
	 */
    private String toWordsFromReferenceService(int number) {

    	String result = null;
    	
		PostMethod postMethod = new PostMethod(SERVICE_URL);
		
		postMethod.addParameter(FIELD_NAME_TEXT, "" + number);
		
	    try {
	        // Execute the method.
	        int statusCode = client.executeMethod(postMethod);

	        if (statusCode != HttpStatus.SC_OK) {
	          System.err.println("Method failed: " + postMethod.getStatusLine());
	        }

	        // Read the response body.
	        byte[] responseBody = postMethod.getResponseBody();

	        // Deal with the response.
	        result = new String(responseBody);

	      } catch (HttpException e) {
	        System.err.println("Fatal protocol violation: " + e.getMessage());
	        e.printStackTrace();
	      } catch (IOException e) {
	        System.err.println("Fatal transport error: " + e.getMessage());
	        e.printStackTrace();
	      } finally {
	        // Release the connection.
	    	  postMethod.releaseConnection();
	      }
	    
	    // Trimming result to get only word representation of the number. 
	    if (result != null) {
	    	result = result.substring(45);
	    	result = result.substring(0, result.length()-6);
	    }
	    
	    return result;
	}

}
