package org.uims.common.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uims.common.constant.CommonCodeEnum;
import org.uims.common.result.Result;

/**
 * 用于处理非前后端分离项目使用时统一处理404异常返回统一json
 * <p>如果项目为前后端分离项目请试用application.yml中的特定配置，
 * <em>spring.mvc.throw-exception-if-no-handler-found=true
 * spring.resources.add-mappings=false</em>并去当前Controller的注入</p>
 *
 * <p>注入方式可以选择在服务的启动类@SpringBootApplication注解中加入扫描包，
 * 例如<em>@SpringBootApplication(scanBasePackages = {"org.uims.datamaintain","org.uims.common.config"})</em></p>
 * @author kyrie
 * @since 1.0
 */
@Controller
public class NotFoundController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public Result notFound(){
        Result result = new Result(CommonCodeEnum.FAILED_CODE.getCode(),"url not found 404");

        return result;
    }
}
