package creature;

public interface Creature {
    boolean isAlive();
    void showStatus();
    String getName();
    int getHp();
    void setHp(int hp);
}
