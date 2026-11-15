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

    public static final Color SUPPORT_COLOR =
            new Color(80, 180, 100);

    public static final Font TITLE_FONT =
            new Font("SansSerif",
                    Font.BOLD,
                    18);

    public static final Font NORMAL_FONT =
            new Font("SansSerif",
                    Font.PLAIN,
                    14);

    public static final Font LEVEL_FONT =
            new Font("Monospaced",
                    Font.BOLD,
                    14);

    public static final Border CARD_BORDER =
            BorderFactory.createEmptyBorder(
                    10, 10, 10, 10);

    public static Color colorFor(CharacterType type) {

    switch (type) {

        case WARRIOR:
            return WARRIOR_COLOR;

        case MAGE:
            return MAGE_COLOR;

        case ARCHER:
            return new Color(255, 140, 0);

        case HEALER:
            return new Color(60, 170, 80);

        default:
            return Color.LIGHT_GRAY;
    }
}
}