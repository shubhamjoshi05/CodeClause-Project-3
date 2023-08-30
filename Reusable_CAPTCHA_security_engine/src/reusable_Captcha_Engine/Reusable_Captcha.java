package reusable_Captcha_Engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Reusable_Captcha extends JFrame
{
	private JLabel captchaLabel;
	private JTextField captchaTextField;
	private JButton generateButton;
	private String generatedCaptcha;
	private JButton copyButton;
	
	public Reusable_Captcha()
	{
		setTitle("Captcha Generator");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		copyButton = new JButton("Copy To Clipboard");
		copyButton.setForeground(Color.magenta);
		copyButton.setFont(new Font("Arial",Font.BOLD,20));
		
		copyButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Get the system clipboard
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                // Get the text from the text field
                String text = generatedCaptcha;
                // Create a StringSelection object with the text
                StringSelection stringSelection = new StringSelection(text);
                // Set the string selection as the clipboard contents
                clipboard.setContents(stringSelection, null);
                // Show a message dialog to confirm the copying action
                JOptionPane.showMessageDialog(Reusable_Captcha.this, "Text copied to clipboard!");
            }
        });
		
		captchaLabel = new JLabel();
		captchaLabel.setFont(new Font("Arial",Font.BOLD,20));
		captchaLabel.setForeground(Color.BLUE);
		captchaTextField = new JTextField(10);
		generateButton = new JButton("Generate Captcha");
		generateButton.setForeground(Color.RED);
		generateButton.setFont(new Font("Arial",Font.BOLD,20));
		
		generateButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				generatedCaptcha = generateCaptcha();
				captchaLabel.setText(generatedCaptcha);			
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(captchaLabel);
		panel.add(captchaTextField);
		panel.add(generateButton);
		panel.add(copyButton);
		
		add(panel);
	}
	
	private String generateCaptcha() 
	{
		String characters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm01234567890";
		StringBuilder captcha = new StringBuilder();
		Random random = new Random();
		
		for(int i=0;i<6;i++)
		{
			char c = characters.charAt(random.nextInt(characters.length()));
			captcha.append(c);
		}
		return captcha.toString();
	}
	
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			
			@Override
			public void run() 
			{
				new Reusable_Captcha().setVisible(true);		
			}
		});
	}
}