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
    private static final String PORT = "80";

    private Client client;

    public ApiClient() {
        client = client.create();
//        client.addFilter(new LoggingFilter());
    }

    public ClientResponse execute(String service) throws Exception {
        Request request = new Request();
        request.setToken(TOKEN);
        return client.resource("http://" + IP + ":" + PORT + service)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, request);
    }

    public Integer start() throws Exception {
        ClientResponse response = null;
        try {
            response = execute(START_RESOURCE);
            StartReturn result = response.getEntity(StartReturn.class);
            if (result.getError() != null && !result.getError().equals("")) {
                throw new Exception(result.getError());
            }
            return result.getSize();
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public ProposalResult test(String proposal) throws Exception {
        ClientResponse response = null;
        try {
            response = execute(TEST_RESOURCE);
            ProposalResult result = response.getEntity(ProposalResult.class);
            if (result.getError() != null && !result.getError().equals("")) {
                throw new Exception(result.getError());
            }
            return result;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
