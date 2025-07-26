package creature;

import weapon.Weapon;

public abstract class Character implements Creature {
    private String name;
    private int hp;
    private Weapon weapon;

    public Character(String name, int hp, Weapon weapon) {
        if(hp < 0) {
            throw new IllegalArgumentException("初期設定に誤りがあるため、キャラクターを作成できませんでした");
        }
        this.name = name;
        this.hp = hp;
        this.weapon = weapon;
    }

    public final boolean isAlive() {
        return getHp() > 0;
    }

    public void die() {
        System.out.println(getName() + "は死んでしまった！");
    }

    public abstract void attack(Monster target);

    public void showStatus() {
        System.out.println(getName() + ": HP " + getHp());
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
