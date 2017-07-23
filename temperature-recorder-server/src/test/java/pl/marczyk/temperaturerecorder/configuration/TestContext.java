package pl.marczyk.temperaturerecorder.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.marczyk.temperaturerecorder.service.TemperatureService;

/**
 * Created by mm on 13.07.2017.
 */
@Configuration
public class TestContext {

    @Bean
    public TemperatureService temperatureService(){
        return Mockito.mock(TemperatureService.class);
    }
}
