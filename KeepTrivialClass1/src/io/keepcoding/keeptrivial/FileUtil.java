package io.keepcoding.keeptrivial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * Carga las preguntas desde un directorio dado.
     * 
     * @param directoryPath Ruta del directorio que contiene los archivos de preguntas.
     * @return Lista de temas cargados con sus respectivas preguntas.
     */
    public static List<Topic> loadQuestions(String directoryPath) {
        List<Topic> topics = new ArrayList<>();

        File folder = new File(directoryPath);
        if (!folder.exists()) {
            System.out.println("Error: No se encontró el directorio de preguntas: " + directoryPath);
            return topics;
        }

        File[] filesList = folder.listFiles();
        if (filesList == null || filesList.length == 0) {
            System.out.println("Error: No hay archivos de preguntas en el directorio: " + directoryPath);
            return topics;
        }

        // Iterar sobre los archivos en el directorio
        for (File file : filesList) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                // Obtener el nombre del tema a partir del nombre del archivo
                String topicName = file.getName().substring(0, file.getName().length() - 4);
                
                // Buscar el tema en la lista de temas, si no existe, crear uno nuevo
                Topic topic = findTopic(topics, topicName);
                if (topic == null) {
                    topic = new Topic(topicName);
                    topics.add(topic);
                }

                // Leer las preguntas del archivo
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    List<String> block = new ArrayList<>();
                    while ((line = br.readLine()) != null) {
                        block.add(line);

                        // Cuando se alcanza el tamaño del bloque (6 líneas), se crea una nueva pregunta
                        if (block.size() == 6) {
                            String question = block.get(0);
                            String answer1 = block.get(1);
                            String answer2 = block.get(2);
                            String answer3 = block.get(3);
                            String answer4 = block.get(4);
                            int rightOption = Integer.parseInt(block.get(5));

                            // Crear la pregunta y asignar el tema
                            Question q = new Question(question, answer1, answer2, answer3, answer4, rightOption);
                            q.setTopic(topic); // Asignar el tema a la pregunta
                            topic.addQuestion(q); // Agregar la pregunta al tema
                            block.clear(); // Limpiar el bloque para la siguiente pregunta
                        }
                    }
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        return topics; // Devolver la lista de temas cargados
    }

    /**
     * Busca un tema en la lista de temas dado su nombre.
     * 
     * @param topics    Lista de temas en la que buscar.
     * @param topicName Nombre del tema a buscar.
     * @return El tema encontrado o null si no se encuentra.
     */
    private static Topic findTopic(List<Topic> topics, String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equalsIgnoreCase(topicName)) {
                return topic; // Devolver el tema si se encuentra
            }
        }
        return null; // Devolver null si no se encuentra el tema
    }
}
