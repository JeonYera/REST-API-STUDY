package com.rest.study.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaticController {
    @RequestMapping(value="/foodboards", method= RequestMethod.GET)
    public String getFoodBoards() { return "forward:/foodboard/foodBoard_list.html"; }

    @RequestMapping(value="/foodboards/{id}", method= RequestMethod.GET)
    public String getFoodBoard() {return "forward:/foodboard/foodBoard_detail.html";}

    @RequestMapping(value="/foodboards/writeBoard", method= RequestMethod.GET)
    public String writeFoodBoard() {return "forward:/foodboard/foodBoard_write.html";}

    @RequestMapping(value="foodboards/edit/{id}", method= RequestMethod.GET)
    public String editFoodBoard() {return "forward:/foodboard/foodBoard_edit.html";}

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @RequestMapping(value ="/freeboards", method = RequestMethod.GET)
    public String freeBoards() { return "forward:/freeboard/freeBoard_list.html";
    }

    @RequestMapping(value = "/freeboards/{id}", method = RequestMethod.GET)
    public String freeBoardDetail() {
        return "forward:/freeboard/freeBoard_detail.html";
    }

    @RequestMapping(value = "/freeboards/writeBoard", method = RequestMethod.GET)
    public String writeBoard() { return "forward:/freeboard/freeBoard_write.html";
    }

    @RequestMapping(value = "/freeboards/updateBoard/{id}", method = RequestMethod.GET)
    public String updateBoard() {
        return "forward:/freeboard/freeBoard_update.html";

    }

    @RequestMapping(value = "/travelboards", method = RequestMethod.GET)
    public String travelBoardList() {
        return "forward:/travelboard/travelBoard_list.html";
    }

    @RequestMapping(value = "/travelboards/{id}", method = RequestMethod.GET)
    public String travelBoardDetail() {
        return "forward:/travelboard/travelBoard_detail.html";
    }

    @RequestMapping(value = "/travelboards/create", method = RequestMethod.GET)
    public String travelBoardCreate() {
        return "forward:/travelboard/travelBoard_create.html";
    }

    @RequestMapping(value = "/travelboards/update/{id}", method = RequestMethod.GET)
    public String travelBoardUpdate() {
        return "forward:/travelboard/travelBoard_update.html";
    }
}
