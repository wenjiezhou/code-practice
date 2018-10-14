package com.apple.jay;

import com.apple.jay.resources.CryptoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CodePracticeApplication extends Application<CodePracticeConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CodePracticeApplication().run(args);
    }

    @Override
    public String getName() {
        return "CodePractice";
    }

    @Override
    public void initialize(final Bootstrap<CodePracticeConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CodePracticeConfiguration configuration,
                    final Environment environment) {
        CryptoResource cryptoResource = new CryptoResource();
        environment.jersey().register(cryptoResource);
    }

}
