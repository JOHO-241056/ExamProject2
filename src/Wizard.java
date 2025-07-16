public class Wizard extends Character {
    private int mp;

    public Wizard(String name, int hp, int mp) {
        super(name, hp);
        this.mp = mp;
    }

    public void attack(Creature target) {
        int damage = 3;
        int mpCost = 1;
        System.out.println(getName() + "は火の玉を放った！" + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
        this.mp -= mpCost;
    }
}
