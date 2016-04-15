package Segment;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tw.cheyingwu.ckip.CKIP;
import tw.cheyingwu.ckip.Term;
import tw.cheyingwu.ckip.WordSegmentationService;

public class wordSegment {
	 
    public static void main(String[] args) {
        WordSegmentationService c; //宣告一個class變數c
        ArrayList<String> inputList = new ArrayList<String>(); //宣告動態陣列 存切詞的name
        ArrayList<String> TagList = new ArrayList<String>();   //宣告動態陣列 存切詞的詞性.
        //放想要斷詞的字串
        String s = "今天天氣真好耶!";
         
        System.out.println("********** 使用中研院斷詞伺服器 *********");
         
        c = new CKIP( "IP" , 1501, "account", "password"); //輸入申請的IP、port、帳號、密碼
         
        c.setRawText(s);
        c.send(); //傳送至中研院斷詞系統服務使用
         
        for (Term t : c.getTerm()) {
           
            inputList.add(t.getTerm()); // t.getTerm()會讀到斷詞的String，將其存到inputList陣列
            TagList.add(t.getTag());    // t.getTag() 會讀到斷詞的詞性，將其存到TagList陣列
        }
         
        //將資料output成檔案
        try {
            FileWriter fr1 = new FileWriter("output.txt");
            BufferedWriter bw = new BufferedWriter(fr1);
            for(int i=0;i<inputList.size();i++)
            {
                bw.write(inputList.get(i));
                bw.write("\t"+TagList.get(i));
                bw.newLine();
            }
            bw.close();
            System.out.println("********** 斷詞結束 *********");
             
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
 
}



