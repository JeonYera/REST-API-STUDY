package com.rest.study.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaticController {

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @RequestMapping(value ="/freeboards", method = RequestMethod.GET)
    public String freeBoards() { return "forward:/board/freeBoard_list.html";
    }

    @RequestMapping(value = "/freeboards/{id}", method = RequestMethod.GET)
    public String freeBoardDetail() {
        return "forward:/board/freeBoard_detail.html";
    }

    @RequestMapping(value = "/freeboards/writeBoard", method = RequestMethod.GET)
    public String writeBoard() { return "forward:/board/freeBoard_write.html";
    }

    @RequestMapping(value = "/freeboards/updateBoard/{id}", method = RequestMethod.GET)
    public String updateBoard() {
        return "forward:/board/freeBoard_update.html";

    }
}
