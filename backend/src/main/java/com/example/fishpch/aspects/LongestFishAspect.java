package com.example.fishpch.aspects;

import com.example.fishpch.model.Entry;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LongestFishAspect {
    private static Entry longestFish;

    @AfterReturning(pointcut = "execution(* com.example.fishpch.service.GlobalServiceImpl.saveEntry(..)) && args(entry)",
            returning = "result", argNames = "entry,result")
    public void findLongestFish(Entry entry, Object result) {

        if (entry == null || entry.getLength() == 0) return;

        if (longestFish == null){
            longestFish = entry;
            return;
        }

        if(longestFish.getLength() < entry.getLength()){
            longestFish = entry;
        }
    }
    public static Entry getLongestFishId() {
        return longestFish;
    }
}
