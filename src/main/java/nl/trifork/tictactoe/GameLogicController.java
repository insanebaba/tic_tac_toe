package nl.trifork.tictactoe;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
public class GameLogicController {

    private TreeSet<ScoreEntity> scores = new TreeSet<>((s1, s2) -> s2.getScore().compareTo(s1.getScore()));

    @PostMapping("/executeTurn")
    public String move(@RequestParam boolean turn, @RequestParam int column, @RequestParam int row) {
        return String.format("Turn executed for player '%b', on column '%d' and row '%d'", turn, column, row);
    }

    @PostMapping("/addScore")
    public boolean addScore(@RequestParam(required = false) String username, @RequestParam int score) {
        boolean result = scores.add(new ScoreEntity(username, LocalDateTime.now(), score));
        while (scores.size() > 10) {
            scores.pollLast();
        }
        return result;
    }

    @GetMapping("/topScores")
    @ResponseBody
    public Map<String, List<Integer>> topScores() {
        return scores.stream().map(ScoreEntity::getScore).collect(Collectors.groupingBy(s->"scores"));
    }

    public TreeSet<ScoreEntity> getScores() {
        return scores;
    }
}
