package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Thief extends Character {
    private boolean guard = false;

    public Thief(String name, int hp, Weapon weapon) {
        super(name, hp, weapon);
    }

    public void attack(Creature target) {
        int damage = getWeapon().getDamage() * 2;
        System.out.println(getName() + "は"+ getWeapon().getName() + "で素早く2回攻撃した！" + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }

    public void guard() {
        this.guard = true;
    }

    public void setHp(int hp) {
        if(this.guard) {
            this.guard = false;
            System.out.println("しかし、" + getName() + "は攻撃を回避し、ダメージが入らなかった！");
        } else {
            super.setHp(hp);
        }
    }
}
