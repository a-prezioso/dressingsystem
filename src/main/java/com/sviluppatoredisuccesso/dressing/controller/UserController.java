package com.sviluppatoredisuccesso.dressing.controller;

import com.sviluppatoredisuccesso.dressing.dto.GeneralDto;
import com.sviluppatoredisuccesso.dressing.entity.Role;
import com.sviluppatoredisuccesso.dressing.entity.UserEntity;
import com.sviluppatoredisuccesso.dressing.entity.excel.Age;
import com.sviluppatoredisuccesso.dressing.repository.AgeRepository;
import com.sviluppatoredisuccesso.dressing.repository.UserRepository;
import com.sviluppatoredisuccesso.dressing.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AgeRepository ageRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/nontest")
    public String nontest() {
        return "nontest";
    }


    @PostMapping("/upload")
    public GeneralDto uploadFile(@RequestParam("file") MultipartFile file) {


        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<Age> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
                ageRepository.saveAll(tutorials);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }

        return new GeneralDto("uploaded", true);
    }
}
