package org.ms.servlet;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ms.entities.Files;
import org.ms.hibernate.DAO.FilesDAO;

@WebServlet("/FilesHandler")
public class FilesHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String path = "c:/images/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "filesUpload":
			filesUpload(request, response);
			break;
		
		case "updateInformation":
			updateInformation(request, response);
			break;

		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "listImages":
			listImages(request, response);
			break;

		case "viewImage":
			viewImage(request, response);
			break;
		
		case "deleteImage":
			deleteImage(request, response);
			break;


			
			
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
	}
	
	
	private void deleteImage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int fileId =Integer.parseInt(request.getParameter("fileId"));
		Files file = new FilesDAO().getFile(fileId);
		new FilesDAO().deleteFile(fileId);
		// logic for file deletion from filesystem
		
		File fileOnDisk = new File (path+file.getFileName());
		if(fileOnDisk.delete()) {
			System.out.println("File got deleted from filesystem");
		}
		else {
			System.out.println("File not deleted from filesystem");
		}
		listImages(request, response);
	}


	private void viewImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fileId= Integer.parseInt(request.getParameter("fileId"));
		Files file = new FilesDAO().getFile(fileId);
		request.setAttribute("file", file);
		request.setAttribute("path", path);
		request.getRequestDispatcher("viewImage.jsp").forward(request, response);
		
	}


	private void updateInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		String fileName = request.getParameter("fileName");
		String label = request.getParameter("label");
		String caption = request.getParameter("caption");
		Files file = new Files(fileId,fileName,label,caption);
		new FilesDAO().updateInformation(fileId, label,caption);
		listImages(request, response);
		
	}

	
	
	private void listImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Files> files = new FilesDAO().listFiles();
		request.setAttribute("files", files);
		request.setAttribute("path", path);
		request.getRequestDispatcher("listFiles.jsp").forward(request, response);
	}




	public void filesUpload (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
	try {
		List<FileItem> images = upload.parseRequest(request);
		for(FileItem image:images) {
			String name= image.getName();
			try {name=name.substring(name.lastIndexOf("\\")+1);} catch (Exception ex) {}
			File file =new File(path+name);
			if (!file.exists()) {
				new FilesDAO().addFileDetails(new Files (name));
				image.write(file);
			}
			
			

		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}listImages(request, response);
	}

}
