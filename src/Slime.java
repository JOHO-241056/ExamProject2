public final class Slime extends Monster {
    public Slime(char suffix, int hp) {
        super("スライム", suffix, hp);
    }

    public void attack(Creature target) {
        int damage = 5;
        System.out.println(getName() + getSuffix() + "は体当たり攻撃！" + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }
}
