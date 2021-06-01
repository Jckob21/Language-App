package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.ComboBox;

public class AddPanelController implements Initializable{
	ObservableList<String> langs = FXCollections.observableArrayList("Polish", "Spanish", "English");
	
	
	@FXML
	private TextField textWord1;
	@FXML
	private TextField textWord2;
	@FXML
	private ComboBox<String> comboLang1;
	@FXML
	private ComboBox<String> comboLang2;
	@FXML
	private ComboBox<String> comboPackage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboLang1.setItems(langs);
		comboLang2.setItems(langs);
		//TODO comboLang2 has to be set to main language and disabled
		
	}
	
	public void loadPackages(ActionEvent event)
	{
		File languageFolder = new File("src/dataset/" + comboLang1.getValue().toLowerCase());
		// CHECKS IF THE FOLDER IS FOUND FOR DEBUGGING
		/*if(languageFolder.exists())
		{
			System.out.println("Folder found!");
		} else
		{
			System.out.println("Not found!");
		}
		System.out.println("Absolute path: " + languageFolder.getAbsolutePath());*/
		
		ObservableList<String> fileNames
				= FXCollections.observableArrayList(languageFolder.list());
		
		comboPackage.setItems(fileNames);
	}

	/* Uses language1 comboBox and package folder to get the file.
	 * Appends that file with a new word.
	 * TODO if package combobox not selected, put the word in 'general.txt' file
	 * TODO Add a way to create new package
	 */
	public void addWord(ActionEvent event)
	{
		// debug message
		System.out.printf("Word added: %s - %s, Language: %s to %s", textWord1.getText(),
				textWord2.getText(), comboLang1.getValue(), comboLang2.getValue());
		
		// Open file of the path: project_folder_abspath/src/dataset/languageChosen/packageChosen
		File packageFile = new File("src/dataset/" + comboLang1.getValue().toLowerCase() + "/" + comboPackage.getValue());
		// Write word to the file in format: word-translation
		try {
			FileWriter fileWriter = new FileWriter(packageFile, true);
			PrintWriter packageWriter = new PrintWriter(fileWriter);
			packageWriter.println(textWord1.getText() + "-" + textWord2.getText());
			packageWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
