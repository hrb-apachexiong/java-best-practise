/*
 * $Id$
 *
 * Copyright 1999-2011 Tinnote.com . 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * environment : macbook air 711/A i5 1.5GHZ
 *
 * StringTokenizer 的性能在tokens数量少的情况下由于String.split, 当tokens的数量超过900时，性能率低于split
 *
 */
package com.tinnote.string;

import org.junit.Test;

import java.util.StringTokenizer;

import static com.tinnote.utils.TestUtils.*;

//--------------------- Change Logs----------------------
// <p>@author xiong.he Initial Created at 2015-05-31</p>
//-------------------------------------------------------
public class TestString {

    private static String testSplitString = "";

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 900; i++) {
            sb.append("h" + i);
            sb.append(";");
        }
        testSplitString = sb.toString();
    }

    @Test
    public void testString(){
        testStringSplit();
        testStringTokenizer();
    }

    /**
     * 10 tokens mills: 190 seconds:0 minutes:0
     * 100 tokens mills: 236 seconds:0 minutes:0
     * 1000 tokens  mills: 766 seconds:0 minutes:0
     * 10000 tokens  mills: 6268 seconds:6 minutes:0
     * 10000 tokens  mills: 85821 seconds:85 minutes:1
     * 100000 tokens   mills: 107460 seconds:107 minutes:1
     *
     *
     *
     *
     */

    @Test
    public void testStringSplit() {

        long before = now();
        for (int i = 0; i < 10000; i++) {
            testSplitString.split(";");
        }
        println(interval(before));

    }

    /**
     * 10 tokens mills: 47 seconds:0 minutes:0
     * 100 tokens mills: 95 seconds:0 minutes:0
     * 1000 tokens  mills: 689 seconds:0 minutes:0
     * 10000 tokens  mills: 8888 seconds:8 minutes:0
     *
     *
     *
     */

    @Test
    public void testStringTokenizer(){

        long before = now();
        StringTokenizer stringTokenizer = new StringTokenizer(testSplitString, ";");
        for(int i=0; i< 10000; i++){
            while(stringTokenizer.hasMoreTokens()){
                stringTokenizer.nextToken();
            }
            stringTokenizer = new StringTokenizer(testSplitString, ";");
        }
        println(interval(before));

    }

}
