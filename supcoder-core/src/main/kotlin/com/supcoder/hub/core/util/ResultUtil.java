package com.supcoder.hub.core.util;


import com.supcoder.hub.core.exception.ErrorCodeEnum;

/**
 * @author lee
 */
public class ResultUtil {

    /**
     * 构建一个带返回数据的结果类
     *
     * @param data
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public static <T>JsonResult success(T data) {
        return success(ErrorCodeEnum.SUCCESS.getCode(), data, "success");
    }

    public static JsonResult<?> error() {
        return success(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), null, "failed");
    }

    public static JsonResult<?> error(Object data) {
        return success(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), data, "failed");
    }

    public static JsonResult<?> error(String msg) {
        return success(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), null, msg);
    }

    public static JsonResult<?> success(int resultCode, String message) {
        return success(resultCode, null, message);
    }

    public static JsonResult<?> success(int resultCode, Object data, String message) {
        JsonResult<Object> result = new JsonResult<Object>();
        result.setCode(resultCode);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    /**
     * 构建一个不带返回数据的结果类
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static JsonResult success() {
        return success(0, "success");
    }

    /**
     * 构建一个自定义错误的返回结果类
     *
     * @param resultCode
     * @param message
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static JsonResult error(Integer resultCode, String message) {
        JsonResult result = new JsonResult();
        result.setCode(resultCode);
        result.setMsg(message);
        return result;
    }

    public static JsonResult errorWithData(Integer resultCode, String message, String data) {
        JsonResult result = new JsonResult();
        result.setCode(resultCode);
        result.setMsg(message);
        result.setData(data);
        return result;
    }



    public static Object badArgument() {
        return error(401, "参数不对");
    }

    public static Object badArgumentValue() {
        return error(402, "参数值不对");
    }

    public static Object unLogin() {
        return error(501, "请登录");
    }

    public static Object serious() {
        return error(502, "系统内部错误");
    }

    public static Object unSupport() {
        return error(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return error(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return error(505, "更新数据失败");
    }

    public static Object unAuth() {
        return error(506, "无操作权限");
    }

}