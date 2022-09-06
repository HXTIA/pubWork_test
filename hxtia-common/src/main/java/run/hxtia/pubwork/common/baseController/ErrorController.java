package run.hxtia.pubwork.common.baseController;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api("处理Filter内部产生的异常")
public class ErrorController {

    public void handle(HttpServletRequest request) throws Exception {
        throw request.getAttribute()
    }

}
