package com.rest.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaticController {

   @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @RequestMapping(value ="/boards", method = RequestMethod.GET)
    public String boards() { return "forward:/board/board_list.html";
    }

    @RequestMapping(value = "/boards/{id}", method = RequestMethod.GET)
    public String boardDetail() { return "forward:/board/board_detail.html";
    }

    @RequestMapping(value = "/boards/writeBoard", method = RequestMethod.GET)
    public String writeBoard() { return "forward:/board/board_write.html";
    }

    @RequestMapping(value = "/boards/updateBoard/{id}", method = RequestMethod.GET)
    public String updateBoard() { return "forward:/board/board_update.html";

    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String login() { return "forward:/user/login.html";
    }

    @RequestMapping(value = "/user/signup", method = RequestMethod.GET)
    public String signup() { return "forward:/user/signup.html";
    }
}
