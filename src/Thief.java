public class Thief extends Character {
    public Thief(String name, int hp) {
        super(name, hp);
    }

    public void attack(Creature target) {
        int damage = 5;
        System.out.println(getName() + "は素早く攻撃した！" + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }
}
