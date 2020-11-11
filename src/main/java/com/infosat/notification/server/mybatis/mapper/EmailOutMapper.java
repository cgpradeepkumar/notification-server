package com.infosat.notification.server.mybatis.mapper;

import com.infosat.notification.server.mybatis.model.EmailOut;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author pradeepcg
 * @created 08/11/2020 - 19:41
 */

@Mapper
public interface EmailOutMapper {

    @Select("select * from Email_Out")
    @Results({@Result(property = "id", column = "ID"),
            @Result(property = "emailBody", column = "EmailBody"),
            @Result(property = "emailSubject", column = "EmailSubject"),
            @Result(property = "fromId", column = "FromID"),
            @Result(property = "toId", column = "ToID"),
            @Result(property = "status", column = "Status"),
            @Result(property = "eventTime", column = "Eventtime"),
    })
    public List<EmailOut> findAll();

    @Update("update Email_Out set Status=#{status} where ID=#{id}")
    public void update(EmailOut emailOut);
}
