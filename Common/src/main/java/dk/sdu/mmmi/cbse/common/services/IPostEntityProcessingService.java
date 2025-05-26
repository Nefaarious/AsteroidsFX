package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface serves as a service to process entities after the IEntityProcessorService is finished.
 */
public interface IPostEntityProcessingService {

    /**
     * This method is for processing entities after they have been processed by IEntityProcessingService.
     *
     * @param gameData contains stuff like display width and height and game keys.
     * @param world contains stuff like entities and their data.
     * @pre gameData and world has been initialized. IEntityProcessingService has been called already.
     * @post entities in the world have been processed and updated based on the post-processing logic.
     */

    void process(GameData gameData, World world);
}
