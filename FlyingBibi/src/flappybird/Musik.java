package flappybird;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Musik {

	public static synchronized void music(String track) {
		
		final String trackname = track;
		
		new Thread (new Runnable() {
			
			public void run() {
				//while (true) {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File (trackname));
						clip.open(inputStream);
						clip.loop(0);
						//clip.loop(Clip.LOOP_CONTINUOUSLY);
						
						Thread.sleep(clip.getMicrosecondLength()/1000);						
					}
					catch (Exception e){
						e.printStackTrace();
					}
				//}
			}
		
		
	}).start();
}
}