/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praveen.psr.asi.customExceptions;

/**
 *
 * @author temp
 */
public class AppiumDriverLockedException extends Exception {

    public AppiumDriverLockedException(String Message) {
        super(Message);
    }
}
