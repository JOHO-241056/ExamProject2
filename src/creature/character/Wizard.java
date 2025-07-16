package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Wizard extends Character {
    private int mp;

    public Wizard(String name, int hp, int mp, Weapon weapon) {
        super(name, hp, weapon);
        this.mp = mp;
    }

    public void magic(Creature target) {
        int damage = getWeapon().getDamage();
        int mpCost = getWeapon().getCost();

        if(getMp() < mpCost) {
            System.out.println("MPが足りない！");
            return;
        }

        System.out.println(getName() + getWeapon().attackMessage() + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
        setMp(getMp() - mpCost);
    }

    public void attack(Creature target) {
        int damage = 3;
        System.out.println(getName() + "は石を投げた！" + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }

    @Override
    public void showStatus() {
        System.out.println(getName() + ": HP " + getHp() + " MP " + getMp());
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = Math.max(mp, 0);
    }
}
