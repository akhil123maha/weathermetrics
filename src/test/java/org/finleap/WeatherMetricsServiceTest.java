package org.finleap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherMetricsServiceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void weatherForecastAverageTest() throws Exception {

        String city = "London";

        mvc.perform(get("/weather/data")
                .contentType(MediaType.APPLICATION_JSON)
                .param("city", city))
                .andDo(print())
                .andExpect(status().isOk());

        city = "ABC";

        mvc.perform(get("/weather/data")
                .contentType(MediaType.APPLICATION_JSON)
                .param("city", city))
                .andDo(print())
                .andExpect(status().isNotFound());

        mvc.perform(get("/weather/data")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mvc.perform(get("/weather/data")
                .contentType(MediaType.APPLICATION_JSON)
                .param("city", ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
