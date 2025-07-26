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
        ArrayList<creature.Character> party =  new ArrayList<>();
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
        while(!party.isEmpty() && !monsters.isEmpty()) {
            System.out.println("---味方のターン---");
            ListIterator<Character> it = party.listIterator();

            while (it.hasNext()) {
                Character c = it.next();
                System.out.println(c.getName() + "のターン");

                if (c instanceof Hero h) {
                    System.out.println("1:攻撃");
                    System.out.println("2:スーパーヒーローになる");
                    int action = readCommand(2);
                    switch(action) {
                        case 1:
                            h.attack(selectAttackTarget(monsters));
                            System.out.println();
                            break;
                        case 2:
                            if (h.getHp() > 30) {
                                SuperHero superHero = new SuperHero(h);
                                it.set(superHero);
                                System.out.println(h.getName() + "はスーパーヒーローに進化した！");
                                System.out.println(h.getName() + "は力を解放する代償として30のダメージを受けた！");
                                System.out.println();
                            } else {
                                System.out.println(h.getName() + "はスーパーヒーローに進化しようとしたが、その代償は大きすぎた…");
                                h.die();
                                it.remove();
                                System.out.println();
                            }
                            break;
                    }


                } else if (c instanceof Wizard w) {
                    System.out.println("1:攻撃");
                    System.out.println("2:魔法攻撃");
                    int action = readCommand(2);
                    switch(action) {
                        case 1:
                            w.attack(selectAttackTarget(monsters));
                            System.out.println();
                            break;
                        case 2:
                            w.magic(selectAttackTarget(monsters));
                            System.out.println();
                            break;
                    }

                } else if (c instanceof Thief t) {
                    System.out.println("1:攻撃");
                    System.out.println("2:守り");
                    int action = readCommand(2);
                    switch(action) {
                        case 1:
                            t.attack(selectAttackTarget(monsters));
                            System.out.println();
                            break;
                        case 2:
                            t.guard();
                            System.out.println();
                            break;
                    }

                } else if (c instanceof SuperHero sh) {
                    sh.attack(selectAttackTarget(monsters));
                    System.out.println();
                }

                if(monsters.isEmpty()) break;
            }
        }

        //最終ステータス表示
        showAllResults("味方パーティー", party);
        showAllResults("敵グループ", monsters);
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
