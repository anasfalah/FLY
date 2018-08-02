/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.io.InputStream;
import java.util.Properties;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jtapiat
 */
public class ApiClient {

    private static final String TOKEN = "tokenfly";
    private static final String START_RESOURCE = "/api/start";
    private static final String TEST_RESOURCE = "/api/test";

    private final String ip;
    private final String port;

    private Client client = Client.create();
    
    protected synchronized Client getClient() {
        if (client == null) {
            client = Client.create();
        }
        return client;
    }

    public ApiClient() throws Exception {
        InputStream is = getClass().getResourceAsStream("/application.properties");
        Properties props = new Properties();
        props.load(is);
        this.ip = props.getProperty("api.ip");
        this.port = props.getProperty("api.port");
    }

    public ClientResponse execute(String service) throws ApiFailureException {
        return execute(service, null);
    }

    public ClientResponse execute(String service, String result) throws ApiFailureException {
        Request request = new Request();
        request.setToken(TOKEN);
        request.setResult(result);
        try {
            return getClient().resource("http://" + ip + ":" + port + service)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(ClientResponse.class, request);
        } catch (Exception e) {
            throw new ApiFailureException(e);
        }
    }

    public int start() throws ApiFailureException {
        ClientResponse response = null;
        try {
            response = execute(START_RESOURCE);
            StartReturn result = response.getEntity(StartReturn.class);
            if (result.getError() != null && !result.getError().equals("")) {
                throw new ApiFailureException(result.getError());
            }
            return result.getSize();
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public ProposalResult test(String proposal) throws ApiFailureException {
        ClientResponse response = null;
        try {
            response = execute(TEST_RESOURCE, proposal);
            ProposalResult result = response.getEntity(ProposalResult.class);
            if (result.getError() != null && !result.getError().equals("")) {
                throw new ApiFailureException(result.getError());
            }
            return result;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
