package sample;

import robocode.*;

public class random5X extends Robot {


public void run() {
while(true){
turnLeft(48);
turnRight(102);
back(14);
}
}

public void onScannedRobot(ScannedRobotEvent e) {
doNothing();
fire(3);
back(582);
}

public void onHitWall(HitWallEvent e) {
turnGunLeft(75);
back(694);
turnGunRight(37);
}

public void onHitRobot(HitRobotEvent e) {
doNothing();
turnLeft(56);
fire(3);
}

public void onHitByBullet(HitByBulletEvent e) {
turnGunLeft(169);
turnGunLeft(134);
turnLeft(6);
}

public void onBulletHit(BulletHitEvent event){
turnLeft(178);
fire(2);
fire(1);
}
}
