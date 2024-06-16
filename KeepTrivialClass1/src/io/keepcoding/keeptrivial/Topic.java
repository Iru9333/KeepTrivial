package io.keepcoding.keeptrivial;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name; // Nombre del tema
    private List<Question> questions; // Lista de preguntas asociadas a este tema

    // Constructor que inicializa el nombre del tema y una lista vacía de preguntas
    public Topic(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    // Método getter para obtener el nombre del tema
    public String getName() {
        return name;
    }

    // Método getter para obtener la lista de preguntas
    public List<Question> getQuestions() {
        return questions;
    }

    // Método para añadir una pregunta a la lista de preguntas del tema
    public void addQuestion(Question question) {
        questions.add(question);
    }
}
