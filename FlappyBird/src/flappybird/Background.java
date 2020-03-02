package flappybird;

import java.util.Timer;
import java.util.TimerTask;

public class Background {
	Timer back;
	
	public Background() {
		back = new Timer();
		back.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(FlappyBird.backgroundX1 < 800) {
					FlappyBird.backgroundX1 += 2;
				}
				else {
					FlappyBird.backgroundX1 = -600;
				}
				if(FlappyBird.backgroundX2 < 800) {
					FlappyBird.backgroundX2 += 2;
				}
				else {
					FlappyBird.backgroundX2 = -600;
				}
			}
			
		}, 0, FlappyBird.backgroundspeed);
	}
}
