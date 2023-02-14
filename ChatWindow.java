package message;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.*;

public class ChatWindow {

	public ChatWindow() {
		SwingUtilities.invokeLater(() -> createWindow());
	}

	public DefaultListModel<String> listModel = new DefaultListModel<String>();
	public DataOutputStream oStream;
	
	private void createWindow() {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();

		JList<DefaultListModel> jlist = new JList(listModel);
		
		JScrollPane listPane = new JScrollPane(jlist);
		
		JPanel panel = new JPanel();
		
		JButton sendButton = new JButton("Send");
		JTextField textField = new JTextField();
		
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sentMessage = textField.getText();
				listModel.addElement("sent: " + sentMessage);
				textField.setText("");
				try {
					oStream.writeUTF(sentMessage);
					oStream.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} 
		});
		
		sendButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "click");
		sendButton.getActionMap().put("click", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        sendButton.doClick();
		    }
		});
		
		
		textField.setColumns(20);
		
		panel.add(textField);
		panel.add(sendButton);
		
		
		
		
		pane.add(listPane);
		
		pane.add(panel, BorderLayout.SOUTH);
		
		

		
		frame.setVisible(true);

		pane.add(listPane);
		
		frame.pack();
		frame.setSize(500, 600);
		

		
		
		
		
		
	}
}
