package p0.pojos;

import java.io.File;

public class FileInfo {
	private  File file;
	private  File file2;

	public  void setFile(String fileName) {
		this.file = new File(fileName);
	}

	public  File getFile() {
		return file;
	}

	public  void setFile2(String fileName) {
		this.file2 = new File(fileName);
	}

	public  File getFile2() {
		return file2;
	}
}
