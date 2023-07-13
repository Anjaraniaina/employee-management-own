package com.learn.demo.service;

import com.learn.demo.model.Employee;
import com.learn.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public Employee getById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        } else {
            throw new RuntimeException();
        }
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public List<Employee> createOrUpdateEmployees(List<Employee> toUpdate){
        return employeeRepository.saveAll(toUpdate);
    }

    public String imageFileToBase64(MultipartFile file) throws IOException {
        log.error("It passed HERE HEY");
        System.out.println(Base64.getEncoder().encodeToString(resizeAndCompress(file, 100)));
        return Base64.getEncoder().encodeToString(resizeAndCompress(file, 100));
    }

    public static byte[] resizeAndCompress(MultipartFile file, int maxSize) throws IOException {

        BufferedImage originalImage = ImageIO.read(file.getInputStream());

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int newWidth, newHeight;

        if (originalWidth > originalHeight) {
            newWidth = maxSize;
            newHeight = (int) Math.round(originalHeight * (maxSize / (double) originalWidth));
        } else {
            newHeight = maxSize;
            newWidth = (int) Math.round(originalWidth * (maxSize / (double) originalHeight));
        }

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpeg").next();
        ImageWriteParam imageWriteParam = new JPEGImageWriteParam(null);
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(0.5f); // Set the compression quality to medium (adjust as needed)

        imageWriter.setOutput(ImageIO.createImageOutputStream(outputStream));
        imageWriter.write(null, new IIOImage(resizedImage, null, null), imageWriteParam);
        imageWriter.dispose();

        return outputStream.toByteArray();
    }
}
