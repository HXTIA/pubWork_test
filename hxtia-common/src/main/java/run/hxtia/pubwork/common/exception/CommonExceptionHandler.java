package run.hxtia.pubwork.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import run.hxtia.pubwork.common.utils.JsonVos;
import run.hxtia.pubwork.pojo.vo.result.JsonVo;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    // 拦截所有异常。
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public JsonVo handle(Throwable t) {
        // 先将日志打印了
        log.error("handle：", t);

        // 判断是什么异常。有对应处理方案的
        if (t instanceof CommonException) {
            return handle((CommonException) t);
        } // 继续 else if 拓展异常类即可

        // 递归处理异常
        Throwable cause = t.getCause();
        if (cause != null) return handle(cause);

        // 最终也处理不了的异常【直接返回 400】
        return JsonVos.error();
    }

    // 处理自定义异常
    private JsonVo handle(CommonException ce) {
        return JsonVos.error(ce.getCode(), ce.getMessage());
    }


}
