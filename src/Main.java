import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        CharacterManager manager = new CharacterManager();

        manager.addCharacter(
                new Character("George", CharacterType.WARRIOR, 5));

        manager.addCharacter(
                new Character("Paul", CharacterType.MAGE, 3));

        manager.addCharacter(
                new Character("Lennon", CharacterType.ARCHER, 4));

        manager.addCharacter(
                new Character("Ringo", CharacterType.HEALER, 6));

        manager.addCharacter(
                new Character("Arthur", CharacterType.WARRIOR, 20));

        manager.addCharacter(
                new Character("Robin", CharacterType.ARCHER, 1));

        // Karakter tambahan TP12
        manager.addCharacter(
                new Character("Michael", CharacterType.MAGE, 8));

        manager.addCharacter(
                new Character("Jaafar", CharacterType.HEALER, 10));

        SwingUtilities.invokeLater(() -> {
            new MainWindow(manager);
        });
    }
}