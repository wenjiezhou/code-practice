package com.apple.jay.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class CryptoResponse {
    
    private Double average;
    
    private Double standardDeviation;
    
    private String plaintext;
    
    private String encryptedAverage;
    
    private String encryptedStandardDeviation;

    private CryptoResponse(Double average, 
            Double standardDeviation, 
            String plaintext, 
            String encryptedAverage, 
            String encryptedStandardDeviation) {
        this.average = average;
        this.standardDeviation = standardDeviation;
        this.plaintext = plaintext;
        this.encryptedAverage = encryptedAverage;
        this.encryptedStandardDeviation = encryptedStandardDeviation;
    }
    
    @JsonProperty()
    public Double getAverage() {
        return average;
    }

    @JsonProperty("standard_deviation")
    public Double getStandardDeviation() {
        return standardDeviation;
    }    
    
    @JsonProperty
    public String getPlaintext() {
        return plaintext;
    }

    @JsonProperty("encrypted_average")
    public String getEncryptedAverage() {
        return encryptedAverage;
    }

    @JsonProperty("encrypted_standard_deviation")
    public String getEncryptedStandardDeviation() {
        return encryptedStandardDeviation;
    }   
    
    public static class Builder {
        private Double average;
        
        private Double standardDeviation;
        
        private String plaintext;
        
        private String encryptedAverage;
        
        private String encryptedStandardDeviation;

        public Double getAverage() {
            return average;
        }

        public Builder average(Double average) {
            this.average = average;
            return this;
        }

        public Builder standardDeviation(Double standardDeviation) {
            this.standardDeviation = standardDeviation;
            return this;
        }

        public Builder plaintext(String plaintext) {
            this.plaintext = plaintext;
            return this;
        }

        public Builder encryptedAverage(String encryptedAverage) {
            this.encryptedAverage = encryptedAverage;
            return this;
        }

        public Builder encryptedStandardDeviation(String encryptedStandardDeviation) {
            this.encryptedStandardDeviation = encryptedStandardDeviation;
            return this;
        }
        
        public CryptoResponse build() {
            return new CryptoResponse(average, 
                    standardDeviation, 
                    plaintext, 
                    encryptedAverage,
                    encryptedStandardDeviation);
        }
    }
}
