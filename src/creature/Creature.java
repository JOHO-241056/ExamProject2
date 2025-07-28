package creature;

public interface Creature {
    void die();
    boolean isAlive();
    void showStatus();
    String getName();
    int getHp();
    void setHp(int hp);
}
