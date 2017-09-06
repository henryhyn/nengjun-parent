package com.nengjun.app.plant.web.controller;

import com.nengjun.app.todo.dao.entity.PoiTodo;
import com.nengjun.app.todo.dao.mapper.PoiTodoMapper;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Henry on 2017/8/19.
 */
@RequestMapping("/api")
@RestController
public class PoiTodoController {
    @Autowired
    private PoiTodoMapper poiTodoMapper;

    @GetMapping("/todos")
    public Result _index() {
        PageModel<PoiTodo> todoPageModel = new PageModel<>();
        todoPageModel.setPageAndPageSize(1, 10);
        List<PoiTodo> todoList = poiTodoMapper.selectByPage(todoPageModel);
        Validate.isEmpty("todoList", todoList);
        todoPageModel.setList(todoList);
        return ResultUtil.success(todoPageModel);
    }

    @GetMapping("/todos/{id}")
    public Result show(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiTodo todo = poiTodoMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, todo);
        return ResultUtil.success(todo);
    }

    @PostMapping("/todos")
    public Result create(@Valid @RequestBody PoiTodo poiTodo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiTodo todo = new PoiTodo();
        copyProperties(poiTodo, todo);
        todo.setStatus(0);
        return ResultUtil.success(poiTodoMapper.insert(todo));
    }

    @PutMapping("/todos/{id}")
    public Result update(@PathVariable("id") Integer id, @Valid @RequestBody PoiTodo poiTodo, BindingResult bindingResult) {
        Validate.idValid("id", id);
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiTodo todo = poiTodoMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, todo);
        copyProperties(poiTodo, todo);
        return ResultUtil.success(poiTodoMapper.updateByPrimaryKey(todo));
    }

    @DeleteMapping("/todos/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiTodoMapper.deleteByPrimaryKey(id));
    }

    private void copyProperties(PoiTodo poiTodo, PoiTodo todo) {
        todo.setName(poiTodo.getName());
        todo.setDescription(poiTodo.getDescription());
        todo.setStatus(poiTodo.getStatus());
        todo.setImportance(poiTodo.getImportance());
        todo.setEmergency(poiTodo.getEmergency());
        todo.setDifficulty(poiTodo.getDifficulty());
        todo.setBeginTime(poiTodo.getBeginTime());
        todo.setEndTime(poiTodo.getEndTime());
    }
}
