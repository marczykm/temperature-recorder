package pl.marczyk.temperaturerecorder.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.marczyk.temperaturerecorder.model.Temperature;
import pl.marczyk.temperaturerecorder.service.TemperatureService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by mm on 13.07.2017.
 */
@RestController
@RequestMapping(TemperatureController.TEMPERATURE_API_URL)
public class TemperatureController {

    static final String TEMPERATURE_API_URL = "/api/v1/temperature";
    private static final String CREATE_TEMPERATURE_URL = "/{temperature:.+}";
    public static final String CURRENT_URL = "/current";
    public static final String CURRENT_WIDGET = "/current/widget";

    private TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping(CREATE_TEMPERATURE_URL)
    public ResponseEntity createTemperature(@PathVariable double temperature){
        temperatureService.createTemperature(temperature);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Temperature>> getLastTenTemperatures() {
        return new ResponseEntity<>(temperatureService.getLastTenTeperatures(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(CURRENT_URL)
    public ResponseEntity<Temperature> getCurrentTemperature() {
        // TODO: add error if temperature older than 1 minute
        return new ResponseEntity<>(temperatureService.getLastTemperature(), HttpStatus.OK);
    }

    @GetMapping(CURRENT_WIDGET)
    public String getCurrentTemperatureForWidget() {
        Temperature lastTemperature = temperatureService.getLastTemperature();
        LocalDateTime localDate = LocalDateTime.ofInstant(lastTemperature.getDate().toInstant(), ZoneId.systemDefault());
        return String.format("%s\n%s°C", localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                lastTemperature.getTemperature());
    }
}
