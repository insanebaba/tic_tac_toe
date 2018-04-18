package nl.trifork.tictactoe;

import java.time.LocalDateTime;
import java.util.Objects;

public class ScoreEntity {
    private Long id;

    private String username;

    private LocalDateTime scoreTime;

    private Integer score;

    public ScoreEntity() {
    }

    public ScoreEntity(String username, LocalDateTime scoreTime, Integer score) {
        this.username = username;
        this.scoreTime = scoreTime;
        this.score = score;
    }

    public ScoreEntity(LocalDateTime scoreTime, Integer score) {
        this.scoreTime = scoreTime;
        this.score = score;
    }

    public ScoreEntity(Integer score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(LocalDateTime scoreTime) {
        this.scoreTime = scoreTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreEntity)) return false;
        ScoreEntity that = (ScoreEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getScoreTime(), that.getScoreTime()) &&
                Objects.equals(getScore(), that.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getScoreTime(), getScore());
    }
}
