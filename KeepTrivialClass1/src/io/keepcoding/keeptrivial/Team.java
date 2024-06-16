package io.keepcoding.keeptrivial;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private String name; // Nombre del equipo
    private Set<String> quesitos; // Conjunto de quesitos (trozos de pastel) recolectados por el equipo

    // Constructor que inicializa el nombre del equipo y un conjunto vacío de quesitos
    public Team(String name) {
        this.name = name;
        this.quesitos = new HashSet<>();
    }

    // Método getter para obtener el nombre del equipo
    public String getName() {
        return name;
    }

    // Método para verificar si el equipo tiene un quesito específico
    public boolean hasQuesito(String quesito) {
        return quesitos.contains(quesito);
    }

    // Método para añadir un quesito a la colección del equipo
    public void addQuesito(String quesito) {
        quesitos.add(quesito);
    }

    // Método para verificar si el equipo ha recolectado todos los quesitos
    public boolean hasAllQuesitos() {
        return quesitos.size() == 5;
    }

    // Método getter para obtener el conjunto de quesitos
    public Set<String> getQuesitos() {
        return quesitos;
    }
}
