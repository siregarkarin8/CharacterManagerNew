package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final int HIGH_LEVEL = 5;

    private CharacterManager manager;
    private JPanel mainPanel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager v0.4");

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(AppTheme.CARD_BORDER);

        refreshContent();

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void refreshContent() {

        mainPanel.removeAll();

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.TITLE_FONT);

        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(10));

        for (Character character : manager.getAllCharacters()) {

            JLabel card = new JLabel(
                    character.getName()
                            + " | "
                            + character.getType()
                            + " | Level "
                            + character.getLevel()
            );

            card.setOpaque(true);

            card.setBackground(
                    AppTheme.colorFor(character.getType())
            );

            card.setBorder(
                    new LineBorder(
                            AppTheme.colorFor(
                                    character.getType()
                            ).darker(),
                            2
                    )
            );

            card.setFont(AppTheme.NORMAL_FONT);

            if (character.getLevel() >= HIGH_LEVEL) {

                card.setForeground(
                        new Color(255, 215, 0)
                );

                card.setFont(
                        AppTheme.LEVEL_FONT
                );
            }

            card.setToolTipText(
                    "HP: "
                            + character.getHitPoints()
                            + " | ATK: "
                            + character.getAttackPower()
            );

            if (character.getLevel() == 1) {
                card.setEnabled(false);
            }

            mainPanel.add(card);
            mainPanel.add(Box.createVerticalStrut(5));
        }

        JLabel averageLabel =
                new JLabel(
                        "Rata-rata Level: "
                                + manager.getAverageLevel()
                );

        averageLabel.setFont(
                AppTheme.LEVEL_FONT
        );

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(averageLabel);

        JButton tambahButton =
                new JButton("Tambah Karakter");

        tambahButton.addActionListener(e -> {

            String nama =
                    JOptionPane.showInputDialog(
                            this,
                            "Masukkan nama karakter:"
                    );

            if (nama == null ||
                    nama.trim().isEmpty()) {
                return;
            }

            String levelText =
                    JOptionPane.showInputDialog(
                            this,
                            "Masukkan level karakter:"
                    );

            if (levelText == null ||
                    levelText.trim().isEmpty()) {
                return;
            }

            int level;

            try {

                level = Integer.parseInt(levelText);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Level harus berupa angka!"
                );

                return;
            }

            CharacterType role =
                    (CharacterType) JOptionPane.showInputDialog(
                            this,
                            "Pilih role:",
                            "Role Character",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            CharacterType.values(),
                            CharacterType.WARRIOR
                    );

            if (role == null) {
                return;
            }

            manager.addCharacter(
                    new Character(
                            nama,
                            role,
                            level
                    )
            );

            refreshContent();

            mainPanel.revalidate();
            mainPanel.repaint();

            pack();

            JOptionPane.showMessageDialog(
                    this,
                    "Karakter berhasil ditambahkan!"
            );
        });

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(tambahButton);
    }
}