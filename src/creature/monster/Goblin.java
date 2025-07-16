package creature.monster;

import creature.Creature;
import creature.Monster;

public class Goblin extends Monster {
    public Goblin(char suffix, int hp) {
        super("ゴブリン", suffix, hp);
    }

    public void attack(Creature target) {
        int damage = 8;
        System.out.println(getName() + getSuffix() + "はナイフで切りつけた！" + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }
}
