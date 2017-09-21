package org.revocen.util.common.file;

import java.io.File;
import java.util.List;

public class FileInfo {

	private File file;
	private List<FileInfo> fileInfos;

	public File getFile(){
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
	public boolean isLeaf(){
		return file.isFile();
	}
	
	public boolean isEmpty(){
		return (fileInfos == null) || fileInfos.size() <= 0;
	}

}
