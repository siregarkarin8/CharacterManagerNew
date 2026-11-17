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
    private JPanel statsPanel;

    private JLabel statusLabel;
    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel highestLabel;
    private JLabel roleLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager v0.6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 450));

        rootPanel = new JPanel(new BorderLayout(10, 10));
        rootPanel.setBorder(AppTheme.CARD_BORDER);

        setContentPane(rootPanel);

        createHeader();

        rootPanel.add(
                new CharacterFormPanel(
                        manager,
                        this::refreshList
                ),
                BorderLayout.WEST
        );

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

        headerPanel.add(title, BorderLayout.WEST);

        statsPanel = new JPanel(new GridLayout(2, 2, 10, 5));

        totalLabel = new JLabel();
        averageLabel = new JLabel();
        highestLabel = new JLabel();
        roleLabel = new JLabel();

        statsPanel.add(totalLabel);
        statsPanel.add(averageLabel);
        statsPanel.add(highestLabel);
        statsPanel.add(roleLabel);

        headerPanel.add(statsPanel, BorderLayout.SOUTH);

        rootPanel.add(headerPanel, BorderLayout.NORTH);

        updateStatistics();
    }

    // CHARACTER LIST
    private void createCharacterList() {

        cardsPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        refreshList();

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(cardsPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapperPanel);
        scroll.setPreferredSize(new Dimension(520, 300));

        rootPanel.add(scroll, BorderLayout.CENTER);
    }

    // REFRESH LIST
    private void refreshList() {

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
                    AppTheme.colorFor(character.getType())
            );

            card.setBorder(
                    AppTheme.borderFor(character.getType())
            );

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

        updateStatistics();

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    // UPDATE STATISTICS
    private void updateStatistics() {

        totalLabel.setText(
                "Total Karakter: " + manager.getTotalCharacters()
        );

        averageLabel.setText(
                String.format(
                        "Rata-rata Level: %.1f",
                        manager.getAverageLevel()
                )
        );

        highestLabel.setText(
                "Level Tertinggi: " + getHighestLevel()
        );

        roleLabel.setText(
                "Jumlah Role: " + CharacterType.values().length
        );
    }

    // LEGEND
    private void createLegend() {

        JPanel legendPanel = new JPanel();

        legendPanel.setLayout(
                new BoxLayout(legendPanel, BoxLayout.Y_AXIS)
        );

        legendPanel.setBorder(
                new EmptyBorder(5, 5, 5, 5)
        );

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

        statusLabel = new JLabel(
                "Character Manager siap digunakan"
        );

        JLabel versionLabel = new JLabel("v0.6");

        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(versionLabel, BorderLayout.EAST);

        rootPanel.add(statusPanel, BorderLayout.SOUTH);
    }

    // GET HIGHEST LEVEL
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