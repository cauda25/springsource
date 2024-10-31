package com.example.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.dto.TodoDTO;
import com.example.todo.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/todo")
@Controller
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public void getList(Boolean completed, Model model) {
        log.info("todo 목록 페이지 요청");

        List<TodoDTO> list = todoService.geList(completed);
        model.addAttribute("list", list);

    }

    @GetMapping("/read")
    public void getRead(@RequestParam Long id, Model model) {
        log.info("todo 상세 요청{}", id);

        TodoDTO dto = todoService.getRow(id);
        model.addAttribute("dto", dto);
    }

    @GetMapping("/create")
    public void getCreate() {
        log.info("todo 입력 폼 요청");
    }

    @PostMapping("/create")
    public String postCreate(TodoDTO dto) {
        log.info("todo 입력 요청", dto);
        todoService.create(dto);

        return "redirect:/todo/list";
    }

    @PostMapping("/update")
    public String postUpdate(TodoDTO dto, RedirectAttributes rttr) {
        log.info("todo 수정 요청", dto);
        todoService.updateCompleted(dto);
        rttr.addAttribute("id", dto.getId());

        return "redirect:/todo/read";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam Long id) {
        log.info("todo 삭제 요청 {}", id);
        todoService.deldeteRow(id);

        return "redirect:/todo/list";
    }

}
