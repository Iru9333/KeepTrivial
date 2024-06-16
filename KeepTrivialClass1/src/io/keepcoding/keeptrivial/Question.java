package io.keepcoding.keeptrivial;

public class Question {
    private String question;     // Pregunta
    private String answer1;      // Respuesta 1
    private String answer2;      // Respuesta 2
    private String answer3;      // Respuesta 3
    private String answer4;      // Respuesta 4
    private int rightOption;     // Opción correcta
    private Topic topic;         // Tema al que pertenece la pregunta

    // Constructor para inicializar la pregunta y respuestas, así como la opción correcta
    public Question(String question, String answer1, String answer2, String answer3, String answer4, int rightOption) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightOption = rightOption;
    }

    // Método getter para obtener la pregunta
    public String getQuestion() {
        return question;
    }

    // Métodos getter para obtener las respuestas
    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    // Método getter para obtener la opción correcta
    public int getRightOption() {
        return rightOption;
    }

    // Método getter para obtener el tema al que pertenece la pregunta
    public Topic getTopic() {
        return topic;
    }

    // Método setter para establecer el tema al que pertenece la pregunta
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    // Método para comprobar si la respuesta seleccionada es la correcta
    public boolean checkAnswer(String answer) {
        int chosenOption;
        try {
            chosenOption = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return false;  // Retorna falso si no se puede convertir la respuesta a un número
        }
        return chosenOption == rightOption;  // Retorna verdadero si la opción seleccionada es la correcta
    }
}
