package org.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CircularList<T> {
    protected final List<T> items;
    private int currentIndex = 0;

    public CircularList() {
        this.items = new ArrayList<>();
    }

    public CircularList(List<T> initialItems) {
        this.items = new ArrayList<>();
        for (T item : initialItems) {
            add(item); // Utilise notre méthode add qui gère les doublons
        }
    }

    /**
     * Ajoute uniquement si l'élément n'existe pas déjà
     * @return true si l'élément a été ajouté
     */
    public boolean add(T item) {
        Objects.requireNonNull(item, "L'élément ne peut pas être null");

        if (contains(item)) {
            return false;
        }

        boolean wasEmpty = items.isEmpty();
        items.add(item);

        if (wasEmpty) {
            currentIndex = 0;
        }
        return true;
    }

    public boolean contains(T item) {
        return items.contains(item);
    }

    // Opérations cycliques
    public T getCurrent() {
        if (items.isEmpty()) return null;
        return items.get(currentIndex);
    }

    public T next() {
        if (items.isEmpty()) return null;
        currentIndex = (currentIndex + 1) % items.size();
        return getCurrent();
    }

    public T previous() {
        if (items.isEmpty()) return null;
        currentIndex = (currentIndex - 1 + items.size()) % items.size();
        return getCurrent();
    }

    public boolean remove(T item) {
        int indexToRemove = items.indexOf(item);
        if (indexToRemove == -1) return false;

        items.remove(indexToRemove);

        if (items.isEmpty()) {
            currentIndex = 0;
        } else if (currentIndex >= indexToRemove) {
            currentIndex = currentIndex % items.size();
        }

        return true;
    }

    public void shuffle() {
        Collections.shuffle(items);
        currentIndex = 0;
    }

    // Utilitaires
    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
        currentIndex = 0;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}