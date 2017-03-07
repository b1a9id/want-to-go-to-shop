package com.uchitate.web.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class FileUploadController {

	@GetMapping("/upload")
	public String index() {
		return "shop/upload";
	}


	@PostMapping(value = "/upload")
	public void handle(HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new Exception();
		}

		try {
			BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file.getOriginalFilename()));
			FileCopyUtils.copy(inputStream, outputStream);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}
