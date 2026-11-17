package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;

public class CharacterFormPanel extends JPanel {

    private CharacterManager manager;
    private Runnable onAdded;

    private JTextField nameField;
    private JComboBox<CharacterType> roleCombo;
    private JSpinner levelSpinner;
    private JLabel messageLabel;

    private Border normalBorder;

    public CharacterFormPanel(
            CharacterManager manager,
            Runnable onAdded) {

        this.manager = manager;
        this.onAdded = onAdded;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
                "Tambah Karakter"
        ));

        JPanel formPanel = new JPanel(
                new GridLayout(0, 1, 5, 5)
        );

        nameField = new JTextField(15);

        roleCombo = new JComboBox<>(
                CharacterType.values()
        );

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1)
        );

        normalBorder = nameField.getBorder();

        formPanel.add(new JLabel("Nama Karakter:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Role:"));
        formPanel.add(roleCombo);

        formPanel.add(new JLabel("Level:"));
        formPanel.add(levelSpinner);

        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> saveCharacter());

        messageLabel = new JLabel(" ");

        JPanel bottomPanel = new JPanel(
                new BorderLayout(5, 5)
        );

        bottomPanel.add(messageLabel, BorderLayout.CENTER);
        bottomPanel.add(saveButton, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // ===== VALIDASI NAMA =====
    private String validateName(String name) {

        // 1. Kelengkapan
        if (name == null || name.isEmpty()) {
            return "Nama tidak boleh kosong";
        }

        // 2. Panjang
        if (name.length() < 3 || name.length() > 20) {
            return "Nama harus 3-20 karakter";
        }

        // 3. Format
        if (!name.matches("[a-zA-Z ]+")) {
            return "Nama hanya boleh berisi huruf dan spasi";
        }

        // Aturan tambahan 1: tidak boleh ada spasi ganda
        if (name.contains("  ")) {
            return "Nama tidak boleh memiliki spasi ganda";
        }

        // 4. Keunikan
        if (manager.isNameTaken(name)) {
            return "Nama karakter sudah digunakan";
        }

        return null;
    }

    // ===== SIMPAN KARAKTER =====
    private void saveCharacter() {

        String name = nameField.getText().trim();

        nameField.setBorder(normalBorder);

        // Validasi nama
        String error = validateName(name);

        if (error != null) {
            showError(error);
            nameField.requestFocusInWindow();
            return;
        }

        // Membaca spinner
        int level;

        try {
            levelSpinner.commitEdit();

            level = (Integer) levelSpinner.getValue();

        } catch (ParseException | ClassCastException ex) {

            showError("Level harus berupa angka 1-99");
            levelSpinner.requestFocusInWindow();
            return;
        }

        CharacterType role =
                (CharacterType) roleCombo.getSelectedItem();

        try {

            Character character = new Character(
                    name,
                    role,
                    level
            );

            boolean added = manager.addCharacter(character);

            if (!added) {
                showError("Nama karakter sudah digunakan");
                nameField.requestFocusInWindow();
                return;
            }

            // Berhasil
            messageLabel.setForeground(AppTheme.SUCCESS);
            messageLabel.setText(
                    "Karakter berhasil ditambahkan!"
            );

            nameField.setText("");
            roleCombo.setSelectedIndex(0);
            levelSpinner.setValue(1);

            nameField.setBorder(normalBorder);

            onAdded.run();

        } catch (IllegalArgumentException ex) {

            // Pesan validasi dari Model
            showError(ex.getMessage());
        }
    }

    // ===== PESAN ERROR =====
    private void showError(String message) {

        messageLabel.setForeground(AppTheme.ERROR);
        messageLabel.setText(message);

        nameField.setBorder(
                BorderFactory.createLineBorder(
                        AppTheme.ERROR,
                        2
                )
        );
    }
}