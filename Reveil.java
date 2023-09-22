package Reveil;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.TimerTask;
import javax.sound.sampled.*;

import javax.swing.Timer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Reveil {
	private static final long serialVersionUID = 4018947799328914894L;
	public static String heure = "00";
	public static String minute = "00";
	public static String seconde = "00";
	public static long delaiEnMillisecondes;
	
	
    public static void main(String[] args) {
    	
    	
    	
        JFrame frame = new JFrame("Réveil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Créez un JPanel pour les labels avec un BoxLayout vertical
        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.setVisible(true);

        JPanel heurePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        heurePanel.setLayout(new BoxLayout(heurePanel, BoxLayout.Y_AXIS));
        JPanel minutePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        minutePanel.setLayout(new BoxLayout(minutePanel, BoxLayout.Y_AXIS));
        JPanel secondePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        secondePanel.setLayout(new BoxLayout(secondePanel, BoxLayout.Y_AXIS));
        JPanel reveilPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel labelHeure = new JLabel(heure);
        JLabel labelMinute = new JLabel(minute);
        JLabel labelSeconde = new JLabel(seconde);
        

        JButton boutonHautHeure = new JButton("+");
        JButton boutonBasHeure = new JButton("-");
        boutonHautHeure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int heureValue = Integer.parseInt(heure);
                System.out.println(heure);
                if (heureValue < 23 && heureValue >= 0) {
                    heureValue++;
                } else {
                    heureValue = 0;
                }
                heure = String.valueOf(heureValue); 
                String nouvelleHeure = (heureValue < 10) ? "0" + heureValue : String.valueOf(heureValue);
                
                System.out.println(heure);
                labelHeure.setText(nouvelleHeure);
                heurePanel.revalidate();
            }
            
        });
        
        
        boutonBasHeure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int heureValue = Integer.parseInt(heure);

                if ( heureValue >0) {
                    heureValue--;
                } else {
                    heureValue = 23;
                }
                heure = String.valueOf(heureValue); 
                String nouvelleHeure = (heureValue < 10) ? "0" + heureValue : String.valueOf(heureValue);
                
                labelHeure.setText(nouvelleHeure);
                heurePanel.revalidate();
            
            }
        });
        JButton boutonHautMinute = new JButton("+");
        JButton boutonBasMinute = new JButton("-");
        boutonHautMinute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int minuteValue = Integer.parseInt(minute);
                
                if (minuteValue < 59 && minuteValue >= 0) {
                	minuteValue++;
                } else {
                	minuteValue = 0;
                }
                minute = String.valueOf(minuteValue); 
                String nouvelleMinute = (minuteValue < 10) ? "0" + minuteValue : String.valueOf(minuteValue);
                
               
                labelMinute.setText(nouvelleMinute);
                minutePanel.revalidate();
            }
        });
        
        boutonBasMinute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int minuteValue = Integer.parseInt(minute);
                
                if ( minuteValue >0) {
                	minuteValue--;
                } else {
                	minuteValue = 59;
                }
                minute = String.valueOf(minuteValue); 
                String nouvelleMinute = (minuteValue < 10) ? "0" + minuteValue : String.valueOf(minuteValue);
                
               
                labelMinute.setText(nouvelleMinute);
                minutePanel.revalidate();
            
            }
        });
        JButton boutonHautSeconde = new JButton("+");
        JButton boutonBasSeconde = new JButton("-");
        boutonHautSeconde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int secondeValue = Integer.parseInt(seconde);
                
                if ( secondeValue >=0 && secondeValue <59) {
                	secondeValue++;
                } else {
                	secondeValue = 0;
                }
                seconde = String.valueOf(secondeValue); 
                String nouvelleSeconde = (secondeValue < 10) ? "0" + secondeValue : String.valueOf(secondeValue);
                
               
                labelSeconde.setText(nouvelleSeconde);
                minutePanel.revalidate();
            }
        });
        
        boutonBasSeconde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int secondeValue = Integer.parseInt(seconde);
                
                if ( secondeValue >0) {
                	secondeValue--;
                } else {
                	secondeValue = 59;
                }
                seconde = String.valueOf(secondeValue); 
                String nouvelleSeconde = (secondeValue < 10) ? "0" + secondeValue : String.valueOf(secondeValue);
                
               
                labelSeconde.setText(nouvelleSeconde);
                minutePanel.revalidate();
            }
        });
       
       
        JButton reveil = new JButton("Reveil");
        reveil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Réinitialisez delaiEnMillisecondes en fonction des valeurs de l'heure, des minutes et des secondes
                delaiEnMillisecondes = (Integer.parseInt(heure) * 3600 + Integer.parseInt(minute) * 60
                        + Integer.parseInt(seconde)) * 1000;
                java.util.Timer timerSound = new java.util.Timer();
                labelPanel.setVisible(false);
                final Timer timer = new Timer(1000, new ActionListener() {
                    long remainingTime = delaiEnMillisecondes;
                    
                    

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	System.out.println(remainingTime/1000);
                        if (remainingTime > 0) {
                            remainingTime -= 1000;
                            // Mettez à jour l'interface utilisateur ici si nécessaire
                        } else {
                            ((Timer) e.getSource()).stop(); // Arrête le timer lorsque le temps est écoulé
                            timerSound.schedule(new Sound(), 0);
                        }
                    }
                });
                
                timer.start();
            }
        });
        
        heurePanel.add(boutonHautHeure);
        heurePanel.add(labelHeure);
        heurePanel.add(boutonBasHeure);

        minutePanel.add(boutonHautMinute);
        minutePanel.add(labelMinute);
        minutePanel.add(boutonBasMinute);

        secondePanel.add(boutonHautSeconde);
        secondePanel.add(labelSeconde);
        secondePanel.add(boutonBasSeconde);

        
        reveilPanel.add(reveil);
        // Ajoutez les panels au labelPanel
        labelPanel.add(heurePanel);
        labelPanel.add(minutePanel);
        labelPanel.add(secondePanel);
        labelPanel.add(reveilPanel);
        
        reveilPanel.add(heurePanel);
        reveilPanel.add(minutePanel);
        reveilPanel.add(secondePanel);
        

        // Ajoutez le labelPanel au frame principal
        frame.add(labelPanel);
        frame.add(reveilPanel);
        frame.setVisible(true);
     
    }
}
