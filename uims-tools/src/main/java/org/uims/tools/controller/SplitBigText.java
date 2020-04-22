package org.uims.tools.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.uims.tools.util.TaskReadUtil;

@RestController
public class SplitBigText {

    @RequestMapping(value = "/startTask/{taskId}",method = RequestMethod.GET)
    public String startTask(@PathVariable("taskId") String taskId){

        System.out.println(taskId);
        TaskReadUtil taskReadUtil = new TaskReadUtil("F:/test/task_"+taskId+".json");

        System.out.println(taskReadUtil.getTaskStr());
        return "{\"result\":\"start\"}";
    }
}
