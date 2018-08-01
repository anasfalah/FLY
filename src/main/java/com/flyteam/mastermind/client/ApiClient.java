/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jtapiat
 */
public class ApiClient {

    private static final String TOKEN = "tokenfly";
    private static final String START_RESOURCE = "/api/start";
    private static final String TEST_RESOURCE = "/api/test";

    private static final String IP = "172.16.37.129";
    private static final String PORT = "8080";

    private Client client;

    public ApiClient() {
        client = client.create();
    }

    public Integer start() throws Exception {
        ClientResponse response = null;
        try {
            Request request = new Request();
            request.setToken(TOKEN);
            response = client.resource("http://" + IP + ":"  + PORT + START_RESOURCE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(ClientResponse.class, request);
            return response.getEntity(StartReturn.class).getSize();
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public ProposalResult test(String proposal) throws Exception {
        ClientResponse response = null;
        try {
            Request request = new Request();
            request.setToken(TOKEN);
            request.setResult(proposal);
            response = client.resource("http://" + IP + ":" + PORT + TEST_RESOURCE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(ClientResponse.class, request);
            return response.getEntity(ProposalResult.class);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
