package sample;

import robocode.*;

public class random3X extends Robot {


public void run() {
while(true){
doNothing();
turnRight(102);
turnRight(143);
}
}

public void onScannedRobot(ScannedRobotEvent e) {
doNothing();
fire(3);
turnLeft(100);
}

public void onHitWall(HitWallEvent e) {
ahead(799);
turnGunLeft(134);
turnGunRight(67);
}

public void onHitRobot(HitRobotEvent e) {
doNothing();
turnLeft(56);
fire(3);
}

public void onHitByBullet(HitByBulletEvent e) {
turnGunRight(45);
back(284);
turnLeft(6);
}

public void onBulletHit(BulletHitEvent event){
turnLeft(178);
fire(2);
fire(1);
}
}
