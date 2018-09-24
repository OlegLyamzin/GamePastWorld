package life.oleg.pastworld.model;

public interface ICard{
    void leftOpinion(Status status);
    void rightOpinion(Status status);
    Status getLeftStatus();
    Status getRightStatus();
    int getQuestion();
    int getLeftOpinion();
    int getRightOpinion();
    int getName();
    int getImage();
}
