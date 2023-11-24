package tanks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import sample.Commands.*;
import sample.Reactions.*;

public class CreateRobot {

	// nutno zmenit cestu k projektu na vasem systemu
	public String cesta = "C:\\Projects\\Robocode\\MPC-PDA-GP-2020";

	public String PATH = new String(cesta + "\\src\\sample");
	public String PACKAGE = new String("sample");
	public String JARS = new String(cesta + "\\lib\\robocode.jar;");

	public String tankResult;

	public String getTankResult() {
		return tankResult;
	}

	public void setTankResult(String tankResult) {
		this.tankResult = tankResult;
	}

	private String tankName;

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	private ArrayList<AbstractReactions> reactionList = new ArrayList<AbstractReactions>();

	public ArrayList<AbstractReactions> getReactionList() {
		return reactionList;
	}

	public void setReactionList(ArrayList<AbstractReactions> reactionList) {
		this.reactionList = reactionList;
	}

	public enum tankType{
		Corners,
		TrackFire,
		Walls,
		Tracker,
		RamFire,
		Rand
	}

	public CreateRobot(String tankName, tankType tankType) {
		super();
		this.tankName = tankName;

		switch (tankType) {
			case Rand:
				CreateRandomRobot();
				break;
			case Walls:
				CreateWallsRobot();
				break;
			case Corners:
				CreateCornersRobot();
				break;
			case RamFire:
				CreateRamFireRobot();
				break;
			case Tracker:
				CreateTrackerRobot();
				break;
			case TrackFire:
				break;
		}
	}
	private void CreateTrackFireRobot(){
		tankResult = "package sample;\n" +
				"\n" +
				"\n" +
				"import robocode.Robot;\n" +
				"import robocode.ScannedRobotEvent;\n" +
				"import robocode.WinEvent;\n" +
				"import static robocode.util.Utils.normalRelativeAngleDegrees;\n" +
				"\n" +
				"import java.awt.*;\n" +
				"\n" +
				"\n" +
				"/**\n" +
				" * TrackFire - a sample robot by Mathew Nelson.\n" +
				" * <p>\n" +
				" * Sits still. Tracks and fires at the nearest robot it sees.\n" +
				" *\n" +
				" * @author Mathew A. Nelson (original)\n" +
				" * @author Flemming N. Larsen (contributor)\n" +
				" */\n" +
				"public class TrackFire extends Robot {\n" +
				"\n" +
				"\t/**\n" +
				"\t * TrackFire's run method\n" +
				"\t */\n" +
				"\tpublic void run() {\n" +
				"\t\t// Set colors\n" +
				"\t\tsetBodyColor(Color.pink);\n" +
				"\t\tsetGunColor(Color.pink);\n" +
				"\t\tsetRadarColor(Color.pink);\n" +
				"\t\tsetScanColor(Color.pink);\n" +
				"\t\tsetBulletColor(Color.pink);\n" +
				"\n" +
				"\t\t// Loop forever\n" +
				"\t\twhile (true) {\n" +
				"\t\t\tturnGunRight(10); // Scans automatically\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onScannedRobot:  We have a target.  Go get it.\n" +
				"\t */\n" +
				"\tpublic void onScannedRobot(ScannedRobotEvent e) {\n" +
				"\t\t// Calculate exact location of the robot\n" +
				"\t\tdouble absoluteBearing = getHeading() + e.getBearing();\n" +
				"\t\tdouble bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());\n" +
				"\n" +
				"\t\t// If it's close enough, fire!\n" +
				"\t\tif (Math.abs(bearingFromGun) <= 3) {\n" +
				"\t\t\tturnGunRight(bearingFromGun);\n" +
				"\t\t\t// We check gun heat here, because calling fire()\n" +
				"\t\t\t// uses a turn, which could cause us to lose track\n" +
				"\t\t\t// of the other robot.\n" +
				"\t\t\tif (getGunHeat() == 0) {\n" +
				"\t\t\t\tfire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));\n" +
				"\t\t\t}\n" +
				"\t\t} // otherwise just set the gun to turn.\n" +
				"\t\t// Note:  This will have no effect until we call scan()\n" +
				"\t\telse {\n" +
				"\t\t\tturnGunRight(bearingFromGun);\n" +
				"\t\t}\n" +
				"\t\t// Generates another scan event if we see a robot.\n" +
				"\t\t// We only need to call this if the gun (and therefore radar)\n" +
				"\t\t// are not turning.  Otherwise, scan is called automatically.\n" +
				"\t\tif (bearingFromGun == 0) {\n" +
				"\t\t\tscan();\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\tpublic void onWin(WinEvent e) {\n" +
				"\t\t// Victory dance\n" +
				"\t\tturnRight(36000);\n" +
				"\t}\n" +
				"}";
	}
	private void CreateTrackerRobot(){
		tankResult = "package sample;\n" +
				"\n" +
				"\n" +
				"import robocode.HitRobotEvent;\n" +
				"import robocode.Robot;\n" +
				"import robocode.ScannedRobotEvent;\n" +
				"import robocode.WinEvent;\n" +
				"import static robocode.util.Utils.normalRelativeAngleDegrees;\n" +
				"\n" +
				"import java.awt.*;\n" +
				"\n" +
				"\n" +
				"/**\n" +
				" * Tracker - a sample robot by Mathew Nelson.\n" +
				" * <p>\n" +
				" * Locks onto a robot, moves close, fires when close.\n" +
				" *\n" +
				" * @author Mathew A. Nelson (original)\n" +
				" * @author Flemming N. Larsen (contributor)\n" +
				" */\n" +
				"public class Tracker extends Robot {\n" +
				"\tint count = 0; // Keeps track of how long we've\n" +
				"\t// been searching for our target\n" +
				"\tdouble gunTurnAmt; // How much to turn our gun when searching\n" +
				"\tString trackName; // Name of the robot we're currently tracking\n" +
				"\n" +
				"\t/**\n" +
				"\t * run:  Tracker's main run function\n" +
				"\t */\n" +
				"\tpublic void run() {\n" +
				"\t\t// Set colors\n" +
				"\t\tsetBodyColor(new Color(128, 128, 50));\n" +
				"\t\tsetGunColor(new Color(50, 50, 20));\n" +
				"\t\tsetRadarColor(new Color(200, 200, 70));\n" +
				"\t\tsetScanColor(Color.white);\n" +
				"\t\tsetBulletColor(Color.blue);\n" +
				"\n" +
				"\t\t// Prepare gun\n" +
				"\t\ttrackName = null; // Initialize to not tracking anyone\n" +
				"\t\tsetAdjustGunForRobotTurn(true); // Keep the gun still when we turn\n" +
				"\t\tgunTurnAmt = 10; // Initialize gunTurn to 10\n" +
				"\n" +
				"\t\t// Loop forever\n" +
				"\t\twhile (true) {\n" +
				"\t\t\t// turn the Gun (looks for enemy)\n" +
				"\t\t\tturnGunRight(gunTurnAmt);\n" +
				"\t\t\t// Keep track of how long we've been looking\n" +
				"\t\t\tcount++;\n" +
				"\t\t\t// If we've haven't seen our target for 2 turns, look left\n" +
				"\t\t\tif (count > 2) {\n" +
				"\t\t\t\tgunTurnAmt = -10;\n" +
				"\t\t\t}\n" +
				"\t\t\t// If we still haven't seen our target for 5 turns, look right\n" +
				"\t\t\tif (count > 5) {\n" +
				"\t\t\t\tgunTurnAmt = 10;\n" +
				"\t\t\t}\n" +
				"\t\t\t// If we *still* haven't seen our target after 10 turns, find another target\n" +
				"\t\t\tif (count > 11) {\n" +
				"\t\t\t\ttrackName = null;\n" +
				"\t\t\t}\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onScannedRobot:  Here's the good stuff\n" +
				"\t */\n" +
				"\tpublic void onScannedRobot(ScannedRobotEvent e) {\n" +
				"\n" +
				"\t\t// If we have a target, and this isn't it, return immediately\n" +
				"\t\t// so we can get more ScannedRobotEvents.\n" +
				"\t\tif (trackName != null && !e.getName().equals(trackName)) {\n" +
				"\t\t\treturn;\n" +
				"\t\t}\n" +
				"\n" +
				"\t\t// If we don't have a target, well, now we do!\n" +
				"\t\tif (trackName == null) {\n" +
				"\t\t\ttrackName = e.getName();\n" +
				"\t\t\tout.println(\"Tracking \" + trackName);\n" +
				"\t\t}\n" +
				"\t\t// This is our target.  Reset count (see the run method)\n" +
				"\t\tcount = 0;\n" +
				"\t\t// If our target is too far away, turn and move toward it.\n" +
				"\t\tif (e.getDistance() > 150) {\n" +
				"\t\t\tgunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));\n" +
				"\n" +
				"\t\t\tturnGunRight(gunTurnAmt); // Try changing these to setTurnGunRight,\n" +
				"\t\t\tturnRight(e.getBearing()); // and see how much Tracker improves...\n" +
				"\t\t\t// (you'll have to make Tracker an AdvancedRobot)\n" +
				"\t\t\tahead(e.getDistance() - 140);\n" +
				"\t\t\treturn;\n" +
				"\t\t}\n" +
				"\n" +
				"\t\t// Our target is close.\n" +
				"\t\tgunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));\n" +
				"\t\tturnGunRight(gunTurnAmt);\n" +
				"\t\tfire(3);\n" +
				"\n" +
				"\t\t// Our target is too close!  Back up.\n" +
				"\t\tif (e.getDistance() < 100) {\n" +
				"\t\t\tif (e.getBearing() > -90 && e.getBearing() <= 90) {\n" +
				"\t\t\t\tback(40);\n" +
				"\t\t\t} else {\n" +
				"\t\t\t\tahead(40);\n" +
				"\t\t\t}\n" +
				"\t\t}\n" +
				"\t\tscan();\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onHitRobot:  Set him as our new target\n" +
				"\t */\n" +
				"\tpublic void onHitRobot(HitRobotEvent e) {\n" +
				"\t\t// Only print if he's not already our target.\n" +
				"\t\tif (trackName != null && !trackName.equals(e.getName())) {\n" +
				"\t\t\tout.println(\"Tracking \" + e.getName() + \" due to collision\");\n" +
				"\t\t}\n" +
				"\t\t// Set the target\n" +
				"\t\ttrackName = e.getName();\n" +
				"\t\t// Back up a bit.\n" +
				"\t\t// Note:  We won't get scan events while we're doing this!\n" +
				"\t\t// An AdvancedRobot might use setBack(); execute();\n" +
				"\t\tgunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));\n" +
				"\t\tturnGunRight(gunTurnAmt);\n" +
				"\t\tfire(3);\n" +
				"\t\tback(50);\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onWin:  Do a victory dance\n" +
				"\t */\n" +
				"\tpublic void onWin(WinEvent e) {\n" +
				"\t\tfor (int i = 0; i < 50; i++) {\n" +
				"\t\t\tturnRight(30);\n" +
				"\t\t\tturnLeft(30);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"}";
	}
	private void CreateRamFireRobot(){
		tankResult = "package sample;\n" +
				"\n" +
				"\n" +
				"import robocode.HitRobotEvent;\n" +
				"import robocode.Robot;\n" +
				"import robocode.ScannedRobotEvent;\n" +
				"\n" +
				"import java.awt.*;\n" +
				"\n" +
				"\n" +
				"/**\n" +
				" * RamFire - a sample robot by Mathew Nelson.\n" +
				" * <p>\n" +
				" * Drives at robots trying to ram them.\n" +
				" * Fires when it hits them.\n" +
				" *\n" +
				" * @author Mathew A. Nelson (original)\n" +
				" * @author Flemming N. Larsen (contributor)\n" +
				" */\n" +
				"public class RamFire extends Robot {\n" +
				"\tint turnDirection = 1; // Clockwise or counterclockwise\n" +
				"\n" +
				"\t/**\n" +
				"\t * run: Spin around looking for a target\n" +
				"\t */\n" +
				"\tpublic void run() {\n" +
				"\t\t// Set colors\n" +
				"\t\tsetBodyColor(Color.lightGray);\n" +
				"\t\tsetGunColor(Color.gray);\n" +
				"\t\tsetRadarColor(Color.darkGray);\n" +
				"\n" +
				"\t\twhile (true) {\n" +
				"\t\t\tturnRight(5 * turnDirection);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onScannedRobot:  We have a target.  Go get it.\n" +
				"\t */\n" +
				"\tpublic void onScannedRobot(ScannedRobotEvent e) {\n" +
				"\t\tSystem.out.println(\"XXXXXXXXXXX SCANNED XXXXXXXXXXXX \");\n" +
				"\t\tif (e.getBearing() >= 0) {\n" +
				"\t\t\tturnDirection = 1;\n" +
				"\t\t} else {\n" +
				"\t\t\tturnDirection = -1;\n" +
				"\t\t}\n" +
				"\n" +
				"\t\tturnRight(e.getBearing());\n" +
				"\t\tahead(e.getDistance() + 5);\n" +
				"\t\tscan(); // Might want to move ahead again!\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onHitRobot:  Turn to face robot, fire hard, and ram him again!\n" +
				"\t */\n" +
				"\tpublic void onHitRobot(HitRobotEvent e) {\n" +
				"\t\tif (e.getBearing() >= 0) {\n" +
				"\t\t\tturnDirection = 1;\n" +
				"\t\t} else {\n" +
				"\t\t\tturnDirection = -1;\n" +
				"\t\t}\n" +
				"\t\tturnRight(e.getBearing());\n" +
				"\n" +
				"\t\t// Determine a shot that won't kill the robot...\n" +
				"\t\t// We want to ram him instead for bonus points\n" +
				"\t\tif (e.getEnergy() > 16) {\n" +
				"\t\t\tfire(3);\n" +
				"\t\t} else if (e.getEnergy() > 10) {\n" +
				"\t\t\tfire(2);\n" +
				"\t\t} else if (e.getEnergy() > 4) {\n" +
				"\t\t\tfire(1);\n" +
				"\t\t} else if (e.getEnergy() > 2) {\n" +
				"\t\t\tfire(.5);\n" +
				"\t\t} else if (e.getEnergy() > .4) {\n" +
				"\t\t\tfire(.1);\n" +
				"\t\t}\n" +
				"\t\tahead(40); // Ram him again!\n" +
				"\t}\n" +
				"}";
	}

	private void CreateCornersRobot(){
		tankResult = "package sample;\n" +
				"\n" +
				"\n" +
				"import robocode.DeathEvent;\n" +
				"import robocode.Robot;\n" +
				"import robocode.ScannedRobotEvent;\n" +
				"import static robocode.util.Utils.normalRelativeAngleDegrees;\n" +
				"\n" +
				"import java.awt.*;\n" +
				"\n" +
				" \n" +
				"/**\n" +
				" * Corners - a sample robot by Mathew Nelson.\n" +
				" * <p>\n" +
				" * This robot moves to a corner, then swings the gun back and forth.\n" +
				" * If it dies, it tries a new corner in the next round.\n" +
				" *\n" +
				" * @author Mathew A. Nelson (original)\n" +
				" * @author Flemming N. Larsen (contributor)\n" +
				" */\n" +
				"public class Corners extends Robot {\n" +
				"\tint others; // Number of other robots in the game\n" +
				"\tstatic int corner = 0; // Which corner we are currently using\n" +
				"\t// static so that it keeps it between rounds.\n" +
				"\tboolean stopWhenSeeRobot = false; // See goCorner()\n" +
				"\n" +
				"\t/**\n" +
				"\t * run:  Corners' main run function.\n" +
				"\t */\n" +
				"\tpublic void run() { \n" +
				"\t\t// Set colors\n" +
				"\t\tsetBodyColor(Color.red);\n" +
				"\t\tsetGunColor(Color.black);\n" +
				"\t\tsetRadarColor(Color.yellow);\n" +
				"\t\tsetBulletColor(Color.green);\n" +
				"\t\tsetScanColor(Color.green);\n" +
				"\n" +
				"\t\t// Save # of other bots\n" +
				"\t\tothers = getOthers();\n" +
				"\n" +
				"\t\t// Move to a corner\n" +
				"\t\tgoCorner();\n" +
				"\n" +
				"\t\t// Initialize gun turn speed to 3\n" +
				"\t\tint gunIncrement = 3;\n" +
				"\n" +
				"\t\t// Spin gun back and forth\n" +
				"\t\twhile (true) {\n" +
				"\t\t\tfor (int i = 0; i < 30; i++) {\n" +
				"\t\t\t\tturnGunLeft(gunIncrement);\n" +
				"\t\t\t}\n" +
				"\t\t\tgunIncrement *= -1;\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * goCorner:  A very inefficient way to get to a corner.  Can you do better?\n" +
				"\t */\n" +
				"\tpublic void goCorner() {\n" +
				"\t\t// We don't want to stop when we're just turning...\n" +
				"\t\tstopWhenSeeRobot = false;\n" +
				"\t\t// turn to face the wall to the \"right\" of our desired corner.\n" +
				"\t\tturnRight(normalRelativeAngleDegrees(corner - getHeading()));\n" +
				"\t\t// Ok, now we don't want to crash into any robot in our way...\n" +
				"\t\tstopWhenSeeRobot = true;\n" +
				"\t\t// Move to that wall\n" +
				"\t\tahead(5000);\n" +
				"\t\t// Turn to face the corner\n" +
				"\t\tturnLeft(90);\n" +
				"\t\t// Move to the corner\n" +
				"\t\tahead(5000);\n" +
				"\t\t// Turn gun to starting point\n" +
				"\t\tturnGunLeft(90);\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onScannedRobot:  Stop and fire!\n" +
				"\t */\n" +
				"\tpublic void onScannedRobot(ScannedRobotEvent e) {\n" +
				"\t\t// Should we stop, or just fire?\n" +
				"\t\tif (stopWhenSeeRobot) {\n" +
				"\t\t\t// Stop everything!  You can safely call stop multiple times.\n" +
				"\t\t\tstop();\n" +
				"\t\t\t// Call our custom firing method\n" +
				"\t\t\tsmartFire(e.getDistance());\n" +
				"\t\t\t// Look for another robot.\n" +
				"\t\t\t// NOTE:  If you call scan() inside onScannedRobot, and it sees a robot,\n" +
				"\t\t\t// the game will interrupt the event handler and start it over\n" +
				"\t\t\tscan();\n" +
				"\t\t\t// We won't get here if we saw another robot.\n" +
				"\t\t\t// Okay, we didn't see another robot... start moving or turning again.\n" +
				"\t\t\tresume();\n" +
				"\t\t} else {\n" +
				"\t\t\tsmartFire(e.getDistance());\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * smartFire:  Custom fire method that determines firepower based on distance.\n" +
				"\t *\n" +
				"\t * @param robotDistance the distance to the robot to fire at\n" +
				"\t */\n" +
				"\tpublic void smartFire(double robotDistance) {\n" +
				"\t\tif (robotDistance > 200 || getEnergy() < 15) {\n" +
				"\t\t\tfire(1);\n" +
				"\t\t} else if (robotDistance > 50) {\n" +
				"\t\t\tfire(2);\n" +
				"\t\t} else {\n" +
				"\t\t\tfire(3);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * onDeath:  We died.  Decide whether to try a different corner next game.\n" +
				"\t */\n" +
				"\tpublic void onDeath(DeathEvent e) {\n" +
				"\t\t// Well, others should never be 0, but better safe than sorry.\n" +
				"\t\tif (others == 0) {\n" +
				"\t\t\treturn;\n" +
				"\t\t}\n" +
				"\n" +
				"\t\t// If 75% of the robots are still alive when we die, we'll switch corners.\n" +
				"\t\tif ((others - getOthers()) / (double) others < .75) {\n" +
				"\t\t\tcorner += 90;\n" +
				"\t\t\tif (corner == 270) {\n" +
				"\t\t\t\tcorner = -90;\n" +
				"\t\t\t}\n" +
				"\t\t\tout.println(\"I died and did poorly... switching corner to \" + corner);\n" +
				"\t\t} else {\n" +
				"\t\t\tout.println(\"I died but did well.  I will still use corner \" + corner);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"}";
	}
	private void CreateWallsRobot(){
	tankResult = "package sample;\n" +
			"\n" +
			"\n" +
			"import robocode.HitRobotEvent;\n" +
			"import robocode.Robot;\n" +
			"import robocode.ScannedRobotEvent;\n" +
			"\n" +
			"import java.awt.*;\n" +
			"\n" +
			"\n" +
			"/**\n" +
			" * Walls - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen\n" +
			" * <p>\n" +
			" * Moves around the outer edge with the gun facing in.\n" +
			" *\n" +
			" * @author Mathew A. Nelson (original)\n" +
			" * @author Flemming N. Larsen (contributor)\n" +
			" */\n" +
			"public class Walls extends Robot {\n" +
			"\n" +
			"\tboolean peek; // Don't turn if there's a robot there\n" +
			"\tdouble moveAmount; // How much to move\n" +
			"\n" +
			"\t/**\n" +
			"\t * run: Move around the walls\n" +
			"\t */\n" +
			"\tpublic void run() {\n" +
			"\t\t// Set colors\n" +
			"\t\tsetBodyColor(Color.black);\n" +
			"\t\tsetGunColor(Color.black);\n" +
			"\t\tsetRadarColor(Color.orange);\n" +
			"\t\tsetBulletColor(Color.cyan);\n" +
			"\t\tsetScanColor(Color.cyan);\n" +
			"\n" +
			"\t\t// Initialize moveAmount to the maximum possible for this battlefield.\n" +
			"\t\tmoveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());\n" +
			"\t\t// Initialize peek to false\n" +
			"\t\tpeek = false;\n" +
			"\n" +
			"\t\t// turnLeft to face a wall.\n" +
			"\t\t// getHeading() % 90 means the remainder of\n" +
			"\t\t// getHeading() divided by 90.\n" +
			"\t\tturnLeft(getHeading() % 90);\n" +
			"\t\tahead(moveAmount);\n" +
			"\t\t// Turn the gun to turn right 90 degrees.\n" +
			"\t\tpeek = true;\n" +
			"\t\tturnGunRight(90);\n" +
			"\t\tturnRight(90);\n" +
			"\n" +
			"\t\twhile (true) {\n" +
			"\t\t\t// Look before we turn when ahead() completes.\n" +
			"\t\t\tpeek = true;\n" +
			"\t\t\t// Move up the wall\n" +
			"\t\t\tahead(moveAmount);\n" +
			"\t\t\t// Don't look now\n" +
			"\t\t\tpeek = false;\n" +
			"\t\t\t// Turn to the next wall\n" +
			"\t\t\tturnRight(90);\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\n" +
			"\t/**\n" +
			"\t * onHitRobot:  Move away a bit.\n" +
			"\t */\n" +
			"\tpublic void onHitRobot(HitRobotEvent e) {\n" +
			"\t\t// If he's in front of us, set back up a bit.\n" +
			"\t\tif (e.getBearing() > -90 && e.getBearing() < 90) {\n" +
			"\t\t\tback(100);\n" +
			"\t\t} // else he's in back of us, so set ahead a bit.\n" +
			"\t\telse {\n" +
			"\t\t\tahead(100);\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\n" +
			"\t/**\n" +
			"\t * onScannedRobot:  Fire!\n" +
			"\t */\n" +
			"\tpublic void onScannedRobot(ScannedRobotEvent e) {\n" +
			"\t\tfire(2);\n" +
			"\t\t// Note that scan is called automatically when the robot is moving.\n" +
			"\t\t// By calling it manually here, we make sure we generate another scan event if there's a robot on the next\n" +
			"\t\t// wall, so that we do not start moving up it until it's gone.\n" +
			"\t\tif (peek) {\n" +
			"\t\t\tscan();\n" +
			"\t\t}\n" +
			"\t}\n" +
			"}";
	}

	private void CreateRandomRobot() {
		ArrayList<AbstractTankCommand> commandList = new ArrayList<AbstractTankCommand>();
		Random randomN = new Random();

		reactionListFill();

		for (int i = 0; i < reactionList.size(); i++) {
			for (int j = 0; j < 5; j++) {
				int selectCommand = randomN.nextInt(8);
				switch (selectCommand) {
					case 0: // ahead
						Ahead newAhead = new Ahead();
						commandList.add(newAhead);
						break;
					case 1: // back
						Back newBack = new Back();
						commandList.add(newBack);
						break;
					case 2: // doNothing
						DoNothing newDoNothing = new DoNothing();
						commandList.add(newDoNothing);
						break;
					case 3: // fire
						Fire newFire = new Fire();
						commandList.add(newFire);
						break;
					case 4: // turnGunLeft
						TurnGunLeft newTurnGunLeft = new TurnGunLeft();
						commandList.add(newTurnGunLeft);
						break;
					case 5: // turnGunRight
						TurnGunRight newTurnGunRight = new TurnGunRight();
						commandList.add(newTurnGunRight);
						break;
					case 6: // turnLeft
						TurnLeft newTurnLeft = new TurnLeft();
						commandList.add(newTurnLeft);
						break;
					case 7: // turnRight
						TurnRight newTurnRight = new TurnRight();
						commandList.add(newTurnRight);
						break;
				}
				reactionList.get(i).addCommand(commandList.get(j));
			}
			// reactionList.get(i).setCommandList(commandList);
			commandList.clear();
		}
		createTankResult();
	}

	private void reactionListFill() {
		Run run = new Run();
		OnScannedRobot scanned = new OnScannedRobot();
		OnHitWall onHitWall = new OnHitWall();
		OnHitRobot onHitRobot = new OnHitRobot();
		OnHitByBullet onHitByBullet = new OnHitByBullet();
		OnBulletHit onBulletHit = new OnBulletHit();

		reactionList.add(run);
		reactionList.add(scanned);
		reactionList.add(onHitWall);
		reactionList.add(onHitRobot);
		reactionList.add(onHitByBullet);
		reactionList.add(onBulletHit);
	}

	public void createTankResult() {
		tankResult = "";
		tankResult += "package sample;\n" + "\n" + "import robocode.*;\n\n" + "public class " + this.tankName
				+ " extends Robot {\n\n";

		for (int i = 0; i < reactionList.size(); i++) {
			tankResult += reactionList.get(i);
		}
		tankResult += "}\n";
	}

	public String createRobotFile() {
		try {
			FileWriter fstream = new FileWriter(PATH + "/" + tankName + ".java");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(tankResult);
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return (PATH + "/" + tankName + ".class");
	}

	public static void execute(String command) throws Exception {
		Process process = Runtime.getRuntime().exec(command);
		process.waitFor();
		if (process.exitValue() != 0)
			System.out.println(command + "exited with value " + process.exitValue());
	}

	@Override
	public String toString() {
		return tankResult;
	}

	public void MutateRobot(double reactionMutationProbability, double commandMutationProbability) {
		for (AbstractReactions reaction : reactionList) {
			reaction.MutateReaction(reactionMutationProbability);
			for (AbstractTankCommand command : reaction.getCommandList()) {
				command.MutateCommand(commandMutationProbability);
			}
		}
		createTankResult();
	}

	public CreateRobot(CreateRobot parentRobot, String tankName) {
		super();
		this.tankName = tankName;
		for (AbstractReactions reaction : parentRobot.getReactionList()) {
			this.reactionList.add(reaction.clone());
		}
		createTankResult();
	}

	public void crossOver(double reactionCrossoverProbability, double commandCrossoverProbability,
			CreateRobot subRobot) {
		Random randomN = new Random();
		for (AbstractReactions reaction : reactionList) {
			double chanceNumReaction = randomN.nextDouble();
			if (chanceNumReaction <= reactionCrossoverProbability) {
				reaction.setCommandList(
						subRobot.getReactionList().get(reactionList.indexOf(reaction)).clone().getCommandList());
				continue;
			}
			for (AbstractTankCommand command : reaction.getCommandList()) {
				double chanceNumCommand = randomN.nextDouble();
				if (chanceNumCommand <= commandCrossoverProbability) {
					reaction.getCommandList().set(reaction.getCommandList().indexOf(command),
							subRobot.getReactionList().get(reactionList.indexOf(reaction)).getCommandList()
									.get(reaction.getCommandList().indexOf(command)).clone());
				}
			}

		}
		createTankResult();
	}

}
