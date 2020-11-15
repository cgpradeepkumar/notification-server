package com.infosat.notification.server.mybatis.mapper;

import com.infosat.notification.server.mybatis.model.ReminderMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pradeepcg
 * @created 15/11/2020 - 07:20
 */

@Mapper
public interface ReminderMessageMapper {

    @Insert("Insert into ReminderMessages(Receipient, Message) Values(#{recipient}, #{message})")
    public void insert(ReminderMessage reminderMessage);
}
