package sample;

import robocode.*;

public class random1 extends Robot {


public void run() {
while(true){
ahead(737);
turnRight(100);
turnLeft(12);
turnGunLeft(20);
turnGunLeft(53);
}
}

public void onScannedRobot(ScannedRobotEvent e) {
turnGunLeft(131);
turnGunLeft(71);
ahead(661);
back(326);
turnGunLeft(45);
}

public void onHitWall(HitWallEvent e) {
back(688);
turnGunLeft(162);
turnRight(158);
back(312);
turnLeft(172);
}

public void onHitRobot(HitRobotEvent e) {
fire(1);
ahead(84);
turnGunLeft(148);
back(630);
turnGunRight(32);
}

public void onHitByBullet(HitByBulletEvent e) {
fire(2);
fire(1);
ahead(419);
back(764);
turnGunLeft(160);
}

public void onBulletHit(BulletHitEvent event){
turnLeft(109);
ahead(161);
turnRight(149);
doNothing();
turnGunRight(172);
}
}
