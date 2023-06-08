package kz.kasky.cinemaroom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CinemaRoomApplicationTests {


    private MockMvc mockMvc;

    public CinemaRoomApplicationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void contextLoads() {
    }


    @Test
    @DisplayName("" +
            "When calling the / endpoint we should get ok")
    void homeAuthenticatedTest() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk());
    }

}
