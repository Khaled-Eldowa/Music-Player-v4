package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import app.Backend;
import componentsV2.User;
import mainApp.MainMenu;
import security.DigestManager;

public class SerializationManager {
	
	/**
	 * Serialize the users in a file called users.ser
	 */
	public static void serializeUsers() {
		//try with resources, it closes the stream automatically
		try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
			objOut.writeObject(Backend.users); //write the whole users list on one go
			MainMenu.logMessage("users.ser was written successfully in the directory " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			System.out.println("users.ser was written successfully in the directory " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
		} catch (IOException e) {
			e.printStackTrace();
			MainMenu.logMessage("IO error encountered while trying to serialize the users");
			System.out.println("IO error encountered while trying to serialize the users");
		}
	}
	
	/**
	 * Deserialize the users only if the md5 test is passed
	 */
	public static void deserilaizeAndCheckMD5() {
		//try with resources, it closes the stream automatically
		if(DigestManager.checkUsersMD5() == false)
			return; //MD5 mismatch or an error message would have displayed to the user by now
		//MD5 match success message would have been displayed to the user by now
		try(FileInputStream fis = new FileInputStream("users.ser"); ObjectInputStream objIn = new ObjectInputStream(fis)) {
			ArrayList<User> readList = (ArrayList<User>) objIn.readObject();
			int count = 0;
			User tempUser;
			for(User readUser: readList) {
				if((tempUser = Backend.getUser(readUser.getId()))!=null)
					Backend.users.remove(tempUser);
				Backend.users.add(readUser);	
				count++;
			}
			MainMenu.logMessage(count + " users were derserialized successfully, users were overwritten if they were already loaded");
			System.out.println(count + " users were derserialized successfully, users were overwritten if they were already loaded");
		} catch (IOException e) {
			e.printStackTrace();
			MainMenu.logMessage("could not deserialize the users, failed to get users.ser");
			System.out.println("could not deserialize the users, failed to get users.ser");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			MainMenu.logMessage("could not deserialize the users, object conversion failed or class not found");
			System.out.println("could not deserialize the users, object conversion failed or class not found");
		}
	}

}
