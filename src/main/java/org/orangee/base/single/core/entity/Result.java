package org.orangee.base.single.core.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * 结果实体类
 * （充血模型？）
 *
 * @author orangee
 * @since 2021-1-21 21:55:34
 */
@Getter
@Setter
@Slf4j
public class Result<T> {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 私有化默认的构造方法（禁止外部创建对象实例）
     */
    private Result() {
    }

    private Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private Result(ResultEnum resultEnum) {
        if (null == resultEnum) return;
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> succeed() {
        return new Result("");
    }

    public static <T> Result<T> succeed(T data) {
        return new Result(data);
    }

    public static <T> Result<T> succeed(String msg) {
        return new Result(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return new Result(resultEnum);
    }

    public static <T> Result<T> fail() {
        return new Result(ResultEnum.FAILURE);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result(code, msg);
    }

    public static <T> Result<T> err() {
        return new Result(ResultEnum.ERROR);
    }

    public static <T> Result<T> output(int code, String msg, T data) {
        return new Result(code, msg, data);
    }

    // ------ 分割线 ------
    // 分割线以上为 Controller 常用方法
    //分割线以下为 非Controller（输出流） 常用方法

    public static void succeed(ServletResponse response, Object data) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // try-with-resource Java8新特性
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSON.toJSONString(succeed(data == null ? "" : data)));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        }
    }

    public static void succeed(ServletResponse response, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // try-with-resource Java8新特性
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSON.toJSONString(succeed(msg)));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        }
    }

    public static void succeed(ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // try-with-resource Java8新特性
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSON.toJSONString(succeed()));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        }
    }

    public static void fail(ServletResponse response, ResultEnum resultEnum) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // try-with-resource Java8新特性
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSON.toJSONString(fail(resultEnum)));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        }
    }

    public static void fail(ServletResponse response, int code, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // try-with-resource Java8新特性
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSON.toJSONString(fail(code, msg)));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        }
    }

    public static void fail(ServletResponse response, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // try-with-resource Java8新特性
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSON.toJSONString(fail(ResultEnum.FAILURE.getCode(), msg)));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        }
    }

    public static void output(ServletResponse response, int code, String msg, Object data) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // try-with-resource Java8新特性
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JSON.toJSONString(output(code, msg, data)));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        }
    }
}
