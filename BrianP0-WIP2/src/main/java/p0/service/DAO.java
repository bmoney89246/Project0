package p0.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import p0.pojos.User;
import p0.util.ConnectionFactory;

public class DAO implements DAOpersistable {
	private Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	private  Logger log = Logger.getRootLogger();

	public  boolean loginDao(User user) {
		String sql = "insert into logincredentials (username, password) values ('"+ user.getUsername()+"', '"+user.getPassword()+"');";
		
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			return false;
		}
		return true;
//		try {
//			FileOutputStream fos = new FileOutputStream(file);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			oos.writeObject(writeString);
//			fos.close();
//			oos.close();
//		} catch (Exception e) {
//			log.error(e);
//			return false;
//		}
//		return true;
	}

	public  String readFromFile(File file) {
		try {
			String result;
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			result = (String) ois.readObject();
			fis.close();
			ois.close();
			return result;
		} catch (Exception e) {
			log.error(e);
		}
		return "";
	}

	public  boolean fileExists(File file) {
		if (!file.exists()) {
			return false;
		}
		return true;
	}

	public  boolean createFile(File file) {
		try {
			file.createNewFile();
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}

	public  boolean deleteFile(File file) {
		try {
			file.delete();
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}

	public  boolean copyFile(File file, File dest) {
		try {
			Files.copy(file.toPath(), dest.toPath());
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}

	public  boolean iterateAndDeleteFiles(File directory, String fileName) {
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
			return false;
		}
		return true;
	}

	@Override
	public boolean loginDao(File file) {
		// TODO Auto-generated method stub
		return false;
	}
}
