/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind.client;

import com.flyteam.mastermind.tool.TestTool;
import com.sun.jersey.api.client.ClientResponse;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author jtapiat
 */
public class ApiClientTest {

    public ApiClientTest() {
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

//    /**
//     * Test of execute method, of class ApiClient.
//     */
//    @Test
//    public void testExecute() throws Exception {
//        ApiClient client = Mockito.spy(new ApiClient());
//        ClientResponse res = Mockito.mock(ClientResponse.class);
//        Mockito.when(client.execute(Mockito.anyString())).thenReturn(res);
//        assertEquals(res, client.execute(UUID.randomUUID().toString()));
//        
//    }
    
    /**
     * Test of start method, of class ApiClient.
     * @throws java.lang.Exception
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        StartReturn ret = new StartReturn();
        ret.setName(UUID.randomUUID().toString());
        ret.setSize(Double.valueOf(Math.random() * 10).intValue());
        ret.setQuizzId(Double.valueOf(Math.random() * 10).intValue());

        ClientResponse res = TestTool.getClientResponseMock();
        Mockito.doReturn(ret).when(res).getEntity(StartReturn.class);

        ApiClient client = Mockito.spy(ApiClient.class);
        Mockito.doReturn(res).when(client).execute(ApiClient.START_RESOURCE);

        assertTrue(ret.getSize() == client.start());

        ret.setError(UUID.randomUUID().toString());
        try {
            client.start();
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertEquals(ret.getError(), e.getMessage());
        }
    }

    /**
     * Test of test method, of class ApiClient.
     * @throws java.lang.Exception
     */
    @Test
    public void testTest() throws Exception {
        System.out.println("test");
        ProposalResult ret = new ProposalResult();
        ret.setGood(Double.valueOf(Math.random() * 10).intValue());
        ret.setWrongPlace(Double.valueOf(Math.random() * 10).intValue());

        ClientResponse res = TestTool.getClientResponseMock();
        Mockito.doReturn(ret).when(res).getEntity(ProposalResult.class);

        ApiClient client = Mockito.spy(ApiClient.class);
        Mockito.doReturn(res).when(client).execute(ApiClient.TEST_RESOURCE, Mockito.anyString());

        ProposalResult newRes = client.test(UUID.randomUUID().toString());
        assertTrue(ret.getGood() == newRes.getGood());
        assertTrue(ret.getWrongPlace()== newRes.getWrongPlace());

        ret.setError(UUID.randomUUID().toString());
        try {
            client.test(UUID.randomUUID().toString());
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertEquals(ret.getError(), e.getMessage());
        }
    }

}
