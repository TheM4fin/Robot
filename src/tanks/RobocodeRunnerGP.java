package tanks;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Random;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import robocode.BattleResults;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;

public class RobocodeRunnerGP {

    public static void main(String[] args) throws IOException {

        //final double replicateProb = 0.1; // SUM 1
        final double crossoverProb = 0.7;
        final double mutateProb = 0.2;

        final double mutateTermProb = 0.75;
        final double mutateFuncProb = 0.2;

        final double crossoverTermProb = 0.75;
        final double crossoverFuncProb = 0.2;

        final int numberOfEvolutions = 500;
        int currentEvolution = 0;

        Random randomN = new Random();

        ArrayList<String> battleResults = new ArrayList<String>();

        TreeMap<Integer, String> tm= new TreeMap<Integer, String>();
		ArrayList<Integer> results = new ArrayList<Integer>();
        int score1, score2, score3, score4, score5 = 0;
		int best = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;

        CreateRobot random1 = new CreateRobot("random1", CreateRobot.tankType.Rand);
        CreateRobot random2 = new CreateRobot("random2", CreateRobot.tankType.Rand);
        CreateRobot random3 = new CreateRobot("random3", CreateRobot.tankType.Rand);
        CreateRobot random4 = new CreateRobot("random4", CreateRobot.tankType.Rand);
        CreateRobot random5 = new CreateRobot("random5", CreateRobot.tankType.Rand);

        CreateRobot strongBot1 = new CreateRobot("Walls", CreateRobot.tankType.Walls);
        CreateRobot strongBot2 = new CreateRobot("Tracker", CreateRobot.tankType.Tracker);
        CreateRobot strongBot3 = new CreateRobot("RamFire", CreateRobot.tankType.RamFire);
        CreateRobot strongBot4 = new CreateRobot("Corners", CreateRobot.tankType.Corners);
        CreateRobot strongBot5 = new CreateRobot("TrackFire", CreateRobot.tankType.TrackFire);

        CreateRobot winner = null;
        CreateRobot secondBest = null;
        CreateRobot Final = null;
        String firstRobot = null;
        String secondRobot = null;

        ArrayList<CreateRobot> battleList1 = new ArrayList<CreateRobot>();
        ArrayList<CreateRobot> battleList2 = new ArrayList<CreateRobot>();
        ArrayList<CreateRobot> battleList3 = new ArrayList<CreateRobot>();
        ArrayList<CreateRobot> battleList4 = new ArrayList<CreateRobot>();
        ArrayList<CreateRobot> battleList5 = new ArrayList<CreateRobot>();
        ArrayList<CreateRobot> randoms = new ArrayList<CreateRobot>();
        ArrayList<CreateRobot> FinalList = new ArrayList<CreateRobot>();

        randoms.add(random1);
        randoms.add(random2);
        randoms.add(random3);
        randoms.add(random4);
        randoms.add(random5);


        while (currentEvolution <= numberOfEvolutions) {
            tm.clear();
            battleList1.clear();
            battleList2.clear();
            battleList3.clear();
            battleList4.clear();
            battleList5.clear();

            battleList1 = AddStrongBotsToList(battleList1, strongBot1, strongBot2, strongBot3, strongBot4, strongBot5);
            battleList2 = AddStrongBotsToList(battleList2, strongBot1, strongBot2, strongBot3, strongBot4, strongBot5);
            battleList3 = AddStrongBotsToList(battleList3, strongBot1, strongBot2, strongBot3, strongBot4, strongBot5);
            battleList4 = AddStrongBotsToList(battleList4, strongBot1, strongBot2, strongBot3, strongBot4, strongBot5);
            battleList5 = AddStrongBotsToList(battleList5, strongBot1, strongBot2, strongBot3, strongBot4, strongBot5);


            for(CreateRobot currentRobot : randoms) {
                if (currentRobot.getTankName() == "random1"){
                    battleList1.add(currentRobot);
                }
                if (currentRobot.getTankName() == "random2"){
                    battleList2.add(currentRobot);
                }
                if (currentRobot.getTankName() == "random3"){
                    battleList3.add(currentRobot);
                }
                if (currentRobot.getTankName() == "random4"){
                    battleList4.add(currentRobot);
                }
                if (currentRobot.getTankName() == "random5"){
                    battleList5.add(currentRobot);
                }
            }


            for (CreateRobot currentRobot : battleList1) {
                // currentRobot.createTankResult();
                currentRobot.createRobotFile();
            }
            random2.createRobotFile();
            random3.createRobotFile();
            random4.createRobotFile();
            random5.createRobotFile();

            score1 = runRobocode(battleList1, false);
            score2 = runRobocode(battleList2, false);
            score3 = runRobocode(battleList3, false);
            score4 = runRobocode(battleList4, false);
            score5 = runRobocode(battleList5, false);

            System.out.println("Score1: " + score1);
            System.out.println("Score2: " + score2);
            System.out.println("Score3: " + score3);
            System.out.println("Score4: " + score4);
            System.out.println("Score5: " + score5);

			tm.put(score1, "random1");
			tm.put(score2, "random2");
			tm.put(score3, "random3");
			tm.put(score4, "random4");
			tm.put(score5, "random5");

			Set set = tm.entrySet();

			Iterator i = set.iterator();


			while (i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();
                System.out.println(me.getKey());
                if (!(i.hasNext())) {
                    firstRobot = me.getValue().toString();
                    System.out.println("Best: " + firstRobot);
                    winner = CreateWinnerBot(firstRobot, "best", random1, random2, random3, random4, random5);
                }else{
                    secondRobot = me.getValue().toString();
                }
			}
            System.out.println("Second: " + secondRobot);
            secondBest = CreateWinnerBot(secondRobot, "secondBest", random1, random2, random3, random4, random5);


            /*for (CreateRobot currentRobot : battleList1) {
                if (currentRobot.getTankName().equals(battleResults.get(0))) {
                    winner = new CreateRobot(currentRobot, "best");
                }
                if (currentRobot.getTankName().equals(battleResults.get(1))) {
                    secondBest = new CreateRobot(currentRobot, "secondBest");
                }
            }*/

            for (CreateRobot currentRobot : randoms) {
                int robotIndex = randoms.indexOf(currentRobot);
                currentRobot = new CreateRobot(winner, currentRobot.getTankName());
                double evolutionOperation = randomN.nextDouble();
                if (evolutionOperation <= mutateProb) {// 0.2
                    currentRobot.MutateRobot(mutateFuncProb, mutateTermProb);
                } else if (evolutionOperation <= crossoverProb) {// 0.7
                    currentRobot.crossOver(crossoverFuncProb, crossoverTermProb, secondBest);
                }
                randoms.set(robotIndex, currentRobot);
            }

            System.out.println("Generation " + currentEvolution);
            currentEvolution++;
        }

        Final = new CreateRobot(winner, "Result");
        Final.createRobotFile();

        FinalList = AddStrongBotsToList(FinalList, strongBot1, strongBot2, strongBot3, strongBot4, strongBot5);
        FinalList.add(Final);

        runRobocode(FinalList, true);
    }

    public static int runRobocode(ArrayList<CreateRobot> battleList, boolean Visible) throws IOException {

        String best = "";
        String second = "";
        int i = 0;
        String finalListOfTanks = "";
        //ArrayList<String> navratFitness = new ArrayList<String>();
        for (CreateRobot currentRobot : battleList) {
            Compile(currentRobot.getTankName());
            finalListOfTanks += "sample." + currentRobot.getTankName() + ",";
        }

        // Battle listener used for receiving battle events
        BattleObserver battleListener = new BattleObserver();

        // Create the RobocodeEngine
        RobocodeEngine engine = new RobocodeEngine(); // Run from current
        // working directory
        // Add battle listener to our RobocodeEngine
        engine.addBattleListener(battleListener);

        // Show the battles
        engine.setVisible(Visible);

        // Setup the battle specification

        int numberOfRounds = 5;
        BattlefieldSpecification battlefield = new BattlefieldSpecification(800, 600); // 800x600
        // RobotSpecification[] selectedRobots =
        // engine.getLocalRepository("sample.Corners, sample.MujRobot");
        RobotSpecification[] selectedRobots = engine.getLocalRepository(finalListOfTanks);

        BattleSpecification battleSpec = new BattleSpecification(numberOfRounds, battlefield, selectedRobots);
        // Run our specified battle and let it run till it's over
        engine.runBattle(battleSpec, true/* wait till the battle is over */);


        for (BattleResults result : battleListener.getResults()) {
            if (result.getTeamLeaderName().contains("random")) {
                System.out.println(result.getTeamLeaderName() + " - " + result.getScore());
                engine.close();
                return result.getScore();
            }
        }

		return -1;

		/*for (BattleResults result : battleListener.getResults()) {
			if (i == 0) {
				String vysledek = result.getTeamLeaderName();
				String[] casti = vysledek.split("\\.");
				best = casti[1];
				navratFitness.add(best);
			}
			if (i == 1) {
				String vysledek = result.getTeamLeaderName();
				String[] casti = vysledek.split("\\.");
				second = casti[1];
				navratFitness.add(second);
			}
			i++;
		}*/
        // Cleanup our RobocodeEngine
        //engine.close();

        // Make sure that the Java VM is shut down properly
        // System.exit(0);
        //return navratFitness;
    }

    private static ArrayList<CreateRobot> AddStrongBotsToList(ArrayList<CreateRobot> battleList, CreateRobot strongBot1,
                                                              CreateRobot strongBot2, CreateRobot strongBot3,
                                                              CreateRobot strongBot4, CreateRobot strongBot5) {
        battleList.add(strongBot1);
        battleList.add(strongBot2);
        battleList.add(strongBot3);
        battleList.add(strongBot4);
        battleList.add(strongBot5);
        return battleList;
    }

    private static CreateRobot CreateWinnerBot(String robotName, String robotType, CreateRobot random1,
                                        CreateRobot random2, CreateRobot random3,
                                        CreateRobot random4, CreateRobot random5){
        CreateRobot winner = null;
        switch (robotName) {
            case "random1":
                winner = new CreateRobot(random1, robotType);
                break;
            case "random2":
                winner = new CreateRobot(random2, robotType);
                break;
            case "random3":
                winner = new CreateRobot(random3, robotType);
                break;
            case "random4":
                winner = new CreateRobot(random4, robotType);
                break;
            case "random5":
                winner = new CreateRobot(random5, robotType);
                break;
        }
        return winner;
    }

    private static void Compile(String mujRobot) throws IOException {
        String src = "src/sample/" + mujRobot + ".java";
        String dst = "robots/sample/" + mujRobot + ".java";
        // compile our created robot and store it to robots/samples
        File source = new File(src);
        File dest = new File(dst);
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, System.out, System.out, dst);
    }
}