package com.flyteam.mastermind;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ApiFailureException;
import com.flyteam.mastermind.client.ProposalResult;

/**
 *
 * @author jtapiat
 */
public class App {

    private int size;
    private int firstOk;
    private String chInitiale;

    /**
     * Constructor
     */
    public App() {
    }

    /**
     * Launches a string search
     *
     * @return The string corresponding to the search
     * @throws Exception
     */
    public String search() throws Exception {
        ApiClient apiClient = new ApiClient();
        size = apiClient.start();
        SimpleElementsFinder sef = new SimpleElementsFinder();
        chInitiale = sef.find(apiClient, size);
        ProposalResult proposalResult = apiClient.test(chInitiale);
        firstOk = proposalResult.getGood();
        while (proposalResult.getGood() != size) {
            testPosition(proposalResult, apiClient);
        }
        return chInitiale;
    }

    /**
     * DESCRIPTION : Test for correct position 
     * @param proposalResult
     * @param apiClient
     * @throws ApiFailureException
     */
    protected void testPosition(ProposalResult proposalResult, ApiClient apiClient) throws ApiFailureException {
        for (int position = 0; position < size; position++) {
            for (int i = position + 1; i < size; i++) {
                String chReplaced = invertChar(chInitiale, position, i);
                proposalResult = apiClient.test(chReplaced);
                if (proposalResult.getGood() == size) {
                    position = size;
                    break;
                } else if (proposalResult.getGood() > firstOk) {
                    chInitiale = chReplaced;
                    firstOk = proposalResult.getGood();
                    break;
                }
            }
        }
    }

    /**
     * DESCRIPTION : Methode main to execute
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new App().search();
    }

    /**
     * DESCRIPTION : invert position between two caraters
     *
     * @param chaine
     * @param p1
     * @param p2
     * @return
     */
    private static String invertChar(String chaine, int p1, int p2) {
        char arr[] = chaine.toCharArray();
        char tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
        return new String(arr);
    }
}
