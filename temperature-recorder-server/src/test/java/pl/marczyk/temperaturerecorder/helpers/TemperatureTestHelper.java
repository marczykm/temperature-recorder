package pl.marczyk.temperaturerecorder.helpers;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import pl.marczyk.temperaturerecorder.model.Temperature;

import java.util.List;

/**
 * Created by mm on 13.07.2017.
 */
public class TemperatureTestHelper {

    public static List<Temperature> prepareTemperatures() {
        List<Temperature> temperatures = Lists.newArrayList();
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 18, 0).toDate(), 19.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 16, 0).toDate(), 18.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 14, 0).toDate(), 17.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 12, 0).toDate(), 16.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 10, 0).toDate(), 15.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 8, 0).toDate(), 14.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 6, 0).toDate(), 13.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 4, 0).toDate(), 12.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 2, 0).toDate(), 11.3));
        temperatures.add(new Temperature(new DateTime(2000, 1, 1, 23, 0, 0, 0).toDate(), 10.3));

        return temperatures;
    }

}
