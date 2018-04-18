package nl.trifork.tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicTacToeApplicationTests {


    //To test services
    @LocalServerPort
    private int port;

    @Autowired
    private GameLogicController controller;


    @Autowired
    private TestRestTemplate restTemplate;


    //Check if Context is loaded or not
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void AddingScoreToScoreList() throws Exception {
        Integer score = new Integer(12341);
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/addScore", score,
                String.class)).contains("true");
    }


}
