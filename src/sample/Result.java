package sample;

import robocode.*;

public class Result extends Robot {


public void run() {
while(true){
turnGunRight(105);
back(484);
doNothing();
fire(1);
back(186);
}
}

public void onScannedRobot(ScannedRobotEvent e) {
turnRight(163);
back(499);
fire(2);
turnRight(114);
doNothing();
}

public void onHitWall(HitWallEvent e) {
back(23);
turnGunRight(19);
turnLeft(143);
back(12);
turnLeft(146);
}

public void onHitRobot(HitRobotEvent e) {
back(734);
fire(3);
doNothing();
turnGunLeft(42);
doNothing();
}

public void onHitByBullet(HitByBulletEvent e) {
ahead(94);
turnLeft(114);
turnRight(90);
turnGunLeft(138);
fire(3);
}

public void onBulletHit(BulletHitEvent event){
turnGunRight(173);
turnGunLeft(19);
fire(3);
fire(3);
turnGunLeft(165);
}
}
