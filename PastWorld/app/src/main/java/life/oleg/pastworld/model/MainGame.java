package life.oleg.pastworld.model;

import life.oleg.pastworld.R;
import life.oleg.pastworld.controller.Controller;

import java.util.ArrayList;
import java.util.Random;

public class MainGame implements Runnable {

    public enum Choose {LEFT, RIGHT}
    private Status status = new Status(50, 50, 50, 50, 0); //todo year from file
    private volatile boolean isWait = true;
    private volatile Choose choose;
    private volatile Status.Lose lose = Status.Lose.NOTHING;
    private Initializer initializer;
    private ArrayList<ICard> cards = initArr();
    private Player player = new Player("Name", 2085, 5, 10, true, 1, 0);

    public void increaseYearPlayer() {
        player.addYear();
    }

    public void setLose(Status.Lose lose) {
        this.lose = lose;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void resetStatus() {
        status.setEcology(50);
        status.setPeople(50);
        status.setMilitary(50);
        status.setMoney(50);
        status.setDaysOnThrone(0);
//        Controller.setYear(status.getYear(), status.getDaysOnThrone());
//        Controller.setStatus(status.getEcology(),status.getPeople(),status.getMilitary(),status.getMoney());
    }

    public void setWait(boolean wait) {
        isWait = wait;
    }

    public void setChoose(Choose choose) {
        this.choose = choose;
    }

    public ArrayList<ICard> initArr() {
        initializer = new Initializer();
        return initializer.initArrayCards();
    }

    public void addCounselors() {
        player.addCounselors();
        Controller.setCounselorsCounter();
    }

    public int getCounselors(){
        return player.getCounselors();
    }

    @Override
    public void run() {
        Random random = new Random();
        int lastIndexCard = -1;
        while (true) {
            Controller.setStatus(status.getEcology(),status.getPeople(),status.getMilitary(),status.getMoney());
            Controller.setCounselorsCounter();
            while (lose == Status.Lose.NOTHING) {
                Controller.setYear(player.getYear(), status.getDaysOnThrone());
                int indexCard = random.nextInt(cards.size());
                while (indexCard == lastIndexCard) {
                    indexCard = random.nextInt(cards.size());
                }
                lastIndexCard = indexCard;
                Controller.setTask(cards.get(indexCard));
                isWait = true;
                while (isWait) ;
                if (choose == Choose.LEFT) {
                    cards.get(indexCard).leftOpinion(status);
                } else if (choose == Choose.RIGHT) {
                    cards.get(indexCard).rightOpinion(status);
                }
                status.setDaysOnThrone(status.getDaysOnThrone() + 10 + random.nextInt(10));
                lose = status.isLose();
                Controller.setStatus(status.getEcology(),status.getPeople(),status.getMilitary(),status.getMoney());
            }

            CardLose cardLose;
            if(lose == Status.Lose.ECOLOGY){
                cardLose = new CardLose(R.string.loseEcology);
                Controller.setTask(cardLose);
            } else if(lose == Status.Lose.PEOPLE){
                cardLose = new CardLose(R.string.losePeople);
                Controller.setTask(cardLose);
            } else if(lose == Status.Lose.MILITARY){
                cardLose = new CardLose(R.string.loseMilitary);
                Controller.setTask(cardLose);
            } else {
                cardLose = new CardLose(R.string.loseMoney);
               Controller.setTask(cardLose);
            }
            isWait = true;
            while (isWait) ;
            if (choose == Choose.LEFT) {
                cardLose.leftOpinion(status);
            } else if (choose == Choose.RIGHT) {
                cardLose.rightOpinion(status);
            }
            isWait = true;
            while (isWait) ;
        }
    }

    public int counselorActivate() {
        return player.activateCounselor();
    }
}
