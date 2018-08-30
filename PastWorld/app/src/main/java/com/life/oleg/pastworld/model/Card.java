package com.life.oleg.pastworld.model;

import com.life.oleg.pastworld.R;

public class Card implements ICard {
    private int name;
    private int question;
    private int rightOpinion;
    private int leftOpinion;
    private int image;
//    private String changeLeft;
//    private String changeRight;
    private Status leftStatus;
    private Status rightStatus;

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
        return leftStatus;
    }

        @Override
    public Status getRightStatus() {
        return rightStatus;
    }

    Card(int name, int question, int leftOpinion, int rightOpinion, Status leftStatus, Status rightStatus) {
        this.name = name;
        this.question = question;
        this.leftOpinion = leftOpinion;
        this.rightOpinion = rightOpinion;
        this.leftStatus = leftStatus;
        this.rightStatus = rightStatus;
        image = R.drawable.example_avatar2;
//        changeLeft = "Изменится: " +
//                ((leftStatus.getEcology() == 0) ? "" : " экология ") +
//                ((leftStatus.getPeople() == 0) ? "" : "люди ") +
//                ((leftStatus.getMilitary() == 0) ? "" : "армия ") +
//                ((leftStatus.getMoney() == 0) ? "" : "деньги") + ".";
//        changeRight = "Изменится: " +
//                ((rightStatus.getEcology() == 0) ? "" : "экология ") +
//                ((rightStatus.getPeople() == 0) ? "" : "люди ") +
//                ((rightStatus.getMilitary() == 0) ? "" : "армия ") +
//                ((rightStatus.getMoney() == 0) ? "" : "деньги") + ".";
    }

        @Override
    public void leftOpinion(Status status) {
        status.setMilitary(status.getMilitary() + leftStatus.getMilitary());
        status.setMoney(status.getMoney() + leftStatus.getMoney());
        status.setPeople(status.getPeople() + leftStatus.getPeople());
        status.setEcology(status.getEcology() + leftStatus.getEcology());
    }

        @Override
    public void rightOpinion(Status status) {
        status.setMilitary(status.getMilitary() + rightStatus.getMilitary());
        status.setMoney(status.getMoney() + rightStatus.getMoney());
        status.setPeople(status.getPeople() + rightStatus.getPeople());
        status.setEcology(status.getEcology() + rightStatus.getEcology());
    }

//    @Override
//    public String toString() {
//        return name + " говорит: \'" + question + '\'' +
//                "\n1: \'" + leftOpinion + "\' " + changeLeft +
//                "\n2: \'" + rightOpinion + "\' " + changeRight;
//    }
}
