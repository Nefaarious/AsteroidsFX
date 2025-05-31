package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.components.HealthComponent;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private final Random random = new Random();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String pointsURL = "http://localhost:8080";

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            
            HealthComponent health = enemy.get(HealthComponent.class);
            if (health != null && health.getHealth() <= 0) {
                world.removeEntity(enemy);
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(pointsURL + "/enemy-destroyed"))
                            .GET()
                            .build();
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.discarding());
                } catch (Exception e) {
                    System.err.println("Failed to update score: " + e.getMessage());
                }
                continue;
            }
            
            if (random.nextDouble() < 0.1) { 
                enemy.setRotation(enemy.getRotation() - 5);
            }
            if (random.nextDouble() < 0.1) { 
                enemy.setRotation(enemy.getRotation() + 5);
            }

            
            if (random.nextDouble() < 0.3) { 
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }

           
            if (random.nextDouble() < 0.02) { 
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(enemy, gameData))
                );
            }


            double x = enemy.getX();
            double y = enemy.getY();
            int width = gameData.getDisplayWidth();
            int height = gameData.getDisplayHeight();

            if (x < 0) enemy.setX(width);
            if (x > width) enemy.setX(0);
            if (y < 0) enemy.setY(height);
            if (y > height) enemy.setY(0);
        }
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
