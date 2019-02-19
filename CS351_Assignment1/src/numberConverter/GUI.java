/*
 * @author Daniyal Adzhiyev 
 * Version: 1.1.0
 *  Date: 2/19/2019 
 *  CS351 - Assignment 1
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

	// String to hold conversion values
	String decimalValue;
	String binaryValue;
	String octalValue;
	String hexValue;
	String charValue;
	String floatValue;

	String errorMessage; // holds error message
	Boolean isValid; // checks if string is valid

	String previousBinaryString = ""; // stores previous binary string to restore values

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

		// adds labels and text fields to frame
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
		// adds the color buttons panel and the color chooser to the color panel
		pnlColorChooser.add(colorChooser, BorderLayout.NORTH); // adds the color chooser to the panel
		pnlColorChooser.add(pnlClrButtons, BorderLayout.SOUTH); // adds the pnlClrButtons to the panel

		/*
		 * Document listener for decimalTxt text field
		 */
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

			/*
			 * Checks if there has been any changes in the text field
			 */
			public void changed() {
				// checks if the text field is empty which determines if it is editable or not
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

		/*
		 * Document listener for binaryTxt text field
		 */
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

			/*
			 * Checks if there has been any changes in the text field
			 */
			public void changed() {

				// checks if the text field is empty which determines if it is editable or not
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

		/*
		 * Document listener for octalTxt text field
		 */
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

			/*
			 * Checks if there has been any changes in the text field
			 */
			public void changed() {
				// checks if the text field is empty which determines if it is editable or not
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
		/*
		 * Document listener for charTxt text field
		 */
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

			/*
			 * Checks if there has been any changes in the text field
			 */
			public void changed() {
				// checks if the text field is empty which determines if it is editable or not
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

		/*
		 * Document listener for hexTxt text field
		 */
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

			/*
			 * Checks if there has been any changes in the text field
			 */
			public void changed() {
				// checks if the text field is empty which determines if it is editable or not
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
		/*
		 * Document listener for floatTxt text field
		 */
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

			/*
			 * Checks if there has been any changes in the text field
			 */
			public void changed() {
				// checks if the text field is empty which determines if it is editable or not
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

				// Checks if the decimal text field is not empty, and then converts the the
				// number in the text field to other bases
				if (!decimalTxt.getText().isEmpty()) {

					decimalValue = decimalTxt.getText();
					isValid = InputValidation.decimalValidation(decimalValue); // checks if input is valid

					// if it is valid it can convert the values
					if (isValid == true) {

						binaryValue = Converter.convertFromDecimal(decimalValue, 2);
						binaryTxt.setText(binaryValue);

						octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);

						hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);

						charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);

						floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						// decodes the hex value to get the RGB color values, and sets the color of the
						// text field
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue).substring(2, 8)));
					}
					// if not valid it will give a pop up box error message
					else {
						errorMessage = "Not a valid entry only enter positive numbers between 0 and 4,294,967,295";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}

				}
				// Checks if the binary text field is not empty, and then converts the the
				// number in the text field to other bases
				else if (!binaryTxt.getText().isEmpty()) {

					binaryValue = binaryTxt.getText();
					isValid = InputValidation.binaryValidation(binaryValue); // checks if input is valid

					if (isValid == true) {

						binaryValue = Converter.padNumber(binaryValue, 2); // pads the binary values with 0
						binaryTxt.setText(binaryValue);

						decimalValue = Converter.convertToDecimal(binaryValue, 2);
						decimalTxt.setText(decimalValue);

						octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);

						hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);

						floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);

						// sets the colorTxt background with the RGB values of the hex value, which is
						// the last 6 digits
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue).substring(2, 8)));

					}
					// if not valid it will give a pop up box error message
					else {

						errorMessage = "Not a valid entry only enter binary numbers(0,1) with a length of 32 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				// Checks if the octal text field is not empty, and then converts the the
				// number in the text field to other bases
				else if (!octalTxt.getText().isEmpty()) {

					octalValue = octalTxt.getText();
					isValid = InputValidation.octalValidation(octalValue); // checks if input is valid

					if (isValid == true) {
						octalValue = Converter.padNumber(octalValue, 8); // pads the octal value with 0's
						octalTxt.setText(octalValue);

						decimalValue = Converter.convertToDecimal(octalValue, 8);
						decimalTxt.setText(decimalValue);

						binaryValue = Converter.convertFromDecimal(decimalValue, 2);
						binaryTxt.setText(binaryValue);

						hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);

						charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);

						floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						// sets the colorTxt background with the RGB values of the hex value, which is
						// the last 6 digits
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue).substring(2, 8)));
					}
					// if not valid it will give a pop up box error message
					else {
						errorMessage = "Not a valid entry only enter Octal Numbers(0 - 8) with a length of 10 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				// Checks if the hexadecimal text field is not empty, and then converts the the
				// number in the text field to other bases
				else if (!hexTxt.getText().isEmpty()) {

					hexValue = hexTxt.getText().toUpperCase(); // Upper cases the string for conversion
					isValid = InputValidation.hexValidation(hexValue); // checks if input is valid

					if (isValid == true) {
						hexValue = Converter.padNumber(hexValue, 16); // pads the hex value with 0's
						hexTxt.setText(hexValue);

						decimalValue = Converter.convertToDecimal(hexValue, 16);
						decimalTxt.setText(decimalValue);

						binaryValue = Converter.convertFromDecimal(decimalValue, 2);
						binaryTxt.setText(binaryValue);

						octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);

						charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);

						floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);

						// sets the colorTxt background with the RGB values of the hex value, which is
						// the last 6 digits
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue.substring(2, 8))));

					}
					// if not valid it will give a pop up box error message
					else {
						errorMessage = "Not a valid entry only enter HexaDecimal Values(0 - F) with a length of 8 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				// Checks if the character text field is not empty, and then converts the the
				// number in the text field to other bases
				else if (!charTxt.getText().isEmpty()) {

					charValue = charTxt.getText();
					isValid = InputValidation.charValidation(charValue); // checks if input is valid

					if (isValid == true) {

						binaryValue = Converter.asciiToBinary(charValue);
						binaryTxt.setText(binaryValue);

						decimalValue = Converter.convertToDecimal(binaryValue, 2);
						decimalTxt.setText(decimalValue);

						octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);

						hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);
						floatValue = Converter.binaryToFloatingPoint(binaryValue);
						floatTxt.setText(floatValue);
						// sets the colorTxt background with the RGB values of the hex value, which is
						// the last 6 digits
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue).substring(2, 8)));
					}
					// if not valid it will give a pop up box error message
					else {
						errorMessage = "Not a valid entry only enter characters with a length of 4 or less";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}

				}
				// Checks if the float text field is not empty, and then converts the the
				// number in the text field to other bases
				else if (!floatTxt.getText().isEmpty()) {

					String floatValue = floatTxt.getText();
					isValid = InputValidation.floatValidation(floatValue); // checks if input is valid

					if (isValid == true) {

						binaryValue = Converter.floatingPointToBinary(floatValue);
						binaryTxt.setText(binaryValue);

						decimalValue = Converter.convertToDecimal(binaryValue, 2);
						decimalTxt.setText(decimalValue);

						octalValue = Converter.convertFromDecimal(decimalValue, 8);
						octalTxt.setText(octalValue);

						hexValue = Converter.convertFromDecimal(decimalValue, 16);
						hexTxt.setText(hexValue);

						String charValue = Converter.binaryToASCII(binaryValue);
						charTxt.setText(charValue);
						// sets the colorTxt background with the RGB values of the hex value, which is
						// the last 6 digits
						colorTxt.setBackground(Color.decode("#" + Converter.getFloorVal(hexValue).substring(2, 8)));

					}
					// if not valid it will give a pop up box error message
					else {
						errorMessage = "Not a valid entry only enter numbers between 1.7014x 10^-38 and 1.7014x10^38";
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
					}

				}

			}
		});

		/*
		 * Action Listener for when clearBtn is clicked
		 */
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// sets all the text fields to empty and the colorTxt field to white
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

				// Keeps the binary value before the Color panel is opened to be able to restore
				// the conversions
				previousBinaryString = binaryTxt.getText();
			}
		});
		/*
		 * Action Listener for when colorOkBtn is clicked
		 */
		colorOkBtn.addActionListener(new ActionListener() {
			@Override
			/*
			 * Action performed when colorOkBtn is clicked
			 */
			public void actionPerformed(ActionEvent e) {

				chosenColor = colorChooser.getColor();

				// converts the hex color value to each number base
				hexValue = Converter.colorToHexString(chosenColor);
				decimalValue = Converter.convertToDecimal(hexValue, 16);
				binaryValue = Converter.convertFromDecimal(decimalValue, 2);
				octalValue = Converter.convertFromDecimal(decimalValue, 8);
				charValue = Converter.binaryToASCII(binaryValue);

				// sets the text fields with the corresponding values
				decimalTxt.setText(decimalValue);
				binaryTxt.setText(binaryValue);
				octalTxt.setText(octalValue);
				hexTxt.setText(hexValue);
				charTxt.setText(charValue);
				colorTxt.setBackground(chosenColor);
				floatTxt.setText(Converter.binaryToFloatingPoint(binaryValue));

			}
		});

		/*
		 * Action Listener for when colorCancelBtn is pressed
		 */
		colorCancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmColor.dispose(); // closes the color frame

			}
		});

		/*
		 * Action Listener for when colorResetBtn is pressed
		 */
		colorResetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clearBtn.doClick(); // Clears all the text fields
				binaryTxt.setText(previousBinaryString); // sets the binary text field to previous value

				// clears the decimal text field so will by default convert from binary value
				// clicks the convert Button which will perform conversions by the stored
				// previous binary value
				convertBtn.doClick();
			}
		});

		// default frame preferences for the main frame
		frm.add(pnlMain);
		frm.setPreferredSize(new Dimension(600, 600));
		frm.setVisible(true);
		frm.pack();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLocation(100, 100);
		// default frame preferences for the color frame
		frmColor.add(pnlColorChooser);
		frmColor.setPreferredSize(new Dimension(600, 600));
		frmColor.pack();
		frmColor.setLocation(300, 100);

	}

}