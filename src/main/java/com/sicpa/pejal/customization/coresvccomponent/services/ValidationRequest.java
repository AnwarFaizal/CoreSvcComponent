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
@Builder
@Data
public class ValidationRequest {
    
    private String puId;
    private String hrcStart;
    private String hrcEnd;
    private String productId;
    
}
