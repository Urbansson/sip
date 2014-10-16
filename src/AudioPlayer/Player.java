package AudioPlayer;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player implements Runnable {
	Clip clip =null;
	AudioInputStream inputStream = null;
	public Player(String fileName) {
		try {

		     URL defaultSound = getClass().getResource("/AudioPlayer/"+fileName);
		
			System.out.println("defaultSound " + defaultSound);  // check the URL!
			inputStream = AudioSystem.getAudioInputStream(defaultSound);
			clip = AudioSystem.getClip();
			//inputStream = AudioSystem.getAudioInputStream((new File(fileName)));
			//inputStream = AudioSystem.getAudioInputStream(new File(fileName));
			//inputStream = AudioSystem.getAudioInputStream(new File("/AudioPlayer/RingingSound.wav"));

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
			//e.printStackTrace();
			//System.out.println("play sound error:");
		}
	}

	public void stop(){
		clip.stop();
		synchronized(this){
			this.notify();
		}
		
	}

}
