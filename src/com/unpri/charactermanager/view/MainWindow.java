package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(CharacterManager manager) {

        setTitle("Character Manager");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Character character : manager.getAllCharacters()) {

            JLabel label = new JLabel(
                    character.getName()
                    + " | "
                    + character.getType()
                    + " | Level "
                    + character.getLevel()
            );

            panel.add(label);
        }

        JLabel averageLabel =
                new JLabel(
                        "Rata-rata Level: "
                        + manager.getAverageLevel()
                );

        panel.add(new JLabel("----------------"));
        panel.add(averageLabel);

        JButton tambahButton =
        new JButton("Tambah Karakter");

        tambahButton.addActionListener(e -> {

         JOptionPane.showMessageDialog(
         null,
            "Fitur tambah karakter berhasil dipanggil!"
    );

});

panel.add(tambahButton);

        add(panel);

        setVisible(true);
    }
}