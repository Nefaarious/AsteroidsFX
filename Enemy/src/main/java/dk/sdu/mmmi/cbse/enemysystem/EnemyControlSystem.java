package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    private final Random random = new Random();
    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {

            // Random turning
            if (random.nextDouble() < 0.1) { // 10% chance to turn left
                enemy.setRotation(enemy.getRotation() - 5);
            }
            if (random.nextDouble() < 0.1) { // 10% chance to turn right
                enemy.setRotation(enemy.getRotation() + 5);
            }

            // Random forward movement
            if (random.nextDouble() < 0.3) { // 30% chance to move forward
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }

            // Random shooting
            if (random.nextDouble() < 0.02) { // 2% chance to shoot per frame
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
