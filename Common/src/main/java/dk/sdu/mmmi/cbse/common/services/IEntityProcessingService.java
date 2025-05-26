package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface serves as a service to process entities that are in the world.
 * It is used to initialize entities, update their state and handle any game logic related to them.
 */
public interface IEntityProcessingService {

    /**
     * This method is used to process entities in the game world.
     *
     * @param gameData contains stuff like display width and height and game keys.
     * @param world contains stuff like entities and their data.
     * @pre gameData and world has been initialized.
     * @post entities in the world have been processed and updated.
     */
    void process(GameData gameData, World world);
}
