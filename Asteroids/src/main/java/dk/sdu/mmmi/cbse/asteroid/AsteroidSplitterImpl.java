package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.components.CollisionComponent;
import dk.sdu.mmmi.cbse.common.components.HealthComponent;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    private static final Random rnd = new Random();
    private static final int minSize = 8;

    @Override
    public void createSplitAsteroid(Entity entity, World world) {
        float radius = entity.getRadius();
        
        if (radius <= minSize) {
            world.removeEntity(entity);
            return;
        }

        for (int i = 0; i < 2; i++) {
            Asteroid splitAsteroid = new Asteroid();
            
            float newRadius = radius / 2;
            splitAsteroid.setRadius(newRadius);
            splitAsteroid.setPolygonCoordinates(
                newRadius, -newRadius,
                -newRadius, -newRadius,
                -newRadius, newRadius,
                newRadius, newRadius
            );

            float splitDistance = 40;
            double angle = -120 + (i * 180);
            
            float dx = (float) Math.cos(Math.toRadians(angle)) * splitDistance;
            float dy = (float) Math.sin(Math.toRadians(angle)) * splitDistance;
            
            splitAsteroid.setX(entity.getX() + dx);
            splitAsteroid.setY(entity.getY() + dy);
            
            splitAsteroid.setRotation(angle + rnd.nextInt(20) - 10);

            int health = (int)(newRadius / 5);
            splitAsteroid.addComponent(new HealthComponent(health));
            splitAsteroid.addComponent(new CollisionComponent());

            world.addEntity(splitAsteroid);
        }
        
        world.removeEntity(entity);
    }
}
