package com.life.oleg.pastworld.model;

import com.life.oleg.pastworld.R;

import java.util.ArrayList;

public class Initializer /*extends AppCompatActivity */{

    public  ArrayList<ICard> initArrayCards() {
        ArrayList<ICard> arrayList = new ArrayList<>();
        ambassador(arrayList);
        governor(arrayList);
        ministerOfHelth(arrayList);
        vicePresident(arrayList);
        gardener(arrayList);
        housemaid(arrayList);
        general(arrayList);
        scientist(arrayList);
        priest(arrayList);
        scout(arrayList);
        chefCook(arrayList);
        ministerOfEconomy(arrayList);
//        questCard(arrayList);
        return arrayList;
    }

    private  void ministerOfEconomy(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.ministerEconomyName, R.string.ministerEconomyQ1,
                R.string.ministerEconomyLO1, R.string.ministerEconomyRO1,
                new Status(0, 0, 0, -20),
                new Status(0, 10, -10, 0)));
    }

    private  void chefCook(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.chefName, R.string.chefQ1,
                R.string.chefLO1, R.string.chefRO1,
                new Status(0, 0, 10, 0),
                new Status(0, 10, 0, 0)));
        arrayList.add(new Card(R.string.chefName, R.string.chefQ2,
                R.string.chefLO2, R.string.chefRO2,
                new Status(0, 0, -10, 0),
                new Status(0, 0, 0, 0)));
    }

    private  void scout(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.scoutName, R.string.scoutQ1,
                R.string.scoutLO1, R.string.scoutRO1,
                new Status(-10, -20, 0, 0),
                new Status(0, 10, -10, -10)));
        arrayList.add(new Card(R.string.scoutName, R.string.scoutQ2,
                R.string.scoutLO2, R.string.scotRO2,
                new Status(0, -10, 10, -10),
                new Status(0, -10, 10, 0)));
        arrayList.add(new Card(R.string.scoutName, R.string.scoutQ3,
                R.string.scoutLO3, R.string.scoutRO3,
                new Status(0, 0, 0, 0),
                new Status(0, 0, -10, -10)));
        arrayList.add(new Card(R.string.scoutName,R.string.scoutQ4,
                R.string.scoutLO4, R.string.scoutRO4,
                new Status(0, 0, -10, 0),
                new Status(0, 0, 10, -10)));
    }

    private  void priest(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.priestName, R.string.priestQ1,
                R.string.priestLO1, R.string.priestRO1,
                new Status(0, -10, 0, 0),
                new Status(0, 10, 10, -20)));
        arrayList.add(new Card(R.string.priestName, R.string.priestQ2,
                R.string.priestLO2, R.string.priestRO2,
                new Status(0, -20, 0, 10),
                new Status(0, 10, -10, -20)));
        arrayList.add(new Card(R.string.priestName, R.string.priestQ3,
                R.string.priestLO3, R.string.priestRO3,
                new Status(0, -10, 0, 0),
                new Status(0, 20, 0, -10)));
        arrayList.add(new Card(R.string.priestName, R.string.priestQ4,
                R.string.priestLO4, R.string.priestRO4,
                new Status(0, -20, 10, 0),
                new Status(0, 10, -20, 0)));
        arrayList.add(new Card(R.string.priestName, R.string.priestQ5,
                R.string.priestLO5, R.string.priestRO5,
                new Status(0, -20, 10, 0),
                new Status(0, 10, -10, -20)));
        arrayList.add(new Card(R.string.priestName, R.string.priestQ6,
                R.string.priestLO6, R.string.priestRO6,
                new Status(0, -20, 0, 0),
                new Status(0, 0, 0, -10)));
        arrayList.add(new Card(R.string.priestName, R.string.priestQ7,
                R.string.priestLO7, R.string.priestRO7,
                new Status(0, -20, 0, 0),
                new Status(0, 10, -20, 0)));
        arrayList.add(new Card(R.string.priestName, R.string.priestQ8,
                R.string.priestLO8, R.string.priestRO8,
                new Status(0, 10, 0, 0),
                new Status(0, -20, 10, 10)));
    }

    private  void scientist(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.scientistName, R.string.scientistQ1,
                R.string.scientistLO1, R.string.scientistRO1,
                new Status(-10, -10, 0, 10),
                new Status(20, 10, 0, -20)));
        arrayList.add(new Card(R.string.scientistName, R.string.scientistQ2,
                R.string.scientistLO2, R.string.scientistRO2,
                new Status(10, 10, 0, -10),
                new Status(-20, -10, 0, 20)));
        arrayList.add(new Card(R.string.scientistName, R.string.scientistQ3,
                R.string.scientistLO3, R.string.scientistRO3,
                new Status(0, 0, 0, 10),
                new Status(10, 0, 10, -10)));
        arrayList.add(new Card(R.string.scientistName, R.string.scientistQ4,
                R.string.scientistLO4, R.string.scientistRO4,
                new Status(0, 10, 0, -10),
                new Status(-20, -10, 0, 20)));
        arrayList.add(new Card(R.string.scientistName, R.string.scientistQ5,
                R.string.scientistLO5, R.string.scientistRO5,
                new Status(0, 0, 0, 10),
                new Status(20, 0, 0, -10)));
    }

    private  void general(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.generalName, R.string.generalQ1,
                R.string.generalLO1, R.string.generalRO1,
                new Status(0, 0, -10, 10),
                new Status(0, 10, 10, -20)));
        arrayList.add(new Card(R.string.generalName, R.string.generalQ2,
                R.string.generalLO2, R.string.generalRO2,
                new Status(0, -10, 10, 0),
                new Status(0, 10, -10, -10)));
        arrayList.add(new Card(R.string.generalName, R.string.generalQ3,
                R.string.generalLO3, R.string.generalRO3,
                new Status(0, 10, -10, 0),
                new Status(0, -10, -20, 0)));
        arrayList.add(new Card(R.string.generalName, R.string.generalQ4,
                R.string.generalLO4, R.string.generalRO4,
                new Status(-10, -10, -10, 0),
                new Status(10, 10, 10, 0)));
        arrayList.add(new Card(R.string.generalName, R.string.generalQ5,
                R.string.generalLO5, R.string.generalRO5,
                new Status(0, 10, -10, 0),
                new Status(-20, -10, 30, -10)));
        arrayList.add(new Card(R.string.generalName, R.string.generalQ6,
                R.string.generalLO6, R.string.generalRO6,
                new Status(0, -20, -10, 10),
                new Status(0, 10, 10, -10)));
    }

    private  void housemaid(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.houseMaidName, R.string.housemaidQ1,
                R.string.housemaidLO1, R.string.housemaidRO1,
                new Status(-20, 10, 0, 10),
                new Status(10, 10, 0, 0)));
    }

    private  void gardener(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.gardenerName, R.string.gardenerQ1,
                R.string.gardenerLO1, R.string.gardenerRO1
                ,
                new Status(10, 0, 0, 0),
                new Status(-10, 0, 0, -10)));
        arrayList.add(new Card(R.string.gardenerName, R.string.gardenerQ2,
                R.string.gardenerLO2, R.string.gardenerRO2
                ,
                new Status(0, 0, 0, 0),
                new Status(0, 10, 0, -10)));
    }

    private  void vicePresident(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.vicePresidentName, R.string.vicePresidentQ1,
                R.string.vicePresidentLO1, R.string.vicePresidentRO1
                ,
                new Status(10, 10, 0, -10),
                new Status(-10, 0, 0, 10)));
        arrayList.add(new Card(R.string.vicePresidentName, R.string.vicePresidentQ2,
                R.string.vicePresidentLO2, R.string.vicePresidentRO2
                ,
                new Status(10, 0, 0, -10),
                new Status(-10, -10, 0, 20)));
    }

    private  void ministerOfHelth(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.ministerOfHelthName, R.string.ministerOfHelthQ1,
                R.string.ministerOfHelthLO1, R.string.ministerOfHelthRO1
                ,
                new Status(-20, 10, 0, 10),
                new Status(10, 10, 0, 0)));
        arrayList.add(new Card(R.string.ministerOfHelthName, R.string.ministerOfHelthQ2,
                R.string.ministerOfHelthLO2, R.string.ministerOfHelthRO2
                ,
                new Status(-10, -20, 0, 0),
                new Status(20, 10, -10, -10)));
        arrayList.add(new Card(R.string.ministerOfHelthName, R.string.ministerOfHelthQ3,
                R.string.ministerOfHelthLO3, R.string.ministerOfHelthRO3
                ,
                new Status(-10, -10, 0, 10),
                new Status(0, 10, -10, -10)));
        arrayList.add(new Card(R.string.ministerOfHelthName, R.string.ministerOfHelthQ4,
                R.string.ministerOfHelthLO4, R.string.ministerOfHelthRO4
                ,
                new Status(-20, -20, 0, 0),
                new Status(0, 10, -10, -20)));
    }

    private  void governor(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.governorName, R.string.governorQ1,
                R.string.governorLO1, R.string.governorRO1
                ,
                new Status(0, -10, 0, 10),
                new Status(0, 10, 0, -10)));
        arrayList.add(new Card(R.string.governorName, R.string.governorQ2,
                R.string.governorLO2, R.string.governorRO2
                ,
                new Status(-20, -10, 0, 0),
                new Status(10, 0, 0, -20)));
        arrayList.add(new Card(R.string.governorName, R.string.governorQ3,
                R.string.governorLO3, R.string.governorRO3
                ,
                new Status(0, -10, 10, 0),
                new Status(0, 10, -10, 0)));
        arrayList.add(new Card(R.string.governorName, R.string.governorQ4,
                R.string.governorLO4, R.string.governorRO4
                ,
                new Status(0, 10, -10, 0),
                new Status(0, 0, 10, -20)));
        arrayList.add(new Card(R.string.governorName, R.string.governorQ5,
                R.string.governorLO5, R.string.governorRO5
                ,
                new Status(0, -10, 0, 10),
                new Status(0, 0, 0, 0)));
        arrayList.add(new Card(R.string.governorName, R.string.governorQ6,
                R.string.governorLO6, R.string.governorRO6
                ,
                new Status(0, -20, 0, 0),
                new Status(-10, -10, -10, 0)));
        arrayList.add(new Card(R.string.governorName, R.string.governorQ7,
                R.string.governorLO7, R.string.governorRO7
                ,
                new Status(0, -10, 0, 0),
                new Status(0, 10, 10, -10)));
    }

    private void ambassador(ArrayList<ICard> arrayList) {
        arrayList.add(new Card(R.string.ambassadorName, R.string.ambassadorQ1,
                R.string.ambassadorLO1, R.string.ambassadorRO1
                ,
                new Status(0, 0, -10, 20),
                new Status(0, 0, 10, -20)));
        arrayList.add(new Card(R.string.ambassadorName, R.string.ambassadorQ2,
                R.string.ambassadorLO2, R.string.ambassadorRO2
                ,
                new Status(0, -10, -10, 10),
                new Status(0, 10, 0, -20)));
        arrayList.add(new Card(R.string.ambassadorName, R.string.ambassadorQ3,
                R.string.ambassadorLO3, R.string.ambassadorRO3
                ,
                new Status(10, 10, 10, 0),
                new Status(0, 0, 0, 20)));
    }


}
