public class Main {
    public static int bossHealth = 500;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {280, 300, 260, 320};
    public static int[] heroesDamage = {35, 30, 40, 0};
    public static String[] heroesAttackType = {"Sworsdman", "Wizard", "Archer", "Medic"};

    public static void main(String[] args) {
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        changeBossDefence();
        bossHits();
        heroesHit();
        medicHelp();
        printStatistics();
    }

    public static void changeBossDefence() {
        int randomIndex = new Random().nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose defence: " + bossDefenceType);
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (!heroesAttackType[i].equals("Medical")) {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                    if (heroesHealth[i] < 0) {
                        heroesHealth[i] = 0;
                    }
                }
            }
        }
    }

    public static void medicHelp() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesAttackType[i].equals("Medic")) {
                healHero(i);
                break;
            }
        }
    }

    public static void healHero(int i) {
        heroesHealth[i] += 20; // Медик лечит героя на 20 единиц
        System.out.println("Medic heals " + heroesAttackType[i] + " for 20 health points");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesAttackType[i].equals(bossDefenceType)) {
                    bossHealth -= heroesDamage[i] * 0.8;
                    System.out.println(heroesAttackType[i] + " hits boss with critical damage");
                } else {
                    bossHealth = Math.max(bossHealth - heroesDamage[i], 0);
                    System.out.println(heroesAttackType[i] + " hits boss");
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("_______");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("_______");
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    private static class Random {
        public int nextInt(int length) {
            return 0;
        }
    }
}