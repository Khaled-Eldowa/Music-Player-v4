package security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import mainApp.MainMenu;

public class DigestManager {

	/**
	 * Read the newly created users.ser, generate its MD5, and store it in usersMD5.txt
	 */
	public static void generateAndStoreMD5() {
		//get the users file
		File newUsersFile = new File("users.ser");
		if(!newUsersFile.exists()) {
			MainMenu.logMessage("Writing the MD5 file failed; users.ser was not found in " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			System.out.println("Writing the MD5 file failed; users.ser was not found in " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			return;
		}
		
		//generate the MD5 and store it
		try {
			byte[] usersFileContent = Files.readAllBytes(newUsersFile.toPath()); //closes resources automatically
			MessageDigest md5Generator = MessageDigest.getInstance("MD5");
			md5Generator.reset();
			md5Generator.update(usersFileContent);
			byte[] md5 = md5Generator.digest();
			//nested try catch to be able to specify the exact IO error
			try(FileOutputStream fileOutputStream = new FileOutputStream("usersMD5.txt")) {
				fileOutputStream.write(md5);
				MainMenu.logMessage("usersMD5.txt was written successfully in the directory " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
				System.out.println("usersMD5.txt was written successfully in the directory " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			} catch (IOException e) {
				e.printStackTrace();
				MainMenu.logMessage("Writing the MD5 file failed; could not store usersMD5.txt");
				System.out.println("Writing the MD5 file failed; could not store usersMD5.txt");
			}
		} catch (IOException e) {
			e.printStackTrace();
			MainMenu.logMessage("Writing the MD5 file failed; could not load users.ser");
			System.out.println("Writing the MD5 file failed; could not load users.ser");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			MainMenu.logMessage("Writing the MD5 file failed; no such algorithm excpetion");
			System.out.println("Writing the MD5 file failed; no such algorithm excpetion");
		}
	}
	
	//read users.ser, generate its MD5, read usersMD5.txt, and check if it matches the newly generated MD5
	public static boolean checkUsersMD5() {
		
		//load users.ser to a byte array:
		File usersFile = new File("users.ser");
		if(!usersFile.exists()) {
			MainMenu.logMessage("Checking the MD5 file failed; users.ser was not found in " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			System.out.println("Checking the MD5 file failed; users.ser was not found in " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			return false;
		}
		byte[] usersFileContent;
		try {
			usersFileContent = Files.readAllBytes(usersFile.toPath()); //closes resources automatically
		} catch (IOException e) {
			e.printStackTrace();
			MainMenu.logMessage("Checking the MD5 file failed; could not load users.ser");
			System.out.println("Checking the MD5 file failed; could not load users.ser");
			return false;
		}
		
		//load usersMD5 to a byte array:
		File oldMD5File = new File("usersMD5.txt");
		if(!oldMD5File.exists()) {
			MainMenu.logMessage("Checking the MD5 file failed; usersMD5.txt was not found in " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			System.out.println("Checking the MD5 file failed; usersMD5.txt was not found in " + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
			return false;
		}
		byte[] oldMD5;
		try {
			oldMD5 = Files.readAllBytes(oldMD5File.toPath()); //closes resources automatically
		} catch (IOException e) {
			e.printStackTrace();
			MainMenu.logMessage("Checking the MD5 file failed; could not load usersMD5.txt");
			System.out.println("Checking the MD5 file failed; could not load usersMD5.txt");
			return false;
		}
		
		//generate the MD5 for the fetched users and compare it with usersMD5
		MessageDigest md5Generator;
		try {
			md5Generator = MessageDigest.getInstance("MD5");
			md5Generator.reset();
			md5Generator.update(usersFileContent);
			byte[] newMD5 = md5Generator.digest();
			if(Arrays.equals(oldMD5, newMD5)) {
				MainMenu.logMessage("MD5 check was passed successfully");
				System.out.println("MD5 check was passed successfully");
				return true;
			}
			else {
				MainMenu.logMessage("MD5 did not match; the users file has been TAMPERED WITH!!");
				System.out.println("MD5 did not match; the users file has been TAMPERED WITH!!");
				return false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			MainMenu.logMessage("Checking the MD5 file failed; no such algorithm excpetion");
			System.out.println("Checking the MD5 file failed; no such algorithm excpetion");
			return false;
		}
		
		
	}
	
}
