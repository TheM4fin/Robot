package sample;

import robocode.*;

public class random4 extends Robot {


public void run() {
while(true){
turnRight(173);
back(239);
doNothing();
fire(2);
ahead(707);
}
}

public void onScannedRobot(ScannedRobotEvent e) {
fire(1);
turnGunLeft(121);
turnGunLeft(164);
doNothing();
ahead(332);
}

public void onHitWall(HitWallEvent e) {
fire(1);
back(328);
turnGunRight(42);
doNothing();
turnLeft(55);
}

public void onHitRobot(HitRobotEvent e) {
doNothing();
fire(3);
turnGunLeft(12);
turnGunRight(158);
ahead(412);
}

public void onHitByBullet(HitByBulletEvent e) {
doNothing();
turnLeft(57);
doNothing();
turnLeft(143);
turnLeft(72);
}

public void onBulletHit(BulletHitEvent event){
back(527);
turnRight(63);
back(443);
turnGunRight(164);
ahead(365);
}
}
