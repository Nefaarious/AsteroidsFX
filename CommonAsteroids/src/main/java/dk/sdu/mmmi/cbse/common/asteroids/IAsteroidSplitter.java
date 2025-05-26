package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The purpose of this interface is to split the asteroid into smaller asteroids when hit a certain amount of times.
 *@pre The asteroid has been hit a certain amount of times.
 *@post The asteroid has been split into smaller asteroids, until it is the minimum size where it will be destroyed.
 */
public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w);
}
