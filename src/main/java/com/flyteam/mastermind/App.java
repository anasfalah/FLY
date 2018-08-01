/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind;

import java.util.Random;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ProposalResult;

/**
 *
 * @author jtapiat
 */
public class App {

    public void main(String[] args) throws Exception {
        ApiClient apiClient = new ApiClient();
        String finalResult = "";
        int firstOk = 0;
        int firstWp = 0;
        int sizeStart = apiClient.start();
        boolean isResultOk = false;
        String chInitiale = "";
        while (!isResultOk) {
            if (chInitiale.equals("")) {
                for (int i = 0; i < sizeStart; i++) {
                    chInitiale += getRandomNumberInRange(0, 9) + "";
                    finalResult += "A";
                }
            } 
            System.out.println("token generated : " + chInitiale);
            // traitement
            ProposalResult proposalResult = apiClient.test(chInitiale);
            if (null != proposalResult) {
                // archived first result
                firstOk = proposalResult.getGood();
                firstWp = proposalResult.getWrongPlace();
                if (proposalResult.getGood() == sizeStart) {
                    // case when all is OK
                    isResultOk = true;
                    finalResult = chInitiale;
                } else if(proposalResult.getWrongPlace() == sizeStart){
                    // case when all numbers not good placed
                    
                }else {
                   String chNotUsed = getNumberNotUsed(chInitiale);
                   if(!chNotUsed.equals("")) {
                       int position = 0;
                       for (int i = 0; i <= chNotUsed.length(); i++) {
                           Character c = chNotUsed.charAt(i);
                           String chReplaced = replaceCharacter(c, chInitiale, position);
                           proposalResult = apiClient.test(chReplaced);
                           if(null != proposalResult) {
                              int good = proposalResult.getGood();
                              int wrongPlaced = proposalResult.getWrongPlace();
                              if(good > firstOk) {
                                  finalResult = replaceCharacter(c, finalResult, position);
                                  position++;
                              }else if(good < firstOk) {
                                  Character b = chInitiale.charAt(position);
                                  finalResult = replaceCharacter(b, finalResult, position);
                                  position++;
                              }else if(wrongPlaced > firstWp) {
                                  chInitiale = chReplaced;
                              }else if(wrongPlaced < firstWp) {
                                  
                              }
                           }
                       }
                   }
                }
                // finalResult is completed
                isResultOk = isResultOk(finalResult, sizeStart);
            }
        }
    }

    
    
    
    private String getNumberNotUsed(String ch) {
        String result = "";
        for (int i = 0; i <= 9; i++) {
            if (!ch.contains(String.valueOf(i))) {
                result += i;
            }
        }
        return result;
    }

    private static String replaceCharacter(Character c, String chaine, int position) {
        if (position == 0) {
            return c + chaine.substring(position + 1, chaine.length());
        } else {
            return chaine.substring(0, position) + c + chaine.substring(position + 1, chaine.length());
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    private boolean isResultOk(String finalResult, int length) {
        if(finalResult.length() == length && isNumber(finalResult)) {
            return true;
        }
        return false;
    }
    
    public boolean isNumber(String s) {
        try {
            if (s.equals("")) {
                return false;
            }
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
