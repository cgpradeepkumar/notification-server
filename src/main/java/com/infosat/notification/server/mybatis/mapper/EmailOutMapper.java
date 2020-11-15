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
            @Result(property = "createdBy", column = "CreatedBy"),
            @Result(property = "attachments", column = "Attachments")
    })
    public List<EmailOut> findAll();

    @Select("select * from Email_Out Where Status='P'")
    @Results({@Result(property = "id", column = "ID"),
            @Result(property = "emailBody", column = "EmailBody"),
            @Result(property = "emailSubject", column = "EmailSubject"),
            @Result(property = "fromId", column = "FromID"),
            @Result(property = "toId", column = "ToID"),
            @Result(property = "status", column = "Status"),
            @Result(property = "eventTime", column = "Eventtime"),
            @Result(property = "createdBy", column = "CreatedBy"),
            @Result(property = "attachments", column = "Attachments")
    })
    public List<EmailOut> findAllPending();

    @Select("Select * From Email_Out Where ToID = #{toId} And cast(Eventtime as date) = cast(#{eventTime} as date)")
    @Results({@Result(property = "id", column = "ID"),
            @Result(property = "emailBody", column = "EmailBody"),
            @Result(property = "emailSubject", column = "EmailSubject"),
            @Result(property = "fromId", column = "FromID"),
            @Result(property = "toId", column = "ToID"),
            @Result(property = "status", column = "Status"),
            @Result(property = "eventTime", column = "Eventtime"),
            @Result(property = "createdBy", column = "CreatedBy"),
            @Result(property = "attachments", column = "Attachments")
    })
    public List<EmailOut> findExisting(EmailOut emailOut);

    @Insert("Insert into Email_Out(EmailBody, EmailSubject, FromID, ToID, Status, Eventtime, CreatedBy, Attachments)" +
            " Values(#{emailBody}, #{emailSubject}, #{fromId}, #{toId}, #{status}, #{eventTime}, #{createdBy}, #{attachments})")
    public void insert(EmailOut emailOut);

    @Update("update Email_Out set EmailBody=#{emailBody}, Status=#{status} where ID=#{id}")
    public void update(EmailOut emailOut);
}
