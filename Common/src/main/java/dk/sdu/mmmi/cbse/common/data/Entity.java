package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.components.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.UUID;


public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private final Map<Class<?>, Component> components = new ConcurrentHashMap<>();
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
            

    public String getID() {
        return ID.toString();
    }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
    }

    public void removeComponent(Class<? extends Component> componentClass) {
        components.remove(componentClass);
    }

    public <E extends Component> E get(Class<E> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }
}
