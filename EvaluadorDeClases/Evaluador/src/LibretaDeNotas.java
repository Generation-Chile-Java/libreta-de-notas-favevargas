import java.util.*;

public class LibretaDeNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Aquí guardamos el nombre del estudiante y su lista de notas
        HashMap<String, ArrayList<Double>> calificaciones = new HashMap<>();

        System.out.println("Bienvenido al Evaluador de Clases");

        // Cantidad de alumnos
        int cantidadAlumnos = 0;
        do {
            System.out.print("¿Cuántos alumnos desea ingresar? ");
            cantidadAlumnos = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
        } while (cantidadAlumnos <= 0);

        // Cantidad de notas por alumno
        int cantidadNotas = 0;
        do {
            System.out.print("¿Cuántas notas por alumno? ");
            cantidadNotas = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
        } while (cantidadNotas <= 0);

        // Ingresar alumnos y sus notas
        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Nombre del estudiante #" + (i + 1) + ": ");
            String nombre = scanner.nextLine();

            ArrayList<Double> notas = new ArrayList<>();

            for (int j = 0; j < cantidadNotas; j++) {
                double nota = -1;
                while (nota < 1.0 || nota > 7.0) {
                    System.out.print("Ingrese nota #" + (j + 1) + " (entre 1.0 y 7.0): ");
                    nota = scanner.nextDouble();
                    scanner.nextLine(); // limpiar buffer
                }
                notas.add(nota);
            }

            calificaciones.put(nombre, notas); // guarda al estudiante con sus notas
        }

        // Menú principal
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n====== MENÚ ======");
            System.out.println("1. Mostrar Promedio, Nota Máxima y Mínima por Estudiante");
            System.out.println("2. Verificar si una Nota es Aprobatoria o Reprobatoria");
            System.out.println("3. Comparar una Nota con el Promedio del Curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n--- Promedios por Estudiante ---");
                    for (String nombre : calificaciones.keySet()) {
                        ArrayList<Double> notas = calificaciones.get(nombre);

                        double suma = 0;
                        double max = 0;
                        double min = 7.0;

                        for (double nota : notas) {
                            suma += nota;
                            if (nota > max) max = nota;
                            if (nota < min) min = nota;
                        }

                        double promedio = suma / notas.size();

                        System.out.println("Estudiante: " + nombre);
                        System.out.printf("Promedio: %.2f | Máxima: %.2f | Mínima: %.2f\n", promedio, max, min);
                        System.out.println("-------------------------");
                    }
                }
                case 2 -> {
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombre = scanner.nextLine();

                    if (!calificaciones.containsKey(nombre)) {
                        System.out.println("Estudiante no encontrado.");
                        break;
                    }

                    System.out.print("Ingrese la nota a evaluar: ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine();

                    if (nota >= 4.0) {
                        System.out.println(" Aprueba ");
                    } else {
                        System.out.println(" No aprueba ");
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombre = scanner.nextLine();

                    if (!calificaciones.containsKey(nombre)) {
                        System.out.println("Estudiante no encontrado.");
                        break;
                    }

                    System.out.print("Ingrese la nota a comparar: ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine();

                    // calcular el promedio general del curso
                    double sumaTotal = 0;
                    int totalNotas = 0;

                    for (ArrayList<Double> notas : calificaciones.values()) {
                        for (double n : notas) {
                            sumaTotal += n;
                            totalNotas++;
                        }
                    }

                    double promedioCurso = sumaTotal / totalNotas;

                    System.out.printf("Promedio del curso: %.2f\n", promedioCurso);

                    if (nota > promedioCurso) {
                        System.out.println(" La nota está POR ENCIMA del promedio del curso.");
                    } else if (nota < promedioCurso) {
                        System.out.println(" La nota está POR DEBAJO del promedio del curso.");
                    } else {
                        System.out.println(" La nota es IGUAL al promedio del curso.");
                    }
                }
                case 0 -> System.out.println(" Gracias por usar el Evaluador de Clases.");
                default -> System.out.println(" Opción inválida.");
            }
        }

        scanner.close();
    }
}