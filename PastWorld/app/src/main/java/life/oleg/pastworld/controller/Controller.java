package life.oleg.pastworld.controller;

import android.annotation.SuppressLint;

import life.oleg.pastworld.R;
import life.oleg.pastworld.model.Player;
import life.oleg.pastworld.model.Status;
import life.oleg.pastworld.view.MainActivity;
import life.oleg.pastworld.model.ICard;
import life.oleg.pastworld.model.MainGame;

public class Controller {
    static private MainGame mainGame;
    @SuppressLint("StaticFieldLeak")
    static private MainActivity mainActivity;

    public static void setMainActivity(MainActivity mainActivity) {
        Controller.mainActivity = mainActivity;
    }

    public static void showAdFulscreen(){
        if(mainGame.getPlayer().isAds()) {
            mainActivity.showAd();
        }
    }

    public static void setMainGame(MainGame mainGame) {
        Controller.mainGame = mainGame;
    }

    public static void doChoose(MainGame.Choose choose) {
        mainGame.setChoose(choose);
        continueGame();
    }

    public static void continueGame(){
        mainGame.setWait(false);
    }

    public static void restart() {
//        mainActivity.showAd();
        mainActivity.setCounselor(false);
        mainGame.resetStatus();
        mainGame.setLose(Status.Lose.NOTHING);
        exitGame();
//        mainGame.setWait(false);
    }

    public static void exitGame(){
        mainActivity.exitToStartMenu();
    }

    public static void setYear(int year, int daysOnThrone){
        mainActivity.setYearText(year + " " + mainActivity.getString(R.string.year) + "\n"
                + mainActivity.getString(R.string.onThrone) + " "
                + (daysOnThrone / 365) + " " + mainActivity.getString(R.string.shortYear) + " "
                + (daysOnThrone % 365) + " " + mainActivity.getString(R.string.dayShort));
    }

    public static void setTask(ICard card) {
        mainActivity.setOpinionText(card.getLeftOpinion(), card.getRightOpinion());
        mainActivity.setTaskText(card.getQuestion());
        mainActivity.setNameText(card.getName());
        mainActivity.setChangedImages(getChanged(card.getLeftStatus()), getChanged(card.getRightStatus()));//TODO ERRROR
        mainActivity.setImagePerson(card.getImage());
    }

    public static void setStatus(int ecology, int people, int military, int money) {
        mainActivity.setStatus(ecology, people, military, money);
    }

    private static Status.Change[] getChanged(Status status) {
        Status.Change[] changes = new Status.Change[4];
        if(status != null) {
            if (status.getEcology() > 0) {
                changes[0] = Status.Change.UP;
            } else if (status.getEcology() < 0){
                changes[0] = Status.Change.DOWN;
            } else {
                changes[0] = Status.Change.NOTCHANGED;
            }

            if (status.getPeople() > 0) {
                changes[1] = Status.Change.UP;
            } else if (status.getPeople() < 0){
                changes[1] = Status.Change.DOWN;
            } else {
                changes[1] = Status.Change.NOTCHANGED;
            }

            if (status.getMilitary() > 0) {
                changes[2] = Status.Change.UP;
            } else if (status.getMilitary() < 0){
                changes[2] = Status.Change.DOWN;
            } else {
                changes[2] = Status.Change.NOTCHANGED;
            }

            if (status.getMoney() > 0) {
                changes[3] = Status.Change.UP;
            } else if (status.getMoney() < 0){
                changes[3] = Status.Change.DOWN;
            } else {
                changes[3] = Status.Change.NOTCHANGED;
            }

        } else {
            for (int i = 0; i < 4; i++) {
                changes[i] = Status.Change.NOTCHANGED;
            }
        }
        return changes;
    }


    public static void counselorActivate() {
        int counselors = mainGame.counselorActivate();
        if (counselors> 0){
            mainActivity.setCounselor(true);
            mainActivity.counselorChangeImages();
        }
        setCounselorsCounter();
    }

    public static void setCounselorsCounter(){//TODO
        mainActivity.setCounselorCounter(mainGame.getCounselors());
    }

    public static void increaseYear() {
        mainGame.increaseYearPlayer();
    }

    public static void addCounselors() {
        mainGame.addCounselors();
    }

    public static Player getPlayer(){
        return mainGame.getPlayer();
    }

    public static void setPlayer(Player player){
        mainGame.setPlayer(player);
    }

    public static void adsChange() {
        mainGame.getPlayer().setAds(!mainGame.getPlayer().isAds());
    }
}
