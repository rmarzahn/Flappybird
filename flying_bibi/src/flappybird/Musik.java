/**
 * Projekt: My Flappy
 * @author Group1: Miriam Bucken, Biana Eichentopf, Vincent Gottschling, Rebecca Marzahn
 * @version V1 08.03.2020
 */

package flappybird;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Musik {	
	
	/**
	 * Methode music ermöglicht das Einlesen und Abspielen von Musikclips
	 * @param track
	 */
	public static synchronized void music(String track) {
		final String trackname = track;
		//initalisiert einen neuen Thread, um das Abspielen parallel zum Spielen zu ermöglichen
		new Thread (new Runnable() {
			/**
			 * Methode run definiert das Einlesen und Abspielen des Musikclips (sowie die Anzahl des Abspielens) 
			 */
			public void run() {
				//while (true) {
					try {
						//definiert einen clip, welche einen Audioclip enthält
						Clip clip = AudioSystem.getClip();
						//definiert einen inputStream, welcher einen Audioinput als neuen track speichert
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File (trackname));
						//öffnet den inputStream
						clip.open(inputStream);
						//spielt den Clip einmal ab (Loop 0 = keine Wiederholung, Loop 1 = eine Wiederholung also zwei mal abspielen)
						clip.loop(0);
						//clip.loop(Clip.LOOP_CONTINUOUSLY);
						Thread.sleep(clip.getMicrosecondLength()/1000);						
					}
					catch (Exception e){
						System.out.println("Musikclip kann nicht geladen werden!");
						e.printStackTrace();
					}
				//}
			}
		}).start();
	}
}


