package pl.marczyk.temperaturerecorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marczyk.temperaturerecorder.model.Temperature;
import pl.marczyk.temperaturerecorder.repository.TemperatureRepository;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by mm on 13.07.2017.
 */
@Service
public class TemperatureService {

    @Autowired private Logger logger;
    private TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public Temperature createTemperature(double temperatureValue) {
        Temperature temperature = new Temperature(new Date(), temperatureValue);
        Temperature savedTemperature = temperatureRepository.save(temperature);
        logger.info(String.format("Created %s", savedTemperature));
        return savedTemperature;
    }

    public Iterable<Temperature> getLastTenTeperatures() {
        return temperatureRepository.findFirst10ByOrderByDateDesc();
    }
}
