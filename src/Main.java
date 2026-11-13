import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        CharacterManager manager = new CharacterManager();

        Character hero =
                new Character("George",
                        CharacterType.WARRIOR,
                        5);

        Character wizard =
                new Character("Paul",
                        CharacterType.MAGE,
                        3);

        Character duplicate =
                new Character("George",
                        CharacterType.ARCHER,
                        8);

        System.out.println("Tambah George : "
                + manager.addCharacter(hero));

        System.out.println("Tambah Paul : "
                + manager.addCharacter(wizard));

        System.out.println("Tambah George lagi : "
                + manager.addCharacter(duplicate));

        System.out.println();

        System.out.println("Cari Paul:");
        System.out.println(manager.findByName("Paul"));

        System.out.println();

        System.out.println("Total Character:");
        System.out.println(manager.getTotalCharacters());

        System.out.println();

        ArrayList<Character> copy =
                manager.getAllCharacters();

        copy.clear();

        System.out.println("Ukuran copy:");
        System.out.println(copy.size());
        System.out.println("Ukuran data asli:");
    
        System.out.println(manager.getTotalCharacters());
        System.out.println();
        System.out.println("Rata-rata level:");
        System.out.println(manager.getAverageLevel());
        System.out.println();
        System.out.println("Karakter tipe WARRIOR:");

        for (Character c : manager.findByType(CharacterType.WARRIOR)) {
            System.out.println(c);
        }
        System.out.println();
        System.out.println("JUMLAH WARRIOR:");
        System.out.println(
            manager.countByType(CharacterType.WARRIOR)
        );
    }
}
