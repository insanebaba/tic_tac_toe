package nl.trifork.tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GameLogicControllerTest {

    @InjectMocks
    private GameLogicController gameLogicController;



    @Test
    public void moveReturnsExpectedMessage() {
        String result = gameLogicController.move(true, 2, 3);
        assertEquals(result, String.format("Turn executed for player '%b', on column '%d' and row '%d'", true, 2, 3));
    }

    @Test
    public void addScore() {
        gameLogicController.addScore("sampleUserName",332);
        assertEquals("sampleUserName", gameLogicController.getScores().first().getUsername());
        assertEquals(332, (int) gameLogicController.getScores().first().getScore());
    }

    @Test
    public void topScoresShouldReturnOneResult() {
        int score=455;
        gameLogicController.addScore("sampleUserName",score);
        Map<String, List<Integer>> map = gameLogicController.topScores();
        assertTrue(map.containsKey("scores"));
        assertEquals(1, map.get("scores").size());
        assertEquals((int) map.get("scores").get(0), score);
    }


    @Test
    public void topScoresShouldNotBeLargerThan10() {
        for (int i=1;i<=20;i++)
            gameLogicController.addScore("sampleUserName",i*50);
        Map<String, List<Integer>> map = gameLogicController.topScores();
        assertTrue(map.containsKey("scores"));
        assertEquals(10, map.get("scores").size());
        assertTrue(map.get("scores").get(0)==1000);
        assertTrue(map.get("scores").get(1)==19*50);

    }
}