package life.oleg.pastworld.model;

import life.oleg.pastworld.R;
import life.oleg.pastworld.controller.Controller;

public class CardLose implements ICard {
    private int name;
    private int question;
    private int rightOpinion;
    private int leftOpinion;
    private int image;

        @Override
    public int getName() {
        return name;
    }

    @Override
    public int getImage() {
        return image;
    }

    @Override
    public int getRightOpinion() {
        return rightOpinion;
    }

        @Override
    public int getLeftOpinion() {
        return leftOpinion;
    }

        @Override
    public int getQuestion() {
        return question;
    }

        @Override
    public Status getLeftStatus() {
        return null;
    }

        @Override
    public Status getRightStatus() {
        return null;
    }

    public CardLose(int question) {
        name = R.string.emptyString;
        this.question = question;
        this.leftOpinion = R.string.opinionsLose;
        this.rightOpinion = R.string.opinionsLose;

        image = R.drawable.you_lose;
    }

        @Override
    public void leftOpinion(Status status) {
            Controller.restart(); //todo
    }

        @Override
    public void rightOpinion(Status status) {
            Controller.restart(); //todo
    }

//    @Override
//    public String toString() {
//        return name + " говорит: \'" + question + '\'' +
//                "\n1: \'" + leftOpinion + "\' " + changeLeft +
//                "\n2: \'" + rightOpinion + "\' " + changeRight;
//    }
}
