package com.apple.jay.resources;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.apple.jay.api.CryptoResponse;
import com.apple.jay.util.Encryptor;
import com.apple.jay.util.RunninStatistic;

@Path("/v1/apple/crypto")
@Produces(MediaType.APPLICATION_JSON)
public class CryptoResource {
    
    private RunninStatistic runninStatistic = RunninStatistic.INSTANCE;
    
    private Encryptor encryptor = Encryptor.INSTANCE;
    
    @GET
    public CryptoResponse pushAndRecalculate(@QueryParam("value") double value) {
        runninStatistic.push(value);
        double mean = runninStatistic.mean();
        double sd = runninStatistic.standardDeviation();
        double roundedMean = new BigDecimal(mean).setScale(3, RoundingMode.HALF_UP).doubleValue();
        double roundedSd = new BigDecimal(sd).setScale(3, RoundingMode.HALF_UP).doubleValue();
        return new CryptoResponse.Builder()
                .average(roundedMean)
                .standardDeviation(roundedSd)
                .build();
    }
    @Path("/encrypt")
    @GET
    public CryptoResponse PushRecalculateAndEncrypt(@QueryParam("value") double value) {
        runninStatistic.push(value);
        double mean = runninStatistic.mean();
        double sd = runninStatistic.standardDeviation();
        double roundedMean = new BigDecimal(mean).setScale(3, RoundingMode.HALF_UP).doubleValue();
        double roundedSd = new BigDecimal(sd).setScale(3, RoundingMode.HALF_UP).doubleValue();
        return new CryptoResponse.Builder()
                .encryptedAverage(encryptor.encrypt(String.valueOf(roundedMean)))
                .encryptedStandardDeviation(encryptor.encrypt(String.valueOf(roundedSd)))
                .build();
    }
    
    @Path("/decrypt")
    @GET
    public CryptoResponse decrypt(@QueryParam("value") String ciphertext) {
        return new CryptoResponse.Builder()
                .plaintext(encryptor.decrypt(ciphertext))
                .build();
    }

}
