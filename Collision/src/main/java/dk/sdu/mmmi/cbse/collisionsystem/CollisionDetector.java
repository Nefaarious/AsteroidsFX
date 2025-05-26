package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.components.CollisionComponent;
import dk.sdu.mmmi.cbse.common.components.DamageComponent;
import dk.sdu.mmmi.cbse.common.components.HealthComponent;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                if (entity1.getID().equals(entity2.getID())) {
                    continue;                    
                }

                if (entity1.get(CollisionComponent.class) != null && 
                    entity2.get(CollisionComponent.class) != null) {
                    
                    if (collides(entity1, entity2)) {
                        DamageComponent damage1 = entity1.get(DamageComponent.class);
                        DamageComponent damage2 = entity2.get(DamageComponent.class);
                        
                        if (damage1 != null) {
                            applyDamage(entity2, damage1.getDamage(), world);
                            world.removeEntity(entity1);
                        } else if (damage2 != null) {
                            applyDamage(entity1, damage2.getDamage(), world);
                            world.removeEntity(entity2);
                        }
                    }
                }
            }
        }
    }

    private void applyDamage(Entity target, int damage, World world) {
        HealthComponent health = target.get(HealthComponent.class);
        if (health != null) {
            health.setHealth(health.getHealth() - damage);
        }
    }

    private Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
    
}
