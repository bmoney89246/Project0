package p0.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import org.apache.log4j.Logger;

public class DAO {
	private static Logger log = Logger.getRootLogger();

	public static boolean writeToFile(File file, String writeString) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(writeString);
			fos.close();
			oos.close();
		} catch (Exception e) {
			log.error(e);
			System.out.println("Error writing to file.");
			return false;
		}
		return true;
	}

	public static String readFromFile(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			fis.close();
			ois.close();
			return ois.toString();
		} catch (Exception e) {
			log.error(e);
			System.out.println("Error reading from file.");
		}
		return "";
	}

	public static boolean fileExists(File file) {
		if (!file.exists()) {
			return false;
		}
		return false;
	}

	public static boolean createFile(File file) {
		try {
			file.createNewFile();
		} catch (Exception e) {
			log.error(e);
			System.out.println("Error creating file");
			return false;
		}
		return true;
	}

	public static boolean deleteFile(File file) {
		try {
			file.delete();
		} catch (Exception e) {
			log.error(e);
			System.out.println("Error deleting file");
			return false;
		}
		return true;
	}

	public static boolean copyFile(File file, File dest) {
		try {
			Files.copy(file.toPath(), dest.toPath());
		} catch (Exception e) {
			log.error(e);
			System.out.println("Error copying file");
			return false;
		}
		return true;
	}

	public static boolean iterateAndDeleteFiles(File directory, String fileName) {
		try {
			File[] directoryListing = directory.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().contains(fileName)) {
						child.delete();
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
			System.out.println("Error deleting files");
			return false;
		}
		return true;
	}
}
