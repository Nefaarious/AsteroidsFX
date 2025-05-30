module Collision {
    requires Common;
    requires CommonBullet;
    provides dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
}