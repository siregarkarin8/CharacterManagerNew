package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    private ArrayList<Character> characters;
    public CharacterManager() {
        characters = new ArrayList<>();
    }

    public boolean addCharacter(Character character) {

        for (Character c : characters) {

            if (c.equals(character)) {
                return false;
            }
        }

        characters.add(character);
        return true;
    }

    public Character findByName(String name) {

        for (Character c : characters) {

            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }

        return null;
    }

    public boolean removeCharacter(String name) {

        Character target = findByName(name);

        if (target != null) {
            characters.remove(target);
            return true;
        }

        return false;
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    public double getAverageLevel() {

    if (characters.isEmpty()) {
        return 0;
    }

    int totalLevel = 0;

    for (Character c : characters) {
        totalLevel += c.getLevel();
    }

    return (double) totalLevel / characters.size();
    }
    public ArrayList<Character> findByType(CharacterType type) {

        

    ArrayList<Character> result = new ArrayList<>();

    for (Character c : characters) {

        if (c.getType() == type) {
            result.add(c);
        }
    }

    return result;
    }
    /**
    * Menghitung jumlah karakter
    * berdasarkan tipe tertentu.
    */
    public int countByType(CharacterType type) {
    int count = 0;
    for (Character c : characters) {

        if (c.getType() == type) {
            count++;
        }
    }
    return count;
    } 
}