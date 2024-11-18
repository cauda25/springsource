package com.example.projact1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.projact1.dto.SempleDTO2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

//컨드롤러에 어노테이션 추가
// 1) @Contoller : view(html, jsp) 를 찾으러 감
// 2) @RestController : 리턴하는 값 자체가 브라우저에 보여짐
// -객체 리턴 가능 : 객체는 브라우저에 띄울 수 없음
// ==> converter 가 필요함(spring boot 가 자동으로 라이브러리를 가지고 있음)
// 객체 <===> json

@RestController
public class TestController {
    @GetMapping("/Hello")
    public String getMethodName() {
        return "Hello World";
    }

    @GetMapping("/sample1")
    public SempleDTO2 getSampl1() {
        return SempleDTO2.builder()
                .mmo(11L)
                .firstName("hong")
                .lastName("dong")
                .build();
    }

    @GetMapping("/sample2")
    public List<SempleDTO2> getSampl2() {
        List<SempleDTO2> lsit = new ArrayList<>();
        LongStream.rangeClosed(1, 10).forEach(i -> {
            SempleDTO2 sempleDTO2 = SempleDTO2.builder()
                    .mmo(i)
                    .firstName("hong")
                    .lastName("dong" + i)
                    .build();
            lsit.add(sempleDTO2);
        });
        return lsit;
    }

    // http://localhost:8080/check?height=180&weight=80
    @GetMapping("/check")
    public ResponseEntity<SempleDTO2> check(double height, double weight) {
        SempleDTO2 sempleDTO2 = SempleDTO2.builder()
                .mmo(11L)
                .firstName(String.valueOf(height))
                .lastName(String.valueOf(weight))
                .build();

        if (height < 160) {
            return new ResponseEntity<SempleDTO2>(sempleDTO2, HttpStatusCode.valueOf(500));
        } else {

            // 200
            return new ResponseEntity<SempleDTO2>(sempleDTO2, HttpStatus.OK);
        }
    }

    // @PathVariable
    // http://localhost:8080/products/bag/1234
    // http://localhost:8080/products/shitr/4652
    @GetMapping("/products/{cat}/{pid}")
    public String[] getMethodName(@PathVariable String cat, @PathVariable String pid) {
        return new String[] {
                "category : " + cat,
                "productId : " + pid
        };
    }

}
