# CKIPClient-AutoPOS
This project is an example for using java to getting POS from CKIP (中研院斷詞系統) at client.

[CKIP](http://ckipsvr.iis.sinica.edu.tw/) is a natural language parser for traditional chinese.

In order to easily use the the client program to get the structure of sentences, instead of using the online resourse provided by [CKIP online resourse](http://ckipsvr.iis.sinica.edu.tw/resource.htm), this project provide another way to connect to the CKIP server in java code. 


Before starting, you can check some documents on CKIP to understand some [meaning of tages](http://ckipsvr.iis.sinica.edu.tw/cat.htm) or concepts about [how CKIP work](http://sunlight.iis.sinica.edu.tw/uwextract/demo.htm).

## Sign in

>[To get the account](http://ckipsvr.iis.sinica.edu.tw/reg.php)

And, there are some notice you have to know.

>[Description of service](http://ckipsvr.iis.sinica.edu.tw/webservice.htm)

## Get the necessary jar

> To [http://www.java2s.com](http://www.java2s.com/Code/Jar/c/Downloadckipclient043jar.htm) get `ckipclient-0.4.3-sources.jar` or you can directly clone the jar from [GitHub Page](https://github.com/ChiLunHuang/CKIPClient-AutoPOS/blob/master/CKIPproject/ckipclient-0.4.3.jar).

## How to use 

#### Clone the project and import into your eclipse

```
$ git clone https://github.com/ChiLunHuang/CKIPClient-AutoPOS.git
```

#### Open wordSegment.java and declare class and ArrayList

```java
 WordSegmentationService c; //宣告一個class變數c
 ArrayList<String> inputList = new ArrayList<String>(); //words
 ArrayList<String> TagList = new ArrayList<String>();   //POS
```

#### Input account and sentences


```java
c = new CKIP( "IP address" , port, "account", "password"); //輸入申請的IP、port、帳號、密碼

String s = "今天天氣真好耶!";
```

#### Sent the sentence to CKIP server and get result to lists

```java
c.setRawText(s);
c.send(); //傳送至中研院斷詞系統服務使用
         
for (Term t : c.getTerm()) {
           
	inputList.add(t.getTerm()); // t.getTerm()會讀到斷詞的String，將其存到inputList陣列
        TagList.add(t.getTag());    // t.getTag() 會讀到斷詞的詞性，將其存到TagList陣列
        }
         
```
#### Result of POS 

```
今天	N
天氣	N
真	ADV
好	Vi
耶	T
!	EXCLAMATIONCATEGORY
```


## Sample code

```java
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
         
        c = new CKIP( "IP address" , port, "account", "password"); //輸入申請的IP、port、帳號、密碼
         
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




```


