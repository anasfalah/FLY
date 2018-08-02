package com.flyteam.mastermind;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ProposalResult;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author jtapiat
 */
public class SimpleElementsFinderTest {
    
    public SimpleElementsFinderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class SimpleElementsFinder.
     * @throws java.lang.Exception
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Map<Character, ProposalResult> map = new HashMap<>();
        String expected = "";
        ApiClient client = Mockito.spy(ApiClient.class);
        for (char character: SimpleElementsFinder.ELEMENTS) {
            ProposalResult ret = new ProposalResult();
            ret.setGood(Double.valueOf(Math.random() * 10).intValue());
            for (int i = 0; i < ret.getGood(); i++) {
                expected += character;
            }
            map.put(character, ret);
        }

        for (char character: SimpleElementsFinder.ELEMENTS) {
            String test = "";
            for (int i = 0; i < expected.length(); i++) {
                test += character;
            }
            Mockito.doReturn(map.get(character)).when(client).test(test);
        }

        SimpleElementsFinder instance = new SimpleElementsFinder();
        String result = instance.find(client, expected.length());
        assertEquals(expected, result);
    }
    
}
