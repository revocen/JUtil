package org.revocen.util.common.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * 遍历文件路径下所有文件
	 * @param path
	 * @return
	 */
	public static List<FileInfo> getFileList(String path) {
		List<FileInfo> lst = new ArrayList<FileInfo>();
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
			} else {
				for (File f : files) {
					FileInfo info = new FileInfo();
					info.setFile(f);
					if (f.isDirectory()) {
						info.setFileInfos(getFileList(f.getAbsolutePath()));
					}
					lst.add(info);
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
		return lst;
	}
	
	public static void main(String[] args) {
		List<FileInfo> lst = getFileList("E:\\temp");
		System.out.println(lst.size());
	}
}
