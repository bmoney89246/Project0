package p0.pojos;

import java.io.File;

public class FileInfo {
	private static File file;
	private static File file2;
	
	public static void setFile(String fileName) {
		FileInfo.file = new File(fileName);
	}

	public static File getFile() {
		return FileInfo.file;
	}
	public static void setFile2(String fileName) {
		FileInfo.file2 = new File(fileName);
	}

	public static File getFile2() {
		return FileInfo.file2;
	}
}
