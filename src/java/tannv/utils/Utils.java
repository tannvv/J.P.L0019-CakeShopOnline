/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.utils;

/**
 *
 * @author TanNV
 */
public class Utils {
    public static int numberPage(int element){
        int result = 0;
        if (element <= 0) {
            throw new IllegalArgumentException();
        }
        result = element / 20;
        if ((element % 20) != 0) {
            result = result + 1;
        }
        return result;
    }
}
