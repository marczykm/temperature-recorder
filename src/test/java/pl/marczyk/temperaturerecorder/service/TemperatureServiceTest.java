package pl.marczyk.temperaturerecorder.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import pl.marczyk.temperaturerecorder.model.Temperature;
import pl.marczyk.temperaturerecorder.repository.TemperatureRepository;

import java.util.Date;
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

/**
 * Created by mm on 13.07.2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TemperatureService.class})
public class TemperatureServiceTest {

    public static final Date DATE = new DateTime(2000, 1, 1, 1,1).toDate();
    public static final double TEMPERATURE_VALUE = 23.2;
//    @InjectMocks
//    private TemperatureService temperatureService;

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
        when(spy, method(TemperatureService.class, "getActualTemperature"))
                .withNoArguments().thenReturn(DATE);

        // when
        Temperature result = spy.createTemperature(TEMPERATURE_VALUE);

        // then
        assertThat(result).isEqualTo(temperature);
        verify(temperatureRepository, times(1)).save(temperature);
        verifyNoMoreInteractions(temperatureRepository);
    }

}