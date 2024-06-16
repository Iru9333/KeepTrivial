package io.keepcoding.keeptrivial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal para el juego Keep Trivial.
 */
public class MainTrivial {
    private static List<Topic> topics; // Lista para almacenar todos los temas
    private static List<Team> teams;   // Lista para almacenar todos los equipos
    private static List<String> allQuesitos; // Lista para almacenar todos los quesitos posibles

    /**
     * Método principal para iniciar el juego.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        topics = FileUtil.loadQuestions("C:/KEEPCODING/Java/KeepTrivialClass1/questions"); // Cargar preguntas desde archivos
        initializeTeams(); // Inicializar los equipos

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        do {
            for (Team team : teams) {
                playTurn(team, scanner); // Turno para cada equipo
                if (team.hasAllQuesitos()) {
                    exit = true;
                    System.out.println();
                    title("¡Enhorabuena, el equipo " + team.getName() + " ha ganado!");
                    break;
                }
            }
        } while (!exit);

        scanner.close(); // Cerrar el scanner
    }

    /**
     * Inicializa los equipos según la entrada del usuario.
     */
    private static void initializeTeams() {
        teams = new ArrayList<>(); // Inicializar la lista de equipos
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de equipos: ");
        int numTeams = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (int i = 1; i <= numTeams; i++) {
            System.out.print("Nombre del equipo " + i + ": ");
            String teamName = scanner.nextLine();
            Team team = new Team(teamName); // Crear nuevo equipo
            teams.add(team); // Agregar equipo a la lista
        }
    }

    /**
     * Juega un turno para un equipo dado.
     * @param team Equipo al que pertenece el turno.
     * @param scanner Objeto Scanner para la entrada del usuario.
     */
    private static void playTurn(Team team, Scanner scanner) {
        Topic topic = getRandomTopic(team); // Obtener un tema aleatorio para el equipo
        if (topic == null) {
            System.out.println("No hay preguntas disponibles para este tema."); // No hay preguntas disponibles para este tema
            return;
        }

        Question question = getRandomQuestion(topic); // Obtener una pregunta aleatoria del tema

        displayQuestion(question); // Mostrar la pregunta
        String answer = scanner.nextLine(); // Obtener la respuesta del usuario

        if (question.checkAnswer(answer)) {
            team.addQuesito(question.getTopic().getName()); // Agregar el quesito al equipo
            displayScoreboard(); // Mostrar el marcador actualizado
        } else {
            System.out.println("Respuesta incorrecta. La pregunta será marcada como fallada."); // Respuesta incorrecta
        }
    }

    /**
     * Obtiene un tema aleatorio que el equipo aún no ha completado.
     * @param team Equipo para el cual obtener el tema.
     * @return Un tema aleatorio que el equipo no ha completado.
     */
    private static Topic getRandomTopic(Team team) {
        List<Topic> availableTopics = new ArrayList<>();
        for (Topic topic : topics) {
            if (!team.hasQuesito(topic.getName())) {
                availableTopics.add(topic);
            }
        }
        if (availableTopics.isEmpty()) {
            return null; // Devuelve null si el equipo ya ha completado todos los quesitos
        }
        return availableTopics.get(new Random().nextInt(availableTopics.size())); // Devuelve un tema aleatorio
    }

    /**
     * Obtiene una pregunta aleatoria de un tema dado.
     * @param topic Tema del cual obtener la pregunta.
     * @return Una pregunta aleatoria del tema.
     */
    private static Question getRandomQuestion(Topic topic) {
        if (topic == null) {
            return null;
        }
        List<Question> questions = topic.getQuestions();
        return questions.get(new Random().nextInt(questions.size()));
    }

    /**
     * Muestra una pregunta en la consola.
     * @param question La pregunta a mostrar.
     */
    private static void displayQuestion(Question question) {
        if (question != null) {
            System.out.println();
            title("### Pregunta ###");
            System.out.println(question.getQuestion());
            System.out.println("1. " + question.getAnswer1());
            System.out.println("2. " + question.getAnswer2());
            System.out.println("3. " + question.getAnswer3());
            System.out.println("4. " + question.getAnswer4());
            System.out.print("Respuesta: ");
        }
    }

    /**
     * Muestra el marcador actualizado.
     */
    private static void displayScoreboard() {
        System.out.println();
        title("### Tabla de Puntuaciones ###");
        for (Team team : teams) {
            System.out.println(team.getName() + ": " + team.getQuesitos());
        }
    }

    /**
     * Imprime un título rodeado de almohadillas.
     * @param text El texto a mostrar como título.
     */
    private static void title(String text) {
        int length = text.length();
        printHashtagLine(length + 4); // Bordes
        System.out.println("# " + text + " #");
        printHashtagLine(length + 4);
    }

    /**
     * Imprime una línea de almohadillas de una longitud especificada.
     * @param length La longitud de la línea (número de almohadillas).
     */
    private static void printHashtagLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
}
