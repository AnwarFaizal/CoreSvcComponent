/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 *
 * @author azainal
 */
@Data
@Builder
@Jacksonized
public class DeclarationRequest {
    @JsonProperty("puid")
    private String puid;
    
    @JsonProperty("startHrc")
    private String startHrc;
    
    @JsonProperty("endHrc")
    private String endHrc;
    
    @JsonProperty("siteId")
    private String siteId;
    
    @JsonProperty("productId")
    private String productId;
}
