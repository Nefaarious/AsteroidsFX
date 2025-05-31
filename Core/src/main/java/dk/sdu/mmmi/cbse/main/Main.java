package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.List;
import javafx.application.Application;
import dk.sdu.mmmi.cbse.main.SpringConfig;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    public static void main(String[]args){
        launch(Main.class);
    }


    @Override
    public void start(Stage window) throws Exception {
        var ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        World world = ctx.getBean(World.class);
        GameData gameData = ctx.getBean(GameData.class);

        List<IEntityProcessingService> entityProcess = ctx.getBean("entityProcess", List.class);
        List<IGamePluginService> gamePlugins = ctx.getBean("gamePlugins", List.class);
        List<IPostEntityProcessingService> postProcess = ctx.getBean("postProcess", List.class);

        Game game = new Game(gameData, world, gamePlugins, entityProcess, postProcess);

        game.start(window);
    }

}
