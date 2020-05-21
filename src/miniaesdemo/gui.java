package miniaesdemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * <p>
 * Graphical User Interface
 * </p>
 * 
 * @author Team Calligula
 * @version 1.0
 * @since 02.04.2019
 */
public class gui extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	String fileName = "";

	// Start
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("MiniAES");

		/* 16-bit Key Tab Children */

		/* Image Child */
//         
//        Image imageKeyTab = new Image(new FileInputStream("file:/pictures/img.png"));
//        ImageView imageViewKeyTab = new ImageView(imageKeyTab);
//        imageViewKeyTab.setTranslateX(0);
//        imageViewKeyTab.setTranslateY(-160);

		/* Labels Children */

		// Header Label
		Label labelHeader = new Label("16-bit String");
		labelHeader.setTranslateY(-100);
		labelHeader.setTranslateX(-15);
		labelHeader.setFont(Font.font("Elephant", FontWeight.BOLD, 24));
		// Label that says Random
		Label labelRandom = new Label("Random");
		labelRandom.setTranslateX(263);
		labelRandom.setTranslateY(0);
		labelRandom.setFont(Font.font("Verdana", 12));
		// Label that says Key
		Label labelKey = new Label("Key");
		labelKey.setTranslateX(263);
		labelKey.setTranslateY(10);
		labelKey.setFont(Font.font("Verdana", 12));
		// Label that says Encryption
		Label labelEncryption = new Label("Encryption");
		labelEncryption.setTranslateY(-60);
		labelEncryption.setTranslateX(-15);
		labelEncryption.setFont(Font.font("Elephant", FontWeight.BOLD, 24));
		// Label that says Decryption
		Label labelDecryption = new Label("Decryption");
		labelDecryption.setTranslateY(80);
		labelDecryption.setTranslateX(-15);
		labelDecryption.setFont(Font.font("Elephant", FontWeight.BOLD, 24));

		/* Buttons Children */

		// Encrypt Button
		Button buttonEncrypt;
		buttonEncrypt = new Button();
		buttonEncrypt.setText("Encrypt");
		buttonEncrypt.setPrefSize(120, 40);
		buttonEncrypt.setTranslateX(168);
		buttonEncrypt.setTranslateY(30);
		buttonEncrypt.setFont(Font.font("Verdana", 12));
		// Decrypt Button
		Button buttonDecrypt;
		buttonDecrypt = new Button();
		buttonDecrypt.setText("Decrypt");
		buttonDecrypt.setPrefSize(120, 40);
		buttonDecrypt.setTranslateX(168);
		buttonDecrypt.setTranslateY(170);
		buttonDecrypt.setFont(Font.font("Verdana", 12));

		/* TextField Children */

		// Encryption TextFields
		// TextField Encrypt
		TextField textField16bitString = new TextField();
		textField16bitString.setPromptText("Enter 16-bit string");
		textField16bitString.setPrefSize(100, 40);
		textField16bitString.setMaxWidth(240);
		textField16bitString.setTranslateX(-140);
		textField16bitString.setTranslateY(-20);
		textField16bitString.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Key for Encrypting
		TextField textFieldKeyForEncryption = new TextField();
		textFieldKeyForEncryption.setPromptText("Enter 16-bit Encryption Key");
		textFieldKeyForEncryption.setPrefSize(100, 40);
		textFieldKeyForEncryption.setMaxWidth(245);
		textFieldKeyForEncryption.setTranslateX(105);
		textFieldKeyForEncryption.setTranslateY(-20);
		textFieldKeyForEncryption.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Encrypted String
		TextField textFieldEncrypted = new TextField();
		textFieldEncrypted.setPromptText("Encrypted key");
		textFieldEncrypted.setPrefSize(100, 40);
		textFieldEncrypted.setMaxWidth(250);
		textFieldEncrypted.setTranslateX(-20);
		textFieldEncrypted.setTranslateY(30);
		textFieldEncrypted.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		textFieldEncrypted.setEditable(false);

		// Decryption TextFields
		// TextField Decrypt
		TextField textField16bitStringDecryption = new TextField();
		textField16bitStringDecryption.setPromptText("Enter 16-bit String");
		textField16bitStringDecryption.setPrefSize(100, 40);
		textField16bitStringDecryption.setMinWidth(10);
		textField16bitStringDecryption.setMaxWidth(240);
		textField16bitStringDecryption.setTranslateX(-140);
		textField16bitStringDecryption.setTranslateY(120);
		textField16bitStringDecryption.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Key for Decrypting
		TextField textFieldKeyForDecryption = new TextField();
		textFieldKeyForDecryption.setPromptText("Enter 16-bit Decryption Key");
		textFieldKeyForDecryption.setPrefSize(100, 40);
		textFieldKeyForDecryption.setMaxWidth(245);
		textFieldKeyForDecryption.setTranslateX(105);
		textFieldKeyForDecryption.setTranslateY(120);
		textFieldKeyForDecryption.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Decrypted String
		TextField textFieldDecrypted = new TextField();
		textFieldDecrypted.setPromptText("Decrypted key");
		textFieldDecrypted.setPrefSize(100, 40);
		textFieldDecrypted.setMaxWidth(250);
		textFieldDecrypted.setTranslateX(-20);
		textFieldDecrypted.setTranslateY(170);
		textFieldDecrypted.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		textFieldDecrypted.setEditable(false);

		/* CheckBox Child */

		// CheckBox Initialization
		CheckBox generateEncryptKey = new CheckBox();
		generateEncryptKey.setTranslateX(265);
		generateEncryptKey.setTranslateY(-20);

		/* Restrictions */

		// 19 character restriction for the Encryption textField
		Pattern patternEncrypt = Pattern.compile(".{0,19}");
		TextFormatter formatterEncrypt = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return patternEncrypt.matcher(change.getControlNewText()).matches() ? change : null;
		});
		textField16bitString.setTextFormatter(formatterEncrypt);

		// 19 character restriction for the Decryption textField
		Pattern patternDecrypt = Pattern.compile(".{0,19}");
		TextFormatter formatterDecrypt = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return patternDecrypt.matcher(change.getControlNewText()).matches() ? change : null;
		});
		textField16bitStringDecryption.setTextFormatter(formatterDecrypt);

		// 19 character restriction for the Key textField
		Pattern patternKey = Pattern.compile(".{0,19}");
		TextFormatter formatterKey = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return patternKey.matcher(change.getControlNewText()).matches() ? change : null;
		});
		textFieldKeyForEncryption.setTextFormatter(formatterKey);

		/* Event Handlers */

		// Check Box Event Handler for the Random Key generation
		generateEncryptKey.setOnAction((ActionEvent event) -> {
			Boolean isSelectedEncrypt = generateEncryptKey.isSelected();
			if (isSelectedEncrypt == true) {
				RandomKey stringEncrypt = new RandomKey();
				textFieldKeyForEncryption.setText(stringEncrypt.getRandomKeyString());
				textFieldKeyForEncryption.setEditable(false);
			} else {
				textFieldKeyForEncryption.setText("");
				textFieldKeyForEncryption.setEditable(true);
			}
		});

		// Encrypt Button Event Handler
		buttonEncrypt.setOnAction((ActionEvent event) -> {
			Boolean isSelectedEncrypt = generateEncryptKey.isSelected();
			if (isSelectedEncrypt == true) {
				MiniAES encryptkey = new MiniAES();
				textFieldEncrypted.setText(
						encryptkey.Encrypt(textField16bitString.getText(), textFieldKeyForEncryption.getText()));
			} else {
				MiniAES encryptkey = new MiniAES();
				textFieldEncrypted.setText(
						encryptkey.Encrypt(textField16bitString.getText(), textFieldKeyForEncryption.getText()));
			}
		});

		// EventHandler button Decyrpt
		buttonDecrypt.setOnAction((ActionEvent event) -> {
			Boolean isSelectedEncrypt = generateEncryptKey.isSelected();
			if (isSelectedEncrypt == true) {
				MiniAES decryptkey = new MiniAES();
				textFieldDecrypted.setText(decryptkey.Decrypt(textField16bitStringDecryption.getText(),
						textFieldKeyForDecryption.getText()));
			} else {
				MiniAES decryptkey = new MiniAES();
				textFieldDecrypted.setText(decryptkey.Decrypt(textField16bitStringDecryption.getText(),
						textFieldKeyForDecryption.getText()));
			}
		});

		/* 16-bit Key Tab Children End */

		/* String Tab Children */

		/* Images Child */

		// Image
//        Image imageStringTab = new Image(new FileInputStream("C:\\Users\\icowemaina\\Documents\\NetBeansProjects\\MiniAES\\pictures\\img.png"));
//        ImageView imageViewStringTab = new ImageView(imageStringTab);
//        imageViewStringTab.setTranslateX(0);
//        imageViewStringTab.setTranslateY(-160);

		/* Label children */

		// Header Label
		Label labelString = new Label("String");
		labelString.setTranslateY(-100);
		labelString.setTranslateX(-15);
		labelString.setFont(Font.font("Elephant", FontWeight.BOLD, 24));
		// Label that says Random
		Label labelRandomStringTab = new Label("Random");
		labelRandomStringTab.setTranslateX(263);
		labelRandomStringTab.setTranslateY(-20);
		labelRandomStringTab.setFont(Font.font("Verdana", 12));
		// Label that says Key
		Label labelKeyStringTab = new Label("Key");
		labelKeyStringTab.setTranslateX(263);
		labelKeyStringTab.setTranslateY(-10);
		labelKeyStringTab.setFont(Font.font("Verdana", 12));

		/* Button children */

		// Encrypt Button
		Button buttonEncryptString;
		buttonEncryptString = new Button();
		buttonEncryptString.setText("Encrypt");
		buttonEncryptString.setPrefSize(120, 40);
		buttonEncryptString.setTranslateX(165);
		buttonEncryptString.setTranslateY(5);
		buttonEncryptString.setFont(Font.font("Verdana", 12));
		// Decrypt Button
		Button buttonDecryptString;
		buttonDecryptString = new Button();
		buttonDecryptString.setText("Decrypt");
		buttonDecryptString.setPrefSize(120, 40);
		buttonDecryptString.setTranslateX(165);
		buttonDecryptString.setTranslateY(160);
		buttonDecryptString.setFont(Font.font("Verdana", 12));

		/* Text field children */

		// Encrypt
		// TextField Encrypt
		TextField textFieldString = new TextField();
		textFieldString.setPromptText("Enter String");
		textFieldString.setPrefSize(100, 40);
		textFieldString.setMaxWidth(450);
		textFieldString.setTranslateX(0);
		textFieldString.setTranslateY(-40);
		textFieldString.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Key for Encrypting
		TextField textFieldKeyForEncryptingString = new TextField();
		textFieldKeyForEncryptingString.setPromptText("Enter 16-bit Encryption Key");
		textFieldKeyForEncryptingString.setPrefSize(100, 40);
		textFieldKeyForEncryptingString.setMaxWidth(245);
		textFieldKeyForEncryptingString.setTranslateX(-21);
		textFieldKeyForEncryptingString.setTranslateY(5);
		textFieldKeyForEncryptingString.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Encrypted String
		TextField textFieldEncryptedString = new TextField();
		textFieldEncryptedString.setPromptText("Encrypted String");
		textFieldEncryptedString.setPrefSize(100, 40);
		textFieldEncryptedString.setMaxWidth(450);
		textFieldEncryptedString.setTranslateX(0);
		textFieldEncryptedString.setTranslateY(50);
		textFieldEncryptedString.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		textFieldEncryptedString.setEditable(false);

		// Decrypt
		// TextBox Decrypt
		TextField textFieldDecryptString = new TextField();
		textFieldDecryptString.setPromptText("Enter Encrypted String");
		textFieldDecryptString.setPrefSize(100, 40);
		textFieldDecryptString.setMinWidth(10);
		textFieldDecryptString.setMaxWidth(450);
		textFieldDecryptString.setTranslateX(0);
		textFieldDecryptString.setTranslateY(115);
		textFieldDecryptString.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Key for Decrypting
		TextField textFieldKeyForDecryptingString = new TextField();
		textFieldKeyForDecryptingString.setPromptText("Enter 16-bit Decryption Key");
		textFieldKeyForDecryptingString.setPrefSize(100, 40);
		textFieldKeyForDecryptingString.setMaxWidth(245);
		textFieldKeyForDecryptingString.setTranslateX(-21);
		textFieldKeyForDecryptingString.setTranslateY(160);
		textFieldKeyForDecryptingString.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		// TextField Decrypted String
		TextField textFieldDecryptedString = new TextField();
		textFieldDecryptedString.setPromptText("Decrypted String");
		textFieldDecryptedString.setPrefSize(100, 40);
		textFieldDecryptedString.setMaxWidth(450);
		textFieldDecryptedString.setTranslateX(0);
		textFieldDecryptedString.setTranslateY(205);
		textFieldDecryptedString.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		textFieldDecryptedString.setEditable(false);

		/* CheckBox Child */

		// CheckBox
		CheckBox generateKeyForString = new CheckBox();
		generateKeyForString.setTranslateX(265);
		generateKeyForString.setTranslateY(-40);

		/* Restrictions */

		// 19 character restriction for the Encryption textField
		Pattern patternKeyString = Pattern.compile(".{0,19}");
		TextFormatter formatterKeyString = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return patternKeyString.matcher(change.getControlNewText()).matches() ? change : null;
		});
		textFieldKeyForEncryptingString.setTextFormatter(formatterKeyString);
		// 19 character restriction for the Decryption textField
		Pattern patternKeyStringDecrypt = Pattern.compile(".{0,19}");
		TextFormatter formatterKeyStringDecrypt = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return patternKeyStringDecrypt.matcher(change.getControlNewText()).matches() ? change : null;
		});
		textFieldKeyForDecryptingString.setTextFormatter(formatterKeyStringDecrypt);

		/* Event Handlers */
		// EventHandler button Encrypt
		buttonEncryptString.setOnAction((ActionEvent event) -> {
			Boolean isSelectedEncryptString = generateKeyForString.isSelected();
			if (isSelectedEncryptString == true) {
				textFieldEncryptedString.setText(
						MiniAES.EncryptText(textFieldString.getText(), textFieldKeyForEncryptingString.getText()));
			} else {
				textFieldEncryptedString.setText(
						MiniAES.EncryptText(textFieldString.getText(), textFieldKeyForEncryptingString.getText()));
			}
		});
		// Event Handler Checkbox
		generateKeyForString.setOnAction((ActionEvent event) -> {
			Boolean isSelectedEncryptString = generateKeyForString.isSelected();
			if (isSelectedEncryptString == true) {
				RandomKey stringEncryptString = new RandomKey();
				textFieldKeyForEncryptingString.setText(stringEncryptString.getRandomKeyString());
				textFieldKeyForEncryptingString.setEditable(false);
			} else {
				textFieldKeyForEncryptingString.setText("");
				textFieldKeyForEncryptingString.setEditable(true);
			}
		});
		// EventHandler button Decyrpt
		buttonDecryptString.setOnAction((ActionEvent event) -> {
			Boolean isSelectedEncryptString = generateKeyForString.isSelected();
			if (isSelectedEncryptString == true) {
				textFieldDecryptedString.setText(MiniAES.DecryptText(textFieldDecryptString.getText(),
						textFieldKeyForDecryptingString.getText()));
			} else {
				textFieldDecryptedString.setText(MiniAES.DecryptText(textFieldDecryptString.getText(),
						textFieldKeyForDecryptingString.getText()));
			}
		});

		/* String Tab Children End */

		/* File Tab Children */

		/* Image Child */
//        Image imageKeyTabFile = new Image(new FileInputStream("C:\\Users\\icowemaina\\Documents\\NetBeansProjects\\MiniAES\\pictures\\img.png"));
//        ImageView imageViewFile = new ImageView(imageKeyTabFile);
//        imageViewFile.setTranslateX(0);
//        imageViewFile.setTranslateY(-160);

		/* Label Children */
		// Header Label
		Label labelFile = new Label("File");
		labelFile.setTranslateY(-100);
		labelFile.setTranslateX(-15);
		labelFile.setFont(Font.font("Elephant", FontWeight.BOLD, 24));
		// Label that says Random
		Label randomFile = new Label("Random");
		randomFile.setTranslateY(100);
		randomFile.setTranslateX(-1);
		randomFile.setFont(Font.font("Verdana", 12));
		// Label that says Key
		Label keyFile = new Label("key");
		keyFile.setTranslateY(110);
		keyFile.setTranslateX(-1);
		keyFile.setFont(Font.font("Verdana", 12));

		/* Button Children */

		// Button Choose File
		Button buttonChooseFile = new Button("...");
		buttonChooseFile.setPrefSize(40, 40);
		buttonChooseFile.setTranslateX(230);
		buttonChooseFile.setTranslateY(-20);
		buttonChooseFile.setFont(Font.font("Verdana", 12));
		// Button Encrypt File
		Button buttonEncryptFile = new Button("Encrypt");
		buttonEncryptFile.setPrefSize(80, 40);
		buttonEncryptFile.setTranslateX(-105);
		buttonEncryptFile.setTranslateY(130);
		buttonEncryptFile.setFont(Font.font("Verdana", 12));
		// Button Decrypt File
		Button buttonDecryptFile = new Button("Decrypt");
		buttonDecryptFile.setPrefSize(80, 40);
		buttonDecryptFile.setTranslateX(105);
		buttonDecryptFile.setTranslateY(130);
		buttonDecryptFile.setFont(Font.font("Verdana", 12));

		/* TextField Children */

		// TextField for File Path
		TextField textFieldFilePath = new TextField();
		textFieldFilePath.setPromptText("File path");
		textFieldFilePath.setPrefSize(100, 40);
		textFieldFilePath.setMaxWidth(460);
		textFieldFilePath.setTranslateX(-20);
		textFieldFilePath.setTranslateY(-20);
		textFieldFilePath.setFont(Font.font("Verdana", 12));
		// Field for the Key for Encryption
		TextField textFieldKeyForEncryptionFile = new TextField();
		textFieldKeyForEncryptionFile.setPromptText("Enter 16-bit Encryption Key");
		textFieldKeyForEncryptionFile.setPrefSize(100, 40);
		textFieldKeyForEncryptionFile.setMaxWidth(245);
		textFieldKeyForEncryptionFile.setTranslateX(0);
		textFieldKeyForEncryptionFile.setTranslateY(40);
		textFieldKeyForEncryptionFile.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		/* CheckBox Child */

		// CheckBox
		CheckBox generateEncryptKeyFile = new CheckBox();
		generateEncryptKeyFile.setTranslateX(0);
		generateEncryptKeyFile.setTranslateY(80);

		/* Restrictions */

		// 19 character restriction for the key textfield
		Pattern patternFileStringEncrypt = Pattern.compile(".{0,19}");
		TextFormatter formatterFileStringEncrypt = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return patternFileStringEncrypt.matcher(change.getControlNewText()).matches() ? change : null;
		});
		textFieldKeyForEncryptionFile.setTextFormatter(formatterFileStringEncrypt);

		/* Event Handlers */

		// Event Handler Checkbox
		generateEncryptKeyFile.setOnAction((ActionEvent event) -> {
			Boolean isSelectedEncryptFile = generateEncryptKeyFile.isSelected();
			if (isSelectedEncryptFile == true) {
				RandomKey stringEncryptFile = new RandomKey();
				textFieldKeyForEncryptionFile.setText(stringEncryptFile.getRandomKeyString());
				textFieldKeyForEncryptionFile.setEditable(false);
			} else {
				textFieldKeyForEncryptionFile.setText("");
				textFieldKeyForEncryptionFile.setEditable(true);
			}
		});

		// File Chooser
		FileChooser fileChooser = new FileChooser();
		buttonChooseFile.setOnAction((ActionEvent event) -> {
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

			if (selectedFile != null) {
				fileName = selectedFile.getAbsolutePath();
				textFieldFilePath.setText(fileName);
				// System.out.println(selectedFile.getAbsolutePath());
			}
		});

		// Encrypt Button
		buttonEncryptFile.setOnAction((ActionEvent event) -> {
			try {
				encryptFile(fileName, textFieldKeyForEncryptionFile.getText());
			} catch (IOException ex) {
				Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

		// Decrypt Button
		buttonDecryptFile.setOnAction((ActionEvent event) -> {
			try {
				decryptFile(fileName, textFieldKeyForEncryptionFile.getText());
			} catch (IOException ex) {
				Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

		textFieldFilePath.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					fileName = newValue;
				});

		/* File Tab Children End */

		/* Build Scene */

		BorderPane borderPane = new BorderPane();
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);
		TabPane tab = new TabPane();
		primaryStage.setScene(scene);
		primaryStage.show();

		/* 16-bit key Tab */

		StackPane layoutKey = new StackPane();
		Tab keyTab = new Tab("16-bit Key");
		keyTab.setClosable(false);
		layoutKey.getChildren().addAll(buttonDecrypt, buttonEncrypt, textField16bitString,
				textField16bitStringDecryption, generateEncryptKey, textFieldEncrypted, textFieldDecrypted,
				textFieldKeyForDecryption, textFieldKeyForEncryption, labelRandom, labelKey, labelHeader,
				labelEncryption, labelDecryption);
		layoutKey.setAlignment(Pos.CENTER);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		keyTab.setContent(layoutKey);
		tab.getTabs().add(keyTab);
		borderPane.setCenter(tab);
		root.getChildren().add(borderPane);

		/* String Tab */

		StackPane layoutString = new StackPane();
		Tab stringTab = new Tab("String");
		stringTab.setClosable(false);
		layoutString.getChildren().addAll(buttonDecryptString, buttonEncryptString, textFieldString,
				textFieldDecryptString, generateKeyForString, textFieldEncryptedString, textFieldDecryptedString,
				textFieldKeyForDecryptingString, textFieldKeyForEncryptingString, labelRandomStringTab,
				labelKeyStringTab, labelString);
		layoutString.setAlignment(Pos.CENTER);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		stringTab.setContent(layoutString);
		tab.getTabs().add(stringTab);

		/* File Tab */

		StackPane layoutFile = new StackPane();
		Tab fileTab = new Tab("File");
		fileTab.setClosable(false);
		layoutFile.getChildren().addAll(buttonChooseFile, textFieldFilePath, labelFile, buttonEncryptFile,
				textFieldKeyForEncryptionFile, buttonDecryptFile, generateEncryptKeyFile, randomFile, keyFile);
		layoutFile.setAlignment(Pos.CENTER);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		fileTab.setContent(layoutFile);
		tab.getTabs().add(fileTab);

		/* Build Scene End */
	}

	// Function for reading a file line by line and encyrpting each line

	public static void encryptFile(String fileName, String key) throws IOException {
		try {
			File file = new File(fileName);
			File encryptedFile = new File("Encrypted File.txt");
			FileOutputStream fos = new FileOutputStream(encryptedFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			encryptedFile.createNewFile();

			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					// System.out.println(MiniAES.EncryptText(scanner.nextLine(), key));
					bw.write(MiniAES.EncryptText(scanner.nextLine(), key));
					bw.newLine();
				}

				bw.close();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("File encrypted successfuly.");

				alert.showAndWait();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	// Function for reading a file line by line and decyrpting each line

	public static void decryptFile(String fileName, String key) throws IOException {
		String nextLine;
		try {
			File file = new File(fileName);
			File decryptedFile = new File("Decrypted File.txt");
			FileOutputStream fos = new FileOutputStream(decryptedFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			decryptedFile.createNewFile();

			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {

					nextLine = scanner.nextLine();

					if (!nextLine.equals("")) {
						bw.write(MiniAES.DecryptText(nextLine, key));
					}
					bw.newLine();
				}

				bw.close();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("File decrypted successfuly.");

				alert.showAndWait();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

}
