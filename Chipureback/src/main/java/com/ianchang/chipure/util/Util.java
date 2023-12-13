package com.ianchang.chipure.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Util {
    public static boolean checkLogin(HttpServletRequest request, int user_id) {
        // 判断请求传递的用户id与session中的用户id是否一致
        HttpSession session = request.getSession();
        Integer sessionUserId = (Integer) session.getAttribute("user_id");
        if (user_id != sessionUserId) {
            return false;
        }
        return true;
    }
}
