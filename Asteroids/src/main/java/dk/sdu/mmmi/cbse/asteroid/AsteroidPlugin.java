package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.components.CollisionComponent;
import dk.sdu.mmmi.cbse.common.components.HealthComponent;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private final int initialAsteroids = 4;
    private final Random rnd = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < initialAsteroids; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        int size = rnd.nextInt(10) + 15; 
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        
        asteroid.setX(rnd.nextDouble() * gameData.getDisplayWidth());
        asteroid.setY(rnd.nextDouble() * gameData.getDisplayHeight());
        asteroid.setRadius(size);
        asteroid.addComponent(new CollisionComponent());
        
        int health = size / 5; 
        asteroid.addComponent(new HealthComponent(health));
        
        asteroid.setRotation(rnd.nextDouble() * 360);
        return asteroid;
    }
}
