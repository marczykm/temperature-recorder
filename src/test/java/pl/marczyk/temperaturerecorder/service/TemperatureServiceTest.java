package pl.marczyk.temperaturerecorder.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import pl.marczyk.temperaturerecorder.helpers.TemperatureTestHelper;
import pl.marczyk.temperaturerecorder.model.Temperature;
import pl.marczyk.temperaturerecorder.repository.TemperatureRepository;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static pl.marczyk.temperaturerecorder.helpers.TemperatureTestHelper.prepareTemperatures;

/**
 * Created by mm on 13.07.2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TemperatureService.class})
public class TemperatureServiceTest {

    public static final Date DATE = new DateTime(2000, 1, 1, 1,1).toDate();
    public static final double TEMPERATURE_VALUE = 23.2;

    @Mock
    private TemperatureRepository temperatureRepository;
    @Mock
    private Logger logger;

    @Before
    public void setUp() throws Exception {
        doNothing().when(logger).info(anyString());
    }

    @Test
    public void creates_temperature() throws Exception {
        // given
        TemperatureService spy = spy(new TemperatureService(temperatureRepository, logger));
        Temperature temperature = new Temperature(DATE, TEMPERATURE_VALUE);
        when(temperatureRepository.save(any(Temperature.class))).thenReturn(temperature);
        when(spy, method(TemperatureService.class, "getActualDate"))
                .withNoArguments().thenReturn(DATE);

        // when
        Temperature result = spy.createTemperature(TEMPERATURE_VALUE);

        // then
        assertThat(result).isEqualTo(temperature);
        verify(temperatureRepository, times(1)).save(temperature);
        verifyNoMoreInteractions(temperatureRepository);
    }

    @Test
    public void returns_ten_last_temperatures() throws Exception {
        // given
        List<Temperature> temperatures = prepareTemperatures();
        TemperatureService spy = spy(new TemperatureService(temperatureRepository, logger));
        when(temperatureRepository.findFirst10ByOrderByDateDesc()).thenReturn(temperatures);

        // when
        Iterable<Temperature> result = spy.getLastTenTeperatures();

        // then
        assertThat(result).isEqualTo(temperatures);
        verify(temperatureRepository, times(1)).findFirst10ByOrderByDateDesc();
        verifyNoMoreInteractions(temperatureRepository);
    }

    @Test
    public void return_last_temperature() throws Exception {
        // given
        Temperature temperature = new Temperature(DATE, TEMPERATURE_VALUE);
        TemperatureService spy = spy(new TemperatureService(temperatureRepository, logger));
        when(temperatureRepository.findFirst1ByOrderByDateDesc()).thenReturn(temperature);

        // when
        Temperature result = spy.getLastTemperature();

        // then
        assertThat(result).isEqualTo(temperature);
        verify(temperatureRepository, times(1)).findFirst1ByOrderByDateDesc();
        verifyNoMoreInteractions(temperatureRepository);
    }

}