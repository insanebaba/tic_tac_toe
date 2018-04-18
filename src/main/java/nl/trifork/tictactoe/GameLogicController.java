package nl.trifork.tictactoe;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.TreeSet;

@RestController
public class GameLogicController {

    TreeSet<ScoreEntity> scores = new TreeSet<>((s1, s2) -> s2.getScore().compareTo(s1.getScore()));

    @PostMapping("/executeTurn")
    public String move(@RequestParam boolean turn, @RequestParam int column, @RequestParam int row) {
        return String.format("Turn executed for player '%b', on column '%d' and row '%d'", turn, column, row);
    }

    @PostMapping("/addScore")
    public boolean addScore(@RequestParam(required = false) String username, @RequestParam int score) {
        boolean result = scores.add(new ScoreEntity(username, LocalDateTime.now(), score));
        while(scores.size()>10){
            scores.pollLast();
        }
        return result;
    }
}
