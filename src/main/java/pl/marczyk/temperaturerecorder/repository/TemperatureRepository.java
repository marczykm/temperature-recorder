package pl.marczyk.temperaturerecorder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.marczyk.temperaturerecorder.model.Temperature;

import java.util.List;

/**
 * Created by mm on 13.07.2017.
 */
@Repository
public interface TemperatureRepository extends CrudRepository<Temperature, Long>{
    List<Temperature> findFirst10ByOrderByDateDesc();
    Temperature findFirst1ByOrderByDateDesc();
}
