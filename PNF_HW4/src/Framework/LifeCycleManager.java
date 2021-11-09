/*
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;
import Components.myFilter.MyFilter;
import etc.Props;

import java.util.ArrayList;
import java.util.List;

public class LifeCycleManager {

    public static void main(String[] args) {
        String[] readSources = {Props.STUDENT_TXT,Props.COURSE_TXT };
        String[] outPipes = {Props.PIPE1, Props.PIPE2};
        try {
            /*
             * sourceFilter의 STUDENT_TXT은 PIPE1로 나가게하고,
             * sourceFilter의 COURSE_TXT PIPE2로 나가게 한다.
             *
             * middleFilter는 PIPE2로 들어온 강좌 정보를 거르고,
             * middleFilter의 PIPE1로 들어온 애들은 그대로 PIPE1로 흘려보낸다.
             *
             * myFilter는 PIPE1로 데이터를 받고,
             * PreCourse로 걸러서
             * 수강 잘된 학생은 PIPE3을 통해 sinkFilter1로
             * 잘 안된 학생은 PIPE4를 통해 sinkFilter2로 보낸다.
             *
             * sinkFilter1은 PIPE3으로 받아서 OUTPUT1_TXT에 file write
             * sinkFilter2는 PIPE4로 받아서 OUTPUT2_TXT에 file write
             * */
            //.txt파일을 읽어서 다음으로 던저주는 필터
            CommonFilter sourceFilter = new SourceFilter(readSources,outPipes);
            // 두 필터의 사이에서 특정 로직으로 데이터를 걸러주는 필터
            CommonFilter middleFilter = new MiddleFilter();
            // 수강 잘되면 sink1로, 잘 안되어 있으면 sink2로.
            CommonFilter myFilter = new MyFilter();
//            // 결과 .txt파일에 출력하는 필터
            CommonFilter sinkFilter1 = new SinkFilter(Props.PIPE3, Props.OUTPUT1_TXT);
            CommonFilter sinkFilter2 = new SinkFilter(Props.PIPE4, Props.OUTPUT2_TXT);

            sourceFilter.connectOutputTo(middleFilter, Props.PIPE1);
            sourceFilter.connectOutputTo(middleFilter, Props.PIPE2);
            middleFilter.connectOutputTo(myFilter, Props.PIPE1);//
            myFilter.connectOutputTo(sinkFilter1, Props.PIPE3);
            myFilter.connectOutputTo(sinkFilter2, Props.PIPE4);

            Thread thread1 = new Thread(sourceFilter);
            Thread thread2 = new Thread(middleFilter);
            Thread thread3 = new Thread(myFilter);
            Thread thread4 = new Thread(sinkFilter1);
            Thread thread5 = new Thread(sinkFilter2);

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
