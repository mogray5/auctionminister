/*
 * Copyright (c) 2013 Wayne Gray All rights reserved
 * 
 * This file is part of Auction Minister.
 * 
 * Auction Minister is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Auction Minister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Auction Minister.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.auctionminister.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;

/**
 * @author wggray
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AmFileHandler {

	/**
	 *  
	 */
	public AmFileHandler() {
		super();
	}

	private BufferedReader in = null;

	private PrintWriter out = null;

	private boolean bOpenMode = false;

	private boolean bNewMode = false;

	public void FileOpenRead(String sPath) throws FileNotFoundException {
		if (!bNewMode && !bOpenMode) {
			in = new BufferedReader(new FileReader(sPath));
		}
	}

	public void FileOpenWrite(String sFile) throws IOException {
		if (!bNewMode && !bOpenMode) {
			out = new PrintWriter(new BufferedWriter(new FileWriter(sFile)));
		}
	}

	public void FileClose() throws IOException {
		if (in != null) {
			in.close();
			in = null;
		}

		if (out != null) {
			out.close();
			out = null;
		}
	}

	public File[] getFileList(String sDir, String sFilter) {

		//final String sF = new String(sFilter);
		final String sF = sFilter;

		FileFilter ff = new FileFilter() {

			public boolean accept(File file) {
				if (sF == "") {
					return true;
				} else if (file.getName().endsWith(sF)) {
					return true;
				} else {
					return false;
				}
			}
		};

		File fDir = new File(sDir);
		File[] resultList = fDir.listFiles(ff);
		return resultList;
	}

	public String LineInput() throws IOException {
		if (in != null) {
			return in.readLine();
		} else {
			return null;
		}
	}

	public String getFileAsString()throws IOException{
		return IOUtils.toString(in);
	}
	
	public void FileNew(String sFile) throws IOException {
		if (!bNewMode && !bOpenMode) {
			out = new PrintWriter(new BufferedWriter(new FileWriter(sFile)));
		}
	}

	public void WriteLine(String sLine) {
		out.println(sLine);
	}

	public void copyFile(String source_name, String dest_name)
			throws IOException {

		File source_file = new File(source_name);
		File destination_file = new File(dest_name);
		FileInputStream source = null;
		FileOutputStream destination = null;
		byte[] buffer;
		int bytes_read;

		try {
			// First make sure the specified source file
			// exists, is a file, and is readable.
			if (!source_file.exists() || !source_file.isFile())
				throw new FileCopyException("File does not exist.");
			if (!source_file.canRead())
				throw new FileCopyException("Can not read file:" +  
						source_name);

			// If the destination exists, make sure it is a writeable file
			// and ask before overwriting it. If the destination doesn't
			// exist, make sure the directory exists and is writeable.
			if (destination_file.exists()) {
				if (destination_file.isFile()) {
					DataInputStream in = new DataInputStream(System.in);
					String response;

					if (!destination_file.canWrite())
						throw new FileCopyException("Can not write to directory:" +
							dest_name);

					response = in.readLine();
					if (!response.equals("Y") && !response.equals("y"))
						throw new FileCopyException("error");
				} else
					throw new FileCopyException("File does not exist: "
							+ dest_name);
			} else {
				File parentdir = parent(destination_file);
				if (!parentdir.exists())
					throw new FileCopyException("Parent dir does not exist"
							+ dest_name);
				if (!parentdir.canWrite())
					throw new FileCopyException("Unable to write to directory.  Possible permissions problem: " +
							dest_name);
			}

			// If we've gotten this far, then everything is okay; we can
			// copy the file.
			source = new FileInputStream(source_file);
			destination = new FileOutputStream(destination_file);
			buffer = new byte[1024];
			while (true) {
				bytes_read = source.read(buffer);
				if (bytes_read == -1)
					break;
				destination.write(buffer, 0, bytes_read);
			}
		}

		// No matter what happens, always close any streams we've opened.
		finally {
			if (source != null)
				try {
					source.close();
				} catch (IOException e) {
					;
				}

			if (destination != null)
				try {
					destination.close();
				} catch (IOException e) {
					;
				}
		}
	}

	// File.getParent() can return null when the file is specified without
	// a directory or is in the root directory.
	// This method handles those cases.
	private static File parent(File f) {
		String dirname = f.getParent();
		if (dirname == null) {
			if (f.isAbsolute())
				return new File(File.separator);
			else
				return new File(System.getProperty("user.dir"));
		}
		return new File(dirname);
	}

	//	***
	public void copyFile2(String src, String dest) throws IOException {
		//String newLine = System.getProperty("line.separator");
		FileWriter fw = null;
		FileReader fr = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		File source = null;

		try {
			fr = new FileReader(src);
			fw = new FileWriter(dest);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);

			/* Determine the size of the buffer to allocate */
			source = new File(src);

			int fileLength = (int) source.length();

			char charBuff[] = new char[fileLength];

			while (br.read(charBuff, 0, fileLength) != -1)
				bw.write(charBuff, 0, fileLength);
		} catch (FileNotFoundException fnfe) {
			throw new FileCopyException(src + " " + "File not found");
		} catch (IOException ioe) {
			throw new FileCopyException(ioe.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();

				if (bw != null)
					bw.close();
			} catch (IOException ioe) {
			}
		}
	}

	class FileCopyException extends IOException {
		public FileCopyException(String msg) {
			super(msg);
		}
	}

}