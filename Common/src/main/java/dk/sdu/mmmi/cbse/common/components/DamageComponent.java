package dk.sdu.mmmi.cbse.common.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class DamageComponent implements Component {
    private final int damage;
    
    public DamageComponent(int damage) {
        this.damage = damage;
    }
    
    public int getDamage() {
        return damage;
    }

    @Override
    public void process(GameData gameData, World world) {
    }
} 