import creature.*;
import creature.Character;
import creature.character.Hero;
import creature.character.SuperHero;
import creature.character.Thief;
import creature.character.Wizard;
import creature.monster.Goblin;
import creature.monster.Matango;
import creature.monster.Slime;
import weapon.Dagger;
import weapon.Sword;
import weapon.Wand;

import java.util.ArrayList;

public class GameMaster {
    public static void main(String[] args) {
        Dagger dagger = new Dagger();
        Sword sword = new Sword();
        Wand wand = new Wand();
        Hero h = new Hero("勇者", 100, sword);
        Wizard w = new Wizard("魔法使い", 60, 20, wand);
        Thief t = new Thief("盗賊", 70, dagger);
        ArrayList<creature.Character> party =  new ArrayList<>();
        party.add(h);
        party.add(w);
        party.add(t);


        ArrayList<Monster> monsters = new ArrayList<>();
        char suffixMatango = 'A';
        char suffixGoblin = 'A';
        char suffixSlime = 'A';
        for(int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 3);
            switch(x) {
                case 0:
                    monsters.add(new Matango(suffixMatango, 45));
                    suffixMatango++;
                    break;
                case 1:
                    monsters.add(new Goblin(suffixGoblin, 50));
                    suffixGoblin++;
                    break;
                case 2:
                    monsters.add(new Slime(suffixSlime, 40));
                    suffixSlime++;
                    break;
            }
        }


        System.out.println("---味方パーティー---");
        for(Character c : party) {
            c.showStatus();
        }

        System.out.println("---敵グループ---");
        for(Monster m : monsters) {
            m.showStatus();
        }


        while(party.size() > 0 ||  monsters.size() > 0) {
            System.out.println("\n味方のターン");

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
