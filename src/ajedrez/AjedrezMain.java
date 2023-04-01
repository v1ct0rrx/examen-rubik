package ajedrez;

public class AjedrezMain {

	public static void main(String[] args) {
		int tablero[][] = new int[8][8];

		// Se arma tablero de ajedrez colocaldo a las reinas en posicion
		// POSICION DE LAS REYNAS
		tablero[0][0] = 1;
		tablero[1][4] = 1;
		tablero[2][7] = 1;
		tablero[3][5] = 1;
		tablero[4][2] = 1;
		tablero[5][6] = 1;
		tablero[6][1] = 1;
		tablero[7][3] = 1;

		AjedrezMain ajedrezMain = new AjedrezMain();
		ajedrezMain.validarArmado(tablero);

	}

	private void validarArmado(int[][] tablero) {
		System.out.print("Armado correctamente ? .... ");

		boolean armadoValido = true;

		z: for (int fila = 0; fila <= 7; fila++) {
			for (int columna = 0; columna <= 7; columna++) {
				if (tablero[fila][columna] == 1) {
//				validarMovimientoHorizontal
					int sumaFila = this.validarMovimientoHorizontal(tablero[fila]);
//				validarMovimientoVertical
					int sumaColumna = this.validarMovimientoVertical(tablero, columna);
//				validarMovimientoDiagonal izq ==> der
					int sumaDiagonal1 = this.validarMovimientoDiagonal1(tablero, fila, columna);
//				validarMovimientoDiagonal der ==> izq
					int sumaDiagonal2 = this.validarMovimientoDiagonal2(tablero, fila, columna);

					if (sumaFila > 1) {
						armadoValido = false;
						break z;
					}

					if (sumaColumna > 1) {
						armadoValido = false;
						break z;
					}

					if (sumaDiagonal1 > 1) {
						armadoValido = false;
						break z;
					}

					if (sumaDiagonal2 > 1) {
						armadoValido = false;
						break z;
					}
				}
			}
		}

		System.out.print(armadoValido ? " Simon " : "Nel");

	}

	/**
	 * Validamos si una reina puede atacar de en diagonal izq ==> der
	 * @param tablero
	 * @param fila
	 * @param columna
	 * @return
	 */
	private int validarMovimientoDiagonal1(int[][] tablero, int fila, int columna) {

		int filasTablero = 7;
		int columnasTablero = 7;

		int sumatoria = 0;
		int filaInicial = filasTablero - (filasTablero - fila);
		int columnaInicial = columnasTablero - (columnasTablero - columna);

		if (filaInicial != 0) {
			--filaInicial;
		}

		if (columnaInicial != 0) {
			--columnaInicial;
		}

		for (int i = filaInicial; i <= 7; i++) {
			if (columnaInicial > 7) {
				continue;
			}
			sumatoria += tablero[i][columnaInicial++];
		}

		return sumatoria;
	}

	/**
	 * Validamos si una reina puede atacar de en diagonal der ==> izq
	 * @param tablero
	 * @param fila
	 * @param columna
	 * @return
	 */
	private int validarMovimientoDiagonal2(int[][] tablero, int fila, int columna) {

		int filasTablero = 7;
		int columnasTablero = 7;

		int sumatoria = 0;
		int filaInicial = filasTablero - (filasTablero - fila);
		int columnaInicial = (columnasTablero + columna) - columnasTablero;

		if (filaInicial != 0 && columnaInicial != 7) {
			--filaInicial;
		}

		if (columnaInicial != 0 && columnaInicial < 7) {
			++columnaInicial;
		}

		for (int i = filaInicial; i <= 7; i++) {
			if (columnaInicial < 0) {
				continue;
			} else if (columnaInicial > 7) {
				columnaInicial--;
			}
			sumatoria += tablero[i][columnaInicial];
			columnaInicial -= 1;
		}

		return sumatoria;
	}

	/**
	 * Validamos si una reina puede atacar en vertical
	 * @param tablero
	 * @param fila
	 * @param columna
	 * @return
	 */
	private int validarMovimientoVertical(int[][] tablero, int columna) {
		int sumatoria = 0;
		for (int i = 0; i <= 7; i++) {
			sumatoria += tablero[i][columna];
		}
		return sumatoria;
	}

	/**
	 * Validamos si una reina puede atacar de en horizontal
	 * @param tablero
	 * @param fila
	 * @param columna
	 * @return
	 */
	private int validarMovimientoHorizontal(int[] filaCompleta) {
		int sumatoria = 0;
		for (int i : filaCompleta) {
			sumatoria += i;
		}

		return sumatoria;
	}

}
