/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.services;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author azainal
 */
@Data
@Builder
public class DeclarationResponse {
    
    private String status;
    private String message;
    private String correlation_id;
    
}
