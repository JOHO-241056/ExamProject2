package creature.character;

import creature.Character;
import creature.Monster;
import weapon.Weapon;

public class Hero extends Character {
    public Hero(String name, int hp, Weapon weapon) {
        super(name, hp, weapon);
    }

    public void attack(Monster target) {
        int damage = getWeapon().getDamage();
        System.out.println(getName() + "は" + getWeapon().getName() + getWeapon().attackMessage() + target.getName() + target.getSuffix() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }
}
