package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.Run;

public interface RunMapper {
    public void saveTestRun(Run r);
    public int getTestRunNumByDate(String date);
    public int getTestPassNumByDate(String date);
    public int getTestFailNumByDate(String date);
    public int getCountRun();
    public int getPassRun();
    public int getFailRun();


}
