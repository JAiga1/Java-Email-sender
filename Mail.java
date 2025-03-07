import java.awt.Color;  // Used for defining colors in the graphical user interface.
import java.awt.event.ActionEvent;  // Used for handling events like button clicks.
import java.awt.event.ActionListener;  // Used for defining action listeners to respond to events.
import java.io.BufferedReader;  // Used for reading text from character input streams.
import java.io.IOException;  // Used for handling input/output exceptions.
import java.io.InputStreamReader;  // Used for reading input from various sources.
import java.io.OutputStream;  // Used for writing output to various destinations.
import java.net.HttpURLConnection;  // Used for making HTTP connections.
import java.net.URL;  // Used for specifying URLs.
import java.util.Properties;  // Used for managing key-value pairs (e.g., for configuration settings).
import java.util.regex.Pattern;  // Used for working with regular expressions.
import javax.swing.JOptionPane;  // Used for displaying dialog boxes and messages.
import javax.mail.Authenticator;  // Used for email authentication.
import javax.mail.Message;  // Used for working with email messages.
import javax.mail.MessagingException;  // Used for handling email-related exceptions.
import javax.mail.PasswordAuthentication;  // Used for providing authentication credentials for sending email.
import javax.mail.Session;  // Used for managing email sessions and settings.
import javax.mail.Transport;  // Used for sending email messages.
import javax.mail.internet.InternetAddress;  // Used for working with email addresses.
import javax.mail.internet.MimeMessage;  // Used for creating MIME email messages.
import javax.swing.BorderFactory;  // This import appears to be incorrect and might not be needed. It's not a standard Java Swing import.
import javax.swing.JButton;  // Used for creating buttons in a graphical user interface.
import javax.swing.JFrame;  // Used for creating the main application window.
import javax.swing.JLabel;  // Used for displaying text labels in the user interface.
import javax.swing.JPanel;  // Used for creating panels within the user interface.
import javax.swing.JPasswordField;  // Used for password input fields.
import javax.swing.JScrollPane;  // Used for adding scroll bars to components like text areas.
import javax.swing.JTextArea;  // Used for displaying and editing multiline text.
import javax.swing.JTextField;  // Used for single-line text input fields.


public class Mail extends JFrame {
    private static final long serialVersionUID = 1L;

    // Declare the conn variable for making HTTP requests
    private HttpURLConnection conn;

    JPanel panel;
    JPanel panel2;
    JTextField recipient;
    JTextField sender;
    JTextField subject;
    JTextArea mainmessage;
    JPasswordField psw;
    JLabel label;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JButton btn;

    public Mail() {
        panel = new JPanel();
        panel.setLayout(null);

        // Add a label for the text field
        label = new JLabel("Recipient");
        label.setBounds(50, 10, 100, 40);
        panel.add(label);

        // Add a text field for the mail of the recipient
        recipient = new JTextField();
        recipient.setBounds(120, 15, 200, 30);
        recipient.setFocusable(true);
        panel.add(recipient);

        // Add a button to check if the email is valid
        btn = new JButton("Submit");
        btn.setBounds(150, 70, 100, 30);
        panel.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rec = recipient.getText();

                if (rec.isEmpty()) {// If the text field is empty
                    JOptionPane.showMessageDialog(null, "You must enter a recipient");
                } else {
                    if (!isValid(rec)) {// if the email isn't valid
                        JOptionPane.showMessageDialog(null, "The email you entered is not valid");
                    } else {
                        sendMail(rec);// Recipient of the mail
                    }
                }
            }
        });

        this.setContentPane(panel);
        this.setTitle("Send an Email");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(400, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @SuppressWarnings("deprecation")
    public void sendMail(String recipient) {
        panel2 = new JPanel();
        panel2.setLayout(null);

        System.out.println("Panel is changed waiting for your info & message");
        Properties prop = new Properties();
        /* This is for authentication. It defines if an authentication is needed for the email server. E.g. If its gmail,
        then it is mandatory to have a Username & a password. If you are using an email server that doesn't need
        authentication, then set it to false */
        prop.put("mail.smtp.auth", "true");
        // This is for TLS encryption (For Gmail, it has to be enabled).
        prop.put("mail.smtp.starttls.enable", "true");
        // For Gmail, the host is smtp.gmail.com
        prop.put("mail.smtp.host", "smtp.gmail.com");
        // For Gmail, the port is 587
        prop.put("mail.smtp.port", "587");

        // Add a label for the text field
        label = new JLabel("Enter your Email");
        label.setBounds(20, 10, 100, 40);
        panel2.add(label);

        // Add a text field for the mail of the sender
        sender = new JTextField();
        sender.setBounds(170, 15, 180, 30);
        sender.setFocusable(true);
        panel2.add(sender);

        // Add a label for the text field
        label2 = new JLabel("Enter your Password");
        label2.setBounds(20, 60, 140, 40);
        panel2.add(label2);

        // Add a text field for the password of the sender
        psw = new JPasswordField(20);
        psw.setBounds(170, 65, 180, 30);
        panel2.add(psw);

        // Add a label for the text field
        label3 = new JLabel("Subject");
        label3.setBounds(70, 120, 60, 40);
        panel2.add(label3);

        // Add a text field for the subject of the mail
        subject = new JTextField();
        subject.setBounds(130, 125, 260, 30);
        panel2.add(subject);

        // Add a label for the text field
        label4 = new JLabel("Context");
        label4.setBounds(70, 170, 60, 40);
        panel2.add(label4);

        // Add a text field for the context of the mail
        mainmessage = new JTextArea();
        mainmessage.setLineWrap(true);
        mainmessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JScrollPane scroll = new JScrollPane(mainmessage);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(130, 175, 276, 230);
        panel2.add(scroll);

        btn = new JButton("Send");
        btn.setBounds(100, 420, 100, 30);
        panel2.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myAccEmail = sender.getText(); // User mail
                String myAccPass = new String(psw.getPassword()); // User password for the mail
                String mailSubject = subject.getText(); // The subject of the mail
                String mailMessage = mainmessage.getText(); // The main message of the mail

                if (myAccEmail.isEmpty()) {// If the mail is empty
                    JOptionPane.showMessageDialog(null, "You must enter your email");
                } else if (myAccPass.isEmpty()) {// If the password is empty
                    JOptionPane.showMessageDialog(null, "You must enter a password");
                } else if (myAccPass.length() < 5) {// If the length of the password is smaller than 5 characters
                    JOptionPane.showMessageDialog(null, "Password must have 5 or more characters");
                } else if (mailMessage.isEmpty()) {// If the context of the mail is empty
                    JOptionPane.showMessageDialog(null, "There must be a message!");
                } else {
                    if (!isValid(myAccEmail)) {// If the email isn't valid
                        JOptionPane.showMessageDialog(null, "The email you entered is not valid");
                    } else {
                        Session session = Session.getInstance(prop, new Authenticator() { // Make a new session
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {

                                return new PasswordAuthentication(myAccEmail, myAccPass); // Make sure to connect to
                                                                                           // the Email with the given
                                                                                           // password
                            }
                        });

                        Message message = prepareMessage(session, myAccEmail, recipient, mailSubject, mailMessage); // Prepare
                                                                                                                    // the
                                                                                                                    // message

                        try {
                            Transport.send(message); // Send the message
                            System.out.println("Message sent successfully");
                        } catch (MessagingException arg0) {
                            arg0.printStackTrace();
                            JOptionPane.showMessageDialog(null, arg0);
                        }
                    }
                }

            }
        });

        btn = new JButton("Generate Text");
        btn.setBounds(220, 420, 150, 30);
        panel2.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter their API key
                String apiKey = JOptionPane.showInputDialog(null, "Enter your OpenAI API key:");
        
                if (apiKey == null || apiKey.isEmpty()) {
                    // User canceled or entered an empty API key
                    JOptionPane.showMessageDialog(null, "API key is required.");
                } else {
                    // Continue with the API key and generate text
                    String mailSubject = subject.getText(); // Get the subject from the input field
                    if (mailSubject.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a subject before generating text.");
                    } else {
                        try {
                            String generatedText = generateText(apiKey, mailSubject);
                            mainmessage.setText(mainmessage.getText() + "\n\nGenerated Text:\n" + generatedText);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error generating text: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        btn = new JButton("Close");
        btn.setBounds(350, 420, 100, 30);
        panel2.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // A button to terminate the program
            }
        });

        this.setContentPane(panel2); // Change panel
        this.setTitle("Send an Email");
        this.setLocation(0, 0);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    private static Message prepareMessage(Session session, String myAccEmail, String recipient, String mailSubject,
            String mailMessage) {
        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(myAccEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject(mailSubject);
            msg.setText(mailMessage);

            return msg;
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public String generateText(String apiKey, String prompt) throws IOException {
        // Replace 'YOUR_API_KEY' with your actual OpenAI API key
        String endpoint = "https://api.openai.com/v1/engines/text-davinci-002/completions";
        String model = "text-davinci-002";
    
        String data = "{\"prompt\":\"" + prompt + "\",\"max_tokens\":50}";
    
        URL url = new URL(endpoint);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + apiKey); // Use the provided API key
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
    
        try (OutputStream os = conn.getOutputStream()) {
                  byte[] input = data.getBytes("utf-8");
        os.write(input, 0, input.length);
    }

    int responseCode = conn.getResponseCode();
    if (responseCode != 200) {
        throw new RuntimeException("HTTP Response Code: " + responseCode);
    }

    // Read the response from the API
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = in.readLine()) != null) {
        response.append(line);
    }
    in.close();

    // Extract the generated text from the response
    String responseStr = response.toString();
    int startIndex = responseStr.indexOf("\"text\":\"") + 8;
    int endIndex = responseStr.indexOf("\",", startIndex);

    if (startIndex != -1 && endIndex != -1) {
        return responseStr.substring(startIndex, endIndex);
    } else {
        throw new IOException("Unexpected API response format.");
    }
}

public static boolean isValid(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return Pattern.compile(emailRegex).matcher(email).matches();
}


    public static void main(String[] args) {
        new Mail();
    }
}
