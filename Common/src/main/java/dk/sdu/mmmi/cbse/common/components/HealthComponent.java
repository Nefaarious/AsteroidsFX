package dk.sdu.mmmi.cbse.common.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class HealthComponent implements Component {

    private int health;

    public HealthComponent(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void process(GameData gameData, World world) {
    }
}
