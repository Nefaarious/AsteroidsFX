package dk.sdu.mmmi.cbse.common.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The purpose of this interface is to process components.
 *@pre A component needs to be processed.
 *@post The component is processed.
 */

 /*
  * HealthComponent is a component that processes the health of an entity.
  *@pre An entity is supposed to be damaged somehow.
  *@post The health of the entity is changed.
  */

  /*
   * CollisionComponent is a component that processes the collision of an entity.
   *@pre An entity is colliding with another entity.
   *@post The collision of the entities is processed.
   */

   /*
    * DamageComponent is a component that processes the damage of an entity.
    *@pre An entity is supposed to take or deal damage.
    *@post The correct amount of damage is taken or dealt.
    */

public interface Component {
    void process(GameData gameData, World world);
}
