import java.util.Scanner;

/**
 * Clase Main que ejecuta el programa interactivo para manejar un árbol AVL
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AVLTree arbol = new AVLTree();
    
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("=== PROGRAMA DE ÁRBOLES AVL en JAVA ===");
        System.out.println("====================================");
        
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ", -1);

            switch (opcion) {
                case 1:
                    insertarValoresUnoPorUno();
                    break;
                case 2:
                    eliminarValor();
                    break;
                case 3:
                    buscarValor();
                    break;
                case 4:
                    System.out.println("\nÁrbol AVL actual:");
                    arbol.printTree();
                    break;
                case 5:
                    System.out.println("\nÁrbol AVL actual (visualización gráfica):");
                    arbol.displayTree();
                    break;
                case 6:
                    cargarEjemploPredefinido();
                    break;
                case 7:
                    exportarArbol();
                    break;
                case 0:
                    salir = true;
                    System.out.println("Finalizando programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menú de opciones
     */
    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ ===");
        System.out.println("1. Insertar valor (uno por uno) - Auto-guarda gráfico");
        System.out.println("2. Eliminar valor - Auto-guarda gráfico");
        System.out.println("3. Buscar valor");
        System.out.println("4. Mostrar árbol (formato jerárquico)");
        System.out.println("5. Mostrar árbol (formato gráfico)");
        System.out.println("6. Cargar ejemplo predefinido");
        System.out.println("7. Exportar árbol manualmente");
        System.out.println("0. Salir");
    }
    
    /**
     * Lee un entero desde la entrada estándar con manejo de errores
     * @param mensaje El mensaje para mostrar al usuario
     * @param valorError Valor a retornar en caso de error
     * @return El entero leído o valorError si hay un error
     */
    private static int leerEntero(String mensaje, int valorError) {
        System.out.print(mensaje);
        try {
            String entrada = scanner.nextLine().trim();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return valorError;
        }
    }
    
    /**
     * Inserta valores uno por uno en el árbol, mostrando la visualización después de cada inserción
     */
    private static void insertarValoresUnoPorUno() {
        System.out.println("\n=== INSERTAR VALORES (UNO POR UNO) ===");
        System.out.println("Ingrese un número a la vez (o 'q' para volver al menú)");
        System.out.println("El árbol se guardará automáticamente en 'prueba_de_arbol.dot' y se generará 'prueba_de_arbol.png'");
        
        while (true) {
            System.out.print("Ingrese un valor: ");
            String entrada = scanner.nextLine().trim();
            
            if (entrada.equalsIgnoreCase("q")) {
                break;
            }
            
            try {
                int valor = Integer.parseInt(entrada);
                arbol.insertar(valor);
                
                System.out.println("\nÁrbol actualizado. Archivos regenerados automáticamente.");
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número entero válido.");
            }
        }
    }
    
    /**
     * Elimina un valor del árbol
     */
    private static void eliminarValor() {
        System.out.println("\n=== ELIMINAR VALOR ===");
        int valor = leerEntero("Ingrese el valor a eliminar: ", Integer.MIN_VALUE);
        
        if (valor != Integer.MIN_VALUE) {
            arbol.eliminar(valor);
            System.out.println("\nÁrbol actualizado. Archivos regenerados automáticamente.");
        } else {
            System.out.println("Error: Valor no válido.");
        }
    }
    
    /**
     * Busca un valor en el árbol
     */
    private static void buscarValor() {
        System.out.println("\n=== BUSCAR VALOR ===");
        int valor = leerEntero("Ingrese el valor a buscar: ", Integer.MIN_VALUE);
        
        if (valor != Integer.MIN_VALUE) {
            boolean encontrado = arbol.buscar(valor);
            if (encontrado) {
                System.out.println("El valor " + valor + " se encuentra en el árbol.");
            } else {
                System.out.println("El valor " + valor + " NO se encuentra en el árbol.");
            }
        } else {
            System.out.println("Error: Valor no válido.");
        }
    }
    
    /**
     * Carga un ejemplo predefinido para demostrar las rotaciones AVL
     */
    private static void cargarEjemploPredefinido() {
        System.out.println("\n=== CARGAR EJEMPLO PREDEFINIDO ===");
        System.out.println("Seleccione un ejemplo:");
        System.out.println("1. Árbol balanceado simple");
        System.out.println("2. Rotación simple a la derecha (LL)");
        System.out.println("3. Rotación simple a la izquierda (RR)");
        System.out.println("4. Rotación doble izquierda-derecha (LR)");
        System.out.println("5. Rotación doble derecha-izquierda (RL)");
        
        int opcion = leerEntero("Seleccione una opción: ", 0);
        
        // Crear un nuevo árbol para el ejemplo
        arbol = new AVLTree();
        int[] valores = null;
        
        switch (opcion) {
            case 1:
                System.out.println("Cargando árbol balanceado simple...");
                valores = new int[]{50, 30, 70, 20, 40, 60, 80};
                break;
            case 2:
                System.out.println("Cargando ejemplo para rotación simple a la derecha (LL)...");
                valores = new int[]{50, 30, 20, 10};
                break;
            case 3:
                System.out.println("Cargando ejemplo para rotación simple a la izquierda (RR)...");
                valores = new int[]{10, 20, 30, 40};
                break;
            case 4:
                System.out.println("Cargando ejemplo para rotación doble izquierda-derecha (LR)...");
                valores = new int[]{50, 20, 30};
                break;
            case 5:
                System.out.println("Cargando ejemplo para rotación doble derecha-izquierda (RL)...");
                valores = new int[]{20, 40, 30};
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        
        // Insertar todos los valores del ejemplo seleccionado
        for (int valor : valores) {
            arbol.insertar(valor); // Esto generará automáticamente el DOT y la imagen
        }
        
        System.out.println("\nÁrbol AVL resultante:");
        arbol.displayTree();
        System.out.println("Se ha generado automáticamente 'prueba_de_arbol.dot' y 'prueba_de_arbol.png'");
    }
    
    /**
     * Exporta el árbol actual a un archivo (para exportación manual)
     */
    private static void exportarArbol() {
        System.out.println("\n=== EXPORTAR ÁRBOL (MANUAL) ===");
        System.out.println("Seleccione el formato de exportación:");
        System.out.println("1. Texto plano");
        System.out.println("2. DOT (para visualizar con Graphviz)");
        
        int opcion = leerEntero("Seleccione una opción: ", 0);
        
        if (opcion != 1 && opcion != 2) {
            System.out.println("Opción no válida.");
            return;
        }
        
        System.out.print("Ingrese el nombre del archivo (sin extensión): ");
        String nombreBase = scanner.nextLine().trim();
        
        if (nombreBase.isEmpty()) {
            nombreBase = "arbol_avl_manual";
        }
        
        boolean resultado = false;
        String nombreArchivo = "";
        
        switch (opcion) {
            case 1:
                nombreArchivo = nombreBase + ".txt";
                resultado = AVLTreeExporter.exportarATexto(arbol, nombreArchivo);
                break;
            case 2:
                nombreArchivo = nombreBase + ".dot";
                resultado = AVLTreeExporter.exportarADOT(arbol, nombreArchivo);
                break;
        }
        
        if (resultado) {
            System.out.println("Árbol exportado exitosamente a: " + nombreArchivo);
            if (opcion == 2) {
                System.out.println("Para visualizar el archivo DOT, use Graphviz:");
                System.out.println("  dot -Tpng " + nombreArchivo + " -o " + nombreBase + ".png");
            }
        } else {
            System.out.println("Error al exportar el árbol.");
        }
    }
}