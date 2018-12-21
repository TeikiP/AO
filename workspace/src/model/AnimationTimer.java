package model;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class AnimationTimer extends Timer {

	// Quelques constantes
	private static int STEP = 2; // duree de rafraichissement de l'ecran: 15ms
	private static double MSSTEP = STEP / 1000.0;

	public AnimationTimer(ActionListener actionListener){
		super(STEP, actionListener);

	}

	public static int getSTEP() {
		return STEP;
	}

	public static double getMSSTEP() {
		return MSSTEP;
	}

	
}
