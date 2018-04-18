package nl.trifork.tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicTacToeApplicationTests {


    //To test services
    @LocalServerPort
    private int port;

    //Check if Context is loaded or not
    @Test
    public void contexLoads() throws Exception {
    }



}
