package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class BulletControlSystemTest {

    private BulletControlSystem bulletSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUp() {
        bulletSystem = new BulletControlSystem();

        gameData = new GameData();
        gameData.setDisplayWidth(800);
        gameData.setDisplayHeight(600);

        world = new World();
    }

    @Test
    public void testProcessMovesBullet() {
        Bullet bullet = new Bullet();
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRotation(0);

        System.out.println("Bullet position before being processed by EntityProcessor = ");
        System.out.println("Bullet X value is = " + bullet.getX());
        System.out.println("Bullet Y value is = " + bullet.getY());

        world.addEntity(bullet);
        bulletSystem.process(gameData, world);

        System.out.println("The bullets position after being processed by EntityProcessor = ");
        System.out.println("Bullet X value is = " + bullet.getX());
        System.out.println("Bullet Y value is = " + bullet.getY());

        assertTrue(bullet.getX() > 100);
        assertEquals(100, bullet.getY());
    }

    @Test
    public void testProcessRemovesBulletOutOfBounds() {
        Bullet bullet = new Bullet();
        // We are setting the bullets coordinates/position to be out of bounds.
        bullet.setX(900);
        bullet.setY(100);
        bullet.setRotation(0);

        System.out.println("Where the bullet is before allowed to move(hasnt been processed) = " + bullet.getX() + ", " + bullet.getY());
        world.addEntity(bullet);

        bulletSystem.process(gameData, world);

        boolean bulletExists = world.getEntities().contains(bullet);
        System.out.println("Does bullet exist after being processed? = " + bulletExists);

        assertFalse(bulletExists);
    }
}
