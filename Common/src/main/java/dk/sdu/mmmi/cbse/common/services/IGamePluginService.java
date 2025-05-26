package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface serves as a service to toggle game plugins on and off.
 */

public interface IGamePluginService {

    /**
     * This method is used to initialize the game state with the chosen entities.
     *
     * @param gameData contains stuff like display width and height and game keys.
     * @param world contains stuff like entities and their data.
     * @pre Game is not running.
     * @post Game is running with the chosen plugins/components.
     */
    void start(GameData gameData, World world);

    /**
     * This method is used to stop the game, and clean-up entities and resources.
     *
     * @param gameData contains stuff like display width and height and game keys.
     * @param world contains stuff like entities and their data.
     * @pre Game is running.
     * @post Game is stopped and all entities removed.
     */
    void stop(GameData gameData, World world);
}
