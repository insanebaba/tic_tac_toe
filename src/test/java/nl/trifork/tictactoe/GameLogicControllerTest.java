package nl.trifork.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameLogicControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @InjectMocks
    private GameLogicController gameLogicController;

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void move() {
        gameLogicController.move(true,2,3);

    }

    @Test
    public void addScore() {
        gameLogicController.addScore("sampleUserName",332);
        assertTrue(gameLogicController.getScores().first().getUsername().equals("sampleUserName"));
        assertTrue(gameLogicController.getScores().first().getScore().equals(332));
    }

    @Test
    public void topScoresShouldReturnOneResult() {
        int score=455;
        gameLogicController.addScore("sampleUserName",score);
        Map<String, List<Integer>> map = gameLogicController.topScores();
        assertTrue(map.containsKey("scores"));
        assertTrue(map.get("scores").size()==1);
        assertTrue(map.get("scores").get(0)==score);
    }


    @Test
    public void topScoresShouldNotBeLargerThan10() {
        for (int i=1;i<=20;i++)
            gameLogicController.addScore("sampleUserName",i*50);
        Map<String, List<Integer>> map = gameLogicController.topScores();
        assertTrue(map.containsKey("scores"));
        assertTrue(map.get("scores").size()==10);
        assertTrue(map.get("scores").get(0)==1000);
        assertTrue(map.get("scores").get(1)==19*50);

    }
}