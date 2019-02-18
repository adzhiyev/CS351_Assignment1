/*
 * @author Daniyal Adzhiyev 
 * Version: 0.0.1 
 * Date: 1/25/2019 
 * CS351
 * 
 *         This application creates a number converter between
 *         decimal, binary, octal, hexadecimal, ASCII value,
 *         RGB color value, and floating point decimal
 *         
 */
package numberConverter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JColorChooser;

/*
 * Class that hold the GUI that the user will be able to interact with to convert between
 * different number bases
 * 
 * 
 */
public class GUI {

	// Creates an Application object that will be able to use the methods to convert
	// numbers
	Application app = new Application();
	Converter converter = new Converter();
	// JFrames
	private JFrame frm = new JFrame("Number Converter"); // main frame
	private JFrame frmColor = new JFrame("Choose Background Color"); // frame for the JColorChooser

	// JLabel's
	private JLabel decimalLbl = new JLabel("Decimal");
	private JLabel binaryLbl = new JLabel("Binary");
	private JLabel octalLbl = new JLabel("Octal");
	private JLabel hexLbl = new JLabel("Hexadecimal");
	private JLabel charLbl = new JLabel("Character(s)");
	private JLabel colorLbl = new JLabel("Color");
	private JLabel floatLbl = new JLabel("Float Decimal");
	// JTextFields
	private JTextField decimalTxt = new JTextField(20);
	private JTextField binaryTxt = new JTextField(20);
	private JTextField octalTxt = new JTextField(20);
	private JTextField hexTxt = new JTextField(20);
	private JTextField charTxt = new JTextField(20);
	private JTextField colorTxt = new JTextField(20);
	private JTextField floatTxt = new JTextField(20);
	// JPanels
	private JButton convertBtn = new JButton("Convert");
	private JButton clearBtn = new JButton("Clear");
	private JButton colorBtn = new JButton("Color");
	private JPanel pnlMain = new JPanel();
	private JPanel pnlCenter = new JPanel();
	private JPanel pnlLeft = new JPanel();
	private JPanel pnlRight = new JPanel();
	private JPanel pnlButtons = new JPanel();
	private JPanel pnlColorChooser = new JPanel();
	private JPanel pnlClrButtons = new JPanel();
	private JColorChooser colorChooser = new JColorChooser();
	// JButtons for Color Chooser panel
	private JButton colorOkBtn = new JButton("OK");
	private JButton colorCancelBtn = new JButton("Cancel");
	private JButton colorResetBtn = new JButton("Reset");
	private Color chosenColor;

	String errorMessage;
	Boolean isValid;

	/*
	 * Default constructor
	 */
	public GUI() {
		// sets layout of panels
		pnlMain.setLayout(new BorderLayout());
		pnlLeft.setLayout(new GridLayout(10, 4));
		pnlCenter.setLayout(new GridLayout(10, 4));
		pnlRight.setLayout(new GridLayout(10, 4));
		pnlColorChooser.setLayout(new GridLayout(2, 2));
		// sets button sizes
		convertBtn.setSize(20, 20);
		colorBtn.setSize(20, 20);
		clearBtn.setSize(20, 20);
		colorOkBtn.setSize(20, 20);
		colorCancelBtn.setSize(20, 20);
		colorResetBtn.setSize(20, 20);

		// adds labels and textfields to frame
		pnlLeft.add(decimalLbl);
		pnlCenter.add(decimalTxt);
		pnlLeft.add(binaryLbl);
		pnlCenter.add(binaryTxt);
		pnlLeft.add(octalLbl);
		pnlCenter.add(octalTxt);
		pnlLeft.add(hexLbl);
		pnlCenter.add(hexTxt);
		pnlLeft.add(charLbl);
		pnlCenter.add(charTxt);
		pnlLeft.add(colorLbl);
		pnlCenter.add(colorTxt);
		colorTxt.setEnabled(false);
		pnlLeft.add(floatLbl);
		pnlCenter.add(floatTxt);
		pnlRight.add(colorBtn);

		// adds buttons to panelButtons
		pnlButtons.add(convertBtn, BorderLayout.WEST);
		pnlButtons.add(clearBtn, BorderLayout.EAST);
		pnlButtons.add(colorBtn, BorderLayout.EAST);

		// adds buttons to the pnlClrButtons used in the Color JFrame
		pnlClrButtons.add(colorOkBtn, BorderLayout.WEST);
		pnlClrButtons.add(colorCancelBtn, BorderLayout.EAST);
		pnlClrButtons.add(colorResetBtn, BorderLayout.EAST);

		// adds the panel to the main panel of the JFrame
		pnlMain.add(pnlLeft, BorderLayout.WEST);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlRight, BorderLayout.EAST);
		pnlMain.add(pnlButtons, BorderLayout.SOUTH);

		pnlColorChooser.add(colorChooser, BorderLayout.NORTH); // adds the color chooser to the pnl
		pnlColorChooser.add(pnlClrButtons, BorderLayout.SOUTH); // adds the pnlClrButtons to the pnl

		decimalTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void changed() {
				if (!decimalTxt.getText().isEmpty()) {

					binaryTxt.setEditable(false);
					octalTxt.setEditable(false);
					hexTxt.setEditable(false);
					charTxt.setEditable(false);
					floatTxt.setEditable(false);
				} else {
					binaryTxt.setEditable(true);
					octalTxt.setEditable(true);
					hexTxt.setEditable(true);
					charTxt.setEditable(true);
					floatTxt.setEditable(true);
				}
			}
		});
		binaryTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void changed() {
				if (!binaryTxt.getText().isEmpty()) {

					decimalTxt.setEditable(false);
					octalTxt.setEditable(false);
					hexTxt.setEditable(false);
					charTxt.setEditable(false);
					floatTxt.setEditable(false);
				} else {
					decimalTxt.setEditable(true);
					octalTxt.setEditable(true);
					hexTxt.setEditable(true);
					charTxt.setEditable(true);
					floatTxt.setEditable(true);
				}
			}
		});
		octalTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void changed() {
				if (!octalTxt.getText().isEmpty()) {

					decimalTxt.setEditable(false);
					binaryTxt.setEditable(false);
					hexTxt.setEditable(false);
					charTxt.setEditable(false);
					floatTxt.setEditable(false);
				} else {
					decimalTxt.setEditable(true);
					binaryTxt.setEditable(true);
					hexTxt.setEditable(true);
					charTxt.setEditable(true);
					floatTxt.setEditable(true);
				}
			}
		});
		charTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void changed() {
				if (!charTxt.getText().isEmpty()) {

					decimalTxt.setEditable(false);
					binaryTxt.setEditable(false);
					octalTxt.setEditable(false);
					hexTxt.setEditable(false);
					floatTxt.setEditable(false);
				} else {
					decimalTxt.setEditable(true);
					binaryTxt.setEditable(true);
					octalTxt.setEditable(true);
					hexTxt.setEditable(true);
					floatTxt.setEditable(true);
				}
			}
		});
		hexTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void changed() {
				if (!hexTxt.getText().isEmpty()) {

					decimalTxt.setEditable(false);
					binaryTxt.setEditable(false);
					octalTxt.setEditable(false);
					charTxt.setEditable(false);
					floatTxt.setEditable(false);
				} else {
					decimalTxt.setEditable(true);
					binaryTxt.setEditable(true);
					octalTxt.setEditable(true);
					charTxt.setEditable(true);
					floatTxt.setEditable(true);
				}
			}
		});
		floatTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void changed() {
				if (!floatTxt.getText().isEmpty()) {

					decimalTxt.setEditable(false);
					binaryTxt.setEditable(false);
					octalTxt.setEditable(false);
					hexTxt.setEditable(false);
					charTxt.setEditable(false);

				} else {
					decimalTxt.setEditable(true);
					binaryTxt.setEditable(true);
					octalTxt.setEditable(true);
					hexTxt.setEditable(true);
					charTxt.setEditable(true);
				}
			}
		});
		/*
		 * Action Listener for when convertBtn is Pressed
		 */
		convertBtn.addActionListener(new ActionListener() {
			/*
			 * Converts and displays values for the number
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				// Checks to see which textfield is not empty and then converts that value
				// to each other conversion, and displays them in their corresponding JTextField
				if (!decimalTxt.getText().isEmpty()) {

					String decimalValue = decimalTxt.getText();
					isValid = InputValidation.decimalValidation(decimalValue);
					System.out.println("isValid " + isValid);
					if (isValid == true) {
						String binaryValue = Converter.convertFromDecimal(decimalValue, 2);
						binaryTxt.setText(binaryValue);

						String octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);

						String hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);
						String charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);
						String floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue)));
					} else {
						errorMessage = "Not a valid entry only enter positive numbers between 0 and 2,147,483,647";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}

				}

				else if (!binaryTxt.getText().isEmpty()) {

					String binaryValue = binaryTxt.getText();
					isValid = InputValidation.binaryValidation(binaryValue);

					if (isValid == true) {
						binaryValue = Converter.padNumber(binaryValue, 2);
						binaryTxt.setText(binaryValue);
						String decimalValue = Converter.convertToDecimal(binaryValue, 2);
						decimalTxt.setText(decimalValue);
						String octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);
						String hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);

						String floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						String charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue)));
					} else {
						errorMessage = "Not a valid entry only enter binary numbers(0,1) with a length of 32 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else if (!octalTxt.getText().isEmpty()) {

					String octValue = octalTxt.getText();
					isValid = InputValidation.octalValidation(octValue);
					if (isValid == true) {
						String decimalValue = Converter.convertToDecimal(octValue, 8);
						decimalTxt.setText(decimalValue);
						String binaryValue = Converter.convertFromDecimal(decimalValue, 2);
						binaryTxt.setText(binaryValue);
						String hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);
						String charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);
						String floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue)));
					}

					else {
						errorMessage = "Not a valid entry only enter Octal Numbers(0 - 8) with a length of 10 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else if (!hexTxt.getText().isEmpty()) {

					String hexValue = hexTxt.getText().toUpperCase();
					isValid = InputValidation.hexValidation(hexValue);
					if (isValid == true) {
						hexValue = Converter.padNumber(hexValue, 16);
						hexTxt.setText(hexValue);
						String decimalValue = Converter.convertToDecimal(hexValue, 16);
						decimalTxt.setText(decimalValue);

						String binaryValue = Converter.convertFromDecimal(decimalValue, 2);
						binaryTxt.setText(binaryValue);

						String octValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octValue);
						String charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);
						String floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue.substring(0, 6))));
					}

					else {
						errorMessage = "Not a valid entry only enter HexaDecimal Values(0 - F) with a length of 8 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else if (!charTxt.getText().isEmpty()) {
					String charValue = charTxt.getText();
					isValid = InputValidation.charValidation(charValue);

					if (isValid == true) {
						String binaryValue = Converter.asciiToBinary(charValue);
						binaryTxt.setText(binaryValue);

						String decimalValue = Converter.convertToDecimal(binaryValue, 2);
						decimalTxt.setText(decimalValue);

						String octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);

						String hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);
						String floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue)));
					}

					else {
						errorMessage = "Not a valid entry only enter characters with a length of 4 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (!floatTxt.getText().isEmpty()) {

					String floatValue = floatTxt.getText();
					isValid = InputValidation.floatValidation(floatValue);
					if (isValid == true) {
						String binaryValue = Converter.floatingPointToBinary(floatValue);
						binaryTxt.setText(binaryValue);

						String decimalValue = Converter.convertToDecimal(binaryValue, 2);
						decimalTxt.setText(decimalValue);
						String octValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octValue);

						String hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);
						String charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue)));
					} else {
						errorMessage = "Not a valid entry only enter numbers between 1.7014x 10^38 and 1.7014x10^-38";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}

				}

			}
		});

		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				decimalTxt.setText("");
				binaryTxt.setText("");
				octalTxt.setText("");
				hexTxt.setText("");
				charTxt.setText("");
				colorTxt.setBackground(Color.decode("#FFFFFF"));
				floatTxt.setText("");
			}
		});

		/*
		 * Action Listener for when colorBtn is clicked
		 */
		colorBtn.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {

				frmColor.setVisible(true); // makes the frmColor visible

			}
		});
		/*
		 * Action Listener for when colorOkBtn is clicked
		 */
		colorOkBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This does not do the right thing, it just gets the color
				// from the colorchooser and changes all the JTextFields to that
				// color.

				// I need to get the Hexadecimal value for the color and send
				// that converted value to each text field.

				chosenColor = colorChooser.getColor();

				String hexString = Converter.colorToHexString(chosenColor);

				System.out.println("hex string  " + hexString);
				String decimalVal = Converter.convertToDecimal(hexString, 16);
				System.out.println("dec val " + decimalVal);
				String binaryVal = Converter.convertFromDecimal(decimalVal, 2);

				System.out.println("hex val at gui " + hexString);
				decimalTxt.setText(decimalVal);
				binaryTxt.setText(binaryVal);
				octalTxt.setText(Converter.convertFromDecimal(decimalVal, 8));
				hexTxt.setText(hexString);
				charTxt.setText(Converter.binaryToASCII(binaryVal));
				colorTxt.setBackground(chosenColor);
				floatTxt.setText(Converter.binaryToFloatingPoint(binaryVal));
				frmColor.setVisible(false);

			}
		});

		/*
		 * Action Listener for when colorCancelBtn is pressed
		 */
		colorCancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// closes the frame
				frmColor.dispose();

			}
		});

		/*
		 * Action Listener for when colorResetBtn is pressed
		 */
		colorResetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmColor.dispose();
			}
		});

		// default frame preferences for the main frame and color frame
		frm.add(pnlMain);
		frm.setPreferredSize(new Dimension(600, 600));
		frm.setVisible(true);
		frm.pack();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLocation(100, 100);
		frmColor.add(pnlColorChooser);
		frmColor.setPreferredSize(new Dimension(600, 600));
		frmColor.pack();
		frmColor.setLocation(300, 100);

	}

}