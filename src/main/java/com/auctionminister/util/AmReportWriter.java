
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

import java.util.Map;

import com.auctionminister.exceptions.AmReportException;

/*
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auctionminister.db.SimpleDataSourceFactory;
import com.ibatis.common.resources.Resources;
import org.apache.struts2.ServletActionContext;

import dori.jasper.engine.JRAbstractExporter;
import dori.jasper.engine.JasperExportManager;
import dori.jasper.engine.JasperManager;
import dori.jasper.engine.JasperPrint;
import dori.jasper.engine.JasperReport;
*/
/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AmReportWriter {

	/**
	 * 
	 */
	public AmReportWriter() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void RenderReport(Map parameters, String pdfName, String report) throws AmReportException{

		/*
		ServletOutputStream outputStream = null;
		InputStream in = null;
		SimpleDataSourceFactory dataSource = null;

		try {
		
			//set headers to disable caching
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
		
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.setContentType("application/pdf");
			response.setHeader(
					"Content-disposition",
					"inline; filename=" + pdfName);
		
			String resource =report;
			in = Resources.getResourceAsStream(resource);
		
			JasperReport jasperReport = JasperManager.loadReport(in);

			//connection
			dataSource = new SimpleDataSourceFactory();
			Connection conn = dataSource.getConnection();

			//fill report
			JasperPrint jasperPrint = JasperManager.fillReport(jasperReport,
				   parameters, conn);
		
			byte[] pdfByteArray = JasperExportManager.exportReportToPdf(jasperPrint);

			response.setContentLength(pdfByteArray.length);

			outputStream = response.getOutputStream();
		
			outputStream.write(pdfByteArray, 0, pdfByteArray.length);
			outputStream.flush();

			outputStream.close();
			in.close();
			dataSource.Close();
		
		} catch (Exception e){
			
			throw new AmReportException("Error rendering report: " + e.getMessage());
		
		} finally {
			try{outputStream.close();} catch (Exception e){}
			try{in.close();} catch (Exception f){}
			try{dataSource.Close();} catch (Exception g){}
		}

	*/	
	}
	
}
