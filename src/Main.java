import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.1 ===");

        Character hero = new Character("George", CharacterType.WARRIOR, 5);
        Character wizard = new Character("Paul", CharacterType.MAGE, 3);
        Character scout = new Character("Lennon", CharacterType.ARCHER, 4);

        System.out.println(hero);
        System.out.println(wizard);
        System.out.println(scout);

        System.out.println();
        System.out.println(">> " + hero.getName() + " level up!");

        hero.setLevel(hero.getLevel() + 1);

        System.out.println("After level up: " + hero);

        System.out.println();
        System.out.println(">> Nama karakter: " + hero.getName());
        System.out.println(">> Jenis karakter: " + hero.getType());
        System.out.println(">> HP saat ini: " + hero.getHitPoints());
        System.out.println(">> Mana saat ini: " + hero.getMana());
        System.out.println(">> Alive? " + hero.isAlive());
    }
}