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

            splitAsteroid.setX(entity.getX());
            splitAsteroid.setY(entity.getY());

            splitAsteroid.addComponent(new CollisionComponent());
            int health = (int)(newRadius / 5);
            splitAsteroid.addComponent(new HealthComponent(health));

            double baseRotation = entity.getRotation();
            double spreadAngle = 45.0;
            double newRotation = baseRotation + (i == 0 ? -spreadAngle : spreadAngle);
            newRotation += rnd.nextDouble() * 30 - 15; 
            splitAsteroid.setRotation(newRotation);

            world.addEntity(splitAsteroid);
        }
        
        world.removeEntity(entity);
    }
}
