package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * The purpose of this interface is to create bullets.
 *@pre Player/Enemy tries to shoot.
 *@post A bullet is shot from the player/enemy.
 */
public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
