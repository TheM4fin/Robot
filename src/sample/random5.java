package sample;

import robocode.*;

public class random5 extends Robot {


public void run() {
while(true){
turnRight(57);
fire(3);
fire(3);
back(534);
fire(2);
}
}

public void onScannedRobot(ScannedRobotEvent e) {
turnLeft(139);
back(210);
back(775);
ahead(325);
turnLeft(152);
}

public void onHitWall(HitWallEvent e) {
turnGunRight(175);
doNothing();
turnLeft(130);
back(556);
turnGunRight(161);
}

public void onHitRobot(HitRobotEvent e) {
turnRight(144);
turnRight(160);
ahead(121);
turnGunRight(100);
turnRight(144);
}

public void onHitByBullet(HitByBulletEvent e) {
turnGunRight(149);
ahead(690);
doNothing();
turnLeft(84);
back(754);
}

public void onBulletHit(BulletHitEvent event){
doNothing();
turnLeft(57);
fire(2);
turnGunRight(140);
ahead(766);
}
}
