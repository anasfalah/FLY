/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ProposalResult;

/**
 *
 * @author jtapiat
 */
public class App {

    static Set<String> permutations;
    static Set<String> result = new HashSet<String>();

    public static Set<String> permutation(String string) {
        permutations = new HashSet<String>();

        int n = string.length();
        for (int i = n - 1; i >= 0; i--) {
            shuffle(string.charAt(i));
        }
        return permutations;
    }

    private static void shuffle(char c) {
        if (permutations.size() == 0) {
            permutations.add(String.valueOf(c));
        } else {
            Iterator<String> it = permutations.iterator();
            for (int i = 0; i < permutations.size(); i++) {

                String temp1;
                for (; it.hasNext();) {
                    temp1 = it.next();
                    for (int k = 0; k < temp1.length() + 1; k += 1) {
                        StringBuilder sb = new StringBuilder(temp1);

                        sb.insert(k, c);

                        result.add(sb.toString());
                    }
                }
            }
            permutations = result;
            //'result' has to be refreshed so that in next run it doesn't contain stale values.
            result = new HashSet<String>();
        }
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = new ApiClient();
        String finalResult = "";
        int firstOk = 0;
        ApiClient client = new ApiClient();
        int size = client.start(); //53375480
        SimpleElementsFinder sef = new SimpleElementsFinder();
        String chInitiale = sef.find(client, size);
        ProposalResult proposalResult = apiClient.test(chInitiale);
        // archived first result
        firstOk = proposalResult.getGood();
        int nb = 0;
        while (proposalResult.getGood() != size) {
            if (proposalResult.getGood() == size) {
                // case when all is OK
                finalResult = chInitiale;
                System.out.println("1" + finalResult);
            } else {
                for (int position = 0; position < size; position++) {
                    for (int i = position + 1; i < size; i++) {
                        nb++;
                        String chReplaced = invertChar(chInitiale, position, i);
                        proposalResult = apiClient.test(chReplaced);
                        if (proposalResult.getGood() == size) {
                            finalResult = chReplaced;
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
        System.out.println("final; " + finalResult);
    }

    private static String invertChar(String chaine, int p1, int p2) {
        char arr[] = chaine.toCharArray();
        char tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
        return new String(arr);
    }


   
}
