package dk.sdu.mmmi.cbse.pointsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PointSystem {
    
    private static final int pointsPerEnemy = 10;
    private Long totalScore = 0L;

    public static void main(String[] args) {
        SpringApplication.run(PointSystem.class, args);
    }

    @GetMapping("/score")
    public Long updateScore(@RequestParam(value = "point", required = false) Long point) {
        if (point != null) {
            totalScore += point;
        }
        return totalScore;
    }

    @GetMapping("/current-score")
    public Long getCurrentScore() {
        return totalScore;
    }

    @GetMapping("/update-score")
    public void setScore(@RequestParam("score") Long score) {
        totalScore = score;
    }

    @GetMapping("/enemy-destroyed")
    public Long addEnemyDestroyPoints() {
        totalScore += pointsPerEnemy;
        return totalScore;
    }
} 