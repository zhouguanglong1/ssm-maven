package com.zhougl.distributedtools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author zhougl
 * 2019/3/17
 **/
public class ScriptUtil {
    public static String getScript(String path){
        StringBuilder stringBuilder = new StringBuilder();
        InputStream resourceAsStream = ScriptUtil.class.getClassLoader().getResourceAsStream(path);

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))){
            String str;
            while ((str = bufferedReader.readLine())!=null){
                stringBuilder.append(str).append(System.lineSeparator());
            }
        } catch (IOException e){
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args){
        System.out.println(ScriptUtil.getScript("limit.lua"));
    }
}
