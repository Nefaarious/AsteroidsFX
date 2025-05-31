package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dk.sdu.mmmi.cbse.common.data.World;
@Configuration
class SpringConfig {

    public SpringConfig() {
    }

    @Bean
    public GameData gameData() {
        GameData gameData = new GameData();
        return new GameData();
    }

    @Bean
    public World world() {
        return new World();
    }

    @Bean
    public Game game(GameData gameData, World world, List<IGamePluginService> gamePlugins, List<IEntityProcessingService> entityProcess, List<IPostEntityProcessingService> postProcess) {
        return new Game(gameData, world, gamePlugins, entityProcess, postProcess);
    }

    @Bean
    public List<IEntityProcessingService> entityProcess(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePlugins() {
        System.out.println("Searching for plugins");
        ServiceLoader<IGamePluginService> loader = ServiceLoader.load(IGamePluginService.class);
        var plugins = loader.stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
        System.out.println(plugins.size());
        return plugins;
    }

    @Bean
    public List<IPostEntityProcessingService> postProcess() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}