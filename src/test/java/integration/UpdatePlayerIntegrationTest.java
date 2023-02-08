package integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdatePlayerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUpdateData() throws Exception {
        // Envoyer une requête PUT
        mockMvc.perform(put("/matches/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk());

        // Vérification des résultats
        MvcResult result = mockMvc.perform(get("/matches/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        // Assertions
        assert(response).equals("{\"id\":1,\"field\":\"value\"}");
    }
}
