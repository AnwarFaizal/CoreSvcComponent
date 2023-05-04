/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.services;

import lombok.Data;

/**
 *
 * @author azainal
 */
@Data
public class ValidationStatus {
    
    private String message;
    private boolean passed;
}
