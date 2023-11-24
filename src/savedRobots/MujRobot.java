package savedRobots;

//nejlepsi po 9500 generacich

import robocode.BulletHitEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class MujRobot extends Robot {
	public void run() {
		while(true){
		turnGunRight(180);
		doNothing();
		turnGunRight(8);
		}
		}

		public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
		turnRight(86);
		back(191);
		}

		public void onHitWall(HitWallEvent e) {
		turnGunLeft(47);
		turnGunRight(38);
		turnGunRight(46);
		}

		public void onHitRobot(HitRobotEvent e) {
		doNothing();
		doNothing();
		doNothing();
		}

		public void onHitByBullet(HitByBulletEvent e) {
		fire(3);
		turnGunLeft(112);
		turnGunLeft(118);
		}

		public void onBulletHit(BulletHitEvent event){
		turnGunRight(129);
		doNothing();
		doNothing();
		}
}