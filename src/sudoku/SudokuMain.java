package sudoku;

public class SudokuMain {
	
	public static void main(String[] args) {
		
		// Se arma tablero de SUDOKU 9x9
		int[][] tablero = {
				{
					5,3,4,6,7,8,9,1,2
				},
				{
					6,7,2,1,9,5,3,4,8
				},
				{
					1,9,8,3,4,2,5,6,7
				},
				{
					8,5,9,7,6,1,4,2,3
				},
				{
					4,2,6,8,5,3,7,9,1
				},
				{
					7,1,3,9,2,4,8,5,6
				},
				{
					9,6,1,5,3,7,2,8,4
				},
				{
					2,8,7,4,1,9,6,3,5
				},
				{
					3,4,5,2,8,6,1,7,9
				}
		};
		
		SudokuMain sudokuMain = new SudokuMain();
		
//		Se valida estructura principal del tablero
		boolean tableroValido = sudokuMain.validarTablero(tablero, 9, 9);
		if(!tableroValido) {
			System.out.println("Datos de entrada no valido...");
			System.exit(0);
		}
		
		System.out.print("Se armo chido? ... ");
		
//		Validar armado del tablero general
		boolean armadoValido = sudokuMain.validarArmado(tablero, 9, 9);
		
//		Validar cuadrantes
		boolean cuadrantesValidos = sudokuMain.validarCuadrantes(tablero);
		
		System.out.print(armadoValido && cuadrantesValidos ? " Simon " : "Nel");
	}

	/**
	 * Metodo para validar cuadrantes
	 * @param tablero
	 * @return
	 */
	private boolean validarCuadrantes(int[][] tablero) {
		boolean validarArmado = true;
		for (int i = 0; i < 3; i++) {
			for (int o = 0; o < 3; o++) {
				
				int[][] cuadrante = obtenerCuadrante(tablero, i, o);
				
				validarArmado = this.validarArmado(cuadrante, 3, 3);
				if(!validarArmado) {
					validarArmado = false;
				}
				
			}
		}
		
		
		return validarArmado;
	}

	/**
	 * Validar armado
	 * @param tablero
	 * @param filaMax
	 * @param columnaMax
	 * @return
	 */
	private boolean validarArmado(int[][] tablero, int filaMax, int columnaMax) {
		
		boolean armadoValido = true;
		
		
		//Validar horizontal y vertical
		z: for (int fila = 0; fila < filaMax; fila++) {
			for (int columna = 0; columna < columnaMax; columna++) {
				boolean horizontalRepetido = this.validarHorizontal(tablero[fila][columna], tablero[fila], columna, columnaMax);
				if(horizontalRepetido) {
					armadoValido = false;
					break z;
				}
				
				boolean verticalRepetido = this.validarVertical(tablero[fila][columna], columna, tablero, fila, filaMax);
				if(verticalRepetido) {
					armadoValido = false;
					break z;
				}
				
			}
		}
		
		
		return armadoValido;
	}
	
	/**
	 * Metodo para obtener cuadrante 3x3
	 * @param tablero
	 * @param x
	 * @param y
	 * @return
	 */
	private int[][] obtenerCuadrante(int[][] tablero,int x, int y) {
		int cuadrante[][] = new int[3][3];
	    for (int i=0; i<3; i++) {
	        for (int j=0; j<3; j++) {
	        	cuadrante[i][j] = tablero[3*x+i][3*y+j];
	        }
	    }
	    
	    return cuadrante;
	}

	/**
	 * Metodo para validar vertical 
	 * @param elementoTablero
	 * @param columna
	 * @param tablero
	 * @param filaAux
	 * @param filaMax
	 * @return
	 */
	private boolean validarVertical(int elementoTablero, int columna, int[][] tablero, int filaAux, int filaMax) {
		boolean verticalRepetido = false;
		z: for (int fila = 0; fila < filaMax; fila++) {
			
			if(fila == filaAux) {
				continue;
			}
			
			if(elementoTablero == tablero[fila][columna]) {
				verticalRepetido = true;
				break z;
			}
			
		}
		
		return verticalRepetido;
	}
	
	/**
	 * Validar horizontal 
	 * @param elementoTablero
	 * @param fila
	 * @param columnaAux
	 * @param columnaMax
	 * @return
	 */

	private boolean validarHorizontal(int elementoTablero, int[] fila, int columnaAux, int columnaMax) {
		boolean horizontalRepetido = false;
		z: for (int columna = 0; columna < columnaMax; columna++) {
			if(fila[columna] == elementoTablero && columnaAux != columna) {
				horizontalRepetido = true;
				break z;
			}
		}
		return horizontalRepetido;
	}
	

	/**
	 * 
	 * @param tablero Parametro de entrada
	 * @return boolean - Bandera para identificar si el tablero tiene la dimensiones correctas
	 */
	private boolean validarTablero(int[][] tablero, int filas, int columnas) {
		boolean resultado = true;
		if(tablero.length == 9) {
			z: for (int[] seccion : tablero) {
				if(seccion.length != 9) {
					resultado = false;
					break z;
				}
			}
		}else {
			resultado = false;
		}
		return resultado;
	}

}
