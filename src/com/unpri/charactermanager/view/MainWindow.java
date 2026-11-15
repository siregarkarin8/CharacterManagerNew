package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final int HIGH_LEVEL = 5;

    private CharacterManager manager;

    private JPanel rootPanel;
    private JPanel cardsPanel;
    private JLabel statusLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager v0.5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 350));

        rootPanel = new JPanel(new BorderLayout(10, 10));
        rootPanel.setBorder(AppTheme.CARD_BORDER);

        setContentPane(rootPanel);

        createHeader();
        createCharacterList();
        createLegend();
        createStatusBar();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // HEADER
    private void createHeader() {

        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.TITLE_FONT);

        JButton tambahButton = new JButton("Tambah Karakter");
        tambahButton.addActionListener(e -> addCharacter());

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(tambahButton, BorderLayout.EAST);

        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 10, 5));

        statsPanel.add(new JLabel(
                "Total Karakter: " + manager.getTotalCharacters()));

        statsPanel.add(new JLabel(
                "Rata-rata Level: " + manager.getAverageLevel()));

        statsPanel.add(new JLabel(
                "Level Tertinggi: " + getHighestLevel()));

        statsPanel.add(new JLabel(
                "Jumlah Role: " + CharacterType.values().length));

        headerPanel.add(statsPanel, BorderLayout.SOUTH);

        rootPanel.add(headerPanel, BorderLayout.NORTH);
    }

    // CHARACTER LIST
    private void createCharacterList() {

        cardsPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        refreshContent();

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(cardsPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapperPanel);
        scroll.setPreferredSize(new Dimension(520, 300));

        rootPanel.add(scroll, BorderLayout.CENTER);
    }

    private void refreshContent() {

        cardsPanel.removeAll();

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
                    AppTheme.colorFor(character.getType()));

            card.setBorder(
                    AppTheme.borderFor(character.getType()));

            card.setFont(AppTheme.NORMAL_FONT);

            if (character.getLevel() >= HIGH_LEVEL) {

                card.setForeground(AppTheme.GOLD_COLOR);
                card.setFont(AppTheme.LEVEL_FONT);
            }

            card.setToolTipText(
                    "HP: " + character.getHitPoints()
                            + " | ATK: " + character.getAttackPower()
            );

            if (character.getLevel() == 1) {
                card.setEnabled(false);
            }

            cardsPanel.add(card);
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    // LEGEND
    private void createLegend() {

        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(
                new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

        legendPanel.setBorder(
                new EmptyBorder(5, 5, 5, 5));

        JLabel legendTitle = new JLabel("Legend Role");
        legendTitle.setFont(AppTheme.TITLE_FONT);

        legendPanel.add(legendTitle);
        legendPanel.add(Box.createVerticalStrut(10));

        for (CharacterType type : CharacterType.values()) {

            JLabel roleLabel = new JLabel(type.toString());

            roleLabel.setOpaque(true);
            roleLabel.setBackground(AppTheme.colorFor(type));
            roleLabel.setBorder(AppTheme.borderFor(type));
            roleLabel.setFont(AppTheme.LEGEND_FONT);

            legendPanel.add(roleLabel);
            legendPanel.add(Box.createVerticalStrut(8));
        }

        legendPanel.add(Box.createVerticalGlue());

        rootPanel.add(legendPanel, BorderLayout.EAST);
    }

    // STATUS BAR
    private void createStatusBar() {

        JPanel statusPanel = new JPanel(new BorderLayout());

        statusLabel = new JLabel("Character Manager siap digunakan");

        JLabel versionLabel = new JLabel("v0.5");

        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(versionLabel, BorderLayout.EAST);

        rootPanel.add(statusPanel, BorderLayout.SOUTH);
    }

    // ADD CHARACTER
    private void addCharacter() {

        String nama = JOptionPane.showInputDialog(
                this,
                "Masukkan nama karakter:"
        );

        if (nama == null || nama.trim().isEmpty()) {
            return;
        }

        String levelText = JOptionPane.showInputDialog(
                this,
                "Masukkan level karakter:"
        );

        if (levelText == null || levelText.trim().isEmpty()) {
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

        CharacterType role = (CharacterType) JOptionPane.showInputDialog(
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
                new Character(nama, role, level)
        );

        refreshContent();

        statusLabel.setText("Karakter berhasil ditambahkan");

        rootPanel.revalidate();
        rootPanel.repaint();
    }

    private int getHighestLevel() {

        int highest = 0;

        for (Character character : manager.getAllCharacters()) {
            if (character.getLevel() > highest) {
                highest = character.getLevel();
            }
        }

        return highest;
    }
}