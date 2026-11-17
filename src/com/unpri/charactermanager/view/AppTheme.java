package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

public class AppTheme {

    public static final Color WARRIOR_COLOR =
            new Color(220, 80, 80);

    public static final Color MAGE_COLOR =
            new Color(80, 120, 220);

    public static final Color ARCHER_COLOR =
            new Color(255, 140, 0);

    public static final Color HEALER_COLOR =
            new Color(60, 170, 80);

    public static final Color GOLD_COLOR =
            new Color(255, 215, 0);

    public static final Color DEFAULT_COLOR =
            Color.LIGHT_GRAY;

    // ===== SPRINT 6 =====
    public static final Color ERROR =
            Color.decode("#ff7675");

    public static final Color SUCCESS =
            Color.decode("#55efc4");

    public static final Font TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 18);

    public static final Font NORMAL_FONT =
            new Font("SansSerif", Font.PLAIN, 14);

    public static final Font LEVEL_FONT =
            new Font("Monospaced", Font.BOLD, 14);

    public static final Font LEGEND_FONT =
            new Font("SansSerif", Font.PLAIN, 12);

    public static final Border CARD_BORDER =
            BorderFactory.createEmptyBorder(
                    10, 10, 10, 10
            );

    public static Color colorFor(CharacterType type) {

        switch (type) {

            case WARRIOR:
                return WARRIOR_COLOR;

            case MAGE:
                return MAGE_COLOR;

            case ARCHER:
                return ARCHER_COLOR;

            case HEALER:
                return HEALER_COLOR;

            default:
                return DEFAULT_COLOR;
        }
    }

    public static Border borderFor(CharacterType type) {

        return BorderFactory.createLineBorder(
                colorFor(type).darker(),
                2
        );
    }
}