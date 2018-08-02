package com.flyteam.mastermind;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ProposalResult;

/**
 *
 * @author jtapiat
 */
public class App {

    /**
     * DESCRIPTION : Methode main to execute
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = new ApiClient();
        ApiClient client = new ApiClient();
        int size = client.start();
        SimpleElementsFinder sef = new SimpleElementsFinder();
        String chInitiale = sef.find(client, size);
        ProposalResult proposalResult = apiClient.test(chInitiale);
        int firstOk = proposalResult.getGood();
        while (proposalResult.getGood() != size) {
            for (int position = 0; position < size; position++) {
                for (int i = position + 1; i < size; i++) {
                    String chReplaced = invertChar(chInitiale, position, i);
                    proposalResult = apiClient.test(chReplaced);
                    if (proposalResult.getGood() == size) {
                        position = size;
                        i = size;
                    } else if (proposalResult.getGood() > firstOk) {
                        chInitiale = chReplaced;
                        firstOk = proposalResult.getGood();
                        i = size;
                    }
                }
            }
        }
    }

    /**
     * DESCRIPTION : invert position between two caraters
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
