package rubik;

public class RubikMain {

	public static void main(String[] args) {

		
		//Cubo de rubik
		//6 lados con 9 elementos en cada cara
		
		ColoresEnum[][] coloresCubo = {
				{ 
					ColoresEnum.ROJO, 
					ColoresEnum.ROJO, 
					ColoresEnum.ROJO, 
					ColoresEnum.ROJO, 
					ColoresEnum.ROJO,
					ColoresEnum.ROJO, 
					ColoresEnum.ROJO, 
					ColoresEnum.ROJO, 
					ColoresEnum.ROJO 
				},
				{ 
					ColoresEnum.AZUL, 
					ColoresEnum.AZUL, 
					ColoresEnum.AZUL, 
					ColoresEnum.AZUL, 
					ColoresEnum.AZUL,
					ColoresEnum.AZUL, 
					ColoresEnum.AZUL, 
					ColoresEnum.AZUL, 
					ColoresEnum.AZUL 
				},
				{ 
					ColoresEnum.AMARILLO, 
					ColoresEnum.AMARILLO, 
					ColoresEnum.AMARILLO, 
					ColoresEnum.AMARILLO,
					ColoresEnum.AMARILLO, 
					ColoresEnum.AMARILLO, 
					ColoresEnum.AMARILLO, 
					ColoresEnum.AMARILLO,
					ColoresEnum.AMARILLO 
				},
				{ 
					ColoresEnum.VERDE, 
					ColoresEnum.VERDE, 
					ColoresEnum.VERDE, 
					ColoresEnum.VERDE, 
					ColoresEnum.VERDE,
					ColoresEnum.VERDE, 
					ColoresEnum.VERDE, 
					ColoresEnum.VERDE, 
					ColoresEnum.VERDE 
				},
				{ 
					ColoresEnum.BLANCO, 
					ColoresEnum.BLANCO, 
					ColoresEnum.BLANCO, 
					ColoresEnum.BLANCO, 
					ColoresEnum.BLANCO,
					ColoresEnum.BLANCO, 
					ColoresEnum.BLANCO, 
					ColoresEnum.BLANCO, 
					ColoresEnum.BLANCO
				},
				{ 
					ColoresEnum.NARANJA, 
					ColoresEnum.NARANJA, 
					ColoresEnum.NARANJA, 
					ColoresEnum.NARANJA,
					ColoresEnum.NARANJA, 
					ColoresEnum.NARANJA, 
					ColoresEnum.NARANJA, 
					ColoresEnum.NARANJA, 
					ColoresEnum.NARANJA
				}
			};
		
		RubikMain main = new RubikMain();
		
		// Se valida unicamente que el cubo tenga 6 caras con 9 elementos
		boolean entradaValida = main.validarEntrada(coloresCubo);
		
		if(!entradaValida) {
			System.out.println("Datos de entrada no valido...");
			System.exit(0);
		}
		
		
		System.out.print("Se armo chido? ... ");
		// Se valida que el cubo cumpla con las reglas definidas en el ejercicio
		// Donde cada lado debe ser de un unico color
		boolean armandoValido = main.validarArmando(coloresCubo);
		System.out.println(armandoValido ? "Simon ... " : "Nel ... ");
		

	}
	
	/**
	 * 
	 * @param cubo Parametro de entrada
	 * @return boolean - Bandera para indentificar si el cubo tiene la dimensiones correctas
	 */
	private boolean validarEntrada(ColoresEnum[][] cubo) {
		boolean resultado = true;
		if(cubo.length == 6) {
			z: for (ColoresEnum[] ladoCubo : cubo) {
				if(ladoCubo.length != 9) {
					resultado = false;
					break z;
				}
			}
		}else {
			resultado = false;
		}
		return resultado;
	}	

	/**
	 * Validamos que el cubo este armado correctamente en base a las reglas establecidas
	 * 1. Iteramos cada lado y al obtener el primer color de cada lado consumimos el metodo 
	 * validarLado( ... ) para identificar si todas las posiciones contienen el mismo color
	 * @param cubo Parametro de entrada
	 * @return boolean - Bandera para idenfiticar si el cubo esta armado correctamente
	 */
	private boolean validarArmando(ColoresEnum[][] cubo) {
		
		boolean armadoVallido = true;
		
		x: for (int i = 0; i < 6; i++) {
			
			for ( int o = 0; o < 9; o++ ) {
				
				//Primer lado 
				ColoresEnum color = cubo[i][o];
				
				boolean ladoValido = this.validarLado(color, cubo[i]);
				
				if(!ladoValido) {
					armadoVallido = false;
					break x;
				}
			}
			
		}
		
		return armadoVallido;
	}

	/**
	 * Metodo para identificar si un lado del cubo coincide con el color enviado dentro del primer parametro
	 * @param color
	 * @param coloresEnums
	 * @return
	 */
	private boolean validarLado(ColoresEnum color, ColoresEnum[] lado) {
		boolean ladoValido = true;
		z: for (ColoresEnum colorAux : lado) {
			if(!color.equals(colorAux)) {
				ladoValido = false;
				break z;
			}
		}
		return ladoValido;
	}

}
