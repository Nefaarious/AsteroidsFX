package dk.sdu.mmmi.cbse.system;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Player extends Entity {
    private double lastBullet;

    public void setLastBullet(double lastBullet) {
        this.lastBullet = lastBullet;
    }

    public double getLastBullet() {
        return lastBullet;
    }
}
