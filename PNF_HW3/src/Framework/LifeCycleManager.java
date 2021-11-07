/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;
import Components.myFilter.MyFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            //.txt파일을 읽어서 다음으로 던저주는 필터
            CommonFilter sourceFilter = new SourceFilter("Students.txt");
            // 두 필터의 사이에서 특정 로직으로 데이터를 걸러주는 필터
            CommonFilter middleFilter = new MiddleFilter();
            //직접 구현한 필터
            CommonFilter myFilter = new MyFilter();
            // 결과 .txt파일을 만들어주는 필터
            CommonFilter sinkFilter = new SinkFilter("Output.txt");

            sourceFilter.connectOutputTo(middleFilter);
            middleFilter.connectOutputTo(myFilter);
            myFilter.connectOutputTo(sinkFilter);

            Thread thread1 = new Thread(sourceFilter);
            Thread thread2 = new Thread(middleFilter);
            Thread thread3 = new Thread(myFilter);
            Thread thread4 = new Thread(sinkFilter);

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
