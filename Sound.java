package Reveil;

import java.util.TimerTask;
import javax.sound.sampled.*;


public class Sound extends TimerTask {

   
        @Override
        public void run() {
            try {
                // Chargez le fichier audio (remplacez "votre_son.wav" par le chemin de votre fichier audio)
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                		Sound.class.getResourceAsStream("/reveil.wav"));
                
                // Obtenez un lecteur audio
                Clip clip = AudioSystem.getClip();

                // Ouvrez le flux audio
                clip.open(audioInputStream);

                // Jouez le son
                clip.start();

                // Attendez la fin de la lecture du son
                while (!clip.isRunning())
                    Thread.sleep(10);
                while (clip.isRunning())
                    Thread.sleep(10);

                // Fermez le lecteur audio
                clip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
}


