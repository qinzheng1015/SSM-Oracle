package cn.itcast.service;

import cn.itcast.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog) throws Exception;
    List<SysLog> findAll() throws Exception;
}
