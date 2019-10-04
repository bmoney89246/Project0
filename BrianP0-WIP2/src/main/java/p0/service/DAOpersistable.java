package p0.service;

import java.io.File;


public interface DAOpersistable {

	public  boolean writeToFile(File file, String writeString);

	public String readFromFile(File file);

	public boolean fileExists(File file);

	public boolean createFile(File file);

	public boolean deleteFile(File file);

	public boolean copyFile(File file, File dest);

	public boolean iterateAndDeleteFiles(File directory, String fileName);
}
