package pl.marczyk.temperaturerecorder.web;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.marczyk.temperaturerecorder.configuration.Beans;
import pl.marczyk.temperaturerecorder.configuration.TestContext;
import pl.marczyk.temperaturerecorder.model.Temperature;
import pl.marczyk.temperaturerecorder.service.TemperatureService;

import java.lang.reflect.TypeVariable;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by mm on 13.07.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, Beans.class})
@WebAppConfiguration
public class TemperatureControllerTest {

    private static final double TEMPERATURE_VALUE = 23.2;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private TemperatureService temperatureServiceMock;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .standaloneSetup(new TemperatureController(temperatureServiceMock))
                .build();
    }

    @Test
    public void returns_list_of_last_ten_json_formatted_temperature_values() throws Exception {
        List<Temperature> temperatures = prepareTemperatures();
        when(temperatureServiceMock.getLastTenTeperatures()).thenReturn(temperatures);

        ResultActions $ = mvc.perform(get(TemperatureController.TEMPERATURE_API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(temperatures.size())));

        for (int i = 0; i < temperatures.size(); i++) {
            $.andExpect(jsonPath(id(i), anything()))
                    .andExpect(jsonPath(temperature(i), is(temperatures.get(i).getTemperature())))
                    .andExpect(jsonPath(date(i), is(temperatures.get(i).getDate().getTime())));
        }
        verify(temperatureServiceMock, times(1)).getLastTenTeperatures();
        verifyNoMoreInteractions(temperatureServiceMock);
    }

    @Test
    public void creates_temperature() throws Exception {
        Temperature temperature = new Temperature(new Date(), TEMPERATURE_VALUE);
        when(temperatureServiceMock.createTemperature(anyDouble())).thenReturn(temperature);

        mvc.perform(post(TemperatureController.TEMPERATURE_API_URL + "/" + TEMPERATURE_VALUE))
                .andExpect(status().isCreated());

        verify(temperatureServiceMock, times(1)).createTemperature(TEMPERATURE_VALUE);
        verifyNoMoreInteractions(temperatureServiceMock);
    }

    private String date(int i) {
        return "$[" + i + "].date";
    }

    private String temperature(int i) {
        return "$[" + i + "].temperature";
    }

    private String id(int i) {
        return "$[" + i + "].id";
    }

    private List<Temperature> prepareTemperatures() {
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