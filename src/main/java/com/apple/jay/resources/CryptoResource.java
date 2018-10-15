package com.apple.jay.resources;

import java.math.RoundingMode;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.apple.jay.api.CryptoResponse;
import com.apple.jay.util.Encryptor;
import com.apple.jay.util.NumberHelper;
import com.apple.jay.util.RunninStatistic;

@Path("/v1/apple")
@Produces(MediaType.APPLICATION_JSON)
public class CryptoResource {
    
    private final RunninStatistic runninStatistic = RunninStatistic.INSTANCE;
    
    private final Encryptor encryptor = Encryptor.INSTANCE;
    
    private final NumberHelper numberHelper = NumberHelper.INSTANCE;
    
    @Path("/push-recalculate")
    @POST
    public CryptoResponse pushAndRecalculate(@QueryParam("value") Optional<Double> value) {
        if (!value.isPresent()) {
            //TODO:Request validation. 400 BAD_REQUEST
        }
        runninStatistic.push(value.get());
        double mean = runninStatistic.mean();
        double sd = runninStatistic.standardDeviation(value.get());
        return new CryptoResponse.Builder()
                .average(numberHelper.rounding(mean, 3, RoundingMode.HALF_UP))
                .standardDeviation(numberHelper.rounding(sd, 3, RoundingMode.HALF_UP))
                .build();
    }
    @Path("/push-recalculate-encrypt")
    @POST
    public CryptoResponse PushRecalculateAndEncrypt(@QueryParam("value") Optional<Double> value) {
        if (!value.isPresent()) {
            //TODO:Request validation. 400 BAD_REQUEST
        }
        runninStatistic.push(value.get());
        double mean = runninStatistic.mean();
        double sd = runninStatistic.standardDeviation(value.get());
        double roundedMean = numberHelper.rounding(mean, 3, RoundingMode.HALF_UP);
        double roundedSd = numberHelper.rounding(sd, 3, RoundingMode.HALF_UP);
        return new CryptoResponse.Builder()
                .encryptedAverage(encryptor.encrypt(String.valueOf(roundedMean)))
                .encryptedStandardDeviation(encryptor.encrypt(String.valueOf(roundedSd)))
                .build();
    }
    
    @Path("/decrypt")
    @GET
    public CryptoResponse decrypt(@QueryParam("value") Optional<String> ciphertext) {
        if (!ciphertext.isPresent()) {
            //TODO:Request validation. 400 BAD_REQUEST
        }
        return new CryptoResponse.Builder()
                .plaintext(encryptor.decrypt(ciphertext.get()))
                .build();
    }

}
