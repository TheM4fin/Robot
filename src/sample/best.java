package sample;

import robocode.*;

public class best extends Robot {


public void run() {
while(true){
fire(2);
turnGunRight(115);
back(240);
turnRight(77);
turnGunRight(118);
}
}

public void onScannedRobot(ScannedRobotEvent e) {
ahead(554);
fire(2);
turnRight(86);
turnGunRight(3);
turnLeft(44);
}

public void onHitWall(HitWallEvent e) {
turnGunRight(99);
back(388);
ahead(589);
ahead(292);
turnRight(22);
}

public void onHitRobot(HitRobotEvent e) {
turnRight(35);
fire(2);
turnGunLeft(123);
turnGunLeft(75);
doNothing();
}

public void onHitByBullet(HitByBulletEvent e) {
turnRight(112);
turnLeft(157);
ahead(31);
turnGunLeft(49);
turnGunLeft(63);
}

public void onBulletHit(BulletHitEvent event){
turnGunRight(100);
doNothing();
turnGunRight(53);
doNothing();
turnGunRight(75);
}
}
