package spfy.tool;

import java.util.*;
import java.util.stream.Stream;

public class Similarity {


    public static boolean  is(String aa,String bb,double value) {
        if(aa==null||bb==null)
            return false;
        aa=filter(aa);
        bb=filter(bb);
        double cc=aa.length(), ee=bb.length();
        double ccc=getCommonStrLength(aa, bb)/((cc+ee)/2);
        if(ccc>value)
            return true;
        else
            return false;
    }

    public static double  getV(String aa,String bb) {
        if(aa==null||bb==null)
            return 0;
        System.out.println(bb);
        aa=filter(aa);
        bb=filter(bb);
        double cc=aa.length(), ee=bb.length();
        double ccc=getCommonStrLength(aa, bb)/((cc+ee)/2);
        return ccc;
    }

    public static  Map<String,String> maxSimilarity( List<Map> sizesList,String  databaseInputSize){
        try{
            if(databaseInputSize==null){
                databaseInputSize="";
            }
            String list[]=null;
            databaseInputSize=databaseInputSize.replace("，",",");
            if(databaseInputSize.contains(",")){
                list=databaseInputSize.split(",");
            }else{
                list =new String[1];
                list[0]=databaseInputSize;
            }
            Map<String,String> map=new HashMap <>();
            double max=-1;
            for(Map sizesData:sizesList) {
                for(String bb:list){
                    String sizeName = sizesData.get("name").toString();
                    bb=filter(bb);
                    sizeName=filter(sizeName);
                    double cc=sizeName.length(), ee=bb.length();
                    //System.out.println(getCommonStrLength(sizeName, bb)+">"+sizeName+">"+bb);
                    double ccc=getCommonStrLength(sizeName, bb)/(((cc*1.01+ee*1.01)/2));
                    // System.out.println(ccc);
                    if(ccc>max)
                    {
                        max=ccc;
                        map=sizesData;
                    }
                }
            }
            if(map.size()==0)
                return null;
            return  map;
        }catch (Throwable e){
            return null;
        }
    }

    public static List<Map> test(List<String> aa){
        List<Map> sizesList=new ArrayList<Map>();
        for(String a:aa){
            Map<String,String> mp1=new HashMap<>();
            mp1.put("name",a);
            sizesList.add(mp1);
        }
        return sizesList;
    }

    public static int index(List<String> list,String name){
        name=maxSimilarity(test(list),name).get("name");
        int index =0;
        for(;index<list.size();index++)
        {
            if(list.get(index).equals(name)){
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<String> test=new LinkedList<>();
        test.add("us 7 / uk 7.5");
        test.add("us 8 / uk 9");
        test.add("us 9.5 / uk 1000000000000000");
        test.add("us 9.5 / uk 10");
        test.add("AIR MAX 270 ISPA - WHITE/GHOSTAQUA/AMBERRISE/LTCRIMSON");
//        test.add("7.8");
//        test.add("9");

        System.out.println(maxSimilarity(test(test),"I"));



//        System.out.println(Similarity.is(" ScriptSideline﻿Sleeve ", "Sle﻿eve S﻿cr﻿ip﻿t S﻿i﻿d﻿e﻿lin﻿e﻿ ﻿Ja﻿cket"));;
//        System.out.println(Similarity.is(" ScriptSideline﻿", "Sle﻿eve S﻿cr﻿ip﻿t S﻿i﻿d﻿e﻿lin﻿e﻿ ﻿Ja﻿cket"));;
//        System.out.println(Similarity.is(" Sleeve\uFEFF \uFEFFScrip\uFEFFt\uFEFF Sideli\uFEFFne\uFEFF Jacke\uFEFFt", "Sideline Jacket"));;
//        System.out.println(Similarity.is(" Sle﻿eve S﻿cr﻿ip﻿t S﻿i﻿d﻿e﻿lin﻿e", "Sle﻿eve S﻿cr﻿ip﻿t S﻿i﻿d﻿e﻿lin﻿e﻿ ﻿Ja﻿cket"));;
//        System.out.println(Similarity.is(" Zi\uFEFFp Up\uFEFF W\uFEFFork\uFEFF S\uFEFFhirt\uFEFF", "Work Shirt"));;
//        System.out.println(Similarity.is(" Shirt", "Work Shirt"));;
//        System.out.println(Similarity.is(" Supre\uFEFFm\uFEFFe®/Wilso\uFEFFn® \uFEFFT\uFEFFennis Bal\uFEFFl\uFEFFs ", "Supreme®/Wilson® Tennis Balls"));;
//        System.out.println(Similarity.is("Supreme®/Hanes® Tagless Tees (3 Pack)", "Sup\uFEFFreme®/Han\uFEFFes®\uFEFF T\uFEFFa\uFEFFgle\uFEFFss\uFEFF Tees (3 Pack\uFEFF)"));;
    }

    public static  String filter(String  in){
        if(in==null)
            return in;
        in= RegexParse.baseParseList(in,"[\\w]",0).toString();
        in=in.toUpperCase();
        in=in.replace(",","").replaceAll("\\s","").replaceAll("\\]","").replaceAll("\\[","");
        return in;
    }

    public static int getCommonStrLength(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        int len1 = str1.length();
        int len2 = str2.length();
        String min = null;
        String max = null;
        String target = null;
        min = len1 <= len2 ? str1 : str2;
        max = len1 > len2 ? str1 : str2;
        //最外层：min子串的长度，从最大长度开始
        for (int i = min.length(); i >= 1; i--) {
            //遍历长度为i的min子串，从0开始
            for (int j = 0; j <= min.length() - i; j++) {
                target = min.substring(j, j + i);
                //遍历长度为i的max子串，判断是否与target子串相同，从0开始
                for (int k = 0; k <= max.length() - i; k++) {
                    if (max.substring(k,k + i).equals(target)) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }
}
