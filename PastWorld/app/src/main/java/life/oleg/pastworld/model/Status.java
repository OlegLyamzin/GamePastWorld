package life.oleg.pastworld.model;

import life.oleg.pastworld.controller.Controller;


public class Status {
    public enum Lose {ECOLOGY, PEOPLE, MILITARY, MONEY, NOTHING}
    public enum Change {UP, DOWN, NOTCHANGED}

    private int ecology;
    private int people;
    private int military;
    private int money;
    private int daysOnThrone = 0;
    private int daysForYearIncrease = 365;

    void setDaysOnThrone(int daysOnThrone) {
        this.daysOnThrone = daysOnThrone;
        if (daysOnThrone >= daysForYearIncrease) {
            Controller.increaseYear();
            daysForYearIncrease += 365;
        }
    }

    int getDaysOnThrone() {
        return daysOnThrone;
    }

    Status(int ecology, int people, int military, int money, int daysOnThrone) {
        this.ecology = ecology;
        this.people = people;
        this.military = military;
        this.money = money;
        this.daysOnThrone = daysOnThrone;
    }

    Status(int ecology, int people, int military, int money) {
        this.ecology = ecology;
        this.people = people;
        this.military = military;
        this.money = money;
    }

    @Override
    public String toString() {
        return "экология " + ecology +
                ", население " + people +
                ", армия " + military +
                ", деньги " + money;
    }

    void setEcology(int ecology) {
        this.ecology = ecology;
    }

    void setPeople(int people) {
        this.people = people;
    }

    void setMilitary(int military) {
        this.military = military;
    }

    void setMoney(int money) {
        this.money = money;
    }

    public int getEcology() {
        return ecology;
    }

    public int getPeople() {
        return people;
    }

    public int getMilitary() {
        return military;
    }

    public int getMoney() {
        return money;
    }

    Lose isLose() {
        if (ecology <= 0) {
//            Controller.setLose(Lose.ECOLOGY);
            return Lose.ECOLOGY;
        }
        if (people <= 0) {
//            Controller.setLose(Lose.PEOPLE);
            return Lose.PEOPLE;
        }
        if (military <= 0) {
//            Controller.setLose(Lose.MILITARY);
            return Lose.MILITARY;
        }
        if (money <= 0) {
//            Controller.setLose(Lose.MONEY);
            return Lose.MONEY;
        }
        return Lose.NOTHING;
    }
}
