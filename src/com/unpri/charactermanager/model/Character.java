package com.unpri.charactermanager.model;

public class Character {

    // ===== ATRIBUT =====
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // ===== CONSTRUCTOR 1 =====
    public Character(String name, CharacterType type, int level) {

        validateCharacter(name, type, level);

        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        this.mana = 50 + (level * 5);
    }

    // ===== CONSTRUCTOR 2 =====
    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints,
                     int attackPower, int mana) {

        validateCharacter(name, type, level);

        if (hitPoints < 0 || attackPower < 0 || mana < 0) {
            throw new IllegalArgumentException(
                    "Stat karakter tidak boleh negatif"
            );
        }

        this.id = id;
        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = mana;
    }

    // ===== VALIDASI MODEL =====
    private void validateCharacter(
            String name,
            CharacterType type,
            int level) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Nama tidak boleh kosong"
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                    "Role wajib dipilih"
            );
        }

        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                    "Level harus 1-99"
            );
        }

        // Mini Challenge Sprint 6
        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                    "MAGE tidak boleh memiliki level di atas 60"
            );
        }
    }

    // ===== HELPER METHOD =====
    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    // ===== GETTER =====
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    // ===== SETTER =====
    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {

        validateCharacter(name, type, level);

        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public void setMana(int mana) {

        if (mana < 0) {
            throw new IllegalArgumentException(
                    "Mana tidak boleh negatif"
            );
        }

        this.mana = mana;
    }

    // ===== METHOD TAMBAHAN =====
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // ===== toString =====
    @Override
    public String toString() {

        return "Character{id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hp=" + hitPoints +
                ", atk=" + attackPower +
                ", mana=" + mana + '}';
    }

    // ===== EQUALS =====
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null ||
                getClass() != obj.getClass()) {
            return false;
        }

        Character other = (Character) obj;

        return name.equalsIgnoreCase(other.name);
    }
}