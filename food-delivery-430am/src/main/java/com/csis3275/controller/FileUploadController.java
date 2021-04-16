package com.csis3275.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csis3275.model.FileInfo;
import com.csis3275.model.OrderMenu;
import com.csis3275.service.FileUploadService;

@Controller
public class FileUploadController {
	@Autowired
	ServletContext context;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@ModelAttribute("file")
	public FileInfo setUpAdd() {
		return new FileInfo();
	}
	
	@GetMapping("/uploadForm")
	public String fileUploadForm() {
		return "/upload";
	}
	
	/*
	@PostMapping("/upload")
	public String fileUpload(@RequestParam("file") List<MultipartFile> files, HttpSession session, Model model, HttpServletRequest request) {
		session = request.getSession();
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo> ();
        if (!files.isEmpty()) {
            try {
                for (MultipartFile file: files) {
                    String path = context.getRealPath("/WEB-INF/uploaded") + File.separator +
                        file.getOriginalFilename();
                    File destinationFile = new File(path);
                    file.transferTo(destinationFile);
                    uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
                }
 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
 
        }
        //ModelAndView modelAndView = new ModelAndView("success");
        model.addAttribute("files", uploadedFiles);
        return "/uploadSuccess";
	}*/
	
	@PostMapping("/upload")
	public String fileUpload(Model model, @RequestParam("file1") MultipartFile file) {
		String url = fileUploadService.restore(file);
		model.addAttribute("url", url);
		return "/uploadSuccess";
	}
	
	/*
	@PostMapping("/uploadMenuImg")
	public String menuImgUpload(Model model, @RequestParam("file1") MultipartFile file) {
		String url = fileUploadService.restore(file);
		model.addAttribute("url", url);
		return url;
	}*/
	
	
}
