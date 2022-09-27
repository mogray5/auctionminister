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
package com.auctionminister.action.uploads;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UploadData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.util.AmFileHandler;

/**
 * @author wggray
 */
public class CommaUploadAction extends BaseAction {

	private static final long serialVersionUID = 2560316064313842641L;
	private List lstUploadData = null;
	private String sIsSecure = "S";
	private String sStatus = "";
	private Long lBatchId = null;

	public CommaUploadAction() {
		super();
	}
	public String execute() throws Exception {

		AmFileHandler filehandler = new AmFileHandler();
		String[] aLine = null;
		this.startSession();
		
		try {

			UserSmallData oUserID = this.verifyLogin();

			sStatus = "uploading file\n";
			File f = UploadFile();

			sStatus = "opening file\n";

			filehandler.FileOpenRead(f.getPath());

			String sLine = filehandler.LineInput(); // eat header line

			UploadData dat = null;
			sStatus = "Getting new batch\n";
			LineParser lineParser = new LineParser();
			lBatchId = lineParser.getNewBatchId(session, oUserID.getUserId());
			sStatus = "done Getting new batch\n";
			if (lBatchId == null) {
				addActionError("Error creating upload batch ID.");
				filehandler.FileClose();
				return INPUT;
			}

			sStatus = "starting data loop\n";
			// skip first line
			sLine = filehandler.LineInput();
			
			while (sLine != null) {

				dat = lineParser.ParseLine(sLine, oUserID.getUserId(), lBatchId.longValue());
								
				session.update("AddPaypalTran", dat);
				session.commit();

				sLine = filehandler.LineInput();

			}
			sStatus = "end data loop\n";
			filehandler.FileClose();

			session.update("MarkDuplicateItems", null);
			session.commit();
			
			lstUploadData = session.selectList("GetBatchList", lBatchId);

		} catch (Exception e) {
			addActionError("Error uploading file:  " + sStatus + "  "
					+ e.toString() + "  " + aLine.length);
			if (filehandler != null) {
				filehandler.FileClose();
			}
			return INPUT;
		} finally {
			this.endSession();
		}

		return SUCCESS;
	}

	private File UploadFile() {

		File file = null;

		// start of upload file code
		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext
				.getRequest();

		// check for errors
		if (multiWrapper.hasErrors()) {
			Collection errors = multiWrapper.getErrors();
			Iterator i = errors.iterator();
			while (i.hasNext()) {
				addActionError((String) i.next());
			}
			return null;
		}

		// perform upload
/*
		Enumeration e = multiWrapper.getFileNames();

		while (e.hasMoreElements()) {
			// get the value of this input tag
			String inputValue = (String) e.nextElement();

			// get the content type
			String contentType = multiWrapper.getContentType(inputValue);

			// get the name of the file from the input tag
			String fileName = multiWrapper.getFilesystemName(inputValue);

			// Get a File object for the uploaded File
			file = multiWrapper.getFile(inputValue);

			// If it's null the upload failed
			if (file == null) {
				addActionError("Error uploading: "
						+ multiWrapper.getFilesystemName(inputValue));
			}
		}
*/
		return file;
	}

	private String Clean(String sVal) {
		String sTmp = sVal.replaceAll("\"", "");

		if (sTmp.startsWith("\"")) {
			sTmp = sTmp.substring(1);
		}

		return sTmp;
	}

	

	/**
	 * @return Returns the lstUploadData.
	 */
	public List getUploadData() {
		return lstUploadData;
	}

	/**
	 * @param lstUploadData
	 *            The lstUploadData to set.
	 */
	public void setUploadData(List lstUploadData) {
		this.lstUploadData = lstUploadData;
	}

	/**
	 * @return Returns the sIsSecure.
	 */
	public String getIsSecure() {
		return sIsSecure;
	}

	/**
	 * @param isSecure
	 *            The sIsSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		sIsSecure = isSecure;
	}

	/**
	 * @return Returns the sStatus.
	 */
	public String getStatus() {
		return sStatus;
	}

	/**
	 * @param status
	 *            The sStatus to set.
	 */
	public void setStatus(String status) {
		sStatus = status;
	}

	/**
	 * @return Returns the lBatchId.
	 */
	public Long getBatchId() {
		return lBatchId;
	}

	/**
	 * @param batchId
	 *            The lBatchId to set.
	 */
	public void setBatchId(Long batchId) {
		lBatchId = batchId;
	}

	private String ParseItem(String sVal) {

		String sResult = null;

		int i = sVal.toLowerCase().indexOf("[[am:");
		int j = sVal.indexOf("]]");

		if (i > 0 && j > 0) {

			String sTmp = sVal;

			while (j < i && j < sVal.length()) {
				j = sVal.indexOf("]]", j + 1);
			}

			sResult = sVal.substring(i + 5, j);
		}

		return sResult;
	}
}
