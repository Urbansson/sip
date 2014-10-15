package AudioPlayer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player implements Runnable {
	Clip clip =null;
	AudioInputStream inputStream = null;

	public Player(String fileName) {
		try {

			clip = AudioSystem.getClip();
			inputStream = AudioSystem.getAudioInputStream(new File(fileName));
			//inputStream = AudioSystem.getAudioInputStream(new File("RingingSound.wav"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY); 
			synchronized(this){
				this.wait();
			}
		} catch (Exception e) {
			System.out.println("play sound error:");
		}
	}

	public void stop(){
		clip.stop();
		synchronized(this){
			this.notify();
		}
		
	}

}
