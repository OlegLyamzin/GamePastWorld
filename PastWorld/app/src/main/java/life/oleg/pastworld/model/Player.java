package life.oleg.pastworld.model;

public class Player {//TODO
    private String name;
    //Год
    private int year;
    //Запасных жизней
    private int lives;
    //Советников
    private int counselors;
    //Реклама
    private boolean ads;
    //Эпоха
    private int era;
    //Опыт
    private int experience;

    public Player(String name, int year, int lives, int counselors, boolean ads, int era, int experience) {
        this.name = name;
        this.year = year;
        this.lives = lives;
        this.counselors = counselors;
        this.ads = ads;
        this.era = era;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getCounselors() {
        return counselors;
    }

    public void setCounselors(int counselors) {
        this.counselors = counselors;
    }

    public boolean isAds() {
        return ads;
    }

    public void setAds(boolean ads) {
        this.ads = ads;
    }

    public int getEra() {
        return era;
    }

    public void setEra(int era) {
        this.era = era;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int activateCounselor(){
        if(counselors > 0){
            counselors--;
        }
        return counselors;
    }

    public void addYear() {
        year++;
    }

    public void addCounselors() {
        counselors += 10;
    }
}
