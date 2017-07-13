package pl.marczyk.temperaturerecorder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

/**
 * Created by mm on 13.07.2017.
 */
@Configuration
public class Beans {

    @Bean
    public Logger logger() {
        return Logger.getAnonymousLogger();
    }
}
