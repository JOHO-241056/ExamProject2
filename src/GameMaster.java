import creature.*;
import creature.Character;
import creature.character.Hero;
import creature.character.SuperHero;
import creature.character.Thief;
import creature.character.Wizard;
import creature.monster.Goblin;
import creature.monster.Matango;
import creature.monster.Slime;

import java.util.ArrayList;

public class GameMaster {
    public static void main(String[] args) {
        Hero h = new Hero("勇者", 100, "剣");
        Wizard w = new Wizard("魔法使い", 60, 20);
        Thief t = new Thief("盗賊", 70);
        ArrayList<creature.Character> party =  new ArrayList<>();
        party.add(h);
        party.add(w);
        party.add(t);

        Matango mA = new Matango('A', 45);
        Goblin gA = new Goblin('A', 50);
        Slime sA = new Slime('A', 40);
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(mA);
        monsters.add(gA);
        monsters.add(sA);

        System.out.println("---味方パーティー---");
        for(creature.Character c : party) {
            c.showStatus();
        }

        System.out.println("---敵グループ---");
        for(Monster m : monsters) {
            m.showStatus();
        }

        System.out.println();
        System.out.println("味方の総攻撃！");
        for(creature.Character c : party) {
            for(Monster m : monsters) {
                c.attack(m);
            }
        }

        System.out.println();
        System.out.println("敵の総攻撃！");
        for(Monster m : monsters) {
            for(creature.Character c : party) {
                m.attack(c);
            }
        }

        System.out.println();
        System.out.println("ダメージを受けた勇者が突然光りだした！");
        SuperHero sh = new SuperHero(h);
        party.set(party.indexOf(h), sh);
        System.out.println("勇者はスーパーヒーローに進化した！");
        for(Monster m : monsters) {
            sh.attack(m);
        }

        System.out.println();
        System.out.println("---味方パーティ最終ステータス---");
        for(Character c : party) {
            c.showStatus();
            if(c.isAlive()) {
                System.out.println("生存状況: 生存");
            } else {
                System.out.println("生存状況: 死亡");
            }
        }

        System.out.println();
        System.out.println("---敵グループ最終ステータス---");
        for(Monster m : monsters) {
            m.showStatus();
            if(m.isAlive()) {
                System.out.println("生存状況: 生存");
            } else {
                System.out.println("生存状況: 討伐済み");
            }
        }
    }
}
