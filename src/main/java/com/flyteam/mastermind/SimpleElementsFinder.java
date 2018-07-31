/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ProposalResult;

/**
 *
 * @author jtapiat
 */
public class SimpleElementsFinder {

    public String find(ApiClient client, int size) throws Exception {
        StringBuilder res = new StringBuilder();
        int correctCharacters = 0;
        for (int i = 0; i < 10; i++) {
            StringBuilder test = new StringBuilder();
            for (int j = 0; j < size; j++) {
                test.append(i);
            }
            ProposalResult apiResult = client.test(test.toString());
            for (int k = 0; k < apiResult.getGood() + apiResult.getWrong_place(); k++) {
                res.append(i);
            }
            correctCharacters += apiResult.getGood() + apiResult.getWrong_place();
            if (correctCharacters == size) {
                break;
            }
        }
        return res.toString();
    }
}
