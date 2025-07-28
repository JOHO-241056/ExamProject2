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
import java.util.ListIterator;

public class GameMaster {
    public static void main(String[] args) {
        KeyInput keyInput = new KeyInput();

        //味方パーティ生成
        Dagger dagger = new Dagger();
        Sword sword = new Sword();
        Wand wand = new Wand();
        Hero hero = new Hero("勇者", 100, sword);
        Wizard wizard = new Wizard("魔法使い", 60, 20, wand);
        Thief thief = new Thief("盗賊", 70, dagger);
        ArrayList<Character> party =  new ArrayList<>();
        party.add(hero);
        party.add(wizard);
        party.add(thief);

        //敵グループ生成
        ArrayList<Monster> monsters = new ArrayList<>();
        char suffixMatango = 'A';
        char suffixGoblin = 'A';
        char suffixSlime = 'A';
        for(int i = 0; i < 5; i++) {
            int x = randomInt(3);
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

        //状態表示
        showAllStatus("味方パーティー", party);
        showAllStatus("敵グループ", monsters);
        System.out.println();

        //戦闘開始
        battle:while(!party.isEmpty() && !monsters.isEmpty()) {
            System.out.println("---味方のターン---");
            ListIterator<Character> itParty = party.listIterator();

            while (itParty.hasNext()) {
                Character c = itParty.next();
                System.out.println(c.getName() + "のターン");

                switch (c) {
                    case SuperHero sh -> {
                        sh.attack(selectAttackTarget(monsters));
                    }
                    case Hero h -> {
                        System.out.println("1:攻撃");
                        System.out.println("2:スーパーヒーローになる");
                        int action = readCommand(2);
                        switch (action) {
                            case 1:
                                h.attack(selectAttackTarget(monsters));
                                break;
                            case 2:
                                if (h.getHp() > 30) {
                                    SuperHero superHero = new SuperHero(h);
                                    itParty.set(superHero);
                                    System.out.println(h.getName() + "はスーパーヒーローに進化した！");
                                    System.out.println(h.getName() + "は力を解放する代償として30のダメージを受けた！");
                                } else {
                                    System.out.println(h.getName() + "はスーパーヒーローに進化しようとしたが、その代償は大きすぎた…");
                                    h.die();
                                    itParty.remove();
                                }
                                break;
                        }


                    }
                    case Wizard w -> {
                        System.out.println("1:攻撃");
                        System.out.println("2:魔法攻撃");
                        int action = readCommand(2);
                        switch (action) {
                            case 1:
                                w.attack(selectAttackTarget(monsters));
                                break;
                            case 2:
                                w.magic(selectAttackTarget(monsters));
                                break;
                        }

                    }
                    case Thief t -> {
                        System.out.println("1:攻撃");
                        System.out.println("2:守り");
                        int action = readCommand(2);
                        switch (action) {
                            case 1:
                                t.attack(selectAttackTarget(monsters));
                                break;
                            case 2:
                                t.guard();
                                break;
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + c);
                }

                monsters.removeIf(m -> {
                    if (!m.isAlive()) {
                        m.die(); // die()メソッドを実行
                        return true;     // trueを返すとリストから削除される
                    }
                    return false;        // falseなら何もしない
                });
                System.out.println();
                if(monsters.isEmpty()) break battle;
            }

            System.out.println("---敵のターン---");
            for(Monster m : monsters) {
                m.attack(party.get(randomInt(party.size())));
                party.removeIf(c -> {
                    if (!c.isAlive()) {
                        c.die(); // die()メソッドを実行
                        return true;     // trueを返すとリストから削除される
                    }
                    return false;        // falseなら何もしない
                });
                if(party.isEmpty()) break battle;
            }
            System.out.println();

            showAllStatus("味方パーティー", party);
            showAllStatus("敵グループ", monsters);
            System.out.println();
        }

        if(monsters.isEmpty()) {
            System.out.println("敵を全て倒した！" + party.getFirst().getName() + "達は勝利した!");
        } else {
            System.out.println("味方パーティは全滅してしまった…");
        }
    }

    private static void showAllStatus(String title, ArrayList<? extends Creature> group) {
        System.out.println("---" + title + "---");
        for(Creature c : group) {
            c.showStatus();
        }
    }

    private static void showAllResults(String title, ArrayList<? extends Creature> group) {
        System.out.println("---" + title + "最終ステータス---");
        for(Creature c : group) {
            c.showStatus();
            if(c.isAlive()) {
                System.out.println("生存状況: 生存");
            } else {
                System.out.println("生存状況: ひんし");
            }
        }
    }

    private static Monster selectAttackTarget(ArrayList<Monster> targets) {
        System.out.println("誰に攻撃する？");
        for (int i = 0; i < targets.size(); i++) {
            Monster target = targets.get(i);
            System.out.println((i + 1) + ":" + target.getName() + target.getSuffix());
        }
        while(true) {
            int select = readCommand();
            if(select >= 1 && select <= targets.size()) {
                return targets.get(select - 1);
            } else {
                printWrongCommand();
            }
        }
    }

    private static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    private static int readCommand() {
        KeyInput keyInput = new KeyInput();
        return keyInput.readInt("コマンド");
    }

    private static int readCommand(int n) {
        while(true) {
            int select = readCommand();
            if(select >= 1 && select <= n) {
                return select;
            } else {
                printWrongCommand();
            }
        }
    }

    private static void printWrongCommand() {
        System.out.println("コマンドが間違っています。再入力してください。");
    }
}
